import java.util.ArrayList;

/**
 * @author aidagomezbuenoberezo
 *
 */
public class MaxHeapTest {

	public static void main(String[] args) {

		ArrayList<Integer> heap_test = new ArrayList<>();
		MaxHeap mh = new MaxHeap();
		for(int i=1; i<=100; i++) {
			Task t = new Task(TaskInterface.TaskType.FISHING, i);
			mh.insert(t);
		}
		System.out.println("############ MAXHEAP TEST ############\n");
		System.out.println("1. We create 100 Task objects, with priorities from 1 to 100 (in this order) and insert them in the MaxHeap heap.\n2. Once inserted, we traverse the MaxHeap heap and, at the same time, copy its priorities into a new ArrayList.\n3. If, when accessing the first position of this ArrayList, the priority is the maximum existing one (100), the MaxHeap class works correctly.\n");
		for(int j=0;j<mh.getSize();j++) {
			heap_test.add(mh.getHeap()[j].getPriority());
		}
		System.out.println("\nThe ArrayList is as follows:\n");
		System.out.println(heap_test);
		if(heap_test.get(0)==100) {
			System.out.println("\nThe priority stored in the first position of the array is: " + heap_test.get(0) + ".\nThe MaxHeap class works correctly.\n\nTest done.");
		}

	}	
}