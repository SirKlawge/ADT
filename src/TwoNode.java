
public class TwoNode<K extends Comparable<K>, V> extends TTNode<K, V>{
	private TTTPair<K, V> pair1;
	private TTNode<K, V> first, second, parent;
	
	public TwoNode(TTTPair<K, V> pair1) {
		this.pair1 = pair1;
		this.first = null; //AKA left child
		this.second = null; //AKA right child
		this.parent = null;
	}
	
	public TTTPair<K, V> getPair1(){
		return this.pair1;
	}
	
	public void setPair1(TTTPair<K, V> pair1) {
		this.pair1 = pair1;
	}
	
	public void setfirst(TTNode<K, V> first) {
		this.first = first;
	}
	
	public void setsecond(TTNode<K, V> second) {
		this.second = second;
	}
	
	public TTNode<K, V> getParent(){
		return this.parent;
	}
	
	public void setParent(TTNode<K, V> parent) {
		this.parent = parent;
	}

	@Override
	public TTTPair<K, V> getMinPair() {
		return this.pair1;
	}

	@Override
	public TTTPair<K, V> getMaxPair() {
		return this.pair1;
	}

	@Override
	public boolean isLeaf() {
		return this.first == null && this.second == null;
	}

	@Override
	public TTNode<K, V> getFirst() {
		return this.first;
	}

	@Override
	public TTNode<K, V> getLast() {
		return this.second;
	}
	
	public String toString() {
		return "[" + this.pair1.getKey() + ", " + this.first + ", " + this.second + "]";
	}

}
