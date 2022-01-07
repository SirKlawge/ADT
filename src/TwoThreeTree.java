import java.util.List;
import java.util.ArrayList;

public class TwoThreeTree<K extends Comparable<K>, V> {
	public TTNode<K, V> root;
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
		//See if current is a leaf
		if(current.isLeaf()) {
			insertInCurrentNode(current, newPair);
		}else {
			if(newPair.getKey().compareTo(current.getMinPair().getKey()) == -1) {
				//Here, newPair's key is less than the current Node's min key
				if(current.getFirst() != null) {
					putHelper(current.getFirst(), newPair);
				}else {
					insertInCurrentNode(current, newPair);
				}
			}else if(newPair.getKey().compareTo(current.getMaxPair().getKey()) == 1) {
				//Here, We have to look right
				if(current.getLast() != null) {
					putHelper(current.getLast(), newPair);
				}else {
					insertInCurrentNode(current, newPair);
				}
			}else {
				if(current instanceof ThreeNode) {
					//Is there a middle child?
					ThreeNode<K, V> threeCurrent = (ThreeNode<K, V>) current;
					putHelper(threeCurrent, newPair);
				}else {
					insertInCurrentNode(current, newPair);
				}
			}
		}
	}
	
	private void insertInCurrentNode(TTNode<K, V> current, TTTPair<K, V> newPair) {
		if(current instanceof TwoNode) {
			//Find out which should be pair1 and pair2
			TwoNode<K, V> currentTwo = (TwoNode<K, V>) current;
			ThreeNode<K, V> threeNode = null;
			if(currentTwo.getPair1().getKey().compareTo(newPair.getKey()) == -1) {
				//Here, the pair from current is lesser
				threeNode = new ThreeNode<K, V>(currentTwo.getPair1(), newPair);
			}else if(currentTwo.getPair1().getKey().compareTo(newPair.getKey()) == 1) {
				//Here, the pair from current is greater
				threeNode = new ThreeNode<K, V>(newPair, currentTwo.getPair1());
			}else {
				//Here, they're equal replace currentPair's value with newPair's value
				currentTwo.getPair1().setValue(newPair.getValue());
				return;
			}
			//We have the threeNode, and the Pairs should be in order. Redirect pointers
			threeNode.setParent(currentTwo.getParent());
			if(currentTwo.getParent() != null) {
				//Find out which child the three node should be
				
			}
		}else if(current instanceof ThreeNode) {
			//Gotta find where newPair should be in the three node
			ThreeNode<K, V> currentThree = (ThreeNode<K, V>) current;
			FourNode<K, V> fourNode = null;
			
		}else {
			//Here, it's a four node
		}
	}
	
	private void handleTwoNodeInsert(TTNode<K, V> current, TTTPair<K, V> newPair) {}
	
	private void handleThreeNodeInsert(TTNode<K, V> current, TTTPair<K, V> newPair) {}
	
	//TODO: make .equals methods in the Node subclasses
	private TTNode<K, V> getCorrectChild(TTNode<K, V> current){
		if(current.getParent() == null) {
			return null;
		}
		TTNode<K, V> first, second, third, fourth;
		if(current.getParent() instanceof TwoNode) {
			//if() {}
		}else if(current instanceof ThreeNode) {
			
		}else {
			//Here it's a FourNode
		}
	}
	
	public int size() {
		return this.size;
	}
	
	public boolean isEmpty() {
		return this.size == 0;
	}
	
	public String toString() {
		if(this.isEmpty()) {
			return "[]";
		}
		return this.root.toString();
	}
	
}
