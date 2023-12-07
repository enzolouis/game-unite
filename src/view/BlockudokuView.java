package view;

import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.MouseInfo;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.BlockudokuController;
import controller.BlockudokuMouseMotionController;
import controller.MinesweeperMouseController;
import model.MinesweeperModel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import java.awt.Cursor;

public class BlockudokuView extends JFrame {
	
	private static final ImageIcon L1 = new ImageIcon(new ImageIcon(SnakeView.class.getClassLoader().getResource
			("L1.png")).getImage().getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH));
	private static final ImageIcon THREE_HORIZONTALLY = new ImageIcon(new ImageIcon(SnakeView.class.getClassLoader().getResource
			("THREE_HORIZONTALLY.png")).getImage().getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH));
	private static final ImageIcon SQUARE = new ImageIcon(new ImageIcon(SnakeView.class.getClassLoader().getResource
			("SQUARE.png")).getImage().getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH));
	
	
	// grille de 9x9
	public static final int SIZE = 9;

	private JPanel contentPane;
	private BlockudokuController controller;
	private BlockudokuMouseMotionController controller2;
	private BlockudokuCase[][] buttons;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BlockudokuView frame = new BlockudokuView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	

	public BlockudokuView() {
		this.controller=new BlockudokuController(this);
		this.controller2 = new BlockudokuMouseMotionController(this);
		
		setTitle("Blockudoku");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 600);
		setMinimumSize(new Dimension(600, 700));
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
	    contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
	    
	    
	    JPanel panelGrid = new JPanel();
	    contentPane.add(panelGrid);
	    panelGrid.setLayout(new GridLayout(9, 9, 0, 0));
	    panelGrid.setMaximumSize(new Dimension(400, 400));
	    
	    JPanel panelBloc = new JPanel();
	    contentPane.add(panelBloc);
	    
	    BlockudokuBlocs btnCase1 = new BlockudokuBlocs();
	    btnCase1.setFocusPainted(false);
	    btnCase1.addMouseListener(controller);
	    btnCase1.addMouseMotionListener(controller2);
	    btnCase1.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
	    btnCase1.setContentAreaFilled(false);
	    btnCase1.setBorderPainted(false);
	    btnCase1.setIcon(SQUARE);
	    panelBloc.add(btnCase1);
	    
	    
	    BlockudokuBlocs btnCase2 = new BlockudokuBlocs();
	    btnCase2.addMouseListener(controller);
	    btnCase2.addMouseMotionListener(controller2);
	    btnCase2.setFocusPainted(false);
	    btnCase2.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
	    btnCase2.setContentAreaFilled(false);
	    btnCase2.setBorderPainted(false);
	    btnCase2.setIcon(THREE_HORIZONTALLY);
	    panelBloc.add(btnCase2);
	    
	    BlockudokuBlocs btnCase3 = new BlockudokuBlocs();
	    btnCase3.addMouseListener(controller);
	    btnCase3.addMouseMotionListener(controller2);
	    btnCase3.setFocusPainted(false);
	    btnCase3.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
	    btnCase3.setContentAreaFilled(false);
	    btnCase3.setBorderPainted(false);
	    btnCase3.setIcon(L1);
	    panelBloc.add(btnCase3);
	    
	    //btnCase1
	    
	    this.buttons = new BlockudokuCase[SIZE][SIZE];
	    for (int i = 0; i < SIZE; i++) {
	        for (int j = 0; j < SIZE; j++) {
	            buttons[i][j] = new BlockudokuCase(i,j);
	            panelGrid.add(buttons[i][j]);
	        }
	    }
		
		for (int i = 0; i < SIZE; i++) {
	        for (int j = 0; j < SIZE; j++) {
	            buttons[i][j].addMouseListener(controller);
	        }
	    }
	}

}
