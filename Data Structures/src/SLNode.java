/**
 * This class represents a node in a Skip List data structure. Each node contains a word,
 * as well as references to nodes in the left, up, and down directions, allowing for efficient
 * navigation in the Skip List.
 *
 * The Skip List nodes can also have special keys for negative and positive infinity values.
 * These keys are used for boundary nodes in the Skip List.
 *
 * @author Birkti Ayele
 */
public class SLNode extends LLNode {

	private SLNode left, up, down;
	public static final String negInf = "negInf"; // -inf key value
	public static final String posInf = "posInf"; // +inf key value

	public SLNode(String word) {
		super(word);
	}

	public SLNode getLeft() {
		return left;
	}

	public void setLeft(SLNode left) {
		this.left = left;
	}

	public SLNode getUp() {
		return up;
	}

	public void setUp(SLNode up) {
		this.up = up;
	}

	public SLNode getDown() {
		return down;
	}

	public void setDown(SLNode down) {
		this.down = down;
	}

}
