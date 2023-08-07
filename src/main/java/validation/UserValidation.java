package validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import validation.exception.InvalidUserException;

import model.User;

public class UserValidation {
	
	public  boolean validateUser(User user) throws InvalidUserException{
		
		if(user !=null && validateName(user.getName()) && validatePassword(user.getPassword()) && validateEmail(user.getEmail())) {
			return true;
		}else {
			throw new InvalidUserException("User details not valid");
		}
	}
		public static boolean validateName(String name) {
			boolean match = false;
			if(name==null)
				return false;
			String regex = "^[A-Za-z]\\w{2,29}$";
			Pattern pattern = Pattern.compile(regex);
			Matcher compare = pattern.matcher(name);
			match = compare.matches();
			if(match) {
				System.out.println("They given username is valid.");
			}else {
				System.out.println("They given username is Invalid.");
			}
			return false;
		}


public static boolean validatePassword(String password) {
	boolean match = false;
	if(password == null)
		return false;
	String regex = "(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=])(?=.*[^\\s]).{8,}$";
	match = Pattern.matches(regex, password);
	if(match) {
		System.out.println("Valid Password");
	}else {
		System.out.println("Invalid password");
	}
	return match;
}

public static boolean  validateEmail(String email) {
	boolean isMatch = false;
	
	if(email == null) 
		return false;
	String regex = "^.*@.*\\..*$";
	isMatch = Pattern.matches(regex, email);
	if(isMatch) {
		System.out.println("The email address is: Valid");
	}else {
		System.out.println("The email address is: Invalid");
	}
	return isMatch;
}
}