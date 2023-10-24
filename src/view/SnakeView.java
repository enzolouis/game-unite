package view;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.JButton;
import java.awt.Point;
import java.awt.FlowLayout;
import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JProgressBar;
import java.awt.Font;
import javax.swing.JSpinner;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

import model.SnakeModel;

public class SnakeView extends JFrame {

	private JPanel contentPane;
	private JLabel scoreText;
	private JLabel highScoreText;
	private JProgressBar progressBar;
	private SnakeModel game;
	private Timer timer;
	private Timer countdown;
	private int size;
	private JButton restartButton;
	private JButton[][] caseList;
	private static final Image SNAKE_ICON = new ImageIcon(SnakeView.class.getClassLoader().getResource
			("snakelogo.png")).getImage();
	private static final ImageIcon SNAKE_HEAD_IMAGE = new ImageIcon(new ImageIcon(SnakeView.class.getClassLoader().getResource
			("starscore.png")).getImage().getScaledInstance(30, 30,  java.awt.Image.SCALE_SMOOTH));
	private static final ImageIcon APPLE_IMAGE = new ImageIcon(new ImageIcon(SnakeView.class.getClassLoader().getResource
			("apple.png")).getImage().getScaledInstance(30, 30,  java.awt.Image.SCALE_SMOOTH));
	private static final ImageIcon CHOKBAR = new ImageIcon(new ImageIcon(SnakeView.class.getClassLoader().getResource
			("chokbar.png")).getImage().getScaledInstance(30, 30,  java.awt.Image.SCALE_SMOOTH));
	private static final ImageIcon SCORE = new ImageIcon(new ImageIcon(SnakeView.class.getClassLoader().getResource
			("starscore.png")).getImage().getScaledInstance(30, 30,  java.awt.Image.SCALE_SMOOTH));
	private static final ImageIcon HIGHSCORE = new ImageIcon(new ImageIcon(SnakeView.class.getClassLoader().getResource
			("highscore.png")).getImage().getScaledInstance(30, 30,  java.awt.Image.SCALE_SMOOTH));
	private static final ImageIcon RESTART = new ImageIcon(new ImageIcon(SnakeView.class.getClassLoader().getResource
			("restart.png")).getImage().getScaledInstance(30, 30,  java.awt.Image.SCALE_SMOOTH));
	private Point previousNewPoint;
	// autoriser une seule touche pendant les 1 seconde de temporisation (bouge pas)
	// pour résoudre le bug de pouvoir passer de l'est à l'ouest (et autres) :
	// on est direction est, on press 'z' pour le nord PUIS 'q' pour l'ouest dans la même seconde
	// on fait demi tour, interdit et inutile à partir d'une taille de 2 (>= 2 : perdu instant)
	private boolean keyChange;
	public static final Color BORDER = new Color(255, 255, 255);
	public static final Color SNAKE = new Color(255, 255,255);
	public static final Color GROUND1 = new Color(52, 152, 219);
	public static final Color GROUND2 = new Color(52, 140, 210);
	private JSpinner spinner;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrame tempFrame = new JFrame();
					
					String result = "";
					int size = -1;
					do {
						try {
							result = JOptionPane.showInputDialog(tempFrame, "Taille de la grille :", 10);
							size = Integer.parseInt(result);
						} catch (Exception e){
							
						}
						
					} while ((size < 10 || size > 100) && result != null);
					tempFrame.dispose();
					if (result != null) {
						tempFrame.dispose();
						SnakeView frame = new SnakeView(size);
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

	public SnakeView(int size) {
		setIconImage(SNAKE_ICON);
		setTitle("Snake");
		
		this.size = size;
		this.game = new SnakeModel(this.size);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setSize(500, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_game = new JPanel();
		contentPane.add(panel_game, BorderLayout.CENTER);
		panel_game.setLayout(new GridLayout(size + 2, size + 2, 0, 0));
		
		JPanel panel_title = new JPanel();
		contentPane.add(panel_title, BorderLayout.NORTH);
		panel_title.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		spinner = new JSpinner();
		spinner.setEnabled(false);
		spinner.setValue(size);
		spinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				System.out.println((int) spinner.getValue());
			}
		});
		panel_title.add(spinner);
		
		JLabel scoreIcon = new JLabel("");
		scoreIcon.setIcon(SCORE);
		panel_title.add(scoreIcon);
		
		scoreText = new JLabel("0");
		scoreText.setFont(new Font("Tahoma", Font.BOLD, 20));
		scoreText.setForeground(Color.ORANGE);
		panel_title.add(scoreText);
		
		JLabel highScoreIcon = new JLabel("");
		highScoreIcon.setIcon(HIGHSCORE);
		panel_title.add(highScoreIcon);
		
		highScoreText = new JLabel("0");
		highScoreText.setForeground(Color.RED);
		highScoreText.setFont(new Font("Tahoma", Font.BOLD, 20));
		panel_title.add(highScoreText);
		
		restartButton = new JButton("3");
		restartButton.setIcon(RESTART);
		restartButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		restartButton.setForeground(new Color(89, 136, 66));
		restartButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.newGame((int) spinner.getValue());
				init();
			}
		});
		
		progressBar = new JProgressBar();
		panel_title.add(progressBar);
		panel_title.add(restartButton);
		
		caseList = new JButton[size+2][size+2];
		for (int i = 0; i < size + 2; i++) {
			for (int j = 0; j < size + 2; j++) {
				JButton btn = new JButton("");
				btn.setFocusable(false);
				btn.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
				caseList[i][j] = btn;
				panel_game.add(btn);
			}
		}
		
		init();
		
		
	}
	
	public void init() {
		size = (int) spinner.getValue();
		for (int i = 0; i < size + 2; i++) {
			for (int j = 0; j < size + 2; j++) {
				caseList[i][j].setIcon(null);
				if ((i+j) % 2 == 0)
					caseList[i][j].setBackground(GROUND1);
				else
					caseList[i][j].setBackground(GROUND2);
			}
		}
		
		for (int i = 0; i < size + 2; i++) {
			caseList[0][i].setBackground(BORDER);
			caseList[size+ 1][i].setBackground(BORDER);
			caseList[i][0].setBackground(BORDER);
			caseList[i][size + 1].setBackground(BORDER);
		}
		
		this.keyChange = false;
		scoreText.setFocusable(true);
		scoreText.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (!keyChange) {
				char chr = e.getKeyChar();
				switch (chr) {
				case 'z':
					if (game.getDirection() != 2) {
						game.setDirection(0);
						keyChange = true;
					}
					break;
				case 'd':
					if (game.getDirection() != 3) {
						game.setDirection(1);
						keyChange = true;
					}
					break;
				case 's':
					if (game.getDirection() != 0) {
						game.setDirection(2);
						keyChange = true;
					}
					break;
				
				case 'q':
					if (game.getDirection() != 1) {
						game.setDirection(3);
						keyChange = true;
					}
				}
				}
			}
		});
		
		
		restartButton.setEnabled(false);
		spinner.setEnabled(false);
		
		scoreText.setText("0");
		restartButton.setText("3");
		
		this.run(caseList, game, size);
	}
	
	public void run(JButton[][] caseList, SnakeModel game, int size) {
    	caseList[game.getSpawnPoint().x][game.getSpawnPoint().y].setBackground(SNAKE);
    	caseList[game.getSpawnPoint2().x][game.getSpawnPoint2().y].setBackground(SNAKE);
    	caseList[game.getSpawnPoint2().x][game.getSpawnPoint2().y].setIcon(SNAKE_HEAD_IMAGE);
    	previousNewPoint = game.getSpawnPoint2();
    	caseList[game.getFirstApplePoint().x][game.getFirstApplePoint().y].setIcon(APPLE_IMAGE);
		ActionListener action = new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
				Point[] moves = game.move();
				// System.out.println(moves[1].x + "" + moves[1].y + "" + moves[0].x + "" + moves[0].y);
				// newPoint : moves[0] oldPoint : moves[1] apple : moves[2]
				// oldPoint et apple sont nuls -> perdu
				// oldPoint nul et apple non nul -> on rentre dans une pomme
				// oldPoint non nul -> avancé normal sans pomme ni mur/corps du serpent
				
				// oldPoint != null : mouvement normal : pas de pomme
				if (moves[1] != null) {
					caseList[moves[0].x][moves[0].y].setBackground(SNAKE);
					caseList[moves[0].x][moves[0].y].setIcon(SNAKE_HEAD_IMAGE);
					caseList[previousNewPoint.x][previousNewPoint.y].setBackground(SNAKE);
					caseList[previousNewPoint.x][previousNewPoint.y].setIcon(null);
					
					if ((moves[1].x+moves[1].y) % 2 == 0)
						caseList[moves[1].x][moves[1].y].setBackground(GROUND1);
					else
						caseList[moves[1].x][moves[1].y].setBackground(GROUND2);
				}
				
				else {
					// on rentre dans une pomme
					if (moves[2] != null) {
						caseList[moves[0].x][moves[0].y].setBackground(SNAKE);
						caseList[moves[0].x][moves[0].y].setIcon(SNAKE_HEAD_IMAGE);
						caseList[previousNewPoint.x][previousNewPoint.y].setBackground(SNAKE);
						caseList[previousNewPoint.x][previousNewPoint.y].setIcon(null);

						caseList[moves[2].x][moves[2].y].setIcon(APPLE_IMAGE);
					// on rentre dans le mur/son corps
					} else {
						// chokbar sur l'endroit ou on s'est crash
						
						caseList[moves[0].x][moves[0].y].setIcon(CHOKBAR);
						
						timer.stop();
						restartButton.setEnabled(true);
						spinner.setEnabled(true);
					}
				}
				
				scoreText.setText(Integer.toString(game.getScore()));
				highScoreText.setText(Integer.toString(game.getHighScore()));
				progressBar.setValue(game.getScore());
				progressBar.setMaximum(game.getHighScore());
				
				
				previousNewPoint = moves[0];
				
				
				keyChange = false;
			}
		};
		
		ActionListener wait = new ActionListener() {
			int count = 2;
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	restartButton.setText(Integer.toString(count));
		    	count--;
		    	if (count == -1) {
		    		countdown.stop();
		    		timer.start();
		    		restartButton.setText("");
		    	}
		    }
		};
		
		// start
		countdown = new Timer(1000, wait);
		countdown.start();
		countdown.setRepeats(true);
		
		timer = new Timer(100, action);
		timer.setRepeats(true);
		
		
	
	}

}
