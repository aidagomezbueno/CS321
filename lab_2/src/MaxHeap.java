//import java.util.*;

/**
 * @author aidagomezbuenoberezo
 *
 */

public class MaxHeap {

	private Task[] heap;
	private int size;


	/**
	 * Constructs a MaxHeap with a default heap of size 10 and filled with nulls. 
	 * @param heap
	 */
	public MaxHeap(Task[] heap) {
		super();
		this.heap = heap;
		this.size = 0;
	}


	/**
	 * Constructs a MaxHeap with a default heap of size 10 and filled with nulls.
	 */
	public MaxHeap() {
		super();
		this.heap = new Task[10];
		this.size=0;
	}


	/**
	 * Calculates the position of the parent node of a given node. 
	 * @param i - the position of the node for which we want to calculate the position of the parent node.
	 * @return the position of the parent node.
	 */
	private int parent(int i) { 
		if(i==0) {
			return 0;
		}
		return ((i-1)/2); 
	}

	/**
	 * Calculates the position of the left node of a given node.
	 * @param i - the position of the node for which we want to calculate the position of the left node.
	 * @return the position of the left node.
	 */
	private int left(int i) { 
		return (2*i)+1;
	}

	/**
	 * Calculates the position of the right node of a given node.
	 * @param i - the position of the node for which we want to calculate the position of the right node.
	 * @return the position of the right node.
	 */
	private int right(int i){
		return 2*i+2; //because array starts at 0
	}

	/**
	 * Exchanges two nodes according to their position. 
	 * @param heap - the heap in which the exchange will take place.
	 * @param i - the position of the node to be exchanged.
	 * @param largest - the position of the second node to be exchanged.
	 * @return the exchanged heap.
	 */
	public Task[] swap(Task[] heap, int i, int largest)
	{
		Task t = new Task();
		t = heap[i];
		heap[i] = heap[largest]; 
		heap[largest] = t;
		return heap;
	}

	/**
	 * It changes the position of the nodes so that they satisfy the max-heap condition (higher priorities at minimum positions).
	 * @param i - node from which the heapify process will start (locate at position i the node with the highest priority, taking into account itself and its children).
	 */
	public void heapify(int i){

		int largest = i;
		int left = this.left(i);
		int right = this.right(i);

		if(left < size && heap[left].compareTo(heap[i])>=1 && heap[left]!=null) {
			largest = left;
		}
		if(right < size && heap[right].compareTo(heap[largest])>=1 && heap[right]!=null) {
			largest = right;
		}
		if(largest!=i) { //if finally node i werent the one w highest priority, swap w the one meeting this constraint
			heap = swap(heap, i, largest);
			heapify(largest);
		}

	}

	/**
	 * It makes the entire data structure of the binary tree (the entire ArrayList) is max-heapified, to make each node satisfy the max-heap condition.
	 */
	public void buildMaxHeap() {
		int start = 0;
		start = (size-1)/2;
		for(int i = start; i>=0; i--) {
			heapify(i);
		}
	}

	/**
	 * @return the Task with the highest priority in the heap.
	 */
	public Task max() {
		return heap[0];
	}

	/**
	 * It removes the highest priority Task and places the last node in the heap (lowest priority node) in its place.
	 * @return the highest priority Task in the heap.
	 */
	public Object extractMax() {
		Task max = new Task();
		if(size<1) {
			return new HeapException("Heap underflow");
		}
		max = max();
		heap[0] = heap[size-1];
		size--;
		heapify(0);
		return max;
	}


	/**
	 * It inserts (adds) Tasks into the heap. 
	 * If there's no space: doubles the size of the heap and adds the new task to the heap.
	 * If there's space: adds the new task to the heap.
	 * Finally, BuildMaxHeap is called to reorder the tasks in the heap by pursuing that the nodes meet the Max-Heap condition.  
	 * @param t - new Task to add into the heap.
	 */
	public void insert(Task t) {
		if(size+1==heap.length) {
			Task[] nheap = new Task[2*heap.length];
			for(int i=0; i<heap.length; i++){
				nheap[i] = heap[i];
			}
			heap = nheap;
		}
		increaseKey(heap, size, t);
		this.size++;
	}

	/**
	 * It increases the priority of a given Task only if the new Task has a higher priority.
	 * @param heap - heap on which we are going to work on.
	 * @param i - position of the node to increase the key.
	 * @param key - new potential priority of the given node.
	 */
	public void increaseKey(Task[] heap, int i, Task t) {
		heap[i] = t;
		while(i>0 && heap[i].compareTo(heap[parent(i)])>=1) { //si la task introducida es mayor que su padre
			swap(heap, i, parent(i));
			i = parent(i);
		}
	}

	/**
	 * It considers if the heap is empty of Tasks or not.
	 * @param heap - heap on which we are going to work on.
	 * @return true - if the heap is empty of tasks.
	 */
	public boolean isempty() {
		boolean isEmpty = false;
		if(this.size<=0) {
			isEmpty = true;
		}
		return isEmpty;
	}

	public Task[] getHeap() {
		return heap;
	}

	public void setHeap(Task[] heap) {
		this.heap = heap;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

}
