/**@author Ventura Abram
 * This class implements a priority queue which can either be backed by a binary min
 * heap or a binary max heap.
 * To get a min heap PQ, instantiate by passing in MIN from the MIN_MAX enum:
 * new PriorityQueue<>(MIN_MAX.MIN);
 * 
 * To get a max heap PQ, instantiate by passing MAX from the MIN_MAX enum
 * new PriorityQueue<>(MIN_MAX.MAX);
 * 
 * add(T item) adds an item to the PQ
 * del() removes and returns the max or min item from a non-empty PQ
 * peek() returns, but does not remove, the max or min item from an non-empty PQ
 * */
public class PriorityQueue<T extends Comparable<T>> {
	private MIN_MAX minMax;
	private T[] pq;
	private int size;
	
	public PriorityQueue(MIN_MAX minMax) {
		this.minMax = minMax;
		this.pq = (T[]) new Comparable[20];
		this.size = 0;
	}
	
	public void add(T item) {
		//First check to see if we need to resize
		if(this.size >= this.pq.length) {
			this.resize(this.pq.length * 2);
		}
		//Put the item at the end of the array
		this.pq[this.size] = item;
		if(this.minMax.equals(MIN_MAX.MIN)) {
			minSwim(this.size);
		}else {
			maxSwim(this.size);
		}
		this.size++;
	}
	
	public void resize(int newCap) {
		//Make a new array
		T[] newArray = (T[]) new Comparable[newCap];
		for(int i = 0; i < this.size; i++) {
			//copy the contents of the old one to the new one.
			newArray[i] = this.pq[i];
		}
		this.pq = newArray;
	}
	
	private void swap(int idx1, int idx2) {
		T temp  = this.pq[idx1];
		this.pq[idx1] = this.pq[idx2];
		this.pq[idx2] = temp;
	}
	
	//The parent should be less than the children
	private void minSwim(int childIdx) {
		//Get the parent index of the child.
		int parentIdx = 0;
		if(childIdx % 2 == 0) {
			parentIdx = (childIdx - 1) / 2;
		}else {
			parentIdx = childIdx / 2;
		}
		//Compare parent to child
		if(this.pq[parentIdx].compareTo(this.pq[childIdx]) == 1) { 
			//Parent should be greater here, so swap
			this.swap(childIdx, parentIdx);
			minSwim(parentIdx);
		}
	}
	
	private void maxSwim(int childIdx) {
		//Get the parent index of the child.
		int parentIdx = 0;
		if(childIdx % 2 == 0) {
			parentIdx = (childIdx - 1) / 2;
		}else {
			parentIdx = childIdx / 2;
		}
		//Compare parent to child
		if(this.pq[parentIdx].compareTo(this.pq[childIdx]) == -1) { 
			//Parent should be greater here, so swap
			this.swap(childIdx, parentIdx);
			maxSwim(parentIdx);
		}
	}
	
	private void minSink(int parentIdx) {
		int lChild = (2 * parentIdx) + 1;
		//Check for a left child
		if(lChild < this.size && this.pq[lChild] != null) {
			//Here, there's a left child, so check for a right child
			int rChild = lChild + 1;
			if(rChild < this.size && this.pq[rChild] != null) {
				//Here, there's a right Child, get the min of the children.
				int minChild = getMin(lChild, rChild);
				if(this.getMin(parentIdx, minChild) == minChild) {
					swap(parentIdx, minChild);
					minSink(minChild);
				}
			}
			//Here, there's only a left child.  No right child. Compare lChild to parent
			if(this.getMin(lChild, parentIdx) == lChild) {
				swap(parentIdx, lChild);
			}
		}
	}
	
	private void maxSink(int parentIdx) {
		int lChild = (2 * parentIdx) + 1;
		//Check for a left child
		if(lChild < this.size && this.pq[lChild] != null) {
			//Here, there's a left child, so check for a right child
			int rChild = lChild + 1;
			if(rChild < this.size && this.pq[rChild] != null) {
				//Here, there's a right Child, get the max of the children.
				int minChild = getMax(lChild, rChild);
				if(this.getMax(parentIdx, minChild) == minChild) {
					swap(parentIdx, minChild);
					maxSink(minChild);
				}
			}
			//Here, there's only a left child.  No right child. Compare lChild to parent
			if(this.getMax(lChild, parentIdx) == lChild) {
				swap(parentIdx, lChild);
			}
		}
	}
	
	private int getMin(int idx1, int idx2) {
		if(this.pq[idx1].compareTo(this.pq[idx2]) == -1) {
			return idx1;
		}
		return idx2;
	}
	
	private int getMax(int idx1, int idx2) {
		if(this.pq[idx1].compareTo(this.pq[idx2]) == 1) {
			return idx1;
		}
		return idx2;
	}
	
	
	
	public T del() {
		T removed = null;
		try {
			removed = delHelper();
		}catch(EmptyStructureException e) {
			e.printStackTrace();
		}
		return removed;
	}
	
	private T delHelper() throws EmptyStructureException{
		if(this.isEmpty()) {
			throw new EmptyStructureException();
		}
		T removed = this.pq[0];
		this.swap(0, this.size - 1);
		this.pq[this.size - 1] = null;
		this.size--;
		if(this.minMax.equals(MIN_MAX.MIN)) {
			this.minSink(0);
		}else {
			this.maxSink(0);
		}
		//Check for the size of the array
		double ratio = (double) this.size / this.pq.length;
		if(ratio < 0.25 && (this.pq.length / 2) >= 20) {
			this.resize(this.pq.length / 2);
		}
		return removed;
	}
	
	public T peek() {
		T first = null;
		try {
			first = peekHelper();
		}catch(EmptyStructureException e) {
			e.printStackTrace();
		}
		return first;
	}
	
	private T peekHelper() throws EmptyStructureException{
		if(this.isEmpty()) {
			throw new EmptyStructureException();
		}
		return this.pq[0];
	}
	
	public int size() {
		return this.size;
	}
	
	public boolean isEmpty() {
		return this.size == 0;
	}
	
	public String toString() {
		if(this.isEmpty()) {
			return "[]";
		}
		String result = "[";
		for(int i = 0; i < this.size; i++) {
			result += this.pq[i] + ", ";
		}
		result = result.substring(0, result.length() - 2);
		return result + "]";
	}
}
