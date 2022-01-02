/**@author Ventura Abram
 * What value would this BST be?  If you're not storing key value pairs, then what does
 * this do other than sort numbers?  Could I use this in its current state to store 
 * key value pairs?  If the key-value pairs have*/
public class BST<K extends Comparable<K>, V> {
	private Node<K, V> root;
	private int size;
	private K[] keyArray;
	private V[] valueArray;
	
	public BST() {
		this.root = null;
		this.size = 0;
		this.keyArray = (K[]) new Comparable[this.size];
		this.valueArray = (V[]) new Object[this.size];
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
		V removedValue = null;
		try {
			removedValue = removeHelper(key);
		}catch(EmptyStructureException e) {
			e.printStackTrace();
		}
		if(removedValue == null) {
			return null;
		}else {
			this.size--;
		}
		return removedValue;
	}
	
	private V removeHelper(K key) throws EmptyStructureException {
		if(this.isEmpty()) {
			throw new EmptyStructureException();
		}
		//Search for the key and store the result
		Node<K, V> removed = this.getHelper(key, this.root);
		V removedValue = null;
		if(removed != null) {
			removedValue = removed.value;
			//Handle when removed is a leaf
			if(removed.isLeaf()) {
				this.handleLeaf(removed);
			}else {
				//Here, removed is not a leaf and may have a successor. Check
				Node<K, V> successor = this.getSuccessor(removed);
				if(successor != null) {
					//Replace removed's key and value with successor's key and value
					removed.key = successor.key;
					removed.value = successor.value;
					//Here there is a successor, either removed's right or further down
					if(removed.right.key.equals(successor.key)) {
						//Here, removed's successor is its right child
						if(!successor.isLeaf()) {
							successor.right.parent = removed;
						}
						removed.right = successor.right;
					}else {
						//Here, the successor isn't removed's right child. See if succ has right
						if(successor.right != null) {
							successor.right.parent = successor.parent;
							successor.parent.left = successor.right;
						}else {
							//Here the successor is a leaf
							successor.parent.left = null;
						}
					}
				}else {
					//No successor, so removed has no right child.
					this.handleNoSuccessor(removed);
				}
			}
		}
		return removedValue;
	}
	
	//Regular leaf removal handling.  Like a rake
	private void handleLeaf(Node<K, V> removed) {
		//Here, removed is a leaf, so just nullify it
		if(removed.parent != null) {
			if(removed.key.equals(removed.parent.left.key)) {
				removed.parent.left = null;
			}else {
				removed.parent.right = null;
			}	
		}else {
			//Here, you're removing leaf with no parents?  That's the root.
			removed.key = null;
			removed.value = null;
		}
	}
	
	/**finding a successor involves looking right then left of removed.  If there is no
	 * successor, then we ought to check for removed's left child.*/
	private void handleNoSuccessor(Node<K, V> removed) {
		//Check to see if removed has a left child
		if(removed.left != null) {
			removed.left.parent = removed.parent;
			//Does removed have a parent?
			if(removed.parent != null) {
				removed.parent.left = removed.left;
			}
		}
		this.root = removed.left;
	}
	
	private Node<K, V> getSuccessor(Node<K, V> removed) {
		Node<K, V> successor = null;
		//First check to see if removed has a right child
		if(removed.right != null) {
			//Now repeatedly go to the left until you can't anymore
			successor = removed.right;
			while(successor.left != null) {
				successor = successor.left;
			}
			//successor should now truly be the next greatest number after removed
		}
		//Here, successor will be either null or a node
		return successor;
	}
	
	public void printInOrder() {
		printInOrderHelper(this.root);
	}
	
	private void printInOrderHelper(Node<K, V> current) {
		if(current.left != null) {
			printInOrderHelper(current.left);
		}
		System.out.println(current.key);
		if(current.right != null) {
			printInOrderHelper(current.right);
		}
	}
	
	public K[] keyArray() {
		if(this.size == this.keyArray.length) {
			return this.keyArray;
		}
		this.keyArray = (K[]) new Comparable[this.size];
		keyArrayHelper(this.root, 0);
		return this.keyArray;
	}
	
	private void keyArrayHelper(Node<K, V> current, int index) {
		Node<K, V> temp = current;
		if(current.left != null) {
			keyArrayHelper(current.left, index);
		}
		System.out.println(current.key + " at index " + index);
		this.keyArray[index] = current.key;
		keyArrayHelper(temp, index + 1);
		if(current.right != null) {
			keyArrayHelper(current.right, index);
		}
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
	public class Node<K extends Comparable<K>, V>{
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
		
		private boolean isLeaf() {
			return this.left == null && this.right == null;
		}
		
		public String toString() {
			return "[" + this.key + " " + this.left + " " + this.right + "]";
		}
	}
}
