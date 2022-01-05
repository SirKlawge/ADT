import java.util.List;
import java.util.ArrayList;

public class TwoThreeTree<K extends Comparable<K>, V> {
	private TTNode<K, V> root;
	private int size;
	private List<K> keyList;
	private List<V> valueList;
	
	public TwoThreeTree() {
		this.root = null;
		this.size = 0;
		this.keyList = new ArrayList<K>();
		this.valueList = new ArrayList<V>();
	}
	
	public void put(K key, V value) {
		TTTPair<K, V> newPair = new TTTPair<K, V>(key, value);
		TTNode<K, V> newNode;
		//Handle an empty TTT
		if(this.root == null) {
			newNode = new TwoNode<K, V>(newPair);
			this.root = newNode;
		}else {
			putHelper(this.root, newPair);
		}
	}
	
	private void putHelper(TTNode<K, V> current, TTTPair<K, V> newPair) {
		//Check for equality
		
	}
}
