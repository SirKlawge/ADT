//This will be a LinkedList based Stack.  The left most item will be the bottom
public class Stack<E> {
	public LinkedList<E> stack;
	private int size;
	
	public Stack() {
		this.stack = new LinkedList<E>();
		this.size = 0;
	}
	
	//Push item to the top of the stack
	public void push(E item) {
		this.stack.add(item);
		this.size++;
	}
	
	//Return, but don't remove, the item on the top of the stack
	public E peek() {
		E top = null;
		try {
			top = peekHelper();
		}catch(EmptyStructureException e) {
			e.printStackTrace();
		}
		return top;
	}
	
	private E peekHelper() throws EmptyStructureException{
		if(this.isEmpty()) {
			throw new EmptyStructureException();
		}
		return this.stack.getLast();
	}
	
	//Remove and return the item on the top of the stack
	public E pop() {
		E removed = null;
		try {
			removed = popHelper();
		}catch(EmptyStructureException e) {
			e.printStackTrace();
		}
		return removed;
	}
	
	private E popHelper() throws EmptyStructureException{
		if(this.isEmpty()) {
			throw new EmptyStructureException();
		}
		//Get the last node
		Node<E> last = this.stack.getTail().getPrev();
		last.getPrev().setNext(last.getNext()); //points prev at next (tail)
		last.getNext().setPrev(last.getPrev());
		this.size--;
		return last.getValue();
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
		return this.stack.toString();
	}
	
	public boolean equals(Object o) {
		if(o instanceof Stack) {
			Stack<E> other = (Stack<E>) o;
			if(this.size != other.size) {
				return false;
			}
			Node<E> thisCurrent = this.stack.getHead().getNext(),
					otherCurrent = other.stack.getHead().getNext();
			while(thisCurrent != this.stack.getTail()) {
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
}
