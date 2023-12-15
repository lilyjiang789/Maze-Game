/**
 * Shape
 * an object for the character
 * @author lily
 * @date Jan 23rd 2022
 */
import java.awt.*;

public class Shape {
	Color color; 
	Shape(){
		this.color = Color.DARK_GRAY; //initial color
	}
	
	public void setNewColor(Color colour) {
		this.color = colour; //sets the new color
	}
	
	public Color getColor() {
		return this.color; //gets the color
	}
}
