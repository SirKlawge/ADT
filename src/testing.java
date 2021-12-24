
public class testing {

	public static void main(String[] args) {
		Hashtable<Integer, Boolean> ht = new Hashtable<Integer, Boolean>();
		for(int i = 0; i < 20; i++) {
			ht.put(i, true);
		}
		System.out.println(ht);
		System.out.println(ht.size());
		for(int i = 4; i < 19; i++) {
			ht.remove(i);
		}
		System.out.println(ht);
		System.out.println(ht.size());
	}

}
