package view;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.Timer;
import javax.swing.border.MatteBorder;

public class BlockudokuCase extends JButton {
	private Point positionGrid;
	private boolean discover;
	public static final Color DEFAULT_COLOR = new Color(255, 255, 255);
	public static final Color DEFAULT_COLOR2 = new Color(240, 240, 240);
	public static final Color DISCOVER_COLOR = new Color(204, 204,204);
	
	public static final Color EMPTY_COLOR = new Color(52, 152, 219);
	public static final Color EMPTY_COLOR2 = new Color(52, 140, 210);
	public static final Color EMPTY_COLOR_HOVER = new Color(52, 152, 219);
	public static final Color FILL_COLOR = new Color(52, 140, 210);
	public static final Color FILL_COLOR_HOVER = new Color(204, 204,204);
	private Color initialBackground;
	
	public BlockudokuCase(int x, int y) {
		super();
		this.discover = false;
		this.positionGrid = new Point(x,y);
		this.setPreferredSize(new Dimension(45, 45));
		this.setMinimumSize(new Dimension(45, 45));
		
		if ((x+y) % 2 == 0)
			initialBackground = DEFAULT_COLOR;
		else
			initialBackground = DEFAULT_COLOR2;
		
		this.setBackground(initialBackground);
		this.setRolloverEnabled(false);
		this.setFocusPainted(false);
		
		int borderRight = 1, borderBottom = 1;
		
		if ((y+1)%3 == 0 && y != 8) {
			borderRight = 5;
		}
		if ((x+1)%3 == 0 && x != 8) {
			borderBottom = 5;
		}
		
		
		
		this.setBorder(new MatteBorder(1, 1, borderBottom, borderRight, Color.LIGHT_GRAY));
		
	}
	
	public Point getPositionGrid() {
		return this.positionGrid;
	}
	public boolean isDiscover() {
		return this.discover;
	}
	public Color getInitialBackground() {
		return this.initialBackground;
	}
	
}
