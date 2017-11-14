package GUIpack;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;


/*******************************************************************************
 *
 * 
 * @author Douglas Wallin
 * @author Louis Sullivan
 ******************************************************************************/
public class User {
	private List<MapNode> userClassList;
	private List<MapNode> userFavoriteList;
	private List<MapNode> busStopList;
	
	/**	String to hold a username */
	private String userName;
	
	
	Location class1 = new Location();
	Location class2 = new Location();
	Location class3 = new Location();
	Location class4 = new Location();
	Location class5 = new Location();
	Location class6 = new Location();
	
	public User(String userName) {
		this.userName = userName;
		userClassList = new LinkedList<MapNode>();
		busStopList = new LinkedList<MapNode>();
		userFavoriteList = new LinkedList<MapNode>();
	}
	
	public List<MapNode> getUserClassList() {
		return userClassList;
	}

	public void setUserClassList(List<MapNode> userClassList) {
		this.userClassList = userClassList;
	}

	public List<MapNode> getUserFavoriteList() {
		return userFavoriteList;
	}

	public void setUserFavoriteList(List<MapNode> userFavoriteList) {
		this.userFavoriteList = userFavoriteList;
	}

	public List<MapNode> getBusStopList() {
		return busStopList;
	}

	public void setBusStopList(List<MapNode> busStopList) {
		this.busStopList = busStopList;
	}

	public User(String n, Location one, Location two, Location three, Location four, Location five, Location six){
		
		
		
		
		
		userName = n;
		class1 = one;
		class2 = two;
		class3 = three;
		class4 = four;
		class5 = five;
		class6 = six;
	}
	

}
