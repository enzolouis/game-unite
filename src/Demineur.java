import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class Demineur {
	private int length;
	private int[][] grid;
	private ArrayList<Point> bombes;

	public Demineur(int length) {
		this.length = length;
		this.grid = new int[length][length];
		this.bombes = new ArrayList<>();
	}

	private int calculateNbBombe(int length) {
		return (int) Math.round(0.2 * (length * length) - 10);
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
							if (h == 0 && k == 0) {
								continue; // On ne doit pas compter la case sur laquel on est
							}
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
	
	public void showCaseWithNumberNearEmptyCase(List<Point> points) {
		points.add(new Point(-1, -1));
		List<Point> points_temp = new ArrayList<>(points);
		for (Point p : points_temp) {
			for (int i = -1; i <= 1; i++) {
				for (int j = -1; j <= 1; j++) {
					if (i == 0 && j == 0) {
						continue;
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

	public void recursiveDeployEmptyCase(Point p, List<Point> points) {
		if (grid[p.x][p.y] == 0) {
			for (int h = -1; h <= 1; h++) {
				for (int k = -1; k <= 1; k++) {
					if (h == 0 && k == 0) {
						continue;
					}
					int pos1 = p.x + h;
					int pos2 = p.y + k;
					if (!points.contains(new Point(pos1, pos2))) {
						if (pos1 >= 0 && pos1 < this.length && pos2 >= 0 && pos2 < this.length) {
							if (this.grid[pos1][pos2] == 0) {
								points.add(new Point(pos1, pos2));
								recursiveDeployEmptyCase(new Point(pos1, pos2), points);
							}
						}
					}
				}
			}
		}
	}

	public static void main(String[] args) {
		Demineur demineur = new Demineur(15);
		demineur.generateGrid();
		demineur.showGrid();

		List<Point> p = new ArrayList<Point>();
		
		demineur.recursiveDeployEmptyCase(new Point(5, 5), p);
		demineur.showCaseWithNumberNearEmptyCase(p);
		System.out.println("-------");
		System.out.println("-------");
		for (Point po : p) {
			System.out.println("(" + po.x + "," + po.y + ")");
			
			if (po.x != -1) // et y != -1 (test tempo)
				demineur.grid[po.x][po.y] = 8;
		}
		
		demineur.showGrid();
	}
}
