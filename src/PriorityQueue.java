
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
		for(int i = 0; i < this.pq.length; i++) {
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
				
			}
			//Here
		}
	}
	
	private void maxSink(int parentIdx) {}
	
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
		if(this.minMax.equals(MIN_MAX.MIN)) {
			this.minSink(0);
		}else {
			this.maxSink(0);
		}
		return null;
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
