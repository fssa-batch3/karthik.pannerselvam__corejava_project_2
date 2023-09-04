package services;
import model.User;
import validation.UserValidation;
import validation.exception.InvalidUserException;
import dao.UserDAO;
import dao.exception.DAOException;
import services.exception.ServiceException;
public class UserService {
//	Registering User 
	public boolean registerUser(User user)throws ServiceException{
		UserDAO userDAO = new UserDAO();
		UserValidation uservalidation = new UserValidation();
		try {
			uservalidation.validateUser(user);
			if(userDAO.createUser(user)) {
				System.out.println(user.getName()+"Successfully registered!");
				return true;
			}else {
				return false;
			}
		}catch (DAOException | InvalidUserException e) {
			throw new ServiceException(e);
		}
	}
	
//	Logging in User
	public boolean loginUser(User user)throws ServiceException{
		UserDAO userDAO = new UserDAO();
		UserValidation uValidation = new UserValidation();
		  
		try {
			uValidation.validateEmail(user.getEmail());
			uValidation.validatePassword(user.getPassword());
			
			userDAO.isLogin(user);
			
			return userDAO.getUserPasswordFromDb().equals(user.getPassword());
		}catch(InvalidUserException | DAOException e){
			e.printStackTrace();
			throw new ServiceException("Error in logging in", null);
		}
	}
}
