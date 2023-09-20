import java.awt.Point;

import javax.swing.JButton;

public class Case extends JButton {
	private Point positionGrid;
	
	
	public Case(int x, int y) {
		super();
		this.positionGrid=new Point(x,y);
	}
	public Point getPositionGrid() {
		return this.positionGrid;
	}
	public Boolean isFlaged() {
		return this.getIcon()!=null;
	}
}
