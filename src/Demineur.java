import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Demineur {
	private int length;
	private int[][] grid;
	private ArrayList<Point> bombes;

	public Demineur(int length) {
		this.length = length;
		this.grid = new int[length][length];
		this.bombes = new ArrayList<>();
		this.generateGrid();
		this.showGrid();
	}

	private int calculateNbBombe(int length) {
		return (int) Math.round(0.2 * (length * length) - 10);
	}
	public int getSize() {
		return this.length;
	}
	
	public List<Point> getBombeList() {
		return bombes;
	}

	private void generateRandomBombe() {
		Random random = new Random();
		while (this.bombes.size() < this.calculateNbBombe(this.length)) {
			int pos1 = random.nextInt(this.length);
			int pos2 = random.nextInt(this.length);
			Point bombe = new Point(pos1, pos2);
			if (!(this.bombes.contains(bombe))) {
				this.bombes.add(bombe);
			}
		}
	}
	public int getCase(int x, int y) {
		return this.grid[x][y];
	}
	
	public void generateGrid() {
		this.generateRandomBombe();
		for (int i = 0; i < this.length; i++) {
			for (int j = 0; j < this.length; j++) {
				this.grid[i][j] = 0;
			}
		}
		for (Point p : this.bombes) {
			this.grid[p.x][p.y] = 9;
		}
		for (int i = 0; i < this.length; i++) {
			for (int j = 0; j < this.length; j++) {
				if (!(this.grid[i][j] == 9)) {
					int nb = 0;
					for (int h = -1; h <= 1; h++) {
						for (int k = -1; k <= 1; k++) {
							int pos1 = i + h;
							int pos2 = j + k;
							if (pos1 >= 0 && pos1 < this.length && pos2 >= 0 && pos2 < this.length) {
								if (this.grid[pos1][pos2] == 9) {
									nb++;
								}
							}
						}
					}
					this.grid[i][j] = nb;
				}
			}
		}
	}

	public void showGrid() {
		for (int i = 0; i < this.length; i++) {
			for (int j = 0; j < this.length; j++) {
				System.out.print(((this.grid[i][j] == 0) ? " " : this.grid[i][j]) + " ");
			}
			System.out.println(" ");
		}
	}
	
	public void iterativeShowAdjacentNumberToEmptyCells(List<Point> points) {
		List<Point> points_temp = new ArrayList<>(points);
		for (Point p : points_temp) {
			for (int i = -1; i <= 1; i++) {
				for (int j = -1; j <= 1; j++) {
					if (i == 0 && j == 0) {
						continue; // permet d'eviter un tour inutile
					}
					
					int pos1 = p.x + i;
					int pos2 = p.y + j;
					
					if (!points.contains(new Point(pos1, pos2))) {
						if (pos1 >= 0 && pos1 < this.length && pos2 >= 0 && pos2 < this.length) {
							if (this.grid[pos1][pos2] != 0 && this.grid[pos1][pos2] != 9) {
								points.add(new Point(pos1, pos2));
							}
						}
					}
				}
			}
		}
	}

	public void recursiveRevealEmptyCells(Point p, List<Point> points) {
		if (grid[p.x][p.y] == 0) {
			for (int h = -1; h <= 1; h++) {
				for (int k = -1; k <= 1; k++) {
					int pos1 = p.x + h;
					int pos2 = p.y + k;
					if (!points.contains(new Point(pos1, pos2))) {
						if (pos1 >= 0 && pos1 < this.length && pos2 >= 0 && pos2 < this.length) {
							if (this.grid[pos1][pos2] == 0) {
								points.add(new Point(pos1, pos2));
								recursiveRevealEmptyCells(new Point(pos1, pos2), points);
							}
						}
					}
				}
			}
		}
	}

	
}
