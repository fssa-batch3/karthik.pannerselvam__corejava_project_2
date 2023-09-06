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
			if (userDAO.createUser(user)) {
				System.out.println(user.getName() + "Successfully registered!");
				return true;
			} else {
				return false;
			}
		} catch (DAOException | InvalidUserException e) {
			throw new ServiceException(e);
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
	 * @param id
	 * @return User
	 * @throws ServiceException
	 */
	
	public User getUserById(int id) throws  ServiceException {
		UserDAO userDAO = new UserDAO();
		User user = new User();
		try {
			return userDAO.getUserByIdForUserDetails(id);
		} catch (DAOException e) {
			e.printStackTrace();
		}
	return user; 
	}
	

	 public boolean updateUser(User user) throws ServiceException {
		 UserDAO UserDAO = new UserDAO();
		 UserValidation uservalidate = new UserValidation();
	        try {
	        	uservalidate.validateUser(user);
	            return UserDAO.updateUser(user);
	        } catch (DAOException | InvalidUserException e ) {
	            throw new ServiceException(e);
	        }
	    }
	}
	

