package practica.backend;

public class CellMatrix {

	private Cell[][] cellMatrix;									
	
	private int cellMatrixNumRows, cellMatrixNumColumns;
	
	/**
	 * Creates 5x5 default size cellMatrix 
	 */
	public CellMatrix() {
		this.cellMatrix = new Cell[5][5];
	}
	
	/**
	 * If a cell is occupied an AI agent shouldn't be able to enter it.
	 * 
	 * @Precondition 1 <= x <= this.getNumColums()
	 * @Precondition 1 <= y <= this.getNumRows()
	 * @param x row
	 * @param y column
	 */
	public void occuppyCell(int x, int y) {
		if(1 <= x && x <= this.getNumColumns() && 
				1 <= y && y <= this.getNumRows()) {
			cellMatrix[x][y].setOccupied(true);
		}
	}
	
	/**
	 * A Free cell should allow an AI agent to enter it.
	 * 
	 * @Precondition 1 <= x <= this.getNumColums()
	 * @Precondition 1 <= y <= this.getNumRows()
	 * @param x row
	 * @param y column
	 */
	public void freeCell(int x, int y) {
		if(1 <= x && x <= this.getNumColumns() && 
				1 <= y && y <= this.getNumRows()) {
			cellMatrix[x][y].setOccupied(false);
		}
	}
	
	/**
	 * Part of stage size specification. Used in the cell matrix
	 * 
	 * 
	 * @return numRows Rows in the matrix of cells in the stage
	 */
	public int getNumRows() {
		return cellMatrixNumRows;
	}
	
	/**
	 * Part of stage size specification. Used in the cell matrix
	 * Should be called after instantiation.
	 * 
	 * @param numRows should be at least 1
	 */
	public void setNumRows(int numRows) {
		this.cellMatrix = new Cell[numRows][this.cellMatrixNumColumns];
		this.cellMatrixNumRows = numRows;
	}

	/**
	 * Part of stage size specification. Used in the cell matrix
	 * 
	 * 
	 * @return numColumns Columns in the matrix of cells in the stage
	 */
	public int getNumColumns() {
		return cellMatrixNumColumns;
	}

	/**
	 * Part of stage size specification. Used in cell matrix.
	 * Should be called after instantiation.
	 * 
	 * @return numColumns Columns in the matrix of cells in the stage
	 */
	public void setNumColumns(int numColumns) {
		this.cellMatrix = new Cell[this.cellMatrixNumRows][numColumns];
		this.cellMatrixNumColumns = numColumns;
	}
	
}
