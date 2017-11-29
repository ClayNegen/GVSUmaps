package guiPack;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

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
	
	/**	Instance of the Map Engine. Handles all algorithm work in determining
	 *  the shortest path to a destination requested by the view. */
	private MapEngine algorithm;
	
	/**	A hard-coded graphical representation of the GVSU Allendale campus. */
	private final GVSUMap map;
	
	/***************************************************************************
	 * This constructor instantiates each of the engine components.
	 * 
	 * @param img BufferedImage: The picture to have directions drawn on
	 **************************************************************************/
	public EngineController(final BufferedImage img) {
		lineModel = new LineModeller(img);
		map = new GVSUMap();
		algorithm = new MapEngine(map);
	}
	
	/***************************************************************************
	 * This is one of two primarily used methods from the view. This draws the
	 * directions between two nodes that are specified by the nodeInfo entered.
	 * 
	 * @param srcNodeInfo String: The nodeInfo member of the node desired for
	 * the source node
	 * @param destNodeInfo String: The nodeInfo member of the node desired for
	 * the source node
	 **************************************************************************/
	public void getDirections(final String srcNodeInfo,
			final String destNodeInfo) {
		MapNode srceNode = map.getNode(srcNodeInfo);
		MapNode destNode = map.getNode(destNodeInfo);
		
		algorithm.execute(srceNode);
		LinkedList<MapNode> path = algorithm.getPath(destNode);
		
		drawPath(path);
	}
	
	
	//TODO Get rid of this
	/***************************************************************************
	 * test method. Will stay for now.
	 **************************************************************************/
	public void drawAllLines() {
		List<Edge>	tempVar = map.getEdgeList();
		LinkedList<MapNode> tempPath = new LinkedList<MapNode>();
		
		for (Edge e: tempVar) {
			tempPath.add(e.getSource());
			tempPath.add(e.getDestination());
			drawPath(tempPath);
			tempPath.clear();
		}
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
	 **************************************************************************/
	public BufferedImage reset() {
		lineModel.reset();
		algorithm.reset();
		
		return lineModel.getCanvas();
	}
	
	/***************************************************************************
	 * @return List<MapNode>: A list of all nodes in the GVSU map
	 **************************************************************************/
	public List<MapNode> getNodeList() {
		return map.getNodeList();
	}
}
