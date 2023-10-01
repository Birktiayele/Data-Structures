/**
 * This class represents a custom linked list implementation, List3. It extends
 * the BaseList class and provides methods for adding elements to the list.
 * 
 * Note: The height method always returns 0 in this implementation.
 * 
 * @author Birkti Ayele
 */
public class List3 extends BaseList implements ListInterface {

	@Override
	public int height() {
		return 0;
	}

	@Override
	public void add(String word) {
		// First, we check if there is anything in the list
		if (super.list == null) {
			super.list = new LLNode(word); // Insert the first node of this list at the head
			super.refChanges++;

		} else {
			// Check if the first node, aka the head, has the word
			super.keyCompare++;
			if (super.list.getData().compareTo(word) == 0) {
				int freq = super.list.getCount(); // Get the current frequency of this word
				freq++; // Update +1 for this instance
				super.list.setCount(freq); // Insert the updated frequency
				return; // Exit since we are done here
			}
			LLNode currNode = super.list.getNext(), prevNode = super.list;
			while (currNode != null) {
				super.keyCompare++;
				if (currNode.getData().compareTo(word) == 0) { // Check if we found the word in the LL
					prevNode.setNext(currNode.getNext()); // Make the preNode the currNode next
					currNode.setNext(super.list); // Make currNode super.list(head)
					super.list = currNode; // Make currNode the head
					int freq = currNode.getCount(); // Get the current frequency of this currNode
					freq++; // Update +1 for this instance
					currNode.setCount(freq); // Insert the updated frequency
					super.refChanges += 3;
					return; // Exit
				}
				// Update pointers
				prevNode = currNode;
				currNode = currNode.getNext();
			}
			// We get here if the word is not in the LL, insert at head
			LLNode newNode = new LLNode(word);
			newNode.setNext(super.list); // Set the current head as the newNode's next
			super.list = newNode; // Now make the newNode the head
			super.refChanges += 2;
		}

	}

}
