package model;

public enum BlockudokuCaseType {
	ONE("."),
	
	TWO_HORIZONTALLY("--"),
	THREE_HORIZONTALLY("---"),
	FOUR_HORIZONTALLY("----"),
	FIVE_HORIZONTALLY("-----"),
	
	TWO_VERTICALLY("|\n"
				 + "|"),
	THREE_VERTICALLY("|\n"
			       + "|\n"
				   + "|"),
	FOUR_VERTICALLY("|\n"
		          + "|\n"
			      + "|\n"
		          + "|"),
	FIVE_VERTICALLY("|\n"
	              + "|\n"
		          + "|\n"
	              + "|\n"
		          + "|"),
	
	L1("|\n|\n|--"),
	L2("|--\n|\n|"),
	L3("  |\n  |\n--|"),
	L4("--|\n  |\n  |")
	
	
	
	
	
	
	;
	
	
	public static void main(String[] args) {
		for (BlockudokuCaseType b : BlockudokuCaseType.values()) {
			System.out.println(b.getDeno() + "\n");
		}
	}
	
	private String denomination;
	private BlockudokuCaseType(String denomination) {
		this.denomination = denomination;
	}
	
	public String getDeno() {
		return this.denomination;
	}
	
}
