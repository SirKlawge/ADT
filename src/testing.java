
public class testing {

	public static void main(String[] args) {
		BST<Integer, String> bst = new BST<Integer, String>();
		bst.put(4, "Ventura");
		bst.put(2, "Aazar");
		bst.put(6, "Keneal");
		bst.put(6, "Keneal-San");
		System.out.println(bst);
		bst.remove(6);
		System.out.println(bst);
	}

}
