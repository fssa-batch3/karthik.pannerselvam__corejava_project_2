package services;

import dao.UserDAO;

import dao.exception.DAOException;
import util.Passwordutil;
import model.User;
import services.exception.ServiceException;
import validation.UserValidation;
import validation.exception.InvalidUserException;

public class UserService {
	
	  private UserDAO userDAO;
	    private UserValidation uValidation;

	    public UserService() {
	        userDAO = new UserDAO();
	        uValidation = new UserValidation();
	    }

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
	            // Hash the user's password before storing it
	            String hashedPassword = Passwordutil.hashPassword(user.getPassword());
	            user.setPassword(hashedPassword);


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
//	public String loginUser(User user) throws ServiceException {
//		UserDAO userDAO = new UserDAO();
//		UserValidation uValidation = new UserValidation();
//
//		try {
//			uValidation.validateEmail(user.getEmail());
//			uValidation.validatePassword(user.getPassword());
//
//			User use = userDAO.isLogin(user);
//			if (!user.getEmail().equals(use.getEmail()))
//				throw new ServiceException("email not exists");
//			if (!user.getPassword().equals(use.getPassword()))
//				throw new ServiceException("password not matched");
//			return use.getEmail();
//
//		} catch (InvalidUserException | DAOException e) {
//			throw new ServiceException("Error in logging in");
//		}
//	}
//	
	
	
	
		public String loginUser(User user) throws ServiceException {
		    try {
		        uValidation.validateEmail(user.getEmail());
		        uValidation.validatePassword(user.getPassword());

		        User fromDb = userDAO.getUserByEmailForUserDetails(user.getEmail());

		        if (fromDb != null && Passwordutil.checkPassword(user.getPassword(), fromDb.getPassword())) {
		            String taskAssignee = userDAO.getTaskAssigneeByEmail(user.getEmail());
		            return taskAssignee;
		        } else {
		            throw new ServiceException("Invalid User Credentials");
		        }
		    } catch (InvalidUserException | DAOException e) {
		        throw new ServiceException(e);
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
			User user = null;
			user = UserDao.getUserByEmailForUserDetails(email);
			return user;
		} catch (DAOException e) {
			throw new ServiceException("Error retrieving user by email");
		}
	}
	
	
	public int getUserCountByEmail1(String email) throws ServiceException {
	    UserDAO userDao = new UserDAO();
	    try {
	        int rowCount = userDao.getUserCountByEmailForUserDetails1(email);
	        return rowCount;
	    } catch (DAOException e) {
	        throw new ServiceException("Error retrieving user count by email");
	    }
	}


}
