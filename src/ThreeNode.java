
public class ThreeNode<K extends Comparable<K>, V> extends TTNode<K, V> {
	private TTTPair<K, V> pair1, pair2;
	private TTNode<K, V> first, second, third, parent;
	
	public ThreeNode(TTTPair<K, V> pair1, TTTPair<K, V> pair2) {
		this.pair1 = pair1;
		this.pair2 = pair2;
		this.first = null;
		this.second = null;
		this.third = null;
		this.parent = null;
	}

	public TTNode<K, V> getParent() {
		return this.parent;
	}

	public void setParent(TTNode<K, V> parent) {
		this.parent = parent;
	}

	public TTTPair<K, V> getPair1() {
		return this.pair1;
	}

	public void setPair1(TTTPair<K, V> pair1) {
		this.pair1 = pair1;
	}

	public TTTPair<K, V> getPair2() {
		return this.pair2;
	}

	public void setPair2(TTTPair<K, V> pair2) {
		this.pair2 = pair2;
	}

	public TTNode<K, V> getFirst() {
		return this.first;
	}

	public void setFirst(TTNode<K, V> first) {
		this.first = first;
	}

	public TTNode<K, V> getSecond() {
		return this.second;
	}

	public void setSecond(TTNode<K, V> second) {
		this.second = second;
	}

	public TTNode<K, V> getThird() {
		return this.third;
	}

	public void setThird(TTNode<K, V> third) {
		this.third = third;
	}
	
	
}
