import java.awt.Color;
import java.awt.Point;

import javax.swing.BorderFactory;
import javax.swing.JButton;

public class Case extends JButton {
	private Point positionGrid;
	private boolean discover;
	private int value;
	public static final Color DEFAULT_COLOR = new Color(255, 204, 204);
	public static final Color AREA_COLOR = new Color(255, 0, 0);
	
	public Case(int value, int x, int y) {
		super();
		this.discover = false;
		this.value = value;
		this.positionGrid=new Point(x,y);
		this.setBackground(DEFAULT_COLOR);
		this.setForeground(AREA_COLOR);
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
		if (value != 0) {
			this.setText(Integer.toString(value));
		}
	}
	
	public boolean isDiscover() {
		return this.discover;
	}
}
