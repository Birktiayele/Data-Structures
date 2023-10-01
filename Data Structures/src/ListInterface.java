/**
 * This class represents a node in a linked list data structure. Each node contains
 * a string data value, an integer count representing the number of occurrences of
 * the data value, and a reference to the next node in the linked list.
 *
 * @author Birkti Ayele
 */

public interface ListInterface {
	public void add(String word); // add this word to the linked list

	public long getKeyCompare(); // Get the number of key comparisons

	public long getRefChanges(); // Get the number of reference changes

	public int getDistinctWords(); // Get the # of distinct words on the list

	public int getTotalWords(); // Get the total number of ALL words

	public int height(); // Get the height of the data structure (if applicable)

	public void printAdditionalStats(); // Print any additional statistics (if applicable)

}
