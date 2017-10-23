package GUIpack;

/*******************************************************************************
 * @author Douglas Wallin
 *
 *******************************************************************************/
public class Edge {
	
	/*******************************************************************************
	 * 
	 *******************************************************************************/
	
	private final String id;
	
	/*******************************************************************************
	 * 
	 ******************************************************************************/
	private final double edgeWeight;
	
	/*******************************************************************************
	 * 
	 *******************************************************************************/
	private final MapNode source;
	/*******************************************************************************
	 * 
	 *******************************************************************************/
	private final MapNode destination;
	
	
	/*******************************************************************************
	 * @param source
	 * @param destination
	 *******************************************************************************/
	public Edge(MapNode source, MapNode destination){
		this.source = source;
		this.destination = destination;
		this.edgeWeight = getDistance(source, destination);
		id = source.getNodeId() + " " + destination.getNodeId();
	}
	
	/*******************************************************************************
	 * @param source
	 * @param destination
	 * @return
	 *******************************************************************************/
	private double getDistance(MapNode source, MapNode destination){
		double distance = Math.sqrt(Math.pow((source.getX() - destination.getX()), 2) + Math.pow((source.getY() - destination.getY()), 2));
		return distance;
	}

	/*******************************************************************************
	 * @return
	 *******************************************************************************/
	public String getId(){
		return id;
	}
	
	/*******************************************************************************
	 * @return
	 *******************************************************************************/
	public double getEdgeWeight() {
		return edgeWeight;
	}

	/*******************************************************************************
	 * @return
	 *******************************************************************************/
	public MapNode getDestination() {
        return destination;
    }

    /*******************************************************************************
     * @return
     *******************************************************************************/
    public MapNode getSource() {
        return source;
    }
}
