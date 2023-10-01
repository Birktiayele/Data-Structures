
public abstract class BaseList implements ListInterface {

	LLNode list;
	long refChanges;
	long keyCompare;

	BaseList() // constructor: empty list, counters set to zero
	{
		list = null;
		refChanges = 0;
		keyCompare = 0;
	}

	@Override
	public long getRefChanges() {
		// How many reference changes did we do (how much structural work)?
		//
		return refChanges;
	}

	@Override
	public long getKeyCompare() {
		// How many key comparisons (how much work was done looking for things on the
		// list)?
		//
		return keyCompare;
	}

	@Override
	public int getDistinctWords() {
		// How many nodes are there on the list? Each corresponds to a unique word
		//
		int count = 0; // Alternate coding:
		LLNode p = list; //
		while (p != null) // for (LLNode p = list; p != null; p = p.getNext()) count++;
		{ // return count;
			count++; //
			p = p.getNext(); //
		} //
		return count; //
	}

	@Override
	public int getTotalWords() {
		// How many TOTAL words? That's the sum of the counts in each node.
		//
		int count = 0;
		LLNode p = list;
		while (p != null) {
			count += p.getCount();
			p = p.getNext();
		}
		return count;
	}

	@Override
	public void printAdditionalStats() {
		// Override to specific classes that require additional stats printed for report
	}

}
