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
	String code;
	
	
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
		if (code.equals("ASH")){
			addNode(1,1);
		}
		if (code.equals("BH")){
			addNode(1,1);
		}
		if (code.equals("CAC")){
			addNode(1,1);
		}
		if (code.equals("CDC")){
			addNode(1,1);
		}
		if (code.equals("COM")){
			addNode(1,1);
		}
		if (code.equals("CON")){
			addNode(1,1);
		}
		if (code.equals("FH")){
			addNode(1,1);
		}
		if (code.equals("HHLC")){
			addNode(1,1);
		}
		if (code.equals("HON")){
			addNode(1,1);
		}
		if (code.equals("HRY")){
			addNode(1,1);
		}
		if (code.equals("JHZ")){
			addNode(1,1);
		}
		if (code.equals("KC")){
			addNode(1,1);
		}
		if (code.equals("KHS")){
			addNode(1,1);
		}
		if (code.equals("KTB")){
			addNode(1,1);
		}
		if (code.equals("LHH")){
			addNode(1,1);
		}
		if (code.equals("LIB")){
			addNode(1,1);
		}
		if (code.equals("LMH")){
			addNode(1,1);
		}
		if (code.equals("LOH")){
			addNode(1,1);
		}
		if (code.equals("LSH")){
			addNode(1,1);
		}
		if (code.equals("LTT")){
			addNode(1,1);
		}
		if (code.equals("MAK")){
			addNode(1,1);
		}
		if (code.equals("MAN")){
			addNode(1,1);
		}
		if (code.equals("MUR")){
			addNode(1,1);
		}
		if (code.equals("NMR")){
			addNode(1,1);
		}
		if (code.equals("PAC")){
			addNode(1,1);
		}
		if (code.equals("PAD")){
			addNode(1,1);
		}
		if (code.equals("SH")){
			addNode(1,1);
		}
		if (code.equals("STU")){
			addNode(1,1);
		}
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
