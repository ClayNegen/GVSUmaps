package guiPack;

import java.util.List;

import javax.imageio.ImageIO;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/*******************************************************************************
 * This class is an engine component to our program that does all the math to
 * determine where to draw on the map given the path provided by the
 * EngineController.
 * 
 * @author Douglas Wallin
 ******************************************************************************/
public class LineModeller {
	/** This BufferedImage is representative of the state of the map image in 
	 * the program. */
	private BufferedImage canvas;
	
	/** This is where the original map is stored in the file system. This
	 * String is used to revert back to it. */
	private final String original = "src/gvsuMaps.jpg";
	
	/***************************************************************************
	 * Constructor for the LineModeller. Sets the canvas equal to the image
	 * passed as a parameter.
	 * 
	 * @param image BufferedImage: The image being passed to the LineModeller
	 **************************************************************************/
	public LineModeller(final BufferedImage image) {
		canvas = image;
	}
	
	/***************************************************************************
	 * This method uses all the helper functions to assemble a list of xy
	 * coordinates on the map between two nodes. It then accordingly alters the
	 * RGB of pixels on the map to give the effect of "drawing" on the map.
	 * 
	 * @param node1 MapNode: The first of the two nodes
	 * @param node2 MapNode: The second of the two nodes
	 **************************************************************************/
	public void drawDiagonal(final MapNode node1, final MapNode node2) {
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
				canvas.setRGB(coordinatePair.getElement1(),
						coordinatePair.getElemnt2(), Color.BLUE.getRGB());
				canvas.setRGB(coordinatePair.getElement1(),
						coordinatePair.getElemnt2() + 1, Color.BLUE.getRGB());
				canvas.setRGB(coordinatePair.getElement1(),
						coordinatePair.getElemnt2() - 1, Color.BLUE.getRGB());
			}
		} else {
			for (Tuple<Integer, Integer> coordinatePair: pointsList) {
				canvas.setRGB(coordinatePair.getElement1(),
						coordinatePair.getElemnt2(), Color.BLUE.getRGB());
				canvas.setRGB(coordinatePair.getElement1() + 1,
						coordinatePair.getElemnt2(), Color.BLUE.getRGB());
				canvas.setRGB(coordinatePair.getElement1() - 1,
						coordinatePair.getElemnt2(), Color.BLUE.getRGB());
			}
		}
	}


	/***************************************************************************
	 * The method returns a list of points between two points using some other
	 * helper methods.
	 * 
	 * @param x1 int: The x value of the first point
	 * @param x2 int: The x value of the second point
	 * @param y1 int: The y value of the first point
	 * @param y2 int: The y value of the second point
	 * @return List<Tuple<Integer, Integer>> A list of points
	 **************************************************************************/
	private List<Tuple<Integer, Integer>> getYPoints(int x1,  int x2,
			 int y1,  int y2) {		
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
	
	/***************************************************************************
	 * The method returns a list of points between two points using some other
	 * helper methods.
	 * 
	 * @param x1 int: The x value of the first point
	 * @param x2 int: The x value of the second point
	 * @param y1 int: The y value of the first point
	 * @param y2 int: The y value of the second point
	 * @return List<Tuple<Integer, Integer>> A list of points
	 **************************************************************************/
	private List<Tuple<Integer, Integer>> getXPoints(int x1,  int x2,
			 int y1,  int y2) {		
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
	
	/***************************************************************************
	 * This method finds the f(y) of the y parameter passed in using the point
	 * slope formula.
	 * 
	 * @param y int: The y value for which we want to find the f(y)
	 * @param slope double: The slope variable for the equation
	 * @param xRef int: The x value for the point to be referenced in the
	 * equation.
	 * @param yRef int: The y value for the point to be referenced in the
	 * equation.
	 * 
	 * @return int The f(y) value
	 **************************************************************************/
	private int getXValue(final int y, final double slope, final int xRef,
			final int yRef) {
		int xVal;
		double xIntercept;
		
		xIntercept = (xRef - (slope * yRef));
		
		xVal = round((slope * y) + xIntercept);
		
		return xVal;
	}
	
	/***************************************************************************
	 * This method finds the f(x) of the x parameter passed in using the point
	 * slope formula.
	 * 
	 * @param x int: The x value for which we want to find the f(x)
	 * @param slope double: The slope variable for the equation
	 * @param xRef int: The x value for the point to be referenced in the
	 * equation.
	 * @param yRef int: The y value for the point to be referenced in the
	 * equation.
	 * 
	 * @return int The f(x) value
	 **************************************************************************/
	private int getYValue(final int x, final double slope, final int xRef,
			final int yRef) {
		int yVal;
		double yIntercept;
		
		yIntercept = (yRef - (slope * xRef));
		
		yVal = round((slope * x) + yIntercept);
		
		return yVal;
	}
	
	/***************************************************************************
	 * Method to round values.
	 * 
	 * @param value double: The value to be rounded
	 * @return int An integer that represents value rounded to the nearest int
	 **************************************************************************/
	private int round(final double value) {
		double dAbs = Math.abs(value);
		int i = (int) dAbs;
		double result = dAbs - (double) i;
		
		if (result < 0.5) {
			return value < 0 ? -i : i;            
			} else {
				return value < 0 ? - (i + 1) : i + 1;          
			}
	}

	/***************************************************************************
	 * Resets the canvas to the original map image.
	 **************************************************************************/
	public void reset() {
		try {
			canvas = ImageIO.read(new File(original));
		} catch (IOException e) {
			System.out.println("It happened here lol. Who woulda thought. 0_0");
		}
	}

	/***************************************************************************
	 * @return BufferedImage Getter for the canvas
	 **************************************************************************/
	public BufferedImage getCanvas() {
		return canvas;
	}
}
