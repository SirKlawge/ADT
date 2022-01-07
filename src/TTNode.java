
public abstract class TTNode<K extends Comparable<K>, V> {
	public abstract TTTPair<K, V> getMinPair();
	
	public abstract TTTPair<K, V> getMaxPair();
	
	public abstract boolean isLeaf();
	
	public abstract TTNode<K, V> getFirst();
	
	public abstract TTNode<K, V> getLast();
	
	public abstract TTNode<K, V> getParent();
}
