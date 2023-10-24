package view;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.util.Timer;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.MinesweeperMouseController;
import model.MinesweeperModel;

public class MinesweeperView extends JFrame {

	private JPanel contentPane;
	private MinesweeperModel game;
	private int size;
	private MinesweeperCase[][] buttons;
	
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
						MinesweeperView frame = new MinesweeperView(size2);
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

	
	public MinesweeperView(int size) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, size*35, size*35);
		contentPane = new JPanel();
		this.size=size;
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setTitle("Demineur");
		setContentPane(contentPane);
		this.game=new MinesweeperModel(size);
		this.init();
		MinesweeperMouseController controler = new MinesweeperMouseController(this.game, this.buttons);
		for (int i = 0; i < size; i++) {
	        for (int j = 0; j < size; j++) {
	            buttons[i][j].addMouseListener(controler);
	        }
	    }
    }
	public void init() {
   	    GridLayout gridLayout = new GridLayout(size, size);
	    contentPane.setLayout(gridLayout);
	    this.buttons = new MinesweeperCase[size][size];
	    for (int i = 0; i < size; i++) {
	        for (int j = 0; j < size; j++) {
	            buttons[i][j] = new MinesweeperCase(this.game.getCase(i, j), i,j);
	            contentPane.add(buttons[i][j]);
	        }
	    }
	}
}
