/**
 * This class represents a custom linked list implementation, List2. It extends
 * the BaseList class and provides methods for adding elements to the list.
 * 
 * Note: The height method always returns 0 in this implementation.
 * 
 * @author Birkti Ayele
 */
public class List2 extends BaseList implements ListInterface {
	public List2() {
		super();
	}

	@Override
	public int height() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public void add(String word) {
		// First, we check if there is anything in the list
		if (super.list == null) {
			super.list = new LLNode(word); // Insert the first node of this list at the head
			super.refChanges++;
			return; // Exit since we are done here
		}
		// We get here if the list is not empty
		
		super.keyCompare++;
		int headCompareResult = super.list.getData().compareTo(word);
		if (headCompareResult > 0) { // Check if we insert before the head
			LLNode newHead = new LLNode(word); // Create new word to insert
			newHead.setNext(super.list); // Connect the newNode before the current head
			super.list = newHead; // Make the newNode the new head
			super.refChanges += 2; // Update total references since we swapped the head
		} else if (headCompareResult == 0) { // The current head contains the `word`
			// Here we just update the freq/count of the head
			int freq = super.list.getCount() + 1;
			super.list.setCount(freq);
		} else {
			LLNode currNode = super.list.getNext(), prevNode = super.list;
			// Keep looping until we either insert/update or we fall off the list
			while (currNode != null) {
				int compareResult = currNode.getData().compareTo(word);
				super.keyCompare++;
				if (compareResult > 0) { // If the `word` goes before this node
					LLNode newNode = new LLNode(word); // Create new node to insert between prevNode and currNode
					prevNode.setNext(newNode); // connect the prevNode to the new node
					newNode.setNext(currNode); // set the new Node to the currNode
					super.refChanges += 2;
					return; // We can exit since we are done inserting between nodes
				}
				if (compareResult == 0) { // Get the current frequency of this word
					int freq = currNode.getCount();
					freq++; // Update +1 for this instance
					currNode.setCount(freq); // Insert the updated frequency
					return; // We are done, we can exit `add`
				}
				// Update pointers
				prevNode = currNode;
				currNode = currNode.getNext(); // move through the LL
			}
			// We get here if we never found the `word` and we didn't insert halfway
			// Therefore we need to insert the `word` at the end of the LL
			prevNode.setNext(new LLNode(word));
			super.refChanges++;
		}
	}

}
