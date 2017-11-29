package guiPack;

import java.awt.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.HashSet;
import java.util.LinkedList;

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
	 * @param x int: The x coordinate of the node
	 * @param y int: The y coordinate of the node
	 **************************************************************************/
	public MapNode(int x, int y){
		this.x = x;
		this.y = y;
		nodeId = Integer.toString(x) + "," + Integer.toString(y);
		nodeInfo = null;
	}

	/***************************************************************************
	 * Alternate constructor used primarily for MapNodes that designate a
	 * building or important point. Requires nodeInfo (usually the building
	 * name or a short description, i.e. "Police HQ").
	 * 
	 * @param x int: The x coordinate of the node
	 * @param y int: The y coordinate of the node
	 * @param nodeInfo String: Short describing information about the node
	 **************************************************************************/
	public MapNode(int x, int y, String nodeInfo){
		this.x = x;
		this.y = y;
		nodeId = Integer.toString(x) + "," + Integer.toString(y);
		this.nodeInfo = nodeInfo;
	}

	/***************************************************************************
	 * @return String: nodeInfo
	 **************************************************************************/
	public String getNodeInfo(){
		return nodeInfo;
	}

	/***************************************************************************
	 * @return String: nodeId
	 **************************************************************************/
	public String getNodeId(){
		return nodeId;
	}

	/***************************************************************************
	 * @return int: x
	 **************************************************************************/
	public int getX() {
		return x;
	}

	/***************************************************************************
	 * @param x int
	 **************************************************************************/
	public void setX(int x) {
		this.x = x;
	}

	/***************************************************************************
	 * @return y: int
	 **************************************************************************/
	public int getY() {
		return y;
	}

	/***************************************************************************
	 * @param y int
	 **************************************************************************/
	public void setY(int y) {
		this.y = y;
	}
	
	
	

	@Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((nodeId == null) ? 0 : nodeId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        MapNode other = (MapNode) obj;
        if (nodeId == null) {
            if (other.nodeId != null)
                return false;
        } else if (!nodeId.equals(other.nodeId))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return nodeId;
    }

}
