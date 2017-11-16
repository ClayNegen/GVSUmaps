package GUIpack;

public class Tuple<E, T> { 
	  
	private E x;
	
	private T y;
	  
	public Tuple(E x, T y) { 
		this.x = x; 
		this.y = y; 
	}

	public E getX() {
		return x;
	}

	public T getY() {
		return y;
	}
	
	public void setX(E x) {
		this.x = x;
	}
	
	public void setY(T y) {
		this.y = y;
	}
}