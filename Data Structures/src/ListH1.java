/**
 * This class represents a hash table implementation using an array of linked lists.
 * It extends the BaseList class and provides a custom hashing function.
 *
 * @author Birkti Ayele
 */
public class ListH1 extends BaseList {
	private final int ARRAY_SIZE = 256;
	ListInterface table[];

	 /**
     * Constructor: Initializes the hash table by creating an array of linked lists.
     */
	public ListH1() {
		// Initialize the array of lists
		table = new ListInterface[ARRAY_SIZE];
		// Create a linked list in every element of the array
		for (int i = 0; i < ARRAY_SIZE; i++) {
			table[i] = new List1();
		}
	}

	/**
     * Adds a word to the hash table using the custom hashing function.
     *
     * @param word The word to be added.
     */
	@Override
	public void add(String word) {
		// Make the word lowercase and then hash
		String lowerCaseWord = word.toLowerCase();
		int hashValue = stringToHash(lowerCaseWord);
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
	 * Take the sum of the numeric values of each of the characters in the string
	 * mod 256
	 */
	private int stringToHash(String string) {
		int totSum = 0; // Add the value of every character in the string
		for (int i = 0; i < string.length(); i++) {
			char tempChar = string.charAt(i);
			int asciiValue = tempChar;
			totSum += asciiValue;
		}
		return totSum % ARRAY_SIZE;
	}

	@Override
	public int height() {
		// TODO Auto-generated method stub
		return 0;
	}
}