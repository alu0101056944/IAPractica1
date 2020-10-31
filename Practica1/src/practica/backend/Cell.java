package practica.backend;

public class Cell {

	private boolean occupied;
	private boolean initial;
	private boolean finish;

	public Cell() {}
	
	/**
	 * 
	 * @return true if there is an obstacle on the cell
	 */
	public boolean isOccupied() {
		return occupied;
	}

	/**
	 * 
	 * @param occupied true means cell is occupied
	 */
	public void setOccupied(boolean occupied) {
		if(isFinish()==false && isInitial()==false) this.occupied = occupied;
	}

	/**
	 * @return the initial
	 */
	public boolean isInitial() {
		return initial;
	}

	/**
	 * @param initial the initial to set
	 */
	public void setInitial(boolean initial) {
		if(isFinish()==false) this.initial = initial;
	}

	/**
	 * @return the finish
	 */
	public boolean isFinish() {
		return finish;
	}

	/**
	 * @param finish the finish to set
	 */
	public void setFinish(boolean finish) {
		if(isInitial()==false)this.finish = finish;
	}
	
	
}
