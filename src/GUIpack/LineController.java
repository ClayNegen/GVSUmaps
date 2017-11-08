package GUIpack;

import java.util.List;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class LineController {
	
	private BufferedImage canvas;
	
	private final BufferedImage original;
	
	private final int width;
	
	private final int height;
	
	public LineController (BufferedImage image) {
		original = image;
		canvas = image;
		width = canvas.getWidth();
		height = canvas.getHeight();
	}
	
	public void drawDiagonal (MapNode node1, MapNode node2){
		int x1 = node1.getX();
		int x2 = node2.getX();
		int y1 = node1.getY();
		int y2 = node2.getY();
		
		List<CoordinateTuple> pointsList  = getPoints(x1, x2, y1, y2);
		
		for (CoordinateTuple coordinatePair: pointsList) {
			canvas.setRGB(coordinatePair.getX(), coordinatePair.getY(),
					Color.RED.getRGB());
		}
	}
	
	private List<CoordinateTuple> getPoints(int x1, int x2, int y1, int y2) {
		List<CoordinateTuple> line = new ArrayList<CoordinateTuple>();
		
		double slope = ((y2 - y1) / (x2 - x1));

		int yVal;
		for (int i = x1; i < x2; i++) {
			yVal = getYValue(i, slope, x2, y2);
			CoordinateTuple temp = new CoordinateTuple(i, yVal);
			line.add(temp);
		}
		
		return line;
	}
		
	private int getYValue(int x, double slope, int xRef, int yRef) {
		int yVal;
		double yIntercept;
		
		yIntercept = (yRef - (slope * xRef));
		
		yVal = round((slope * x) + yIntercept);
		
		return yVal;
	}
	
	private int round(double d) {
		double dAbs = Math.abs(d);
		int i = (int) dAbs;
		double result = dAbs - (double) i;
		
		if(result < 0.5){
			return d < 0 ? -i : i;            
			} else {
				return d < 0 ? -(i+1) : i+1;          
				}
	}
	
	private BufferedImage reset() {
		canvas = original;
		return original;
	}
	
	public BufferedImage getCanvas() {
		return canvas;
	}
}
