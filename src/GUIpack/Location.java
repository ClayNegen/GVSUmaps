package GUIpack;

public class Location {
	String name;
	int longitude;
	int latitude;
	
	public Location(){
		String name;
		int longitude;
		int latitude;
	}
	
	public Location (String name, int longitude, int latitude){
		this.name = name;
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
