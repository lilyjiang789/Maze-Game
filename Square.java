/**
 * Square
 * a subclass of square that specifically draws squares
 * @author lily
 * @date Jan 23rd 2022
 */
public class Square extends Shape{
	private String square;
	private GUIMemory memory = new GUIMemory();
	
	public void drawShape() {
		square = "square"; //sets the shape to draw a square
		memory.drawShape = "square";
	}
}
