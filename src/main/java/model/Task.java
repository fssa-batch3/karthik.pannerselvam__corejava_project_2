package model;

public class Task {
	
	private int task_id;
	private  String taskName;
	private  String taskStatus;
	private  String taskDesc;
	private String userEmail;

	public Task(String taskName, String taskStatus, String taskDesc, String userEmail) {
		super();
		this.taskName = taskName;
		this.taskStatus = taskStatus;
		this.taskDesc = taskDesc;
		this.userEmail  = userEmail;
	}
	
	public Task() {
		
	}

	@Override
	public String toString() {
		return "Task [taskName=" + taskName + ", taskStatus=" + taskStatus + ", taskDesc=" + taskDesc + ", userEmail="
				+ userEmail + "]";
	}

	

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
	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public int getId() {
		return task_id;
	}

	public void setId(int id) {
		this.task_id = id;
	}

}
