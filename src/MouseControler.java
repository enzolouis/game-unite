import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

public class MouseControler extends MouseAdapter {
	private Demineur demineur;
	private Case[][] buttons;
	private static final ImageIcon FLAG = new ImageIcon(("./src/flag.png"));
	public MouseControler(Demineur demineur, Case[][] buttons) {
		this.demineur=demineur;
		this.buttons=buttons;
	}
    @Override
    public void mouseClicked(MouseEvent e) {
    	Case btn = (Case) e.getSource();
    	int x = btn.getPositionGrid().x;
        int y = btn.getPositionGrid().y;
        System.out.println(x + ";" + y + ";" + this.demineur.getCase(x, y));
        if (e.getButton() == MouseEvent.BUTTON1) {
            System.out.println("Clic gauche");
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
            }
            
        } else if (e.getButton() == MouseEvent.BUTTON3) {
            System.out.println("Clic droit");
            if (!btn.isDiscover()) {
            	if (btn.isFlaged()) {
                	btn.setIcon(null);
                } else {
                    btn.setIcon(FLAG);
                }
            }
        }
    }
}
