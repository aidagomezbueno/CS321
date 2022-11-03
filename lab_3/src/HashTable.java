
/**
 * @author aidagomezbuenoberezo
 *
 * @param <T>
 */

import java.util.Arrays;

public abstract class HashTable<T> {
	protected HashObject<T>[] hashTable;
	protected int tableSize;
	protected int totalProbes;
	protected int totalInserts;
	protected int totalDuplicates;
	
	@SuppressWarnings("unchecked")
	public HashTable(int tableSize) {
		hashTable = (HashObject<T>[]) new HashObject[tableSize];
		this.tableSize = tableSize;
		this.totalProbes = 0;
		this.totalInserts = 0;
	}
	
	public int getTableSize() {
		return tableSize;
	}
	
	public int getTotalProbes() {
		return totalProbes;
	}
	
	public int getTotalInserts() {
		return totalInserts;
	}
	
	public int getTotalDuplicates() {
		return totalDuplicates;
	}
	
	public HashObject<T>[] getHashTable() {
		return hashTable;
	}
	
	public HashObject<T> getObject(int index) {
		return hashTable[index];
	}
	
	public boolean isEmpty() {
		return totalInserts == 0;
	}
	
	public String toString() {
		return Arrays.toString(hashTable);
	}
	
	protected int positiveMod (int dividend, int divisor) {
		int value = dividend % divisor;
		if (value < 0) {
			value += divisor;
		}
		return value;
	}
	
	protected abstract int hashFunction(HashObject<T> hashObject, int index);
	
	public void hashInsert(T object) {
		HashObject<T> hashObject = new HashObject<>(object);
		int i = 0;
		int count = 0;
		while (i != tableSize) {
			int j = hashFunction(hashObject, i);
			hashObject.increaseObjectProbe();
			if (hashTable[j] == null) {
				hashTable[j] = hashObject;
				totalInserts++;
				count++;
				totalProbes  += count;
				return;
			} else {
				if (hashTable[j].equals(hashObject)) {
					hashTable[j].increaseFrequency();
					totalDuplicates++;
					return;
				}
				i++;
				count++;
			}
		}
	}
}
