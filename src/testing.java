
public class testing {

	public static void main(String[] args) {
		Queue<Integer> q1 = new Queue<Integer>();
		Stack<Integer> s1 = new Stack<Integer>();
		
		for(int i = 0; i < 20; i++) {
			q1.enqueue(i);
		}
		
		while(!q1.isEmpty()) {
			s1.push(q1.dequeue());
		}
		
		while(!s1.isEmpty()) {
			System.out.print(s1.pop() + " ");
		}
	}

}