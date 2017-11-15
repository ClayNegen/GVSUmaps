package GUIpack;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class UserController {
	/** The current user of the application */
	public User loadedUser;
	
	/** File to hold user accounts */
	private File accountsFile = new File("src/accounts.txt");
	
	/** Copy of GVSUMap (maybe temporary fix, maybe not) */
	private GVSUMap map = new GVSUMap();
	
	
	public UserController() {
		loadedUser = null;
	}
	
	public static void main(String args[]) throws IOException {
		UserController testController = new UserController();
		
		testController.clearFile();
		testController.makeAccount("louisS", "hi1");
		testController.addClass("William Kill Patrick LC");
		testController.addClass("Edward J. Frey LC");
		testController.addClass("Robert Kliener Commons");
		
		testController.getTokens("doesnt matter");
		
		
		
		System.out.println("Got this far");
	}
	
	public void getTokens(String name) throws IOException {		
		final String EoL = System.getProperty("line.separator");
		List<String> lines = Files.readAllLines(Paths.get("./src/accounts.txt"),
		        Charset.defaultCharset());

		StringBuilder sb = new StringBuilder();
		for (String line : lines) {
		    sb.append(line).append(EoL);
		}
		final String content = sb.toString();
		
		StringTokenizer tokenizer = new StringTokenizer(content);
		
		System.out.println(content);
	}
	
	public void addClass(String bldName) {
		loadedUser.addClass(map.getNode(bldName));
		
	}
	
	public void writeToFile(String content) throws IOException {
		BufferedWriter writer = new BufferedWriter(
				new FileWriter(accountsFile));
		
		
		writer.write(content);
		writer.close();
	}
	
	public void writeMidFile(String name, String content)
			throws IOException {
	    
		File tempFile = new File("tempFile");
		Writer out = new OutputStreamWriter(new FileOutputStream(tempFile));
	    Scanner scanner = new Scanner(new FileInputStream(accountsFile));
	    String newLine = System.getProperty("line.separator");
	    String line;
	    
	    while(scanner.hasNextLine()){
	        line = scanner.nextLine();

	        out.write(line);
	        out.write(newLine);
	        
	        String searchKey = ("Username: " + name); 
	        
	        if(line.equals(searchKey)){
	            out.write();
	            out.write(newLine);
	        }
	    }

	    scanner.close();
	    out.close();
	}
	
	public void clearFile() throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(
				accountsFile));
		
		writer.write("");
		writer.close();
	}
	
	public void appendToFile(String content) throws IOException {
		BufferedWriter writer = new BufferedWriter(
				new FileWriter(accountsFile, true));
		
		writer.append(content);
		writer.close();
	}
	
	public void makeAccount(String name, String pass) throws IOException {
		loadedUser = new User(name, pass);
		
		if (isEmpty()) {
			writeToFile("Username: " + name + "\n");
		}
		else {
			appendToFile(name + ",");
		}

		appendToFile(pass + ",\n");
		appendToFile("classList,");
		if (!(loadedUser.getClassList().isEmpty()))	
			for (MapNode node: loadedUser.getClassList()) {
				String temp = node.getNodeInfo();
				appendToFile(temp + ",");
			}

	}
	
	private boolean isEmpty() throws IOException {
		BufferedReader br = new BufferedReader(
				new FileReader("src/accounts.txt"));
		if (br.readLine() == null) {
			return true;
		}
		return false;
	}
}
