import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

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
				System.out.print(this.grid[i][j] + " ");
			}
			System.out.println(" ");
		}
	}

	public static void main(String[] args) {
		Demineur demineur = new Demineur(15);
		demineur.generateGrid();
		demineur.showGrid();

	}
}
