package model;

public class Task {

	public Task(String taskName, String taskStatus, String taskDesc) {
		super();
		this.taskName = taskName;
		this.taskStatus = taskStatus;
		this.taskDesc = taskDesc;
	}
	
	public Task() {
		
	}

	@Override
	public String toString() {
		return "Task [taskName=" + taskName + ", taskStatus=" + taskStatus + ", taskDesc=" + taskDesc + "]";
	}

	private  String taskName;
	private  String taskStatus;
	private  String taskDesc;

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}

	
	public  String getTaskDesc() {
		return taskDesc;
	}

	public  void setTaskDesc(String taskDesc) {
		this.taskDesc = taskDesc;
	}
}
