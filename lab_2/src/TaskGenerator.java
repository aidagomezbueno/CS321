import java.util.Random;

public class TaskGenerator implements TaskGeneratorInterface {

	private int currentEnergyStorage;
	private double dying_prob = 0;
	private double passing_out_prob = 0;
	private Task new_task;
	Random r;
	double taskGenerationProbability;

	public TaskGenerator(double taskGenerationProbability, long seed) {
		resetCurrentEnergyStorage();
		this.r = new Random(seed);
		this.taskGenerationProbability = taskGenerationProbability;
	}

	public TaskGenerator(double taskGenerationProbability) {
		resetCurrentEnergyStorage();
		this.taskGenerationProbability = taskGenerationProbability;
	}


	/**
     * Creates a new Task with default zero priority.
     *
     * @param hourCreated hour that the Task was created
     * @param taskType type of the Task
     * @param taskDescription the Task's description
     */
	@Override
	public Task getNewTask(int hourCreated, TaskInterface.TaskType taskType, String taskDescription) {
		Task t = new Task();
		t.setTaskType(taskType);
		t.setHour_created(hourCreated);
		t.setTaskDescription(taskDescription);
		this.new_task = t;
		return this.new_task;
	}

	/**
     * Decrements the total energy storage based on the current task.
     *
     * @param taskType - the type of Task
     */
	@Override
	public void decrementEnergyStorage(TaskInterface.TaskType taskType) {
		this.currentEnergyStorage -= taskType.getEnergyPerHour();

	}

	/**
     * Resets the total energy storage to DEFAULT_ENERGY level.
     */
	@Override
	public void resetCurrentEnergyStorage() {
		this.currentEnergyStorage = DEFAULT_ENERGY;

	}

	/**
     * Returns the current energy storage.
     *
     * @returns current energy storage
     */
	@Override
	public int getCurrentEnergyStorage() {
		return this.currentEnergyStorage;
	}

	/**
     * Sets the current energy storage.
     *
     * @param newEnergyNum number to set the energy
     */
	@Override
	public void setCurrentEnergyStorage(int newEnergyNum) {
		this.currentEnergyStorage = newEnergyNum;

	}

	/**
     * Determines if a new task is to be generated.
     *
     * @return true or false - if task should be generated
     */
	@Override
	public boolean generateTask() {
		boolean generate_task = false;
		if(r.nextDouble()<taskGenerationProbability) {
			generate_task = true;
		}
		return generate_task;
		
	}

	/**
     * Determines the likelihood of passing out or dying
     * based on the task and death probability.  Where
     * 0 is not dying or passing out, 1 is passing out,
     * and 2 is dying in the Mines.
     *
     * @param task - the Task
     * @returns  0, 1 or 2 depending on the unluckiness
     */
	@Override
	public int getUnlucky(Task task, double unluckyProbability) {
		dying_prob = task.getTaskType().getDyingProbabilityProbability();
		passing_out_prob = task.getTaskType().getPassingOutProbability();
		if(unluckyProbability<=passing_out_prob) {
			if(unluckyProbability<=dying_prob && task.getTaskType().equals(TaskInterface.TaskType.MINING)) {
				currentEnergyStorage = currentEnergyStorage/4;
				task.setPriority(0);
				return 2;
			}else {
				currentEnergyStorage=currentEnergyStorage/2;
				return 1;
			}
		}else {
			return 0;
		}
	}

	/**
	 * Create a String containing the Task's information.
	 *
	 * @param task - the Task
	 * @param taskType - the Task's type
	 */
	@Override
	public String toString(Task task, Task.TaskType taskType) {
		if(taskType == Task.TaskType.MINING) {
			return " Mining " + task.getTaskDescription() + " at " +
					currentEnergyStorage + " energy points (Priority:" + task.getPriority() +")";
		}
		if(taskType == Task.TaskType.FISHING) {
			return " Fishing " + task.getTaskDescription() + " at " +
					currentEnergyStorage + " energy points (Priority:" + task.getPriority() +")" ;
		}
		if(taskType == Task.TaskType.FARM_MAINTENANCE) {
			return " Farm Maintenance " + task.getTaskDescription() + " at " + currentEnergyStorage + " energy points (Priority:" + task.getPriority()
			+")";
		}
		if(taskType == Task.TaskType.FORAGING) {
			return " Foraging " + task.getTaskDescription() + " at " +
					currentEnergyStorage + " energy points (Priority:" + task.getPriority() +")" ;
		}
		if(taskType == Task.TaskType.FEEDING) {
			return " Feeding " + task.getTaskDescription() + " at " +
					currentEnergyStorage + " energy points (Priority:" + task.getPriority() +")";
		}
		if(taskType == Task.TaskType.SOCIALIZING) {
			return " Socializing " + task.getTaskDescription() + " at " +
					currentEnergyStorage + " energy points (Priority:" + task.getPriority() +")";
		}
		else { return "nothing to see here..."; }
	}

}
