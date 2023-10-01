/**
 * This class represents a hash table implementation using an array of linked lists.
 * It implements the ListInterface, providing methods for adding words, calculating key comparisons,
 * reference changes, and statistics about the distinct and total words.
 *
 * @author Birkti Ayele
 */
public class ListH2 extends BaseList implements ListInterface {
	private final int ARRAY_SIZE = 256;
	ListInterface table[];

	/**
     * Constructor: Initializes the hash table by creating an array of linked lists.
     */
	public ListH2() {
		// Initialize the array of lists
		table = new ListInterface[ARRAY_SIZE];
		// Create a linked list in every element of the array
		for (int i = 0; i < ARRAY_SIZE; i++) {
			table[i] = new List1();
		}
	}

	/**
     * Adds a word to the hash table.
     * 
     * @param word The word to be added.
     */
	@Override
	public void add(String word) {
		// Calculate the hash value for the word based on the ASCII value of its first character
		int hashValue = stringToHash(word);
		// Add the word to the appropriate linked list
		table[hashValue].add(word);
	}

	/**
     * Calculates the total number of key comparisons across all buckets.
     * 
     * @return The total number of key comparisons.
     */
	@Override
	public long getKeyCompare() {
		long totKeyComparison = 0;
		for (ListInterface list : table) {
			totKeyComparison += list.getKeyCompare();
		}
		return totKeyComparison;
	}
	/**
     * Calculates the total number of reference changes across all buckets.
     * 
     * @return The total number of reference changes.
     */
	@Override
	public long getRefChanges() {
		long totRefChanges = 0;
		for (ListInterface list : table) {
			totRefChanges += list.getRefChanges();
		}
		return totRefChanges;
	}

	/**
     * Calculates the total number of distinct words across all buckets.
     * 
     * @return The total number of distinct words.
     */
	@Override
	public int getDistinctWords() {
		int totDistinctWords = 0;
		for (ListInterface list : table) {
			totDistinctWords += list.getDistinctWords();
		}
		return totDistinctWords;
	}

	/**
     * Calculates the total number of words (including duplicates) across all buckets.
     * 
     * @return The total number of words.
     */
	@Override
	public int getTotalWords() {
		int totWords = 0;
		for (ListInterface list : table) {
			totWords += list.getTotalWords();
		}
		return totWords;
	}

	@Override
	public void printAdditionalStats() {
		// Min, max, average
		double totWordsInTable = 0;
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		// Iterate through every list in our table, aka go through 0-255 lists
		for (int i = 0; i < ARRAY_SIZE; i++) {
			ListInterface list = table[i];
			int tempTotWordsInList = list.getTotalWords(); // Grab the total words in this list
			totWordsInTable += tempTotWordsInList;
			// Update min/max
			min = (tempTotWordsInList < min) ? tempTotWordsInList : min;
			max = (tempTotWordsInList > max) ? tempTotWordsInList : max;
		}
		double average = totWordsInTable / ARRAY_SIZE;
		System.out.println("Min: " + min);
		System.out.println("Max: " + max);
		System.out.println("Average: " + average);
	}

	/**
     * Converts a string to a hash value based on the ASCII value of the first character.
     * 
     * @param string The input string.
     * @return The hash value.
     */
	private int stringToHash(String string) {
		// Grab the ASCII value of the first character in the string
		char firstChar = string.charAt(0);
		return firstChar;
	}

	@Override
	public int height() {
		// TODO Auto-generated method stub
		return 0;
	}
}