
public class PriorityQueue<E> {
	private MIN_MAX minMax;
	private Object[] pq;
	private int size, capacity;
	
	public PriorityQueue(MIN_MAX minMax) {
		this.minMax = minMax;
		this.pq = (E[]) new Object[20];
		this.size = 0;
		this.capacity = 20;
	}
	
	
}
