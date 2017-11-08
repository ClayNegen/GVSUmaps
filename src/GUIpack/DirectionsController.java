package GUIpack;

import java.awt.image.BufferedImage;
import java.util.LinkedList;

public class DirectionsController {
	
	private LineModeller lineModel;
	
	private MapAlgorithmEngine algorithm;
	
	public DirectionsController(BufferedImage img, GVSUMap graph) {
		lineModel = new LineModeller(img);
		algorithm = new MapAlgorithmEngine(graph);
	}
	
	public void drawPath(LinkedList<MapNode> path) {
		for (int i = 0; i < path.size() - 1; i++) {
			lineModel.drawDiagonal(path.get(i), path.get(i + 1));
		}
    }
	
	public BufferedImage getImage() {
    	return lineModel.getCanvas();
    }
	
	public BufferedImage reset() {
		lineModel.reset();
		algorithm.reset();
		
		return lineModel.getCanvas();
	}
}
