/**
 * This class represents a hash table implementation using an array of linked lists.
 * It implements the ListInterface, providing methods for adding words, calculating key comparisons,
 * reference changes, and statistics about the distinct and total words.
 *
 * @author Birkti Ayele
 */
public class ListH3 extends BaseList implements ListInterface {
	private final int ARRAY_SIZE = 256;
	ListInterface table[];

	public ListH3() {
		// Initialize the array of lists of 256 lists
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
		// First, convert the word to lowercase
		String lowerCaseWord = word.toLowerCase();
		// Calculate the hash value for the word
		int hashValue = stringToHash(lowerCaseWord);
		if (hashValue < 0) {
			System.out.println("testing");
		}
		// Add the word to the appropriate linked list (bucket)
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

	 /**
     * Prints additional statistics about the hash table, including minimum, maximum, and average word counts
     * in buckets.
     */
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

	private int stringToHash(String string) {
		long hash = 0;
		for (int i = 0; i < string.length(); i++) {
			hash = (hash * 31) + string.charAt(i);
		}
		long result = hash % 256;
		if (result < 0) {
			result *= -1;
		}
		return (int) result;
	}

	@Override
	public int height() {
		// TODO Auto-generated method stub
		return 0;
	}
}