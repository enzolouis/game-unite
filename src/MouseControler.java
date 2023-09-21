import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;

public class MouseControler extends MouseAdapter {
	private Demineur demineur;
	private Case[][] buttons;
	private static final ImageIcon FLAG = new ImageIcon(("./src/flag.png"));
	private static final ImageIcon BOMBE = new ImageIcon(("./src/bombe.png"));

	public MouseControler(Demineur demineur, Case[][] buttons) {
		this.demineur=demineur;
		this.buttons=buttons;
	}
    @Override
    public void mouseClicked(MouseEvent e) {
    	Case btn = (Case) e.getSource();
    	int x = btn.getPositionGrid().x;
        int y = btn.getPositionGrid().y;
        if (e.getButton() == MouseEvent.BUTTON1) {
        	if (!this.buttons[x][y].isFlaged() && this.buttons[x][y].isEnabled()) {
	            if(this.demineur.getCase(x, y)==0) {
	            	List<Point> points = new ArrayList<Point>();
	        		demineur.recursiveDeployEmptyCase(new Point(x, y), points);
	        		demineur.showCaseWithNumberNearEmptyCase(points);
	        		for (Point p : points) {
	        			this.buttons[p.x][p.y].showArea();
	        			this.buttons[p.x][p.y].setEnabled(false);
	        		}
	            } else if(this.demineur.getCase(x, y)!=9) {
	            	this.buttons[x][y].setEnabled(false);
	            	this.buttons[x][y].showArea();	
	            } else {
	            	Timer bombeTimer = new Timer();
	                for (int i = 0; i < demineur.getSize(); i++) {
	                    for (int j = 0; j < demineur.getSize(); j++) {
	                        final int finalI = i;
	                        final int finalJ = j;
	                        if (demineur.getCase(finalI, finalJ) == 9) {
	                            bombeTimer.schedule(new TimerTask() {
	                                @Override
	                                public void run() {
	                                    buttons[finalI][finalJ].showArea();
	                                    buttons[finalI][finalJ].setIcon(BOMBE);
	                                    System.out.println("Game over !");
	                                }
	                            }, 1000);
	                            
	                        } else {
	                            buttons[finalI][finalJ].setEnabled(false);
	                        }
	                    }
	                }
                }
        	}
            
        } else if (e.getButton() == MouseEvent.BUTTON3) {
            if (!btn.isDiscover()&& btn.isEnabled()) {
            	if (btn.isFlaged()) {
                	btn.setIcon(null);
                } else {
                    btn.setIcon(FLAG);
                }
            }
        }
    }
}
