package validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import validation.exception.InvalidTaskException;

public class TaskValidation {
	public boolean taskName(String taskName) throws InvalidTaskException {
		boolean match = false;

		if (taskName == null) {
			return false;
		}

		String regex = "^[A-Za-z]\\w{2,200}$";
		Pattern tName = Pattern.compile(regex);
		Matcher m = tName.matcher(regex);
		match = m.matches();

		if (match) {
			System.out.println("The Taskname is valid");
		} else {
			throw new InvalidTaskException("The Taskname is not valid");
		}
		return match;
	}

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
