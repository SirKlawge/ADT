/**@author Ventura Abram
This will be a doubly linked list.
 */
public class LinkedList<E> {
	private Node<E> head, tail;
	private int size;
	
	public LinkedList() {
		this.head = new Node<E>(null);
		this.tail = new Node<E>(null);
		this.size = 0;
	}
	
	public void add(E value) {
		//Put the value in a Node
		Node<E> node = new Node<E>(value);
		//First check to see if it's empty
		if(this.isEmpty()) {
			//Point the head and tail at the new node
			this.head.setNext(node);
			this.tail.setPrev(node);
			node.setPrev(this.head);
		}else {
			//Get the current last node.  Should be tail's previous
			Node<E> currentLast = this.tail.getPrev();
			//point currentLast at the new node
			currentLast.setNext(node);
			//point tail at new node and vice versa
			node.setNext(this.tail);
			this.tail.setPrev(node);
			node.setPrev(currentLast);
		}
		this.size++;
	}
	
	/**Insert the value at the specified index such that the new value is now the 
	 * item at index.*/
	public void insert(E value, int index) {
		try {
			insertHelper(value, index);
		}catch(OutOfBoundsException e) {
			e.printStackTrace();
		}
	}
	
	private void insertHelper(E value, int index) throws OutOfBoundsException{
		if(index < 0 || index > this.size) { //Note that we're allowed to insert at size
			throw new OutOfBoundsException();
		}
		Node<E> current = this.head;
		while(index > 0) {
			current = current.getNext();
			index--;
		}
		//Current is now the node at index - 1
		Node<E> node = new Node<E>(value);
		node.setNext(current.getNext());
		current.getNext().setPrev(node);
		current.setNext(node);
		node.setPrev(current);
		this.size++;
	}
	
	public E get(int index) {
		E item = null;
		try {
			item = getHelper(index);
		}catch(OutOfBoundsException e) {
			e.printStackTrace();
		}
		return item;
	}
	
	/**Either get the item at the index provided or throw an OutOfBounds exception
	 * @throws OutOfBoundsException */
	private E getHelper(int index) throws OutOfBoundsException {
		if(index >= this.size() || index <= 0) {
			throw new OutOfBoundsException();
		}
		//Traverse the LinkedList for the index'th item
		Node<E> current = this.head.getNext();
		while(index > 0) {
			current = current.getNext();
			index--;
		}
		return current.getValue();
	}
	
	public E getFirst() {
		return this.head.getNext().getValue();
	}
	
	public E getLast() {
		return this.tail.getPrev().getValue();
	}
	
	//Remove and return the item at provided index
	public E remove(int index) {
		E removed = null;
		try {
			removed = removeHelper(index);
		}catch(OutOfBoundsException e) {
			e.printStackTrace();
		}catch(EmptyStructureException e) {
			e.printStackTrace();
		}
		return removed;
	}
	
	private E removeHelper(int index) throws OutOfBoundsException, EmptyStructureException{
		if(this.isEmpty()) {
			throw new EmptyStructureException();
		} else if(index < 0 || index >= this.size) {
			throw new OutOfBoundsException();
		}
		Node<E> current = this.head.getNext();
		while(index > 0) {
			current = current.getNext();
			index--;
		}
		current.getPrev().setNext(current.getNext()); //point the previous at current's next
		current.getNext().setPrev(current.getPrev()); //make current's next's prev current's prev
		this.size--;
		return current.getValue();
	}
	
	public Node<E> getHead(){
		return this.head;
	}
	
	public Node<E> getTail(){
		return this.tail;
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
		//Traverse the linked list
		Node<E> current = this.head.getNext();
		while(current != null && current.getValue() != null) {
			
			result += current.getValue() + ", ";
			current = current.getNext();
		}
		result = result.substring(0, result.length() - 2);
		return result += "]";
	}
	
	//Two linked lists are equal if they have the same values in the same positions
	public boolean equals(Object o) {
		if(o instanceof LinkedList) {
			LinkedList<E> other = (LinkedList<E>) o;
			if(this.size() != other.size()) {
				return false;
			}
			//Traverse both arrays and return false if this[i] != other[i]
			Node<E> thisCurrent = this.head.getNext(), otherCurrent = other.head.getNext();
			while(thisCurrent != this.tail && otherCurrent != other.tail) {
				if(!thisCurrent.getValue().equals(otherCurrent.getValue())) {
					return false;
				}
				thisCurrent = thisCurrent.getNext();
				otherCurrent = otherCurrent.getNext();
			}
			return true;
		}
		return false;
	}

	//TODO for testing
	public void printReverse() {
		Node<E> current = this.tail.getPrev();
		while(current.getValue() != null) {
			System.out.print(current.getValue() + " ");
			current = current.getPrev();
		}
		System.out.println();
	}
}
