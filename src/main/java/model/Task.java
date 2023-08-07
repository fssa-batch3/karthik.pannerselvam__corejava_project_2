package model;

public class Task {
	private static String taskName;
	private static String taskStatus;
	private int id;

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		Task.taskName = taskName;
	}

	public String getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(String taskStatus) {
		Task.taskStatus = taskStatus;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
