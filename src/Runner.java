package src;

import src.BTree.BTree;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Runner {


	//Arguments you program should expect:
	//1. t (the value for BTree.BTree).
	//2. m (the size of expected hash table with chaining).
	//Note that the files used (friends.txt,messages.txt,spam_words.txt) 
	//should be located in the same location as your src folder.
	
	public static void main(String[] args) {
		
		//Create the btree using the t value and the path to the friends file.
		BTree btree = createTree(args[0]);

		//Get the BFS representation of the btree
		String treeLayout = btree.toString();
		
		// Create a Messages.Messages object based on the messages file.

        // TODO: romove comment
        //Messages messages = createArrayOfMessages();

		//For each message, create a hash table based on the words in the message.


        // TODO: uncomment
        //messages.createHashTables(args[1]);


		//Find the spam messages- Use the path of the spam_words file.
		//	(The Messages.Messages object should use the file path to create the Spam.Spam array.
		//	Then, it will determine for each message if it is spam, using the Spam.Spam array,
		//	the btree and the hash table of the message).


        // TODO: uncomment this
        //String spamMessages = messages.findSpams(System.getProperty("user.dir")+"/spam_words.txt", btree);


		//create a file with the program's expected output

        // TODO: uncomment this
        //createOutputFile(treeLayout, spamMessages, System.getProperty("user.dir")+"/output.txt");
			    
	}


	// Create a Messages.Messages object based on the messages file.
	// Creates a Messages.Messages object and updates its' Messages.Message array using the messages file.
//tODO: uncomment
//	private static Messages createArrayOfMessages() {
//		Messages messages = new Messages();
//		messages.generateMessages(System.getProperty("user.dir")+"/messages.txt");
//		return messages;
//	}



	// Creates BTree.BTree using the t value, and the friends file.
	// Inserts the friends into the tree.
	private static BTree createTree(String tVal) {
		BTree btree = new BTree(tVal); 
		btree.createFullTree(System.getProperty("user.dir")+"/friends.txt");
		return btree;
	}


	//Creates a file with the program's expected output
	private static void createOutputFile(String treeLayout, String spamMessages, String pathToOutput) {
		Path path = Paths.get(pathToOutput);
		StringBuilder sb = new StringBuilder();
		sb.append(treeLayout).append(System.lineSeparator()).append(spamMessages);
	    byte[] strToBytes = sb.toString().getBytes();
	    try {
			Files.write(path, strToBytes);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}
