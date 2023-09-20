import java.awt.Color;
import java.awt.Point;

import javax.swing.BorderFactory;
import javax.swing.JButton;

public class Case extends JButton {
	private Point positionGrid;
	private boolean discover;
	public static final Color DEFAULT_COLOR = new Color(255, 204, 204);
	public static final Color AREA_COLOR = new Color(255, 0, 0);
	
	public Case(String s, int x, int y) {
		super(s);
		this.discover = false;
		this.positionGrid=new Point(x,y);
		this.setBackground(DEFAULT_COLOR);
		this.setForeground(new Color(255, 150, 204));
	}
	public Point getPositionGrid() {
		return this.positionGrid;
	}
	public Boolean isFlaged() {
		return this.getIcon() != null;
	}
	
	public void showArea() {
		this.discover = true;
		this.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		this.setBackground(AREA_COLOR);
	}
	
	public boolean isDiscover() {
		return this.discover;
	}
}
