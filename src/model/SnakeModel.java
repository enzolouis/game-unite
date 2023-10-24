package model;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SnakeModel {
	private final int SIZE;
	private int[][] grid;
	private Point spawnPoint;
	private Point spawnPoint2;
	private Point firstApplePoint;
	private int score;
	private int highScore;
	private int direction;
	List<Point> snakeCoords;

	public SnakeModel(int size) {
		this.SIZE = size;
		this.highScore = 0;
		this.newGame(size);
	}
	
	public void newGame(int size) {
		this.grid = new int[this.SIZE + 2][this.SIZE + 2];
		for (int i = 0; i < this.SIZE + 2; i++) {
			this.grid[0][i] = 1;
			this.grid[this.SIZE + 1][i] = 1;
			this.grid[i][0] = 1;
			this.grid[i][this.SIZE + 1] = 1;
		}
		this.spawnPoint = new Point(this.SIZE / 2, 3);
		this.spawnPoint2 = new Point(this.SIZE / 2, 4);
		this.firstApplePoint = new Point(this.SIZE / 2, 8);
		this.grid[this.firstApplePoint.x][this.firstApplePoint.y] = 2;
		this.snakeCoords = new ArrayList<>();
		this.snakeCoords.add(this.spawnPoint);
		this.snakeCoords.add(this.spawnPoint2);
		this.direction = 1;
		this.score = 0;
	}

	public Point getSpawnPoint() {
		return this.spawnPoint;
	}

	public Point getSpawnPoint2() {
		return this.spawnPoint2;
	}

	public Point getFirstApplePoint() {
		return this.firstApplePoint;
	}
	
	public int getScore() {
		return this.score;
	}
	
	public int getHighScore() {
		return this.highScore;
	}
	
	public int getDirection() {
		return this.direction;
	}
	public void setDirection(int newDirection) {
		this.direction = newDirection;
	}

	private void showGrid() {
		System.out.println(" ");
		for (int i = 0; i < this.SIZE + 2; i++) {
			for (int y = 0; y < this.SIZE + 2; y++) {
				System.out.print(this.grid[i][y] + " ");
			}
			System.out.println(" ");
		}
	}

	private boolean isFinished() {
		for (int i = 0; i < this.SIZE + 2; i++) {
			for (int y = 0; y < this.SIZE + 2; y++) {
				if (this.grid[i][y] == 0) {
					return false;
				}
			}
		}
		return true;
	}

	// Les pommes seront des "2"
	private Point generateApple() {

		Random random = new Random();
		List<Point> zeroCoords = new ArrayList<>();
		// Récupération des coordonnées de tous les 0
		for (int i = 0; i < this.SIZE + 2; i++) {
			for (int j = 0; j < this.SIZE + 2; j++) {
				if (this.grid[i][j] == 0) {
					zeroCoords.add(new Point(i, j));
				}
			}
		}
		// Si le jeu n'est pas fini, place une pomme au hasard parmis zeroCoords dans la
		// grille
		Point randomCoords = zeroCoords.get(random.nextInt(zeroCoords.size()));
		this.grid[randomCoords.x][randomCoords.y] = 2;
		return new Point(randomCoords.x, randomCoords.y);
	}

	public Point addPoint(int x, int y) {
		this.snakeCoords.add(0, new Point(x, y));
		return new Point(x, y);
	}

	public void removePoint() {
		this.grid[this.snakeCoords.get(this.snakeCoords.size() - 1).x][this.snakeCoords
				.get(this.snakeCoords.size() - 1).y] = 0;
		this.snakeCoords.remove(this.snakeCoords.size() - 1);
	}

	public void showSnake() {
		for (Point p : this.snakeCoords) {
			this.grid[p.x][p.y] = 1;
		}
	}

	public Point[] move() {
		Point newPoint = null;
		Point oldPoint = null;
		Point apple = null;
		switch (this.direction) {
		case 0:
			newPoint = this.addPoint(this.snakeCoords.get(0).x - 1, this.snakeCoords.get(0).y);
			break;

		case 1:
			newPoint = this.addPoint(this.snakeCoords.get(0).x, this.snakeCoords.get(0).y + 1);
			break;

		case 2:
			newPoint = this.addPoint(this.snakeCoords.get(0).x + 1, this.snakeCoords.get(0).y);
			break;

		case 3:
			newPoint = this.addPoint(this.snakeCoords.get(0).x, this.snakeCoords.get(0).y - 1);
		}

		if (this.grid[this.snakeCoords.get(0).x][this.snakeCoords.get(0).y] == 0) {
			oldPoint = new Point(this.snakeCoords.get(this.snakeCoords.size() - 1).x,
					this.snakeCoords.get(this.snakeCoords.size() - 1).y);
			this.removePoint();
		}
		if (this.grid[this.snakeCoords.get(0).x][this.snakeCoords.get(0).y] == 1) {
			System.out.println("perdu");
		}
		if (this.grid[this.snakeCoords.get(0).x][this.snakeCoords.get(0).y] == 2) {
			this.score++;
			if (this.score > this.highScore) {
				this.highScore = this.score;
			}
			apple = this.generateApple();
		}
		this.showSnake();

		Point[] points = new Point[3];
		points[0] = newPoint;
		points[1] = oldPoint;
		points[2] = apple;

		return points;

	}

	static public void main(String[] args) {
		SnakeModel snake = new SnakeModel(10);
		// snake.grid[5][7] = 2;

		/*
		 * snake.generateApple(); snake.grid[5][5] = 2; snake.grid[5][8] = 2;
		 * snake.grid[8][8] = 2; snake.grid[9][8] = 2;
		 * 
		 * snake.showSnake(); snake.showGrid(); snake.move(); snake.showGrid();
		 * snake.move(); snake.showGrid();
		 * 
		 * snake.move(); snake.move(); snake.showGrid(); snake.move(); snake.direction =
		 * 2; snake.move(); snake.showGrid(); snake.move(); snake.showGrid();
		 * snake.move(); snake.move(); snake.showGrid(); snake.direction = 3;
		 * snake.move(); snake.showGrid(); snake.move(); snake.showGrid();
		 * snake.direction = 2; snake.move(); snake.showGrid(); snake.move();
		 * snake.showGrid();
		 */
	}
}
