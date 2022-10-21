
public class MyPriorityQueue extends MaxHeap implements PriorityQueueInterface {

	/**
     * Adds a task to the rear of the list.
     * via MaxHeap insert()
     *
     * @param task - Task to enqueue
     */
	@Override
	public void enqueue(Object task) {
		// TODO Auto-generated method stub
		insert((Task)task);
	}

	/**
     * Removes the front element from the list.
     * via MaxHeap ExtractMax()
     *
     * @return first element in the Task array
     */
	@Override
	public Task dequeue() {
		Task t = new Task();
		t = (Task)extractMax();
		return t;
	}

	/**
     * Boolean for if the Heap is empty.
     *
     * @return true or false - depending on if the Heap is empty
     */
	@Override
	public boolean isEmpty() {
		return isempty();
	}

	/**
     * Increase the priorities for a given process in the queue if it has waited enough 
     * time but not to exceed its maximum priority.
     *
     * @param timeToIncrementPriority how long it should have waited for
     * @param maxPriority  the maximum priority value the process can have
     */
	@Override
	public void update(int timeToIncrementPriority, int maxPriority) {
		// TODO Auto-generated method stub
		int priority = 0;
		int waiting_time = 0;
		int i = 0;
		for(Task t: this.getHeap()) {
			if(t!=null&&i<super.getSize()) {
				waiting_time = t.getWaitingTime();
				waiting_time++;
				t.setTaskWaitingTime(waiting_time);
				if(waiting_time>=timeToIncrementPriority) {
					t.resetWaitingTime();
					if(t.getPriority()<maxPriority) {
						priority = t.getPriority();
						priority++;
						t.setPriority(priority);
						buildMaxHeap();
					}
				}
			}
			i++;
		}
	}
}
