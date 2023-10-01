/**
 * This class represents a custom linked list implementation, List1. It extends
 * the BaseList class and provides methods for adding elements to the list.
 * 
 * Note: The height method always returns 0 in this implementation.
 * 
 * @author Birkti Ayele
 */
public class List1 extends BaseList implements ListInterface {
	public List1() {
		super();
	}

	@Override
	public int height() {
		return 0;
	}

	@Override
	public void add(String word) {
		// First we check if there is anything in the list
		if (super.list == null) {
			super.list = new LLNode(word); // Insert the first node of this list at the head
			super.refChanges++; // Update the total references since we swapped the head from null to word
		} else {
			// Perform normal search, if we find the node then we just update the count, if
			// not insert at head
			LLNode currNode = super.list;
			while (currNode != null) {
				int compareResult = currNode.getData().compareTo(word);
				super.keyCompare++; // Update for string comparison
				if (compareResult == 0) { // If we found the `word` in the currNode
					int freq = currNode.getCount() + 1;
					currNode.setCount(freq); // Update the count + 1
					return; // Exit since there is nothing else to do
				}
				// We get here if the currNode didn't contain the `word`
				currNode = currNode.getNext(); // Move to the next node
			}
			// We get here if we didn't find the `word` in the entire LL
			LLNode newHead = new LLNode(word); // Create a new node with the `word
			newHead.setNext(super.list); // Connect the new node to the current head
			super.list = newHead; // Make the new node the head
			super.refChanges += 2; // Setting a new head and connecting the new node to the old head
		}
	}

}
