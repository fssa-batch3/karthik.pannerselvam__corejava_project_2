package validation;

import model.Task;
import validation.exception.InvalidTaskException;

/**
 * 
 * @author KarthikPannerSelvam
 *
 */

/**
 * Checking whether the entered Task is null
 */

public class TaskValidation {

	public boolean validateNewTask(Task task) throws InvalidTaskException {
		if (task == null) {
			throw new InvalidTaskException("Task is null");
		} else {
			return ValidtaskDesc(task.getTaskName()) && ValidtaskStatus(task.getTaskStatus());
		}
	}
	/*
	 * Validating TaskName
	 */

	public boolean taskName(String taskName) throws InvalidTaskException {

		if (taskName != null) {
			System.out.println("The Taskname is valid");
			return true;
		} else {
			throw new InvalidTaskException("The Taskname Cannot be null , Enter a name for your Task");
		}
		
	}

	// Validating task Description

	public boolean ValidtaskDesc(String taskDesc) throws InvalidTaskException {
		if (taskDesc != null) {
			System.out.println("The Task Description is valid");
			return true;
		} else {
			System.out.println("The task Description cannot be empty");
			throw new InvalidTaskException("The  Task Description cannot be empty");
		}
		
	}

	// Validating task Status

	public boolean ValidtaskStatus(String taskStatus) throws InvalidTaskException {
		boolean match = false;
		if (taskStatus == null)
			return match;

		return (taskStatus.equalsIgnoreCase("PENDING") || taskStatus.equalsIgnoreCase("COMPLETED"));
	}
}
