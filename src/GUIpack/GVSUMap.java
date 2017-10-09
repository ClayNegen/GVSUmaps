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
	private final List<MapNode> nodeList;
	private final List<Edge> edgeList;
	
	
	public GVSUMap(List<MapNode> nodeList, List<Edge> edgeList){
		this.nodeList = nodeList;
		this.edgeList = edgeList;
	}
	
	public List<MapNode> getNodeList(){
		return nodeList;
	}
	
	public List<Edge> getEdgeList(){
		return edgeList;
	}
}
