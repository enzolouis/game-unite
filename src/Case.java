import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.Timer;

public class Case extends JButton {
	private Point positionGrid;
	private boolean discover;
	private int value;
	public static final Color DEFAULT_COLOR = new Color(52, 152, 219);
	public static final Color DISCOVER_COLOR = new Color(204, 204,204);
	private Timer showAreaT;
	private ActionListener action;
	
	public Case(int value, int x, int y) {
		super();
		this.discover = false;
		this.value = value;
		this.positionGrid = new Point(x,y);
		this.setBackground(DEFAULT_COLOR);
		this.setRolloverEnabled(false);
		this.setFocusPainted(false);
		this.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		
		this.action = new ActionListener() {
    		int alphaColor = 0;
		    @Override
		    public void actionPerformed(ActionEvent e) {
	    		if (alphaColor <= 255) {
	    			setBackground(new Color(DISCOVER_COLOR.getRed(), DISCOVER_COLOR.getGreen(),
	    					DISCOVER_COLOR.getBlue(), alphaColor));
		    	} else {
		    		showAreaT.stop();
		    		if (value != 0)
		    			setText(Integer.toString(value));
		    	}
	    		alphaColor += 50;
		    }
		};
	}
	public Point getPositionGrid() {
		return this.positionGrid;
	}
	public Boolean isFlaged() {
		return this.getIcon() != null;
	}
	public boolean isDiscover() {
		return this.discover;
	}
	
	public void showArea(boolean multipleShow) {
		this.discover = true;
		
		if (multipleShow) {
			this.setBackground(DISCOVER_COLOR);
			if (value != 0)
				setText(""+value);
		} else {
			showAreaT = new Timer(1, this.action);
			showAreaT.setRepeats(true);
			showAreaT.start();
		}
	}
}
