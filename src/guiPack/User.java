package guiPack;

import java.util.LinkedList;
import java.util.List;


/*******************************************************************************

 * @author Douglas Wallin
 * @author Louis Sullivan
 ******************************************************************************/
public class User {
	/** List of MapNodes to represent the users classes as saved by the user. */
	private List<MapNode> classList;
	
	/** String to hold the username of this User object. */
	private String username;

	/***************************************************************************
	 * Constructor to build an User object given a username and a
	 * password.
	 * 
	 * @param name String: The user's username. Will be used later to populate
	 * the user's various lists
	 **************************************************************************/
	public User(final String name) {
		username = name;
		classList = new LinkedList<MapNode>();
	}

	/***************************************************************************
	 * @return List<MapNode>: A list of users saved classes
	 **************************************************************************/
	public List<MapNode> getClassList() {
		return classList;
	}
	
	/***************************************************************************
	 * This method will add a MapNode specifying a building to the users saved
	 * class list.
	 * 
	 * @param class1 MapNode: The MapNode representing the class to be added
	 **************************************************************************/
	public void addClass(final MapNode class1) {
		classList.add(class1);
	}
	
	/***************************************************************************
	 * @return String: This user's username
	 **************************************************************************/
	public String getUsername() {
		return username;
	}
	
	/***************************************************************************
	 * Empties the user's class list.
	 **************************************************************************/
	public void clearClassList() {
		classList.clear();
	}
}