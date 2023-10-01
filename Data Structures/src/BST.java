import static java.lang.Math.max;

import java.util.LinkedList;
import java.util.Queue;

public class BST extends BaseList implements ListInterface {
	private BSTNode root;
	private int maxHeight;
	private int distinctWords;
	private int totalWords;

	@Override
	public int getDistinctWords() {
		levelOrderTraversal();
		return distinctWords;
	}

	@Override
	public int getTotalWords() {
		levelOrderTraversal();
		return totalWords;
	}

	public BST() {
		super();
		maxHeight = distinctWords = totalWords = 0;
	}

	@Override
	public void add(String word) {
		if (root == null) {
			root = new BSTNode(word);
			super.refChanges++;
			return;
		}
		BSTNode currNode = root, pastNode = null;
		while (currNode != null) {
			int compVal = currNode.getData().compareTo(word);
			super.keyCompare++;
			if (compVal == 0) {
				// We found the node containing the `word`
				currNode.setCount(currNode.getCount() + 1);
				return;
			}
			pastNode = currNode;
			if (compVal < 0) {
				currNode = currNode.getRight();
			} else {
				currNode = currNode.getLeft();
			}
		}
		// We get here if we fell off the tree
		int compVal = pastNode.getData().compareTo(word);
		super.keyCompare++;
		super.refChanges++;
		BSTNode newNode = new BSTNode(word);
		if (compVal < 0) {
			pastNode.setRight(newNode);
		} else {
			pastNode.setLeft(newNode);
		}
	}

	@Override
	public int height() {
		levelOrderTraversal();
		return this.maxHeight;
	}

	private void levelOrderTraversal() {
		if (root == null) {
			return;
		}
		Queue<HelperQueue> q = new LinkedList<>();
		q.add(new HelperQueue(root, 0));
		int maxDepth = 0;
		int totWords = 0, totDistWords = 0;
		while (!q.isEmpty()) {
			HelperQueue currNodeAndLvl = q.peek();
			int currLvl = currNodeAndLvl.lvl;
			// Iterate through this entire level and save it in an array of nodes
			while (!q.isEmpty() && q.peek().lvl == currLvl) {
				currNodeAndLvl = q.remove();
				BSTNode node = currNodeAndLvl.node;
				totDistWords += 1;
				totWords += node.getCount();
				if (node.getLeft() != null) {
					q.add(new HelperQueue(node.getLeft(), currLvl + 1));
				}
				if (node.getRight() != null) {
					q.add(new HelperQueue(node.getRight(), currLvl + 1));
				}
			}
			maxDepth = max(maxDepth, currLvl);
		}
		this.maxHeight = maxDepth;
		this.totalWords = totWords;
		this.distinctWords = totDistWords;
	}

	// Helper class that assists in adding a Node and its lvl in the tree into a
	// queue (for level order traversal)
	public class HelperQueue {
		public BSTNode node;
		public int lvl;

		public HelperQueue(BSTNode node, int lvl) {
			this.node = node;
			this.lvl = lvl;
		}
	}

}
