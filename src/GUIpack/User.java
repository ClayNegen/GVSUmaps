package GUIpack;

import java.util.LinkedList;
import java.util.List;

/**
 * 
 * @author Louis Sullivan
 * @author Clay Negen
 * @author Douglas Wallim
 *
 */
public class User {
	
	/** List of mapNodes to represent the users classes as saved by the user */
	private List<MapNode> classList;
	
	/** String to hold the username of this User object */
	private String username;
	
	/** String to hold the password of this Usser object */
	private String password;
	
	/*******************************************************************************
	 * Constructor to build an User object given a username and a password
	 * 
	 * @param username String: The user's username
	 * @param password String: The user's password
	 *******************************************************************************/
	public User(String username, String password) {
		this.username = username;
		this.password = password;
		classList = new LinkedList<MapNode>();
	}

	public List<MapNode> getClassList() {
		return classList;
	}
	
	public void addClass(MapNode _class) {
		classList.add(_class);
	}
	
	public String getUsername() {
		return username;
	}
}