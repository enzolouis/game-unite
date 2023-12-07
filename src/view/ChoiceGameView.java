package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class ChoiceGameView extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ChoiceGameView dialog = new ChoiceGameView();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ChoiceGameView() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		JComboBox<String> choices = new JComboBox<>();
		choices.addItem("Snake");
		choices.addItem("Minesweeper");
		choices.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame tempFrame = new JFrame();

				String result = "";
				int size2 = -1;
				
				switch (choices.getSelectedItem().toString()) {
				case "Snake":
					do {
						try {
							result = JOptionPane.showInputDialog(tempFrame, "Taille de la grille :", 15);
							size2 = Integer.parseInt(result);
						} catch (Exception ex) {
							
						}

					} while ((size2 < 10) && result != null);
					tempFrame.dispose();
					if (result != null) {
						tempFrame.dispose();
						SnakeView frame = new SnakeView(size2);
						frame.setVisible(true);
					} else {
						System.exit(0);
					}
					break;
				
				case "Minesweeper":
					do {
						try {
							result = JOptionPane.showInputDialog(tempFrame, "Taille de la grille :", 15);
							size2 = Integer.parseInt(result);
						} catch (Exception ex) {

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
				}
			}
			
		});
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.add(choices);
	}

}
