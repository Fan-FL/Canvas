package client.shape;

import java.awt.Graphics2D;
import java.io.Serializable;

/*
   This class is about the base class of drawing on the canvas
 */
public abstract class Shape implements Serializable {

	public int x1, x2, y1, y2,ix,iy; // define the position of the mouse
	public int R, G, B; // define the color of the pen
	public float stroke; // define the line size
	public int type; // define the attributes of the character
	public String s1; // Character style one
	public String s2; // Character style two

	// The drawing function
	public abstract void draw(Graphics2D g2d) ;
}
