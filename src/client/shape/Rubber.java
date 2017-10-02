package client.shape;

import java.awt.*;

public class Rubber extends Shape {
	@Override
	public void draw(Graphics2D g2d) {
		g2d.setPaint(new Color(255, 255, 255));
		// Set the color of the pen white
		g2d.setStroke(new BasicStroke(stroke + 4, BasicStroke.CAP_ROUND,
				BasicStroke.JOIN_BEVEL));
		g2d.drawLine(x1, y1, x2, y2);
	}
}
