package services;

import dao.UserDAO;
import dao.exception.DAOException;
import model.User;
import services.exception.ServiceException;
import validation.UserValidation;
import validation.exception.InvalidUserException;

public class UserService {
	/**
	 * 
	 * @param user
	 * @return boolean
	 * @throws ServiceException
	 */
	public boolean registerUser(User user) throws ServiceException {
		UserDAO userDAO = new UserDAO();
		UserValidation uservalidation = new UserValidation();
		try {
			uservalidation.validateUser(user);
			User userExisting = userDAO.isEmailAlreadyExists(user.getEmail());
			if (userExisting != null) {
				throw new ServiceException("user already exists");
			}
			 
			return userDAO.createUser(user);
		
		} catch (DAOException | InvalidUserException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	/**
	 * 
	 * @param user
	 * @return String
	 * @throws ServiceException
	 */
	public String loginUser(User user) throws ServiceException {
		UserDAO userDAO = new UserDAO();
		UserValidation uValidation = new UserValidation();

		try {
			uValidation.validateEmail(user.getEmail());
			uValidation.validatePassword(user.getPassword());

			User use = userDAO.isLogin(user);
			if (!user.getEmail().equals(use.getEmail()))
				throw new ServiceException("email not exists");
			if (!user.getPassword().equals(use.getPassword()))
				throw new ServiceException("password not matched");
			return use.getEmail();

		} catch (InvalidUserException | DAOException e) {
			throw new ServiceException("Error in logging in");
		}
	}


	/**
	 * 
	 * @param user
	 * @return boolean
	 * @throws ServiceException
	 */
	public boolean updateUser(User user) throws ServiceException {
		UserDAO UserDAO = new UserDAO();
		UserValidation uservalidate = new UserValidation();
		try {
			uservalidate.validateUser(user);
			return UserDAO.updateUser(user);
		} catch (DAOException | InvalidUserException e) {
			throw new ServiceException(e);
		} 
	}

	/**
	 * 
	 * @param email
	 * @return User
	 * @throws ServiceException
	 */
	public User getUserByEmail(String email) throws ServiceException {
		UserDAO UserDao = new UserDAO();
		try {
			return UserDao.getUserByEmailForUserDetails(email);
		} catch (DAOException e) {
			throw new ServiceException("Error retrieving user by email");
		}
	}
}
