public class Node<E> {
	private E value;
	private Node<E> next, prev;
	
	public Node(E value) {
		this.value = value;
		this.next = null;
		this.prev = null;
	}
	
	//TODO: for testing
	public String toString() {
		return "(" + this.value + ")";
	}
	
	public Node<E> getNext() {
		return this.next;
	}
	
	public Node<E> getPrev(){
		return this.prev;
	}
	
	public void setNext(Node<E> next) {
		this.next = next;
	}
	
	public void setPrev(Node<E> prev) {
		this.prev = prev;
	}
	
	public E getValue() {
		return this.value;
	}
	
	public void setValue(E value) {
		this.value = value;
	}

}
