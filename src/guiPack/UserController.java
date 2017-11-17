package guiPack;

import java.io.IOException;

public class UserController {
	/** Copy of GVSUMap (maybe temporary fix, maybe not) */
	private GVSUMap map = new GVSUMap();
	
	/** File Handler to manage reading and writing of files */
	public FileHandlerModel fileHandler = new FileHandlerModel();
	
	/** The current user of the application */
	public User loadedUser;
	
	public UserController() throws IOException {
		loadedUser = null;
	}
	
	public static void main(String args[]) throws IOException {
		UserController testController = new UserController();
			
		System.out.println("Got this far");
	}
	
	public void addClass(String bldName) throws IOException {
		loadedUser.addClass(map.getNode(bldName));
		fileHandler.writeMidFile(loadedUser.getUsername(), "|ClassList|",
				bldName);
	}
	
	public void newUser(String name, String pass) throws IOException {
		if (!fileHandler.loadUser(name, pass).getY().get(1)) {
			loadedUser = fileHandler.makeAccount(name, pass);
		}
	}
	
	public boolean loadUser(String name, String pass, int bool) throws IOException {
		if (fileHandler.loadUser(name, pass).getY().get(0)) {
			loadedUser = fileHandler.loadUser(name, pass).getX();
		}
		return fileHandler.loadUser(name, pass).getY().get(bool);
	}
}
