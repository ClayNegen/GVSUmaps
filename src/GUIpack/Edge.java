package GUIpack;

/*******************************************************************************
 * The Edge object represents an edge in graph theory between two nodes. More
 * specifically between two MapNodes. The edge is weighted.
 * 
 * @author Douglas Wallin
 ******************************************************************************/
public class Edge {
	
	/**	A string to keep track of the edge by name. The name will follow the
	 * style of "idOfSourceNode idOfDestinationNode" */
	private final String id;
	
	/**	A double to represent the edge's weight	*/
	private final double edgeWeight;
	
	/**	The source MapNode */
	private final MapNode source;
	
	/**	The destination MapNode	*/
	private final MapNode destination;
	
	/***************************************************************************
	 * A constructor for an Edge that takes a node the edge will originate from
	 * and a node the edge will end at
	 * 
	 * @param source MapNode: The origin MapNode
	 * @param destination MapNode: The destination MapNode
	 **************************************************************************/
	public Edge(final MapNode source, final MapNode destination){
		this.source = source;
		this.destination = destination;
		this.edgeWeight = getDistance(source, destination);
		id = source.getNodeId() + " " + destination.getNodeId();
	}
	
	/***************************************************************************
	 * Takes two nodes and calculates and returns the distance between them
	 * using the distance formula
	 * 
	 * @param source: The origin node
	 * @param destination: The destination node
	 * @return double: The edge weight
	 **************************************************************************/
	private double getDistance(MapNode source, MapNode destination){
		double distance = Math.sqrt(Math.pow((source.getX() -
				destination.getX()), 2) + Math.pow((source.getY() -
				destination.getY()), 2));
		return distance;
	}

	/***************************************************************************
	 * @return String: id
	 **************************************************************************/
	public String getId(){
		return id;
	}
	
    /***************************************************************************
	 * @return double: edgeWeight
	 **************************************************************************/
	public double getEdgeWeight() {
		return edgeWeight;
	}

	/***************************************************************************
	 * @return MapNode: destination
	 **************************************************************************/
	public MapNode getDestination() {
        return destination;
    }

    /***************************************************************************
     * @return MapNode: source
     **************************************************************************/
    public MapNode getSource() {
        return source;
    }
}
