package guiPack;

/*******************************************************************************
 * @author Douglas Wallin
 *
 * @param <E> The first type in the tuple
 * @param <T> The second type in the tuple
 ******************************************************************************/
public class Tuple<E, T> { 
	/** The first element of the tuple. */
	private E x;
	
	/** The second element of the tuple. */
	private T y;
	  
	/***************************************************************************
	 * Creates a tuple of types <E,T>.
	 * 
	 * @param x E: Instantiates the first element of the tuple of type E
	 * @param y T: Instantiates the second element of the tuple of type T
	 **************************************************************************/
	public Tuple(final E x, final T y) { 
		this.x = x; 
		this.y = y; 
	}

	/***************************************************************************
	 * @return E: Returns the first element of the tuple of type E
	 **************************************************************************/
	public E getX() {
		return x;
	}

	/***************************************************************************
	 * @return T: Returns the second element of the tuple of type T
	 **************************************************************************/
	public T getY() {
		return y;
	}
	
	/***************************************************************************
	 * @param x
	 **************************************************************************/
	public void setX(E x) {
		this.x = x;
	}
	
	public void setY(T y) {
		this.y = y;
	}
}