/**
 * This program performs text analysis on a given input file using various data structures.
 * It reads the input text, processes it to extract words, and stores them in different data structures.
 * The program measures the execution time, vocabulary size, total word count, key comparisons, reference changes,
 * and height (for certain data structures) for each data structure used.
 *
 * The program supports multiple data structures for text analysis, including unsorted lists,
 * sorted lists, self-adjusting lists, skip lists, and binary search trees.
 *
 * Input: The program expects the name of the input text file as a command-line argument.
 * If no file name is provided, it uses a default file named "shakespeare.txt."
 *
 * Output: The program prints a summary of the analysis results for each data structure.
 *
 * @author Birkti Ayele
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * The main class for text analysis.
 */

public class Main {

	public static void main(String[] args) {
		String fileName = "/Users/user/Downloads/shakespeare.txt";
		fileName = (args.length > 0) ? args[0] : fileName;
		// Initialize an array of ListInterface objects to hold different data structures
		ListInterface[] Lists = new ListInterface[10]; 
		Lists[0] = new List1(); // Unsorted, insertions at beginning, no self-optimization
		Lists[1] = new List2(); // Sorted linked list
		Lists[2] = new List3(); // Unsorted, heavy-handed self-adjusting (moves repeated word to the front of the list)
		Lists[3] = new List4(); // Unsorted, conservative self-adjusting (moves repeated word one position towards front of list)
		Lists[4] = new List2a(); // Extension of List2 with an improved search method
		Lists[5] = new List5(); // SkipList
		Lists[6] = new ListH1(); // ADT with sum of values
		Lists[7] = new ListH2(); // ADT with hash of just first letter
		Lists[8] = new ListH3(); // ADT with given hash function, similar to java's
		Lists[9] = new BST(); // Binary search tree


		long[] elapsedTimes = new long[10]; // Save the elapsed time per each list
		
		// Loop through each data structure and perform operations
		for (int i = 0; i < Lists.length; i++) {
			long start = System.currentTimeMillis();
			Scanner scan;
			try {
				scan = new Scanner(new File(fileName)); // made a file try
				// scan across each line using scanner
				while (scan.hasNextLine()) {
					Scanner lineScanner = new Scanner(scan.nextLine());
					// String fileName = ""; // declaring a string variable while
					while (lineScanner.hasNext()) {
						String word = lineScanner.next();
						String cleanWord = trimTheWord(word);
						if (!cleanWord.isEmpty()) {
							cleanWord = cleanWord.toLowerCase();
							Lists[i].add(cleanWord);
						}
					}
				}
				scan.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			// We get here after reading the entire file, lets reset our stop watch for elapse time
			long end = System.currentTimeMillis();
			long elapseTime = end - start;
			elapsedTimes[i] = elapseTime;
		}
		String[] ListNames = { "Unsorted", "Sorted", "Self-Adj (Front)", "Self-Adj (By One)", "Sorted (Improved)",
				"SkipList", "HASH1", "HASH2", "HASH3", "BST" };

		System.out.println(" #   List Name\t\t Run Time\tVocabulary \tTotal Words\t Key Comp \tRef Chgs \t h");
		for (int i = 0; i < ListNames.length; i++) {

			long elapse = elapsedTimes[i];
			int height = Lists[i].height();
			int distinctWords = Lists[i].getDistinctWords();
			int totalWords = Lists[i].getTotalWords();
			long refChanges = Lists[i].getRefChanges();
			long keyCompares = Lists[i].getKeyCompare();
			System.out.println(
					"-- -----------------\t --------\t----------\t----------- \t------------\t ------------\t ---");
			System.out.printf(" %1d %-18s %8.3f %13d %20d %19d %10d %10d %n", i + 1, ListNames[i], elapse / 1000.0,
					distinctWords, totalWords, keyCompares, refChanges, height);

		}
		for (int i = 0; i < ListNames.length; i++) {
			ListInterface list = Lists[i];
			System.out.println(ListNames[i] + " additional data");
			list.printAdditionalStats();
			System.out.println("\n");
		}
	}

	public static String trimTheWord(String word) {
		 // Clean the word by removing leading and trailing punctuation characters

		char intialChar = word.charAt(0); // r
		char finalChar = word.charAt(word.length() - 1); //
		String punctuations = "!@#$%^&*()_+-=[]\\{}|;':\"`~,./<>?\r\n";
		boolean isIntialCharPunct = punctuations.indexOf(intialChar) != -1;
		boolean isFinalCharPunct = punctuations.indexOf(finalChar) != -1;

		while (!word.isEmpty() && (isIntialCharPunct || isFinalCharPunct)) {
			// We get here if the word needs to be trimmed

			if (isIntialCharPunct) {
				word = word.substring(1); //
			}
			if (!word.isEmpty() && isFinalCharPunct) {
				word = word.substring(0, word.length() - 1);
			}
			
			// Update variables to check if the word still has punctuation
			if (!word.isEmpty()) {

				intialChar = word.charAt(0); // r
				finalChar = word.charAt(word.length() - 1); //
				isIntialCharPunct = punctuations.indexOf(intialChar) != -1;
				isFinalCharPunct = punctuations.indexOf(finalChar) != -1;
			}
		}
		return word;
	}
}
