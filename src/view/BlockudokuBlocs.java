package view;

import java.awt.Point;

import javax.swing.JButton;

public class BlockudokuBlocs extends JButton {
	
	public final int initialPositionX;
	public final int initialPositionY;
	public BlockudokuBlocs() {
		this.initialPositionX = this.getX();
		this.initialPositionY = this.getY();
	}
}
