package GUIpack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/************************************************************************************
 * This class creates an object called DjikstraAlgorithim to perform Djikstras famous
 * algorithm to solve the shortest path problem. In this case it is being applied to 
 * a GVSUMap object with nodes and edges.
 * 
 * @author Louis Sullivan
 * @author Clay Negen
 * @author Douglas Wallim
 ************************************************************************************/
public class DijkstraAlgorithm {
	
	/** Instance variable to hold all the nodes in the graph. */
    private List<MapNode> nodes;
    
    /** Instance variable to hold all the edges in the graph. */
    private  List<Edge> edges;
    
    /** Instance variable to keep track of which nodes have already been checked
     * in the algorithm. */
    private Set<MapNode> settledNodes;
    
    /** Instance variable to keep track of all nodes which have not been used yet
     * in the algorithm. */
    private Set<MapNode> unSettledNodes;
    
    /** Instance variable to hold all nodes that have edges to the current node being
     * evaluated. */
    private Map<MapNode, MapNode> predecessors;
    
    /**Instance variable to hold all the distances corresponding from each node in
     * predecessors to the current node being evaluated. */
    private Map<MapNode, Double> distance;

    /***********************************************************************************
     * Constructor that creates a copy of the input map so that we can alter the arrays
     * within.
     * 
     * @param GVSUMap graph: The map on which the algorithm is going to be run
     ************************************************************************************/
    public DijkstraAlgorithm(GVSUMap graph) {
        this.nodes = new ArrayList<MapNode>(graph.getNodeList());
        this.edges = new ArrayList<Edge>(graph.getEdgeList());
    }
    
    /***********************************************************************************
     * This method runs Djikstras algorithm from the source node to all other nodes in
     * the map loading the results into the predecessors HashMap.
     * 
     * @param String name: This string is ID of the source node desired
     ************************************************************************************/
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
    
    /***********************************************************************************
     * This method does most of the work for the algorithm determining the minimal
     * distance from all neighboring nodes
     * 
     * @param MapNode node: This string is ID of the source node desired
     ************************************************************************************/
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
    
    /***********************************************************************************
     * The getDistance method does the calculation between two nodes to see what the
     * actual distance is
     * 
     * @param MapNode node: This node is the first of the two nodes
     * @param MapNode target: The second of the two nodes
     * 
     * @return double: The edge weight between the two nodes
     ************************************************************************************/
    private double getDistance(MapNode node, MapNode target) {
        for (Edge edge : edges) {
            if (edge.getSource().equals(node)
                    && edge.getDestination().equals(target)) {
                return edge.getEdgeWeight();
            }
        }
        throw new RuntimeException("Should not happen");
    }
    
    /***********************************************************************************
     * Returns a list of all nodes that have an edge connecting them to the input node.
     * Works in close conjunction with the findMinimalDistance method
     * 
     * @param MapNode node: The node we want to find all the nodes that are connected to
     * it
     * 
     * @return List<MapNode>: A list of all connected nodes
     ************************************************************************************/
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

    /***********************************************************************************
     * Compares the current shortest distance from one node to another to the next
     * possible distance
     * 
     * @param Set<MapNode> MapNodes: The set of MapNodes with the keys representing the
     * cumulative distance to the source node
     * 
     * @return MapNode: The MapNode with the shortest distance
     ************************************************************************************/
    private MapNode getMinimum(Set<MapNode> MapNodes) {
        MapNode minimum = null;
        for (MapNode node : MapNodes) {
            if (minimum == null) {
                minimum = node;
            } 
            else {
                if (getShortestDistance(node) < getShortestDistance(minimum)) {
                    minimum = node;
                }
            }
        }
        return minimum;
    }
    
    /***********************************************************************************
     * Checks to see if a given node has been settled by seeing if it is contained
     * within the settledNodes list.
     * 
     * @param MapNode node:
     * 
     * @return boolean: A boolean representing whether a node has been settled
     ************************************************************************************/
    private boolean isSettled(MapNode node) {
        return settledNodes.contains(node);
    }

    /***********************************************************************************
     * Gets the distance
     * 
     * @param MapNode node:
     * 
     * @return boolean: A boolean representing whether a node has been settled
     ************************************************************************************/
    private double getShortestDistance(MapNode destination) {
        Double d = distance.get(destination);
        if (d == null) {
            return Integer.MAX_VALUE;
        } 
        else {
            return d;
        }
    }
    
    /***********************************************************************************
     * This method returns the path from the source to the selected target and
     * NULL if no path exists
     * 
     * @param MapNode target: The destination of the shortest path desired
     * 
     * @return LinkedList<MapNode>: A list of nodes along the path
     ************************************************************************************/
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
   
        Collections.reverse(path);
        return path;
    }
    
    /***********************************************************************************
     * Looks up a node by ID from the original map
     * 
     * @param String ID: The ID of the node to be looked up
     * 
     * @return MapNode: The node desired
     ************************************************************************************/
    private MapNode findByNodeInfo(String ID){
    	for (MapNode node : nodes) {
    		if (node.getNodeInfo().equals(ID))
    			return node;
        }
    	return null;
    	//check for errors here, maybe
    }
}