package guiPack;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
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

/*******************************************************************************
 * A model component to handle files and write/read files for the 
 * UserController.
 * 
 * @author Douglas Wallin
 ******************************************************************************/
public class FileHandlerModel {
	/** Copy of GVSUMap (maybe temporary fix, maybe not). */
	private GVSUMap map = new GVSUMap();
	
	/** File to hold user accounts. */
	private File accountsFile;
	
    /** File to temporarily re-write to. */
	private File tempFile;
	
	/***************************************************************************
	 * Creates a FileHandlerModel to manipulate files storing user information.
	 **************************************************************************/
	public FileHandlerModel() {
		accountsFile = new File("src/accounts.txt");
		tempFile = new File("src/tempFile.txt");
	}
	
    /***************************************************************************
	 * A helper method that takes a String and returns a list of Strings using
	 * commmas as a delimiter.
	 * 
	 * @param line String: The string to broken down to tokens
	 * 
	 * @return List<String> The ouput in the format of Strings "tokens" in a
	 * list
	 * 
	 * @throws IOException
	 **************************************************************************/
	private List<String> getTokens(final String line) {		
		StringTokenizer tokenizer = new StringTokenizer(line);
		List<String> result = new LinkedList<String>();
		
		while (tokenizer.hasMoreTokens()) {
			result.add(tokenizer.nextToken(","));
		}
		return result;
	}
	
	/***************************************************************************
	 * Reconstructs a list of tokens back to a single string.
	 * 
	 * @param line List<String>: The current line as a list of strings(tokens).
	 * @return String: A detokenized version of the current line. In other
	 * words back to a single string
	 **************************************************************************/
	private String deTokenize(final List<String> line) {
		StringJoiner joiner = new StringJoiner(",");
		
		for (int i = 0; i < line.size(); i++) {
			joiner.add(line.get(i));
		}
		
		return joiner.toString();
	}
	
	/***************************************************************************
	 * Tries to load a user based on search parameters of a String(username) and
	 * another String(password). If it is able to find an user with matching
	 * name it will set the second boolean to true. It it is able to find an
	 * user with matching name and password it will also set the first boolean
	 * to be true, and create an user based on the information stored in the
	 * file
	 * 
	 * @param name
	 * @param pass
	 * @return
	 * @throws IOException
	 **************************************************************************/
	public Tuple<User, List<Boolean>> loadUser(final String name,
			final String pass) throws IOException {
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
	    		matchedUsername = true;
	    	}
	    	
	    	if (tokens.get(0).equals("|Password|") && matchedUsername ) {
	    		if (tokens.get(1).equals(pass)) {
		    		user = new User(tempName, tokens.get(1));
		    		foundUser = true;
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
