
/**
 * @author aidagomezbuenoberezo
 *
 * @param <T>
 */

public class HashObject<T> {
	private final Object key;
	private int frequencyCount;
	private int probeCount;
	
	public HashObject(Object key) {
		this.key = key;
		this.frequencyCount = 0;
		this.probeCount = 0;
	}
	
	public Object getKey() {
		return key;
	}

	public int getFrequencyCount() {
		return this.frequencyCount;
	}

	public int getProbeCount() {
		return this.probeCount;
	}
	
	public void increaseFrequency() {
		this.frequencyCount++;
	}
	
	public void increaseObjectProbe() {
		this.probeCount++;
	}
	
	public String toString() {
		return this.key + " " + this.frequencyCount + " " + this.probeCount;
	}
	
	public boolean equals(HashObject<T> hashObject) {
		return this.getKey().equals(hashObject.getKey());
	}
}
