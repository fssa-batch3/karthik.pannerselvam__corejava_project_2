package model;

import java.time.LocalDate;

public class Task {

	private int task_id;
	private String taskName;
	private String taskStatus;
	private String taskDesc;
	private String userEmail;
	private String taskPriority;
	private String assignee;
	private LocalDate startDate;
	private LocalDate endDate;

	public Task(String taskName, String taskStatus, String taskDesc, String userEmail, String taskPriority,
			String assignee, LocalDate startDate , LocalDate endDate) {
		super();
		this.taskName = taskName;
		this.taskStatus = taskStatus;
		this.taskDesc = taskDesc;
		this.userEmail = userEmail;
		this.taskPriority = taskPriority;
		this.assignee = assignee;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public Task() {

	}

	@Override
	public String toString() {
		return "Task [task_id=" + task_id + ", taskName=" + taskName + ", taskStatus=" + taskStatus + ", taskDesc="
				+ taskDesc + ", userEmail=" + userEmail + ", taskPriority=" + taskPriority + ", assignee=" + assignee
				+ "]";
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

	public String getTaskDesc() {
		return taskDesc;
	}

	public void setTaskDesc(String taskDesc) {
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

	public String getTaskPriority() {
		return taskPriority;
	}

	public void setTaskPriority(String taskPriority) {
		this.taskPriority = taskPriority;
	}

	public String getAssignee() {
		return assignee;
	}

	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}
	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
}
