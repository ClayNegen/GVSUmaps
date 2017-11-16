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
import java.nio.channels.FileChannel;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.StringJoiner;
import java.util.StringTokenizer;

public class FileHandler {
	/** Copy of GVSUMap (maybe temporary fix, maybe not) */
	private GVSUMap map = new GVSUMap();
	
	/** File to hold user accounts */
	private File accountsFile = new File("src/accounts.txt");
	
	/** File to temporarily re-write to */
	private File tempFile = new File("src/tempFile.txt");
	
	public FileHandler() throws IOException {
		//clearFile(accountsFile);
	}
	
	public static void main(String args[]) throws IOException {
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
			joiner.add(line.get(i));
		}
		
		return joiner.toString();
	}
	
	public Tuple<User, List<Boolean>> loadUser(String name, String pass) throws IOException{
		User user = null;
		String tempName = null;
	    Tuple<User, List<Boolean>> result;
		Scanner scanner = new Scanner(new FileInputStream(accountsFile));
	    String line;
	    List<String> tokens;
	    boolean foundUser = false;
	    boolean matchedUsername = false;
	    
	    while (scanner.hasNextLine()) {
	    	line = scanner.nextLine();
	    	tokens = getTokens(line);
	    	if (tokens.get(0).equals("|Username|") &&
	    			tokens.get(1).equals(name)) {
	    		tempName = tokens.get(1);
	    		foundUser = true;
	    		matchedUsername = true;
	    	}
	    	
	    	if (tokens.get(0).equals("|Password|") && foundUser ) {
	    		if (tokens.get(1).equals(pass)) {
		    		user = new User(tempName, tokens.get(1));
	    		}
	    		else {
	    			foundUser = false;
	    		}
	    	}
	    	
 	    	if (tokens.get(0).equals("|ClassList|") && foundUser) {
	    		for (int i = 1; i < tokens.size(); i++) {
	    			user.addClass(map.getNode(tokens.get(i)));
	    		}
	    	}
	    	
	    }
	    List<Boolean> bools = new LinkedList<Boolean>();
	    
	    bools.add(foundUser);
	    bools.add(matchedUsername);
	    
	    result = new Tuple<User, List<Boolean>>(user, bools);
	    
	    scanner.close();
	    return result;
	}
	
	public void writeMidFile(String name, String category, String content)
			throws IOException {
	    clearFile(tempFile);
		Scanner scanner = new Scanner(new FileInputStream(accountsFile));
	    String newLine = System.getProperty("line.separator");
	    String line;
	    boolean foundUser = false;
	    List<String> tokens;
	    
	    
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
	        appendToFile(line, tempFile);
	        appendToFile(newLine, tempFile);
	        
	        String searchKey = ("|Username|," + name);
	        
	        if(line.equals(searchKey)){
	            foundUser = true;
	        }
	    }

	    FileInputStream fileInput = new FileInputStream(tempFile);
	    FileOutputStream fileOutput = new FileOutputStream(accountsFile);
	    FileChannel src = fileInput.getChannel();
	    FileChannel dest = fileOutput.getChannel();
	    dest.transferFrom(src, 0, src.size());
	    
	    scanner.close();
	    fileOutput.close();
	    fileInput.close();
	    src.close();
	    dest.close();
	}
	
	public void clearFile(File file) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		
		writer.write("");
		writer.close();
	}
	
	private void writeToFile(String content, File file) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		
		
		writer.write(content);
		writer.close();
	}
	
	private void appendToFile(String content, File file) throws IOException {
		BufferedWriter writer = new BufferedWriter(
				new FileWriter(file, true));
		
		writer.append(content);
		writer.close();
	}
	
	public User makeAccount(String name, String pass) throws IOException {
		User user = new User(name, pass);
		
		if (isEmpty()) {
			writeToFile("|Username|," + name + "\n", accountsFile);
		}
		else {
			appendToFile("|Username|," + name + "\n", accountsFile);
		}

		appendToFile("|Password|," + pass + "\n", accountsFile);
		appendToFile("|ClassList|,", accountsFile);

//		pretty sure this check is unneccesary because a fresh account
//		shouldnt ever have classes right?
//		
//		if (!(loadedUser.getClassList().isEmpty()))	{
//			for (MapNode node: loadedUser.getClassList()) {
//				String temp = node.getNodeInfo();
//				appendToFile(temp + ",", accountsFile);
//			}
//		}
		appendToFile("\n", accountsFile);
		
		return user;
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
