
public class TTTPair<K extends Comparable<K>, V> {
	private K key;
	private V value;
	
	public TTTPair(K key, V value) {
		this.key = key;
		this.value = value;
	}
	
	public void setValue(V value) {
		this.value = value;
	}
	
	public K getKey() {
		return this.key;
	}
	
	public V getValue() {
		return this.value;
	}
	
	public String toString() {
		return "(" + this.key + ", " + this.value + ")";
	}
	
	public boolean equals(Object o) {
		if(o instanceof TTTPair) {
			TTTPair<K, V> other = (TTTPair<K, V>) o;
			return this.key.equals(other.key);
		}
		return false;
	}
	
	public int compareTo(TTTPair<K, V> other) {
		return this.key.compareTo(other.key);
	}
}
