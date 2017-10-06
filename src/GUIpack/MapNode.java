package GUIpack;

public class MapNode {
	String locationName;
	
	private int x;
	private int y;
	
	public MapNode(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
}
