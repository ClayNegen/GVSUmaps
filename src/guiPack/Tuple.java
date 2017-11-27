package guiPack;

/*******************************************************************************
 * @author Douglas Wallin
 *
 * @param <E> The first type in the tuple
 * @param <T> The second type in the tuple
 ******************************************************************************/
public class Tuple<E, T> { 
	/** The first element of the tuple. */
	private E element1;
	
	/** The second element of the tuple. */
	private T element2;
	  
	/***************************************************************************
	 * Creates a tuple of types <E,T>.
	 * 
	 * @param first x E: Instantiates the first element of the tuple of type E
	 * @param second T: Instantiates the second element of the tuple of type T
	 **************************************************************************/
	public Tuple(final E first, final T second) { 
		element1 = first;
		element2 = second; 
	}

	/***************************************************************************
	 * @return E: Returns the first element of the tuple of type E
	 **************************************************************************/
	public E getElement1() {
		return element1;
	}

	/***************************************************************************
	 * @return T: Returns the second element of the tuple of type T
	 **************************************************************************/
	public T getElemnt2() {
		return element2;
	}
	
	/***************************************************************************
	 * Sets the first element of the tuple to the input.
	 * 
	 * @param input E: The data to replace element 1 of the tuple
	 **************************************************************************/
	public void setX(final E input) {
		element1 = input;
	}
	
	/***************************************************************************
	 * Sets the second element of the tuple to the input.
	 * 
	 * @param input T: The data to replace element 2 of the tuple
	 **************************************************************************/
	public void setY(final T input) {
		element2 = input;
	}
}