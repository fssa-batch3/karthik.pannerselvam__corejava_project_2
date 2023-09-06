package validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dao.UserDAO;
import dao.exception.DAOException;
import model.User;
import validation.exception.InvalidUserException;

public class UserValidation {

	public boolean validateUser(User user) throws InvalidUserException {

		if (user != null && validateName(user.getName()) && validatePassword(user.getPassword())
				&& validateEmail(user.getEmail()) && isEmailExist(user.getEmail())) {

			return true;
		} else {
			throw new InvalidUserException("User details not valid");
		}
	}

//	public boolean validateLogin()
	public boolean validateName(String name) {
		boolean match = false;
		if (name == null)
			return false;
		String regex = "^[A-Za-z][A-Za-z0-9_]{2,29}$";

		Pattern pattern = Pattern.compile(regex);
		Matcher compare = pattern.matcher(name);
		match = compare.matches();
		if (match) {
			System.out.println("They given username is valid.");
			return true;
		} else {
			System.out.println("They given username is Invalid.");
			return false;
		}

	}

	public boolean validatePassword(String password) {
		if (password == null || password.isEmpty()) {
			System.out.println("Invalid password: Password is null or empty");
			return false;
		}

		String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=])(?=.*[^\\s]).{8,}$";

		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(password);

		if (matcher.matches()) {
			System.out.println("Valid Password");
			return true;
		} else {
			System.out.println("Invalid password: Password does not meet complexity requirements");
			return false;
		}
	}

	public boolean validateEmail(String email) throws InvalidUserException {
		boolean isMatch = false;

		if (email == null)
			return false;
		String regex = "^.*@.*\\..*$";

		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(email);

		isMatch = matcher.matches();
		if (isMatch) {
			System.out.println("The email address is: Valid");
		} else {

			throw new InvalidUserException("The email address is: Invalid");
		}
		return isMatch;
	}

	public boolean isEmailExist(String email) throws InvalidUserException {

		UserDAO userDAO = new UserDAO();

		try {
			if (userDAO.isEmailAlreadyExists(email))
				return true;

		} catch (DAOException e)  {
			e.printStackTrace();
			throw new InvalidUserException(e);
		}
		return true;

	}

}