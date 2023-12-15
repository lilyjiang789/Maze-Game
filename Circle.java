/**
 * Circle
 * a subclass of shape that specifically draws a circle
 * @author lily
 * @date Jan 23rd 2022
 */
public class Circle extends Shape{
	private String circle;
	private GUIMemory memory = new GUIMemory();
	
	public void drawShape() {
		circle = "circle"; //sets the shape to draw a circle
		memory.drawShape = "circle";
	}
}
