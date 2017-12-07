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
	 * @return String: A de-tokenized version of the current line. In other
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
	 * @param name String: The username of the target user
	 * @param pass String:
	 * @return Tuple<User, List<Boolean>>: A tuple object containing an user if
	 * one was successfully loaded. And also a list of two booleans describing
	 * ton what degree the loadUser call succeeded
	 * 
	 * @throws IOException  Exception
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
	    boolean done = false;
	    
	    while (scanner.hasNextLine()) {
	    	line = scanner.nextLine();
	    	tokens = getTokens(line);
	    	if (tokens.get(0).equals("|Username|") 
	    			&& tokens.get(1).equals(name)) {
	    		tempName = tokens.get(1);
	    		matchedUsername = true;
	    	}
	    	
	    	if (tokens.get(0).equals("|Password|") && matchedUsername) {
	    		if (tokens.get(1).equals(pass)) {
		    		user = new User(tempName);
		    		foundUser = true;
	    		}
	    	}
	    	
 	    	if (tokens.get(0).equals("|ClassList|") && foundUser && !done) {
	    		for (int i = 1; i < tokens.size(); i++) {
	    			user.addClass(map.getNode(tokens.get(i)));
	    			done = true;
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
	
	/***************************************************************************
	 * Method to insert information into an users saved profile.
	 * 
	 * @param name String: The username of the user to have information added
	 * @param category String: A key to tell the scanner where to put the
	 * information
	 * @param content String: The info to be added
	 * @throws IOException Exception
	 **************************************************************************/
	public void writeMidFile(final String name, final String category,
			final String content) throws IOException {
	    clearFile(tempFile);
		Scanner scanner = new Scanner(new FileInputStream(accountsFile));
	    String newLine = System.getProperty("line.separator");
	    String line;
	    boolean foundUser = false;
	    boolean done = false;
	    List<String> tokens;
	    
	    
	    while (scanner.hasNextLine()) {
	    	line = scanner.nextLine();
	    	
	    	if (foundUser && !done) {
		    	tokens = getTokens(line);
	    		if (category.equals(tokens.get(0))) {
	    			if (content != null) {
	    				tokens.add(content);
	    				done = true;
	    			}
	    		}
	    		line = deTokenize(tokens);
	    	}	 
	        appendToFile(line, tempFile);
	        appendToFile(newLine, tempFile);
	        
	        String searchKey = ("|Username|," + name);
	        
	        if (line.equals(searchKey)) {
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
	
	/***************************************************************************
	 * Method to reset an users class list. Uses a similar methodology to the
	 * writeMidFile method. But instead of adding content it deletes all saved
	 * classes
	 * 
	 * @param name String: The username of the user to have information added
	 *
	 * @throws IOException Exception
	 **************************************************************************/
	public void clearClassList(final String name) throws IOException {
	    clearFile(tempFile);
		Scanner scanner = new Scanner(new FileInputStream(accountsFile));
	    String newLine = System.getProperty("line.separator");
	    String line;
	    boolean foundUser = false;
	    boolean done = false;
	    List<String> tokens;


	    while (scanner.hasNextLine()) {
	    	line = scanner.nextLine();

	    	if (foundUser && !done) {
	    		tokens = getTokens(line);
	    		
	    		if (tokens.get(0).equals("|ClassList|")) {
	    			tokens = tokens.subList(0, 1);
	    			done = true;
	    			line = deTokenize(tokens) + ",";
	    		} else {
	    			line = deTokenize(tokens);
	    		}
	    	}	 
	    	appendToFile(line, tempFile);
	    	appendToFile(newLine, tempFile);

	    	String searchKey = ("|Username|," + name);
	        
	        if (line.equals(searchKey)) {
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
	
	
	/***************************************************************************
	 * This method resets the passed file to be empty. Should only be called
	 * when the user decides to reset the accounts folder.
	 * 
	 * @param file File: The file to be reset
	 * @throws IOException Exception
	 **************************************************************************/
	public void clearFile(final File file) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		
		writer.write("");
		writer.close();
	}
	
	/***************************************************************************
	 * This method writes some String to an empty file. Sister method to
	 * appendToFile()
	 * 
	 * @param content String: The content to be added to the user's information
	 * in the file
	 * @param file File: The file to be written to
	 * @throws IOException Exception
	 **************************************************************************/
	private void writeToFile(final String content, final File file) 
			throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		
		
		writer.write(content);
		writer.close();
	}
	
	/***************************************************************************
	 * This method adds a string to a file. The only difference between
	 * appendToFile and writeToFile is that appendToFile writes to a non-empty
	 * file.
	 * 
	 * @param content String: The content to be written to the file
	 * @param file File: The file to be written to
	 * @throws IOException Exception
	 **************************************************************************/
	private void appendToFile(final String content, final File file)
			throws IOException {
		BufferedWriter writer = new BufferedWriter(
				new FileWriter(file, true));
		
		writer.append(content);
		writer.close();
	}
	
	/***************************************************************************
	 * Creates an account for an user and records the account information in
	 * accounts.txt.
	 * 
	 * @param name String: The user's username
	 * @param pass String: The user's password
	 * @return User: An instance of the user class to represent the new account
	 * (makes the new account the logged in user)
	 * @throws IOException Exception
	 **************************************************************************/
	public User makeAccount(final String name, final String pass) 
			throws IOException {
		User user = new User(name);
		
		if (isEmpty()) {
			writeToFile("|Username|," + name + "\n", accountsFile);
		} else {
			appendToFile("|Username|," + name + "\n", accountsFile);
		}

		appendToFile("|Password|," + pass + "\n", accountsFile);
		appendToFile("|ClassList|,", accountsFile);
		appendToFile("\n", accountsFile);
		
		return user;
	}
	
	/***************************************************************************
	 * Checks to see if the accountsFile is empty.
	 * 
	 * @return Boolean: Whether the file is empty or not
	 * @throws IOException Exception
	 **************************************************************************/
	private boolean isEmpty() throws IOException {
		BufferedReader reader = new BufferedReader(
				new FileReader("src/accounts.txt"));
		if (reader.readLine() == null) {
			reader.close();
			return true;
		}
		reader.close();
		return false;
	}
}
