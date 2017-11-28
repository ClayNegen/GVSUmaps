package guiPack;

/**
 * 
 * @author Louis Sullivan
 * @author Clay Negen
 * @author Douglas Wallim
 *
 */
public class Location {
	String name;
	int longitude;
	int latitude;
	MapNode node;
	
	public Location(){
		String name;
		MapNode node;
		int longitude;
		int latitude;
	}
	
	public Location (MapNode node, int longitude, int latitude){
		this.node = node;
		this.longitude = longitude;
		this.latitude = latitude;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLongitude() {
		return longitude;
	}

	public void setLongitude(int longitude) {
		this.longitude = longitude;
	}

	public int getLatitude() {
		return latitude;
	}

	public void setLatitude(int latitude) {
		this.latitude = latitude;
	}
}
