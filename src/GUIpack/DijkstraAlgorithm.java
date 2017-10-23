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
 * This class creates an object called DjikstraAlgorithim to perform Djikstras famous algorithim to
 * solve the shortest path problem. In this case it is being applied to a GVSUMap object with nodes
 * and edges.
 * 
 * @author Louis Sullivan
 *  @author Clay Negen
 * @author Douglas Wallim
 */
public class DijkstraAlgorithm {
	
	/** Instance variable to hold all the nodes in the graph. */
    private List<MapNode> nodes;
    
    /** Instance variable to hold all the edges in the graph. */
    private  List<Edge> edges;
    
    /** Instance variable to keep track of which nodes have already been checked
     * in the algorithim. */
    private Set<MapNode> settledNodes;
    
    /** Instance variable to keep track of all nodes which have not been used yet
     * in the algorithim. */
    private Set<MapNode> unSettledNodes;
    
    /** Instance variable to hold all nodes that have edges to the current node being
     * evaluated. */
    private Map<MapNode, MapNode> predecessors;
    
    /**Instance variable to hold all the distances corresponding from each node in
     * predecessors to the current node being evaluated. */
    private Map<MapNode, Double> distance;

    /**
     * Constructor for a DijkstraAlgorithm object that initializes nodes and edges
     * with a copy of the information held in the graph object passed in
     * @param graph
     */
    public DijkstraAlgorithm(GVSUMap graph) {
        // create a copy of the array so that we can operate on this array
        this.nodes = new ArrayList<MapNode>(graph.getNodeList());
        this.edges = new ArrayList<Edge>(graph.getEdgeList());
    }
    
    /**
     * Primary command to run the Djikstras on the current node storing its shortest path
     * to every other node in the graph
     * @param name
     */
    public void execute(String name) {
        settledNodes = new HashSet<MapNode>();
        unSettledNodes = new HashSet<MapNode>();
        distance = new HashMap<MapNode, Double>();
        predecessors = new HashMap<MapNode, MapNode>();
        
        MapNode source = findByNodeInfo(name);
        
        distance.put(source, 0.0);
        unSettledNodes.add(source);
        while (unSettledNodes.size() > 0) {
            MapNode node = getMinimum(unSettledNodes);
            settledNodes.add(node);
            unSettledNodes.remove(node);
            findMinimalDistances(node);
        }
    }
/**
 * 
 * @param node
 */
    private void findMinimalDistances(MapNode node) {
        List<MapNode> adjacentNodes = getNeighbors(node);
        for (MapNode target : adjacentNodes) {
            if (getShortestDistance(target) > getShortestDistance(node)
                    + getDistance(node, target)) {
                distance.put(target, getShortestDistance(node)
                        + getDistance(node, target));
                predecessors.put(target, node);
                unSettledNodes.add(target);
            }
        }

    }
/**
 * 
 * @param node
 * @param target
 * @return
 */
    private double getDistance(MapNode node, MapNode target) {
        for (Edge edge : edges) {
            if (edge.getSource().equals(node)
                    && edge.getDestination().equals(target)) {
                return edge.getEdgeWeight();
            }
        }
        throw new RuntimeException("Should not happen");
    }
/**
 * 
 * @param node
 * @return
 */
    private List<MapNode> getNeighbors(MapNode node) {
        List<MapNode> neighbors = new ArrayList<MapNode>();
        for (Edge edge : edges) {
            if (edge.getSource().equals(node)
                    && !isSettled(edge.getDestination())) {
                neighbors.add(edge.getDestination());
            }
        }
        return neighbors;
    }

/**
 * 
 * @param MapNodees
 * @return
 */
    private MapNode getMinimum(Set<MapNode> MapNodees) {
        MapNode minimum = null;
        for (MapNode node : MapNodees) {
            if (minimum == null) {
                minimum = node;
            } else {
                if (getShortestDistance(node) < getShortestDistance(minimum)) {
                    minimum = node;
                }
            }
        }
        return minimum;
    }
/**
 * 
 * @param MapNode
 * @return
 */
    private boolean isSettled(MapNode MapNode) {
        return settledNodes.contains(MapNode);
    }
/**
 * 
 * @param destination
 * @return
 */
    private double getShortestDistance(MapNode destination) {
        Double d = distance.get(destination);
        if (d == null) {
            return Integer.MAX_VALUE;
        } 
        else {
            return d;
        }
    }

    
    /**
     * This method returns the path from the source to the selected target and
     * NULL if no path exists
     * @param target
     * @return
     */
    public LinkedList<MapNode> getPath(MapNode target) {
        LinkedList<MapNode> path = new LinkedList<MapNode>();
        MapNode step = target;
        // check if a path exists
        if (predecessors.get(step) == null) {
            return null;
        }
        path.add(step);
        while (predecessors.get(step) != null) {
            step = predecessors.get(step);
            path.add(step);
        }
        // Put it into the correct order
        Collections.reverse(path);
        return path;
    }
    /**
     * 
     * @param input
     * @return
     */
    private MapNode findByNodeInfo(String input){
    	for (MapNode node : nodes) {
    		if (node.getNodeInfo().equals(input))
    			return node;
        }
    	return null;
    	//check for errors here, maybe
    }
}
