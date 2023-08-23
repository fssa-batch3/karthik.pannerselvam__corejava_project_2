package model;

public class Task {
	private  String taskName;
	private  String taskStatus;
	private  String taskDesc;
	private int task_id;

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

	public int gettask_id() {
		return task_id;
	}

	public void setId(int task_id) {
		this.task_id = task_id;
	}

	public  String getTaskDesc() {
		return taskDesc;
	}

	public  void setTaskDesc(String taskDesc) {
		this.taskDesc = taskDesc;
	}
}
