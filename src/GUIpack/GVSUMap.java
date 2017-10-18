package GUIpack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GVSUMap {
	private  List<MapNode> nodeList;
	private  List<Edge> edgeList;
	
	
	public GVSUMap(List<MapNode> nodeList, List<Edge> edgeList){
		this.nodeList = nodeList;
		this.edgeList = edgeList;
	}
	
	public GVSUMap(List<MapNode> nodeList){
		this.nodeList = nodeList;
		edgeList = new ArrayList<Edge>();
	}
	
	private void addNode(int x, int y){
		MapNode temp = new MapNode(x, y);
		nodeList.add(temp);
	}

	public List<MapNode> getNodeList(){
		return nodeList;
	}
	
	public List<Edge> getEdgeList(){
		return edgeList;
	}
	
	public void initialize(){
		
	}
	
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
}
