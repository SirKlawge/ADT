//Hashtable uses modular hashing and linear probing
public class Hashtable<K, V> {
	private Pair[] table;
	private int size;
	
	public Hashtable() {
		this.table = new Pair[23];
		this.size = 0;
	}
	
	public void put(K key, V value) {
		Pair<K, V> newPair = new Pair<K, V>(key, value);
		//Use the key's hash function to get a hash code
		int hashcode = key.hashCode();
		int tableIdx = hashcode % this.table.length;
		while(true) {
			if(this.table[tableIdx] == null || this.table[tableIdx].isRemoved()) {
				//Insert here if the current tableIdx is null or has been removed.
				this.table[tableIdx] = newPair;
				this.size++;
				break;
			}else if(this.table[tableIdx].equals(newPair)) {
				//Here, that index in the table already has a Pair with this key.
				//Update the value
				this.table[tableIdx].setValue(value);
				break;
			}else {
				//Here, look at the next spot on the table
				tableIdx++;
				if(tableIdx == this.table.length) {
					tableIdx = 0;
				}
			}
		}
		double maxLF = 0.5, currentLF = (double) this.size / this.table.length;
		if(currentLF > maxLF) {
			resize(nextPrime(this.table.length * 2));
		}
	}
	
	private void resize(int newSize) {
		Pair[] newTable = new Pair[newSize], copy = this.table;
		this.table = newTable;
		this.size = 0;
		for(int i = 0; i < this.table.length; i++) {
			if(this.table[i] != null && !this.table[i].isRemoved()) {
				this.put((K) copy[i].getKey(), (V) copy[i].getValue());
			}
		}
	}
	
	/**
     *This method can be used to get the next prime
     *p that is greater than or equal to num. Use this
     *when resizing your table.
     **/
    private int nextPrime(int num) {
		if(num <= 3) return 3;
		if(num <= 5) return 5;
		if(isPrime(num))
		    return num;
		int rem = num % 6;
		switch(rem) {
		case 0: num++; break;
		case 2: num+=3; break;
		case 3: num+=2; break;
		case 4: num++; break;
		}
		while(!isPrime(num)) {
		    if(num % 6 == 5)
			num+=2;
		    else
			num+=4;
		}
		return num;
    }

    /**
     *This method determines if a number is prime.
     **/
    private boolean isPrime(int num) {
		if(num % 2 == 0)
		    return false;
		for(int i = 3; i <= (int)Math.sqrt(num); i+=2)
		    if(num % i == 0)
			return false;
		return true;
    }
	
	public V get(K key) {
		return null;
	}
	
	public V remove(K key) {
		return null;
	}
	
	public String toString() {
		String result = "[";
		return result;
	}
}
