package GUIpack;

public class Edge {
	private final String id;
	private final double edgeWeight;
	private final MapNode source;
	private final MapNode destination;
	
	
	public Edge(MapNode source, MapNode destination){
		this.source = source;
		this.destination = destination;
		this.edgeWeight = getDistance(source, destination);
		id = source.getNodeId() + " " + destination.getNodeId();
	}
	
	private double getDistance(MapNode source, MapNode destination){
		double distance = Math.sqrt(Math.pow((source.getX() - destination.getX()), 2) + Math.pow((source.getY() - destination.getY()), 2));
		return distance;
	}

	public String getId(){
		return id;
	}
	
	public double getEdgeWeight() {
		return edgeWeight;
	}

	public MapNode getDestination() {
        return destination;
    }

    public MapNode getSource() {
        return source;
    }
}
