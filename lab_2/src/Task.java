
/**
 * @author aidagomezbuenoberezo
 *
 */
public class Task implements TaskInterface, Comparable<Task> {

	private int priority;
	private TaskType type;
	private int waiting_time;
	private int hour_created;
	private String description;

	public Task() {
		super();
		this.priority = 0;
		this.waiting_time = 0;
	}
	
	public Task(TaskInterface.TaskType type, int priority) {
		super();
		this.type = type;
		this.priority = priority;
		this.waiting_time = 0;
	}

	public void setTaskType(TaskType type) {
		this.type = type;
	}

	public int getTaskHourCreated() {
		return hour_created;
	}

	public void setTaskHourCreated(int hourCreated) {
		this.hour_created = hourCreated;
	}

	public String getTaskDescription() {
		return description;
	}

	public void setTaskDescription(String description) {
		this.description = description;
	}

	public void setTaskWaitingTime(int waitingTime) {
		this.waiting_time = waitingTime;
	}

	public int getHour_created() {
		return hour_created;
	}

	public void setHour_created(int hour_created) {
		this.hour_created = hour_created;
	}

	@Override
	public int getPriority() {
		return this.priority;
	}

	@Override
	public void setPriority(int priority) {
		this.priority = priority;

	}

	@Override
	public void incrementWaitingTime() {
		this.waiting_time++;
	}

	@Override
	public void resetWaitingTime() {
		this.waiting_time = 0;
	}

	@Override
	public int getWaitingTime() {
		return this.waiting_time;
	}
	
	/**
	 * It returns -1 if Task o has greater priority or, if having the same priority, Task o was created earlier.
	 * It returns 1 if Task o has lower priority or, if having the same priority, Task o was created later.
	 */
	@Override
	public int compareTo(Task o) {
		int result = 0;
		int end = 0;
		result = this.getPriority()-o.getPriority();
		if(result<0) { //this es prioridad menor 
			end= -1;
		}
		if(result>0){
			end= 1;
		}
		if(result==0) {
			if(this.getHour_created()<o.getHour_created()) { //creado antes
				end = 1;
			}else if (this.getHour_created()>o.getHour_created()){
				end = -1;
			}
		}
		return end;
	}

	public String toString() {
		return "" + type + " " + description + " at Hour: " + hour_created + ":00";
	}

	@Override
	public TaskInterface.TaskType getTaskType() {
		return this.type;
	}



}
