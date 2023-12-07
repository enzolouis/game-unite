package controller;

import java.awt.Component;
import javax.swing.JPanel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

import view.BlockudokuBlocs;
import view.BlockudokuCase;
import view.BlockudokuView;

public class BlockudokuController implements MouseListener {
	private BlockudokuView view;
	public BlockudokuController(BlockudokuView view) {
		this.view = view;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("test3");
		if (e.getSource() instanceof BlockudokuBlocs) {
			BlockudokuBlocs target = (BlockudokuBlocs) e.getSource();
			//target.setVisible(false);
			this.view.getContentPane().remove(target);
			this.view.getContentPane().revalidate();
			this.view.getContentPane().repaint();
			//target.getParent().remove(target);
			//target
			
			System.out.println(target.initialPositionX);
			System.out.println(target.initialPositionY);
			
			((JPanel) this.view.getContentPane().getComponents()[1]).add(target);
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
	}
}
