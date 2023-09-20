import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;

public class MouseControler extends MouseAdapter {
	private Demineur demineur;
	private static final ImageIcon FLAG = new ImageIcon(("./src/flag.png"));
	public MouseControler(Demineur demineur) {
		this.demineur=demineur;
	}
    @Override
    public void mouseClicked(MouseEvent e) {
    	Case btn = (Case) e.getSource();
    	int x = btn.getPositionGrid().x;
        int y = btn.getPositionGrid().y;
        if (e.getButton() == MouseEvent.BUTTON1) {
            System.out.println("Clic gauche");
            if(this.demineur.getCase(x, y)==0) {
            	
            }
            
        } else if (e.getButton() == MouseEvent.BUTTON3) {
            System.out.println("Clic droit");
            if(btn.isFlaged()) {
            	btn.setIcon(null);
            } else {
                btn.setIcon(FLAG);
            }

        }
    }
}
