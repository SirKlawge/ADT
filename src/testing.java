
public class testing {

	public static void main(String[] args) {
		Queue<Integer> q1 = new Queue<Integer>();
		Queue<Integer> q2 = new Queue<Integer>();
		Stack<Integer> s1 = new Stack<Integer>();
		
		for(int i = 0; i < 20; i++) {
			q1.enqueue(i);
			q2.enqueue(i);
		}
		System.out.println(q1.equals(q2));
	}

}
