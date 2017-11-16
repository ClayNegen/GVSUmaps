package GUIpack;

import java.io.BufferedReader;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.StringJoiner;
import java.util.StringTokenizer;
import static java.nio.file.StandardCopyOption.*;

public class UserController {
	/** Copy of GVSUMap (maybe temporary fix, maybe not) */
	private GVSUMap map = new GVSUMap();
	
	/** File Handler to manage reading and writing of files */
	public FileHandler fileHandler = new FileHandler();
	
	/** The current user of the application */
	public User loadedUser;
	
	public UserController() throws IOException {
		loadedUser = null;
	}
	
	public static void main(String args[]) throws IOException {
		UserController testController = new UserController();
			
		testController.loadUser("louisS", "hi1", 0);
			
		System.out.println("Got this far");
	}
	
	public void addClass(String bldName) throws IOException {
		loadedUser.addClass(map.getNode(bldName));
		fileHandler.writeMidFile(loadedUser.getUsername(), "|ClassList|", bldName);
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
