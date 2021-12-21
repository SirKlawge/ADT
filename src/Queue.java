
public class Queue<E> {
	private LinkedList<E> queue;
	private int size;
	
	public Queue() {
		this.queue = new LinkedList<E>();
		this.size = 0;
	}
	
	//add to the back of the queue
	public void enqueue(E item) {
		this.queue.add(item);
		this.size++;
	}
	
	public E dequeue() {
		E removed = null;
		try {
			removed = dequeueHelper();
		}catch(EmptyStructureException e) {
			e.printStackTrace();
		}
		return removed;
	}
	
	private E dequeueHelper() throws EmptyStructureException{
		if(this.isEmpty()) {
			throw new EmptyStructureException();
		}
		Node<E> front = this.queue.getHead().getNext();
		this.queue.getHead().setNext(front.getNext());
		front.getNext().setPrev(this.queue.getHead());
		this.size--;
		return front.getValue();
	}
	
	public E peek() {
		E front = null;
		try {
			front = peekHelper();
		}catch(EmptyStructureException e) {
			e.printStackTrace();
		}
		return front;
	}
	
	public E peekHelper() throws EmptyStructureException{
		if(this.isEmpty()) {
			throw new EmptyStructureException();
		}
		return this.queue.getFirst();
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
		return this.queue.toString();
	}
	
	public boolean equals(Object o) {
		if(o instanceof Queue) {
			Queue<E> other = (Queue<E>) o;
			if(this.size != other.size) {
				return false;
			}
			Node<E> thisCurrent = this.queue.getHead().getNext(),
					otherCurrent = other.queue.getHead().getNext();
			//Traverse both queues and compare each element
			while(thisCurrent != this.queue.getTail()) {
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
