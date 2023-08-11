package model;

public class Task {
	private static String taskName;
	private static String taskStatus;
	private static String taskDesc;
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

	public static String getTaskDesc() {
		return taskDesc;
	}

	public static void setTaskDesc(String taskDesc) {
		Task.taskDesc = taskDesc;
	}
}
