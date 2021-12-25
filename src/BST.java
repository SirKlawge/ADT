/**@author Ventura Abram
 * What value would this BST be?  If you're not storing key value pairs, then what does
 * this do other than sort numbers?  Could I use this in its current state to store 
 * key value pairs?  If the key-value pairs have*/
public class BST<K extends Comparable<K>, V> {
	private Node<K, V> root;
	private int size;
	
	public BST() {
		this.root = null;
		this.size = 0;
	}
	
	public void put(K key, V value) {
		Node<K, V> newNode = new Node<K, V>(key, value);
		if(this.isEmpty()) {
			this.root = newNode;
			this.size++;
		}else {
			putHelper(newNode, this.root);
		}
	}
	
	private void putHelper(Node<K, V> newNode, Node<K, V> current) {
		if(!current.key.equals(newNode.key)) {
			//Only do something if there newNode's value isn't already in the BST
			if(newNode.key.compareTo(current.key) == -1) {
				//Here, newNode's value is less than the current node's value. look left
				newNode.parent = current;
				if(current.left == null) {
					current.left = newNode;
					this.size++;
				}else {
					putHelper(newNode, current.left);
				}
			}else {
				newNode.parent = current;
				if(current.right == null) {
					current.right = newNode;
					this.size++;
				}else {
					putHelper(newNode, current.right);
				}
			}
		}else {
			current.value = newNode.value;
		}
	}
	
	//Return the value associated with the provided key if it's in the BST.
	public V get(K key) {
		Node<K, V> node = null;
		try {
			node = getHelper(key, this.root);
		}catch(EmptyStructureException e) {
			e.printStackTrace();
		}
		if(node == null) {
			return null;
		}
		return node.value;
	}
	
	private Node<K, V> getHelper(K key, Node<K, V> current) throws EmptyStructureException {
		if(this.isEmpty()) {
			throw new EmptyStructureException();
		}
		if(current == null) {
			return null;
		}else if(current.key.equals(key)) {
			return current;
		}else if(key.compareTo(current.key) == -1) {
			//Here, the target key is less than current key, so look left
			return getHelper(key, current.left);
		}else {
			return getHelper(key, current.right);
		}
	}
	
	public V remove(K key) {
		Node<K, V> removedNode = null;
		try {
			removedNode = removeHelper(key);
		}catch(EmptyStructureException e) {
			e.printStackTrace();
		}
		if(removedNode == null) {
			return null;
		}
		return removedNode.value;
	}
	
	private Node<K, V> removeHelper(K key) throws EmptyStructureException{
		//First try to get the node with the provided key
		Node<K, V> removed = this.getHelper(key, this.root);
		if(removed != null) {
			//Here, the Node with key was actually in the tree.  Check if leaf
			if(removed.left == null && removed.right == null) {
				//Here, removed is a leaf, so just nullify it
				if(removed.key.equals(removed.parent.left.key)) {
					removed.parent.left = null;
				}else {
					removed.parent.right = null;
				}
			}else {
				//Here, removed is not a leaf, so get the successor
				Node<K, V> successor = this.getSuccessor(removed);
				//Swap data of successor and removed
				this.swap(removed, successor);
				//Nullify the successor
			}
		}
		return removed;
	}
	
	public int size() {
		return this.size;
	}
	
	public boolean isEmpty() {
		return this.size == 0;
	}
	
	public String toString() {
		return this.root.toString();
	}
	//-------------------------------------
	private class Node<K extends Comparable<K>, V>{
		private Node<K, V> left, right, parent;
		private K key;
		private V value;
		
		public Node(K key, V value) {
			this.key = key;
			this.value = value;
			this.left = null;
			this.right = null;
			this.parent = null;
		}
		
		public String toString() {
			return "[" + this.key + " " + this.left + " " + this.right + "]";
		}
	}
}
