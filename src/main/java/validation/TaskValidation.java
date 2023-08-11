package validation;

import validation.exception.InvalidTaskException;

public class TaskValidation {

	// Validating TaskName

	public boolean taskName(String taskName) throws InvalidTaskException {
		boolean match = false;

//		if(taskName ==null) {
//			return false;
//		}

//		String regex = "^[A-Za-z]\\w{2,29}$";
//		Pattern tName = Pattern.compile(regex);
//		Matcher m = tName.matcher(regex);
//		match = m.matches();

		if (taskName != null) {
			System.out.println("The Taskname is valid");
		} else {
			throw new InvalidTaskException("The Taskname Cannot be null , Enter a name for your Task");
		}
		return match;
	}

	// Validating task Description
	
	public boolean taskDesc(String taskDesc) throws InvalidTaskException {
		boolean match = false;
		if (taskDesc != null) {
			System.out.println("The Task Description is valid");
		} else {
			System.out.println("The task Description cannot be empty");
		}
		return match;
	}
	
	// Validating task Status
	
	public boolean taskStatus(String taskStatus) throws InvalidTaskException {
		boolean match = false;
		if (taskStatus == null)
			return match;

		String status = "PENDING";
		match = status.equalsIgnoreCase(status);
		if (match) {
			System.out.println("The task is PENDING");
		} else {
			System.out.println("The task is COMPLETED");
		}
		return match;
	}
}
