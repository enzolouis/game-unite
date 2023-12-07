package controller;

import java.awt.MouseInfo;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JButton;

import view.BlockudokuView;

public class BlockudokuMouseMotionController implements MouseMotionListener {

	private BlockudokuView view;
	
	public BlockudokuMouseMotionController(BlockudokuView view) {
		this.view = view;
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		JButton target = (JButton) e.getSource();
		target.getParent().remove(target);
		this.view.getContentPane().add(target);
		
		//System.out.println(this.view.getContentPane().getLocation());
		System.out.println(this.view.getMousePosition());
		System.out.println(MouseInfo.getPointerInfo().getLocation());
		
		//target.setLocation((int)MouseInfo.getPointerInfo().getLocation().getX()-target.getWidth()/2, (int)MouseInfo.getPointerInfo().getLocation().getY()-target.getHeight()/2);
		//target.setLocation((int)MouseInfo.getPointerInfo().getLocation().getX(), (int)MouseInfo.getPointerInfo().getLocation().getY());
		target.setLocation((int)this.view.getMousePosition().getX()-target.getWidth()/2, (int)this.view.getMousePosition().getY()-target.getHeight()/2);
		
		
		
		this.view.getContentPane().setComponentZOrder(target, 0);
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// inutile
	}

}
