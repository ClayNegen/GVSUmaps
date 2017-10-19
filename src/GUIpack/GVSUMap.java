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
	private List<MapNode> nodeList;
	private List<Edge> edgeList;


	
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
	 * @param id
	 */
	public void addNode(int x, int y, String id){
		MapNode temp = new MapNode(x, y, id);
		nodeList.add(temp);
	}
	
	private void addNode(int x, int y, int id) {
		MapNode temp = new MapNode(x, y, Integer.toString(id));
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
		for (MapNode node : nodeList) {
			if (node.getNodeId().equals(sourceLocId)) {
				sourceNode = node;
			}
		}

		for (MapNode node : nodeList) {
			if (node.getNodeId().equals(destLocId)) {
				destinationNode = node;
			}
		}

		if (sourceNode != null && destinationNode != null) {
			Edge lane = new Edge(sourceNode, destinationNode);
			edgeList.add(lane);
			lane = new Edge(destinationNode, sourceNode);
			edgeList.add(lane);
			return;
		}
		throw new NullPointerException();
	}

	public void addLane(int sourceLocNum, int destLocNum) {
		MapNode sourceNode = null;
		MapNode destinationNode = null;
		for (MapNode node : nodeList) {
			if (node.getNodeInfo().equals(Integer.toString(sourceLocNum))) {
				sourceNode = node;
			}
		}

		for (MapNode node : nodeList) {
			if (node.getNodeInfo().equals(Integer.toString(sourceLocNum))) {
				destinationNode = node;
			}
		}

		if (sourceNode != null && destinationNode != null) {
			Edge lane = new Edge(sourceNode, destinationNode);
			edgeList.add(lane);
			lane = new Edge(destinationNode, sourceNode);
			edgeList.add(lane);
			return;
		}
	}

	public void initialize(){
		this.addNode(388, 62, "");
		this.addNode(390, 101, "");
		this.addNode(402, 123, "");
		this.addNode(414, 136, "");
		this.addLane("388,62", "390,101");
		this.addLane("390,101", "402,123");
		this.addLane("402,123", "414,136");
		this.addNode(428, 124, "");
		this.addLane("414,136", "428,124");
		this.addNode(445, 133, "");
		this.addLane("428,124", "445,133");
		this.addNode(457, 118, "");
		this.addLane("445,133", "457,118");

		//End of Unamed nodes
		//todo: Go back and assign numbers to these nodes, will make debugging later on much easier

		this.addNode(472, 121, "3");
		this.addLane("457,118", "472,121");
		this.addNode(442, 153, "1");
		this.addLane("414,136", "442,153");
		this.addLane("445,133", "442,153");
		this.addNode(462, 162, "2");
		this.addLane("442,153", "462,162");
		this.addLane("462,162", "472,121");
		this.addNode(490, 124, "4");
		this.addLane("472,121", "490,124");
		this.addNode(491, 141, "5");
		this.addLane("490,124", "491,141");
		this.addNode(503, 142, "6");
		this.addLane("491,141", "503,142");
		this.addNode(496, 150, "7");
		this.addLane("496,150", "491,141");
		this.addLane("496,150", "503,142");
		this.addNode(497, 170, "8");
		
		//End of adding lanes by coordinates
		//todo: go back and rewrite with node numbers instead of coordinates to increase readability
		
		this.addLane(7, 8);
		this.addNode(492, 180, "9");
		this.addNode(480, 174, "10");
		this.addNode(483, 188, "11");
		
		//added overloaded addNode method to add by integer
		
		this.addLane(2, 10);
		this.addLane(10, 9);
		this.addLane(9, 8);
		this.addLane(10, 11);
		this.addLane(9, 11);
		this.addNode(515, 161, 12);
		this.addLane(12, 8);
		this.addNode(524, 174, 13);
		this.addLane(12, 13);
		this.addNode(470, 209, 14);
		this.addLane(11, 14);
		this.addNode(440, 119, "Alumni House");
		this.addLane("428,124",	"440,119");
	}
}
