package TaskApp.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import model.User;
import services.UserService;
import services.exception.ServiceException;

public class TestLoginFeature {
	@Test
	public void testLoginSuccess() {
		try {
			UserService loginUser = new UserService();
			User user = new User();
			user.setEmail("karthik@gmail.com");
			user.setPassword("karthikumar");

			assertTrue(loginUser.loginUser(user));
		} catch (ServiceException e) {
//			e.printStackTrace();
			fail("error while logging in ");
		}
	}
	@Test
	public void testEmptyEmailId() {

		try {
			UserService loginUser = new UserService();

			User user = new User();
			user.setEmail("");
			user.setPassword("Passw0rd!");
			loginUser.loginUser(user);
		} catch (ServiceException e) {
			 e.getMessage();
		}
	}
	
	@Test
	public void testEmptyPassword() {

		try {
			UserService loginUser = new UserService();

			User user = new User();
			user.setEmail("Karthik9@gmail.com");
			user.setPassword("");
			loginUser.loginUser(user);
		} catch (ServiceException e) {
			e.getMessage();
		}
	}
	
	@Test
	public void testLoginEmailNotFound() {
		try {
			UserService loginUser = new UserService();
			
			User user = new User();
			user.setEmail("Unregisteredemail@gmail.com");
			user.setPassword("Imnew0@123");
			loginUser.loginUser(user);
		}catch(ServiceException e){
			e.getMessage();
		}
	}
}
