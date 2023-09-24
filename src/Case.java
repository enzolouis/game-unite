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
	public static final Color DEFAULT_COLOR2 = new Color(52, 140, 210);
	public static final Color DISCOVER_COLOR = new Color(204, 204,204);
	private Timer showAreaT;
	private ActionListener action;
	private Color initialBackground;
	
	public Case(int value, int x, int y) {
		super();
		this.discover = false;
		this.value = value;
		this.positionGrid = new Point(x,y);
		if ((x+y) % 2 == 0)
			initialBackground = DEFAULT_COLOR;
		else
			initialBackground = DEFAULT_COLOR2;
		
		this.setBackground(initialBackground);
		this.setRolloverEnabled(false);
		this.setFocusPainted(false);
		this.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		
		// transition de 10 tour sur 10 miliseconde
		this.action = new ActionListener() {
    		int counter = 0;
    		int valueToJoinRed = (DISCOVER_COLOR.getRed() - initialBackground.getRed()) / 10;
    		int valueToJoinGreen = (DISCOVER_COLOR.getGreen() - initialBackground.getGreen()) / 10;
    		int valueToJoinBlue = Math.abs(DISCOVER_COLOR.getBlue() - initialBackground.getBlue()) / 10;
		    int red = initialBackground.getRed();
		    int green = initialBackground.getGreen();
		    int blue = initialBackground.getBlue();
		    
    		@Override
		    public void actionPerformed(ActionEvent e) {
    			red += valueToJoinRed;
    			green += valueToJoinGreen;
    			// ici on sait que le bleu doit être réduit et red/green augmenter
    			// sinon si on change de couleur faudra faire des if
    			blue -= valueToJoinBlue;
	    		if (counter < 10) {
	    			setBackground(new Color(red, green, blue));
		    	} else {
		    		showAreaT.stop();
		    		if (value != 0)
		    			setText(Integer.toString(value));
		    	}
	    		counter++;
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
	public Color getInitialBackground() {
		return this.initialBackground;
	}
	
	public void showArea(boolean multipleShow) {
		this.discover = true;
		
		if (multipleShow) {
			this.setBackground(DISCOVER_COLOR);
			if (value != 0)
				setText(""+value);
		} else {
			showAreaT = new Timer(10, this.action);
			showAreaT.setRepeats(true);
			showAreaT.start();
		}
	}
}
