/**
 * GridOfGameBoard
 * sets x and y coordinates for a point
 * @author lily
 * @date Jan 23rd 2022
 */
public class GridOfGameBoard {
	private int x;
	private int y;
	
	public GridOfGameBoard(int x, int y) {
		//sets the x and y points
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return this.x; //returns the x points
	}
	
	public int getY() {
		return this.y; //returns the y points
	}
}
