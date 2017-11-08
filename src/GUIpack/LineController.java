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
		List<CoordinateTuple> pointsList;
		
		
		if (Math.abs(y2 - y1) < Math.abs(x2 - x1)) {
			pointsList  = getYPoints(x1, x2, y1, y2);
		} else {
			pointsList  = getXPoints(x1, x2, y1, y2);
		}
		
		for (CoordinateTuple coordinatePair: pointsList) {
			canvas.setRGB(coordinatePair.getX(), coordinatePair.getY(),
					Color.RED.getRGB());
		}
	}
	
	private List<CoordinateTuple> getYPoints(int x1, int x2, int y1, int y2) {		
		List<CoordinateTuple> line = new ArrayList<CoordinateTuple>();
		
		if (x2 < x1) {
			int temp = x2;
			x2 = x1;
			x1 = temp;
			
			temp = y2;
			y2 = y1;
			y1 = temp;
		}
		
		double slope = (((double) (y2 - y1)) / (x2 - x1));
		
		int yVal;
		for (int i = x1; i < x2; i++) {
			yVal = getYValue(i, slope, x2, y2);
			CoordinateTuple temp = new CoordinateTuple(i, yVal);
			line.add(temp);
		}
		
		return line;
	}
	
	private List<CoordinateTuple> getXPoints(int x1, int x2, int y1, int y2) {		
		List<CoordinateTuple> line = new ArrayList<CoordinateTuple>();
		
		if (y2 < y1) {
			int temp = x2;
			x2 = x1;
			x1 = temp;
			
			temp = y2;
			y2 = y1;
			y1 = temp;
		}
		
		double slope = (((double) (x2 - x1)) / (y2 - y1));
		
		int xVal;
		for (int i = y1; i < y2; i++) {
			xVal = getXValue(i, slope, x2, y2);
			CoordinateTuple temp = new CoordinateTuple(xVal, i);
			line.add(temp);
		}
		
		return line;
	}
	
	private int getXValue(int y, double slope, int xRef, int yRef) {
		int xVal;
		double xIntercept;
		
		xIntercept = (xRef - (slope * yRef));
		
		xVal = round((slope * y) + xIntercept);
		
		return xVal;
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
