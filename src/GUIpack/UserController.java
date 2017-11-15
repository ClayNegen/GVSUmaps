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
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.StringJoiner;
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
		
		
		testController.writeMidFile("louisS", "|ClassList|", null);
		System.out.println("Got this far");
	}
	
	private List<String> getTokens(String line) throws IOException {		
		StringTokenizer tokenizer = new StringTokenizer(line);
		List<String> result = new LinkedList<String>();
		
		while (tokenizer.hasMoreTokens()) {
			result.add(tokenizer.nextToken(","));
		}
		return result;
	}
	
	private String deTokenize(List<String> line) {
		StringJoiner joiner = new StringJoiner(",");
		String result = "";
		
		for (int i = 0; i < line.size(); i++) {
			//if (i != (line.size() - 1)) {
				joiner.add(line.get(i));
			//}
			
		}
		return joiner.toString();
	}
	
	public void addClass(String bldName) throws IOException {
		loadedUser.addClass(map.getNode(bldName));
		writeMidFile(loadedUser.getUsername(), "|ClassList|", bldName);
	}
	
	public void writeToFile(String content) throws IOException {
		BufferedWriter writer = new BufferedWriter(
				new FileWriter(accountsFile));
		
		
		writer.write(content);
		writer.close();
	}
	
	public void writeMidFile(String name, String category, String content)
			throws IOException {
	    
		File tempFile = new File("tempFile");
		Writer out = new OutputStreamWriter(new FileOutputStream(tempFile));
	    Scanner scanner = new Scanner(new FileInputStream(accountsFile));
	    String newLine = System.getProperty("line.separator");
	    String line;
	    boolean foundUser = false;
	    List<String> tokens;
	    int numEntries;
	    
	    while (scanner.hasNextLine()) {
	    	line = scanner.nextLine();
	    	
	    	if (foundUser = true) {
		    	tokens = getTokens(line);
	    		if (category.equals(tokens.get(0))) {
	    			if (content != null) {
	    				tokens.add(content);
	    			}
	    		}
	    		line = deTokenize(tokens);
	    	}	        
	        out.write(line);
	        out.write(newLine);
	        
	        String searchKey = ("|Username|," + name);
	        
	        if(line.equals(searchKey)){
	            foundUser = true;
	        }
	    }

	    scanner.close();
	    out.close();
	    
	    
	    
	    //I dont Understand git//
	    
	    accountsFile = tempFile;
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
			writeToFile("|Username|," + name + "\n");
		}
		else {
			appendToFile("|Username|," + name + "\n");
		}

		appendToFile("|Password|," + pass + "\n");
		appendToFile("|ClassList|,");
		if (!(loadedUser.getClassList().isEmpty()))	{
			for (MapNode node: loadedUser.getClassList()) {
				String temp = node.getNodeInfo();
				appendToFile(temp + ",");
			}
		}
		appendToFile("\n");
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
