package GUIpack;

public class Edge {
	private final String id;
	private final int edgeWeight;
	private final MapNode source;
	private final MapNode destination;
	
	
	public Edge(MapNode source, MapNode destination, int edgeWeight){
		this.source = source;
		this.destination = destination;
		this.edgeWeight = edgeWeight;
		id = null;
	}
	
	public Edge(MapNode source, MapNode destination, int edgeWeight, String id){
		this.source = source;
		this.destination = destination;
		this.edgeWeight = edgeWeight;
		this.id  = id;
	}

	public String getId(){
		return id;
	}
	
	public int getEdgeWeight() {
		return edgeWeight;
	}

	public MapNode getDestination() {
        return destination;
    }

    public MapNode getSource() {
        return source;
    }
}
