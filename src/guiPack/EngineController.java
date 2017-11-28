package guiPack;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;

/*******************************************************************************
 * Controller class based off the MVC design principle. Takes input from the
 * view(GUI) and relays the information to the various engine components to
 * affect the required state changes
 * 
 * @author Douglas Wallin
 ******************************************************************************/
public class EngineController {
	
	/**	Instance of the LineModeller engine component. Handles all changes to
	 * the BufferedImage */
	private LineModeller lineModel;
	
	/**	Instance of the Map Engine. Handles all algorithm work in determining the
	 * shortest path to a destination requested by the view. */
	private MapEngine algorithm;
	
	/**	A hardcoded graphical representation of the GVSU Allendale campus */
	public final GVSUMap map;
	
	/***************************************************************************
	 * This constructor instantiates each of the engine components.
	 * 
	 * @param img BufferedImage: The picture to have directions drawn on
	 **************************************************************************/
	public EngineController(BufferedImage img) {
		lineModel = new LineModeller(img);
		map = new GVSUMap();
		algorithm = new MapEngine(map);
	}
	
	/***************************************************************************
	 * This is one of two primarily used methods from the view. This draws the
	 * directions between two nodes that are specified by the nodeInfo entered.
	 * 
	 * @param sourceNodeInfo String: The nodeInfo member of the node desired for
	 * the source node
	 * @param destNodeInfo String: The nodeInfo member of the node desired for
	 * the source node
	 * 
	 * @return BufferedImage: Returns a BufferedImage with directions drawn on
	 * it to correspond to the shortest path between the source and destination
	 * node
	 **************************************************************************/
	public BufferedImage getDirections(String srcNodeInfo, String destNodeInfo) {
		MapNode srceNode = map.getNode(srcNodeInfo);
		MapNode destNode =map.getNode(destNodeInfo);
		
		algorithm.execute(srceNode);
		LinkedList<MapNode> path = algorithm.getPath(destNode);
		
		drawPath(path);
		
		return getImage() ;
	}
	
	/***************************************************************************
	 * The getDirections method uses this to change pixels on the BufferedImage.
	 * It does most of the interaction with the LineModeller component
	 * 
	 * @param path LinkedList<MapNode>: A path from node to node (the solution)
	 **************************************************************************/
	private void drawPath(LinkedList<MapNode> path) {
		for (int i = 0; i < path.size() - 1; i++) {
			lineModel.drawDiagonal(path.get(i), path.get(i + 1));
		}
    }
	
	/***************************************************************************
	 * @return BufferedImage: The image to display in the GUI
	 **************************************************************************/
	public BufferedImage getImage() {
    	return lineModel.getCanvas();
    }
	
	/***************************************************************************
	 * Resets all engine components to be used once again.
	 * 
	 * @return BufferedImage: An original copy of the map without altered pixels
	 * @throws IOException
	 **************************************************************************/
	public BufferedImage reset() throws IOException {
		lineModel.reset();
		algorithm.reset();
		
		return lineModel.getCanvas();
	}
}
