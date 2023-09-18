import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class DemineurGUI extends JFrame {
	private static final Image FLAG = new ImageIcon(("./src/flag.png"))
			.getImage();
	private JPanel contentPane;
	private Demineur game;
	private int size;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				try {
					JFrame tempFrame = new JFrame();

					String result = "";
					int size2 = -1;
					do {
						try {
							result = JOptionPane.showInputDialog(tempFrame, "Taille de la grille :", 15);
							size2 = Integer.parseInt(result);
						} catch (Exception e) {

						}

					} while ((size2 < 10) && result != null);
					tempFrame.dispose();
					if (result != null) {
						tempFrame.dispose();
						DemineurGUI frame = new DemineurGUI(size2);
						frame.setVisible(true);
					} else {
						System.exit(0);
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public DemineurGUI(int size) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, size*20, size*20);
		contentPane = new JPanel();
		this.size=size;
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setTitle("Demineur");
		setContentPane(contentPane);
		this.game=new Demineur(size);
		this.init();
		
	}
	
	public void init() {
   	    GridLayout gridLayout = new GridLayout(size, size);
	    contentPane.setLayout(gridLayout);
	    JButton[][] buttons = new JButton[size][size];
	    for (int i = 0; i < size; i++) {
	        for (int j = 0; j < size; j++) {
	            buttons[i][j] = new JButton();
	            contentPane.add(buttons[i][j]);
	        }
	    }
	}

}
