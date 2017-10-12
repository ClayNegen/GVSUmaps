package GUIpack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DijkstraAlgorithm {

    private final List<MapNode> nodes;
    private final List<Edge> edges;
    private Set<MapNode> settledNodes;
    private Set<MapNode> unSettledNodes;
    private Map<MapNode, MapNode> predecessors;
    private Map<MapNode, Double> distance;

    public DijkstraAlgorithm(GVSUMap graph) {
        // create a copy of the array so that we can operate on this array
        this.nodes = new ArrayList<MapNode>(graph.getNodeList());
        this.edges = new ArrayList<Edge>(graph.getEdgeList());
    }

    public void execute(MapNode source) {
        settledNodes = new HashSet<MapNode>();
        unSettledNodes = new HashSet<MapNode>();
        distance = new HashMap<MapNode, Double>();
        predecessors = new HashMap<MapNode, MapNode>();
        distance.put(source, 0.0);
        unSettledNodes.add(source);
        while (unSettledNodes.size() > 0) {
            MapNode node = getMinimum(unSettledNodes);
            settledNodes.add(node);
            unSettledNodes.remove(node);
            findMinimalDistances(node);
        }
    }

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

    private double getDistance(MapNode node, MapNode target) {
        for (Edge edge : edges) {
            if (edge.getSource().equals(node)
                    && edge.getDestination().equals(target)) {
                return edge.getEdgeWeight();
            }
        }
        throw new RuntimeException("Should not happen");
    }

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

    private MapNode getMinimum(Set<MapNode> MapNodees) {
        MapNode minimum = null;
        for (MapNode MapNode : MapNodees) {
            if (minimum == null) {
                minimum = MapNode;
            } else {
                if (getShortestDistance(MapNode) < getShortestDistance(minimum)) {
                    minimum = MapNode;
                }
            }
        }
        return minimum;
    }

    private boolean isSettled(MapNode MapNode) {
        return settledNodes.contains(MapNode);
    }

    private double getShortestDistance(MapNode destination) {
        Double d = distance.get(destination);
        if (d == null) {
            return Integer.MAX_VALUE;
        } 
        else {
            return d;
        }
    }

    /*
     * This method returns the path from the source to the selected target and
     * NULL if no path exists
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
}
