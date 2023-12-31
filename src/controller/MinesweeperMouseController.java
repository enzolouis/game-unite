package controller;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.Timer;

import model.MinesweeperModel;
import view.MinesweeperCase;

import javax.swing.ImageIcon;

public class MinesweeperMouseController extends MouseAdapter {
	private MinesweeperModel demineur;
	private MinesweeperCase[][] buttons;
	private static final ImageIcon FLAG = new ImageIcon("./src/flag.png");
	private static final ImageIcon BOMBE = new ImageIcon("./src/bombe.png");
	private Timer showBomb;
	private Timer deployArea;

	public MinesweeperMouseController(MinesweeperModel demineur, MinesweeperCase[][] buttons) {
		this.demineur = demineur;
		this.buttons = buttons;
	}
	
    @Override
    public void mousePressed(MouseEvent e) {
    	MinesweeperCase btn = (MinesweeperCase) e.getSource();
    	int x = btn.getPositionGrid().x;
        int y = btn.getPositionGrid().y;
        if (e.getButton() == MouseEvent.BUTTON1) {
        	if (!this.buttons[x][y].isFlaged() && this.buttons[x][y].isEnabled()) {
	            if (this.demineur.getCase(x, y) == 0) {
	            	List<Point> points = new ArrayList<Point>();
	        		demineur.recursiveRevealEmptyCells(new Point(x, y), points);
	        		demineur.iterativeShowAdjacentNumberToEmptyCells(points);
	        		ActionListener action = new ActionListener() {
	        			Iterator<Point> caseToDeployList = points.listIterator();
	        		    @Override
	        		    public void actionPerformed(ActionEvent e) {
        		    		if (caseToDeployList.hasNext()) {
        		    			Point nxt = caseToDeployList.next();
	        		    		buttons[nxt.x][nxt.y].showArea(true);
	        		    		buttons[nxt.x][nxt.y].setEnabled(false);
	        		    	} else {
	        		    		deployArea.stop();
	        		    	}
        		    		if (caseToDeployList.hasNext()) {
        		    			Point nxt = caseToDeployList.next();
	        		    		buttons[nxt.x][nxt.y].showArea(true);
	        		    		buttons[nxt.x][nxt.y].setEnabled(false);
	        		    	} else {
	        		    		deployArea.stop();
	        		    	}
	        		    }
	        		};
	        		
	        		deployArea = new Timer(1, action);
	        		deployArea.start();
	        		deployArea.setRepeats(true);
	        		
	            } else if(this.demineur.getCase(x, y)!=9) {
	            	this.buttons[x][y].setEnabled(false);
	            	this.buttons[x][y].showArea(false);	
	            } else {
	            	this.buttons[x][y].setIcon(BOMBE);
	            	for (int i = 0;i<this.demineur.getSize();i++) {
	            		for (int j = 0; j<demineur.getSize();j++) {
	            			if (demineur.getCase(i, j) != 9)
	            				buttons[i][j].setEnabled(false);
	            		}
	            	}
	            	ActionListener action = new ActionListener() {
	        			Iterator<Point> bombeList = demineur.getBombeList().listIterator();
	        		    @Override
	        		    public void actionPerformed(ActionEvent e) {
        		    		if (bombeList.hasNext()) {
        		    			Point nxt = bombeList.next();
	        		    		buttons[nxt.x][nxt.y].setIcon(BOMBE);
	        		    	} else {
	        		    		showBomb.stop();
	        		    	}	        		    	
	        		    }
	        		};
	        		
	        		showBomb = new Timer(10, action);
	        		showBomb.start();
	        		showBomb.setRepeats(true);
                }
        	}
        } else if (e.getButton() == MouseEvent.BUTTON3) {
            if (!btn.isDiscover() && btn.isEnabled())
               	btn.setIcon(btn.isFlaged() ? null : FLAG);
        }
    }
    
    @Override
	public void mouseEntered(MouseEvent e) {
		MinesweeperCase btn = (MinesweeperCase) e.getComponent();
		if (!btn.isDiscover())
			// 90,165,216
			btn.setBackground(new Color(
				btn.getBackground().getRed() + (MinesweeperCase.DISCOVER_COLOR.getRed() - btn.getBackground().getRed()) / 4,
				btn.getBackground().getGreen() + (MinesweeperCase.DISCOVER_COLOR.getGreen() - btn.getBackground().getGreen()) / 4,
				btn.getBackground().getBlue() + (MinesweeperCase.DISCOVER_COLOR.getBlue() - btn.getBackground().getBlue()) / 4
			));
	}
    
    @Override
	public void mouseExited(MouseEvent e) {
		MinesweeperCase btn = (MinesweeperCase) e.getComponent();
		if (!btn.isDiscover())
			btn.setBackground(btn.getInitialBackground());
	}

}
