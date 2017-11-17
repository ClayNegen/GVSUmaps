package guiPack;

import java.io.IOException;

/*******************************************************************************
 * This controller will allow the gui to manipulate users and their information.
 * 
 * @author Douglas Wallin
 ******************************************************************************/
public class UserController {
	/** Copy of GVSUMap (maybe temporary fix, maybe not). */
	private GVSUMap map = new GVSUMap();
	
	/** File Handler to manage reading and writing of files. */
	public FileHandlerModel fileHandler = new FileHandlerModel();
	
	/** The current user of the application. */
	public User loadedUser;
	


	/***************************************************************************
	 * Creates an UserController object.
	 * 
	 * @throws IOException Exception
	 **************************************************************************/
	public UserController() throws IOException {
		loadedUser = null;
	}

	/***************************************************************************
	 * This method allows the GUI to add a class to an user's saved information
	 * in the file and also the list held in the user object.
	 * 
	 * @param bldName String: The name of the building where the class is
	 * @throws IOException Exception
	 **************************************************************************/
	public void addClass(final String bldName) throws IOException {
		loadedUser.addClass(map.getNode(bldName));
		fileHandler.writeMidFile(loadedUser.getUsername(), "|ClassList|",
				bldName);
	}
	
	/***************************************************************************
	 * Tries to create a new account with the username and password provided.
	 * If there already exists an user with 
	 * 
	 * @param name String: The username for the new account
	 * @param pass String: The password for the new account
	 * @throws IOException Exception
	 * 
	 * @return boolean: Whether or not the user was successfully created
	 **************************************************************************/
	public boolean newUser(final String name, final String pass)
			throws IOException {
		boolean flag = fileHandler.loadUser(name, pass).getY().get(1);
		if (!flag) {
			loadedUser = fileHandler.makeAccount(name, pass);
		}
		return !flag;
	}
	
	/***************************************************************************
	 * This method attempts to load an User from the accounts.txt file via
	 * the FileHandler. Returns one of two bools; the first is whether the user
	 * was fully matched, the second is whether the username only was matched.
	 * 
	 * @param name String: The username of the user desired to be loaded
	 * @param pass String: The password
	 * @param whichBool int: Which of the booleans to be returned
	 * 
	 * @return boolean: A status of the method call(dependent on the int
	 * whichBool)
	 * 
	 * @throws IOException Exception
	 **************************************************************************/
	public boolean loadUser(final String name, final String pass, 
			final int whichBool) throws IOException {
		if (fileHandler.loadUser(name, pass).getY().get(0)) {
			loadedUser = fileHandler.loadUser(name, pass).getX();
		}
		return fileHandler.loadUser(name, pass).getY().get(whichBool);
	}
}
