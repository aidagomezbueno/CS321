
/**
 * @author aidagomezbuenoberezo
 *
 * @param <T>
 */

public class LinearProbing<T> extends HashTable<T> {

	public LinearProbing(int tableSize) {
		super(tableSize);
	}
	
	protected int hashFunction(HashObject<T> hashObject, int index) {
		return positiveMod((hashObject.getKey().hashCode() + index), getTableSize());
	}

}
