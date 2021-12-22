
public class testing {

	public static void main(String[] args) {
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>(MIN_MAX.MAX);
		for(int i = 0; i < 20; i++) {
			pq.add(i);
		}
		//System.out.println(pq.pq.length);
		System.out.println(pq);
	}

}
