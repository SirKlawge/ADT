
public class Pair<K, V> {
	private K key;
	private V value;
	private boolean removed;
	
	public Pair(K key, V value) {
		this.key = key;
		this.value = value;
		this.removed = false;
	}
	
	public K getKey() {
		return this.key;
	}
	
	public V getValue() {
		return this.value;
	}
	
	public void setValue(V value) {
		this.value = value;
	}
	
	public boolean isRemoved() {
		return this.removed;
	}
	
	public void setRemoved() {
		this.removed = true;
	}
	
	public String toString() {
		return "(" + this.key + ": " + this.value + ")";
	}
	
	public boolean equals(Pair<K, V> other) {
		return this.key.equals(other.key);
	}
}
