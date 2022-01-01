
public class testing {

	public static void main(String[] args) {
		BST<Integer, String> bst = new BST<Integer, String>();
		bst.put(5, "Ventura");
		bst.put(2, "Aazar");
		bst.put(10, "Keneal");
		bst.put(7, "Keneal-San");
		bst.put(12, "Keneal-San");
		bst.put(8, "Keneal-San");
		System.out.println(bst);
		while(!bst.isEmpty()) {
			System.out.println("Removing " + bst.root.key);
			bst.remove(bst.root.key);
			System.out.println(bst);
		}
	}

}
