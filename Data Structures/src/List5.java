/**
 * SkipList implementation that extends the BaseList class and provides custom
 * methods for adding, searching, and managing nodes in a SkipList data structure.
 *
 * This class uses SkipList nodes and random coin tosses to efficiently store and
 * search for elements in multiple layers or lanes.
 *
 * @author Birkti Ayele
 */
import java.util.Random;

public class List5 extends BaseList implements ListInterface {
	private int height; // How many lanes we have in the SL (SkipList)
	private Random random; // For coin toss
	private int n;
	private SLNode head, tail; // First and last element of our fastest/top lane

	/**
     * Constructor: Initializes the SkipList.
     */
	public List5() {
		super();
		// Set the sentinels for the first lane
		head = new SLNode(SLNode.negInf);
		tail = new SLNode(SLNode.posInf);
		super.refChanges += 2;
		// Set the count/fre of the head and tail to 0, since they are sentinel nodes
		head.setCount(0);
		tail.setCount(0);
		// Link the head and tail
		head.setNext(tail);
		tail.setLeft(head);
		super.refChanges += 2;
		height = 1;
		n = 0;
		random = new Random();
		// Set the list equal to the sentinel of the slowest lane, which will have all
		// the nodes
		this.list = head;
		super.refChanges++;
	}

	@Override
	public void add(String word) {
		SLNode searchNode = search(word); // Call the search to try and find the node, or the neighbor node to insert
		// Check if the `word` is in the searchNode
		super.keyCompare++;
		if (searchNode.getData().compareTo(word) == 0) {
			searchNode.setCount(searchNode.getCount() + 1); // Update the count by +1
		} else {
			SLNode newNode = new SLNode(word); // The node to insert
			// Insert the `newNode` to the right of the `searchNode` and to the left of the
			// `searchNode.next`
			newNode.setLeft(searchNode);
			newNode.setNext(searchNode.getNext());
			SLNode searchNodeNext = (SLNode) searchNode.getNext();
			searchNode.setNext(newNode);
			searchNodeNext.setLeft(newNode);
			super.refChanges += 4;
			// Toss coin to see if we create a new level with the `newNode` in it
			int currLvl = 1; // We start at the slowest/lowest lane
			while (random.nextBoolean()) { // Keep tossing a coin, if it keeps landing head we insert a new lane
				// Insert the node in an upper level, if we are at the fastest lane then we
				// create a new lane
				if (currLvl >= height) {
					SLNode p = new SLNode(SLNode.negInf); // negative sentinel
					SLNode q = new SLNode(SLNode.posInf); // Positive sentinel
					// Set the sentinels' counts to 0
					p.setCount(0);
					q.setCount(0);
					// Connect the sentinels to each other and to the current head and tail
					p.setDown(head);
					head.setUp(p);
					super.refChanges += 2;
					q.setDown(tail);
					tail.setUp(q);
					super.refChanges += 2;
					p.setNext(q);
					q.setLeft(p);
					super.refChanges += 2;
					// Now make the new sentinels the head and tail
					head = p;
					tail = q;
					super.refChanges += 2;
					// Increase the size of the list
					height++;
					n = n + 1;
				}
				// Lets traverse to the left until we find a node that contains a lane above it
				SLNode currNode = searchNode;
				while (currNode.getUp() == null) { // If there is no lane above us, then keep looping
					currNode = currNode.getLeft(); // Keep moving left to find a node with a lane above it
					super.refChanges++;
				}
				currNode = currNode.getUp(); // Move to the upper lane
				currLvl++; // Update our current height
				// Now we need to make a new `newNode` that will be inserted in this upper lane
				SLNode upperNewNode = new SLNode(word);
				// Link the `upperNewNode` in this upper lane
				upperNewNode.setLeft(currNode);
				upperNewNode.setNext(currNode.getNext());
				upperNewNode.setDown(newNode);
				super.refChanges += 3;
				// Update the adjacent nodes in the upper level to connect to the `upperNewNode`
				SLNode nextNode = (SLNode) currNode.getNext();
				nextNode.setLeft(currNode);
				currNode.setNext(upperNewNode);
				newNode.setUp(upperNewNode);
				super.refChanges += 3;
			}
		}
	}

	/**
	 * Method looks the word inside the SkipList, it can return either: - The node
	 * that contains the word - The node that will become the left of the new node
	 * 
	 * @param word the word we are looking for
	 * @return node that contains the word or the node w/ value that will go before
	 *         the `word` node
	 */
	private SLNode search(String word) {
		// Keep traversing until the next/right node contains a string greater than `k`
		SLNode currNode = head;
		while (true) {
			super.keyCompare += 2; // Add +2 for the operation inside the while-loop
			while (currNode.getNext().getData().compareTo(SLNode.posInf) != 0
					&& currNode.getNext().getData().compareTo(word) <= 0) {
				currNode = (SLNode) currNode.getNext(); // Move to the next node, remaining in the SAME lane
				// super.keyCompare += 2;
			}
			// Check if there is no lower/slower lane, if we are already at the slowest lane
			// then return
			if (currNode.getDown() == null) {
				return currNode;
			}
			currNode = currNode.getDown(); // Else, there is a lower/slower lane, lets move there!
		}
	}

	/**
     * Returns the size of the SkipList.
     *
     * @return The size of the SkipList.
     */
	public int size() {
		return n;
	}

	/**
     * Returns the height of the SkipList.
     *
     * @return The height of the SkipList.
     */
	@Override
	public int height() {
		return height;
	}

	/**
     * Converts the SkipList to a string for printing.
     *
     * @return The string representation of the SkipList.
     */	
	@Override
	public String toString() {
		String returnString = "";
		// We start at the negative sentinel in order to traverse the entire
		SLNode currNode = (SLNode) super.list; // We start at the slowest lane
		returnString += " -  -  -  -  -  -  -  -  -  -\n";
		while (currNode != null) {
			if (isSentinelNode(currNode)) { // Check if we are in a sentinel node
				currNode = (SLNode) currNode.getNext(); // Then don't add the data to the `returnString`, move next
														// instead
			} else {
				// Here we know the currNode is not a sentinel, therefore it has an actual value
				// && it COULD have copies in other lanes
				String data = "";
				SLNode tempNode = currNode; // We'll use this to check if we can move to the right lanes
				while (tempNode != null) {
					data += tempNode.getData() + " ";
					tempNode = tempNode.getUp(); // Update pointer to the right
				}
				returnString += data + "\n";
				currNode = (SLNode) currNode.getNext();
			}
			// Here we finished traversing the SkipList
		}
		returnString += " +  +  +  +  +  +  +  +  +  + \n";
		return returnString;
	}

	private boolean isSentinelNode(SLNode node) {
		return node.getData().compareTo(SLNode.negInf) == 0 || node.getData().compareTo(SLNode.posInf) == 0;
	}

	/**
	 * We override the parent function since we are using SLNode instead of the
	 * super.list, therefore we use the `slowHead` variable to traverse the LL PLUS
	 * we don't want to count the pos/neg sentinel nodes
	 */
	@Override
	public int getDistinctWords() {
		// Same logic as the `BaseList.getDistinctWords()` but we update the count at
		// the end to discount for the sentinel nodes
		int count = 0;
		LLNode p = this.list;
		while (p != null) {
			count++;
			p = p.getNext();
		}
		return count - 2; // -2 since we don't want to count the sentinel nodes
	}
}
