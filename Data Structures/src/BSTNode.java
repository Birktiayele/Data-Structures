/**
 * This class represents a node for a Binary Search Tree (BST). Each node
 * contains a String data element, an integer count for the frequency of the
 * data, and references to left and right child nodes.
 * 
 * @author Birkti Ayele
 */

public class BSTNode {
	private String data;        // Data stored in the node
    private int count;          // Frequency count of the data
    private BSTNode left;       // Reference to the left child node
    private BSTNode right;      // Reference to the right child node

	  /**
     * Constructor to create a new BSTNode with the given word as data.
     *
     * @param word The word to be stored in the node.
     */
	public BSTNode(String word) {
		data = word;
        count = 1;              // Initialize count to 1 for the first occurrence
        left = null;            // Initialize left child reference to null
        right = null;           // Initialize right child reference to null
	}
	
	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public BSTNode getLeft() {
		return left;
	}

	public void setLeft(BSTNode left) {
		this.left = left;
	}

	public BSTNode getRight() {
		return right;
	}

	public void setRight(BSTNode right) {
		this.right = right;
	}

}
