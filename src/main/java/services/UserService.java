package services;
import model.User;
import validation.UserValidation;
import validation.exception.InvalidUserException;
import dao.UserDao;
import dao.exception.DAOException;
import services.exception.ServiceException;
public class UserService {
	public boolean registerUser(User user)throws ServiceException{
		UserDao userDAO = new UserDao();
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
}
