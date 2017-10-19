package GUIpack;

/**
 * 
 * @author Louis Sullivan
 * @author Clay Negen
 * @author Douglas Wallim
 *
 */
public class User {
	String name;
	Location class1 = new Location();
	Location class2 = new Location();
	Location class3 = new Location();
	Location class4 = new Location();
	Location class5 = new Location();
	Location class6 = new Location();
	
	/**
	 * 
	 * @param n
	 * @param one
	 * @param two
	 * @param three
	 * @param four
	 * @param five
	 * @param six
	 */
	public User(String n, Location one, Location two, Location three, Location four, Location five, Location six){
		name = n;
		class1 = one;
		class2 = two;
		class3 = three;
		class4 = four;
		class5 = five;
		class6 = six;
	}
	
	public User(){
		String name;
		Location class1 = new Location();
		Location class2 = new Location();
		Location class3 = new Location();
		Location class4 = new Location();
		Location class5 = new Location();
		Location class6 = new Location();
	}
	/**
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}
	/**
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 
	 * @return class1
	 */
	public Location getClass1() {
		return class1;
	}
	/**
	 * 
	 * @param class1
	 */
	public void setClass1(Location class1) {
		this.class1 = class1;
	}
	/**
	 * 
	 * @return class2
	 */
	public Location getClass2() {
		return class2;
	}
	/**
	 * 
	 * @param class2
	 */
	public void setClass2(Location class2) {
		this.class2 = class2;
	}
	/**
	 * 
	 * @return class3
	 */
	public Location getClass3() {
		return class3;
	}

	public void setClass3(Location class3) {
		this.class3 = class3;
	}
	/**
	 * 
	 * @return class4
	 */
	public Location getClass4() {
		return class4;
	}
	/**
	 * 
	 * @param class4
	 */
	public void setClass4(Location class4) {
		this.class4 = class4;
	}
	/**
	 * 
	 * @return class5
	 */
	public Location getClass5() {
		return class5;
	}
	/**
	 * 
	 * @param class5
	 */
	public void setClass5(Location class5) {
		this.class5 = class5;
	}
	/**
	 * 
	 * @return class6
	 */
	public Location getClass6() {
		return class6;
	}
	/**	
	 * 
	 * @param class6
	 */
	public void setClass6(Location class6) {
		this.class6 = class6;
	}
	//
}
