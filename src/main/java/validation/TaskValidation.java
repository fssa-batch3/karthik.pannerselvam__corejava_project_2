package validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import validation.exception.InvalidTaskException;

public class TaskValidation {
	public  boolean TaskName(String taskName ) throws InvalidTaskException{
		boolean match = false;
		
		if(taskName ==null) {
			return false;
		}
		
		String regex = "^[A-Za-z]\\w{2,29}$";
		Pattern tName = Pattern.compile(regex);
		Matcher m = tName.matcher(regex);
		match = m.matches();
		
		if(match) {
			System.out.println("The Taskname is valid");
		} else {
			throw new InvalidTaskException("The Taskname is not valid");
		}
		return match;
	}
	
	
	
}
