/**
 * This class represents a custom linked list implementation, List4. It extends
 * the BaseList class and provides methods for adding elements to the list.
 *
 * @author Birkti Ayele
 */

public class List4 extends BaseList implements ListInterface {
	
	/**
     * Constructs a new List4 instance.
     */
	public List4() {
		super();
	}

	@Override
	public int height() {
		return 0;
	}

	@Override
	public void add(String word) {

		// First always check if the list is empty
		if (super.list == null) {
			super.list = new LLNode(word);
			super.refChanges++; // Update to ref changes for the insertion of new head
			return;
		}

		// Check if the word we are inserting is at the head
		super.keyCompare++; // Update the compare since we are going to evaluate the data inside the head
		if (super.list.getData().compareTo(word) == 0) {
			// Here we just update the frequency of the head
			int freq = super.list.getCount();
			freq++;
			super.list.setCount(freq);
			return;
		}

		// Check if we only have 1 node in the list, if so we insert at the head
		if (super.list.getNext() == null) {
			LLNode newHead = new LLNode(word);
			newHead.setNext(super.list);
			super.list = newHead;
			super.refChanges++;
			return;
		}

		// If we have more than 1 node, and we might have to do a swap towards the head
		LLNode currNode = super.list, prevNode = null;
		while (currNode.getNext() != null) { // Iterate through the list to find a node with `word` as data
			LLNode nextNode = currNode.getNext();
			LLNode nextNextNode = nextNode.getNext(); // Grab the reference of the nextNode's next node
			
			// Check if the next node contains the word we are looking for
			int compareResult = nextNode.getData().compareTo(word); // Get the result when comparing the next node's
																	// data vs `word`
			super.keyCompare++; // Update for the comparison
			if (compareResult == 0) { // If we found the `word` in the `nextNode`
				if (currNode == super.list) { // If we are attempting to swap the head
					// The following contains an example of this swap type:
					// boston -> cleveland -> toledo (we here), and inserting `cleveland`
					// cleveland -> boston -> toledo (we want)
					// nextNextNode = `toledo`
					currNode.setNext(nextNextNode); // boston -> toledo
					nextNode.setNext(currNode); // cleveland -> boston
					// cleveland -> boston -> toledo
					super.list = nextNode; // Since we swapped the head, we need to make the `nextNode` the new head
				} else { // We need to perform a swap, and update the node's frequency/count
					// The following contains an example of this swap type:
					// cleveland -> boston -> toledo -> null (we here)
					// cleveland -> toledo -> boston (we want), inserting `toledo`
					prevNode.setNext(nextNode); // cleveland (prev) -> toledo (next)
					currNode.setNext(nextNextNode); // boston (curr) -> nextNextNode (null) aka toledo.next
					nextNode.setNext(currNode); // toledo(next) -> boston (curr)
					// cleveland -> toledo -> boston -> null
				}

				// Update variables
				nextNode.setCount(nextNode.getCount() + 1); // `nextNode` contained the `word`, so update the count
				super.refChanges += 3; // In both swap types we did 3 reference changes
				return;
			}

			// Update pointers
			prevNode = currNode;
			currNode = currNode.getNext();
		}
		
		// We get here if we didn't find the word, aka we insert a new node at the head
		LLNode newHead = new LLNode(word);
		newHead.setNext(super.list);
		super.list = newHead;
	}
}
