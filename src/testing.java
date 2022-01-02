
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
		Object[] keyArray = bst.keyArray();
//		for(int i = 0;  i < keyArray.length; i++) {
//			System.out.println(keyArray[i]);
//		}
	}

}
