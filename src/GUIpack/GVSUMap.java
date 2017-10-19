package GUIpack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 
 * @author Louis Sullivan
 * @author Clay Negen
 * @author Douglas Wallim
 *
 */
public class GVSUMap {
	private  List<MapNode> nodeList;
	private  List<Edge> edgeList;
	
	/**
	 * 
	 * @param nodeList
	 * @param edgeList
	 */
	public GVSUMap(List<MapNode> nodeList, List<Edge> edgeList){
		this.nodeList = nodeList;
		this.edgeList = edgeList;
	}
	/**
	 * 
	 * @param nodeList
	 */
	public GVSUMap(List<MapNode> nodeList){
		this.nodeList = nodeList;
		edgeList = new ArrayList<Edge>();
	}
	/**
	 * 
	 */
	public GVSUMap(){
		nodeList = new ArrayList<MapNode>();
		edgeList = new ArrayList<Edge>();
	}
	/**
	 * 
	 * @param x
	 * @param y
	 */
	public void addNode(int x, int y){
		MapNode temp = new MapNode(x, y);
		nodeList.add(temp);
	}
	/**
	 * 
	 * @param x
	 * @param y
	 * @param id
	 */
	public void addNode(int x, int y, String id){
		MapNode temp = new MapNode(x, y, id);
		nodeList.add(temp);
	}
	/**
	 * 
	 * @return
	 */
	public List<MapNode> getNodeList(){
		return nodeList;
	}
	/**
	 * 
	 * @return
	 */
	public List<Edge> getEdgeList(){
		return edgeList;
	}
	/**
	 * 
	 * @param sourceLocId
	 * @param destLocId
	 */
	public void addLane(String sourceLocId, String destLocId) {
        MapNode sourceNode = null;
        MapNode destinationNode = null;
		for(MapNode node: nodeList){
        	if (node.getNodeId().equals(sourceLocId)){
        		sourceNode = node;
        	}
        }
		
		for(MapNode node: nodeList){
        	if (node.getNodeId().equals(destLocId)){
        		destinationNode = node;
        	}
        }

		if(sourceNode != null && destinationNode != null){
			Edge lane = new Edge(sourceNode, destinationNode);
			edgeList.add(lane);
			lane = new Edge(destinationNode, sourceNode);
			edgeList.add(lane);
			return;
		}
		throw new NullPointerException();
    }
	
	public void initialize(){
		this.addNode(388, 62);
		this.addNode(390, 101);
		this.addNode(402, 123);
		this.addNode(414, 136);
		this.addLane("388,62", "390,101");
		this.addLane("390,101", "402,123");
		this.addLane("402,123", "414,136");
		this.addNode(428, 124);
		this.addLane("414,136", "428,124");
		this.addNode(445, 133);
		this.addLane("428,124", "445,133");
		this.addNode(457, 118);
		this.addLane("445,133", "457,118");
		
		//End of Unamed nodes
		//todo: Go back and assign numbers to these nodes, will make debugging later on much easier
		
		this.addNode(472, 121, "3");
		
	}
}
