package guiPack;

/*******************************************************************************
 * A MapNode based on a node in graph theory. A MapNode can be a point along the
 * path or a building depending on how nodeInfo is assigned.
 * 
 * @author Douglas Wallin
 ******************************************************************************/
public class MapNode {
	
	/** The x coordinate (pixel). */
	private int x;
	
	/**	The y coordinate (pixel). */
	private int y;
	
	/**	This stores information which will be used to lookup nodes with null
	 * nodeInfo. It will be of the format "xcoordinate,ycoordinate"	*/
	private final String nodeId;
	
	/**	A string to describe what a MapNode is. Will be typically used to
	 * distinguish a building from a point along the path */
	private String nodeInfo;
	
	/***************************************************************************
	 * Constructs a MapNode taking the x and y coordinates as parameters. Will
	 * automatically build the String nodeId using this information
	 * 
	 * @param xCoordx int: The x coordinate of the node
	 * @param yCoord int: The y coordinate of the node
	 **************************************************************************/
	public MapNode(final int xCoordx, final  int yCoord) {
		this.x = xCoordx;
		this.y = yCoord;
		nodeId = Integer.toString(xCoordx) + "," + Integer.toString(yCoord);
		nodeInfo = null;
	}

	/***************************************************************************
	 * Alternate constructor used primarily for MapNodes that designate a
	 * building or important point. Requires nodeInfo (usually the building
	 * name or a short description, i.e. "Police HQ").
	 * 
	 * @param xCoord int: The x coordinate of the node
	 * @param yCoord int: The y coordinate of the node
	 * @param nodeInf String: Short describing information about the node
	 **************************************************************************/
	public MapNode(final int xCoord, final int yCoord, final String nodeInf) {
		this.x = xCoord;
		this.y = yCoord;
		nodeId = Integer.toString(xCoord) + "," + Integer.toString(yCoord);
		this.nodeInfo = nodeInf;
	}

	/***************************************************************************
	 * @return String: nodeInfo
	 **************************************************************************/
	public String getNodeInfo() {
		return nodeInfo;
	}

	/***************************************************************************
	 * @return String: nodeId
	 **************************************************************************/
	public String getNodeId() {
		return nodeId;
	}

	/***************************************************************************
	 * @return int: x
	 **************************************************************************/
	public int getX() {
		return x;
	}

	/***************************************************************************
	 * @param xCoord int
	 **************************************************************************/
	public void setX(final int xCoord) {
		this.x = xCoord;
	}

	/***************************************************************************
	 * @return y: int
	 **************************************************************************/
	public int getY() {
		return y;
	}

	/***************************************************************************
	 * @param yCoord int
	 **************************************************************************/
	public void setY(final int yCoord) {
		this.y = yCoord;
	}

	@Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((nodeId == null) ? 0 : nodeId.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        MapNode other = (MapNode) obj;
        if (nodeId == null) {
            if (other.nodeId != null) {
                return false;
            }
        } else if (!nodeId.equals(other.nodeId)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nodeId;
    }

}
