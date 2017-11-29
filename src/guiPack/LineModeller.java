package guiPack;

import java.util.List;

import javax.imageio.ImageIO;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class LineModeller {
	
	private BufferedImage canvas;
	
	private final String original = "src/gvsuMaps.jpg";
	
	public LineModeller (BufferedImage image) {
		canvas = image;
	}
	
	public void drawDiagonal (MapNode node1, MapNode node2){
		int x1 = node1.getX();
		int x2 = node2.getX();
		int y1 = node1.getY();
		int y2 = node2.getY();
		
		List<Tuple<Integer, Integer>> pointsList;
		
		String drawType = null;
		
		if (Math.abs(y2 - y1) < Math.abs(x2 - x1)) {
			pointsList  = getYPoints(x1, x2, y1, y2);
			drawType = "HorizontalLine";
		} else {
			pointsList  = getXPoints(x1, x2, y1, y2);
			drawType = "VerticalLine";
		}
		if (drawType.equals("HorizontalLine")) {
			for (Tuple<Integer, Integer> coordinatePair: pointsList) {
				canvas.setRGB(coordinatePair.getElement1(), coordinatePair.getElemnt2(),
						Color.BLUE.getRGB());
				canvas.setRGB(coordinatePair.getElement1(), coordinatePair.getElemnt2() + 1,
						Color.BLUE.getRGB());
				canvas.setRGB(coordinatePair.getElement1(), coordinatePair.getElemnt2() - 1,
						Color.BLUE.getRGB());
			}
		}
		else {
			for (Tuple<Integer, Integer> coordinatePair: pointsList) {
				canvas.setRGB(coordinatePair.getElement1(), coordinatePair.getElemnt2(),
						Color.BLUE.getRGB());
				canvas.setRGB(coordinatePair.getElement1() + 1, coordinatePair.getElemnt2(),
						Color.BLUE.getRGB());
				canvas.setRGB(coordinatePair.getElement1() - 1, coordinatePair.getElemnt2(),
						Color.BLUE.getRGB());
			}
		}
	}



	private List<Tuple<Integer, Integer>> getYPoints(int x1, int x2,
			int y1, int y2) {		
		List<Tuple<Integer, Integer>> line = 
				new ArrayList<Tuple<Integer, Integer>>();
		
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
			Tuple<Integer, Integer> temp = new Tuple<Integer, Integer>(i, yVal);
			line.add(temp);
		}
		
		return line;
	}
	
	private List<Tuple<Integer, Integer>> getXPoints(int x1, int x2,
			int y1, int y2) {		
		List<Tuple<Integer, Integer>> line = 
				new ArrayList<Tuple<Integer, Integer>>();
		
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
			Tuple<Integer, Integer> temp = new Tuple<Integer, Integer>(xVal, i);
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
	
	public BufferedImage reset() throws IOException  {
		canvas = ImageIO.read(new File(original));
		return canvas;
	}
	
	public BufferedImage getCanvas() {
		return canvas;
	}
}
