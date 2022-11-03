
/**
 * @author aidagomezbuenoberezo
 *
 * @param <T> Generic object type to be stored in DoubleHashing kind of HashTable.
 */

public class DoubleHashing<T> extends HashTable<T> {
	
	public DoubleHashing(int tableSize) {
		super(tableSize);
	}
	
	private int firstHash(HashObject<T> hashObject) {
		return positiveMod(hashObject.getKey().hashCode(), getTableSize());
	}
	
	private int secondHash(HashObject<T> hashObject) {
		return (1 + positiveMod(hashObject.getKey().hashCode(), getTableSize() - 2));
	}
	
	protected int hashFunction(HashObject<T> hashObject, int index) {
		return positiveMod(firstHash(hashObject) + (index * secondHash(hashObject)), getTableSize());
	}
}
