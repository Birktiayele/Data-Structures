/**
 * This class represents a node in a linked list data structure. Each node contains
 * a string data value, an integer count representing the number of occurrences of
 * the data value, and a reference to the next node in the linked list.
 *
 * @author Birkti Ayele
 * @version 1.0
 */
public class LLNode {
	private String data;
	private int count;
	private LLNode next;

	public LLNode(String word) {
		data = word;
		count = 1;
		next = null;
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

	public LLNode getNext() {
		return next;
	}

	public void setNext(LLNode nextNode) {
		this.next = nextNode;
	}
}
