package TaskApp.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import dao.exception.DAOException;
import model.User;
import services.UserService;
import services.exception.ServiceException;

public class TestLoginFeature {
	@Test
	public void testLoginSuccess() {
		try {
			UserService loginUser = new UserService();
			User user = new User();
			user.setEmail("muthu@gmail.com");
			user.setPassword("Karthik@123");
			
			assertEquals(user.getEmail(),loginUser.loginUser(user));
			
		} catch (ServiceException e) {
			fail("error while logging in "+e.getMessage());
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
	
	 @Test
	    public void testGetUserById() {
	        // Taking  user ID from Db for testing
	        int userId = 1; 
	        UserService userService = new UserService();
	        try {
	            User user = userService.getUserById(userId);

	            assertNotNull(user);
	            assertEquals(userId, user.getId());
	            assertNotNull(user.getName());
	            assertNotNull(user.getEmail());
	            assertNotNull(user.getPassword());

	        } catch (ServiceException e) {
	            fail("ServiceException should not be thrown: " + e.getMessage());
	        }
	    }
	 
	    @Test
	    public void testUpdateUser() {
	        // Creating a user object with updated information
	        User updatedUser = new User();
	        UserService userService = new UserService();
	        updatedUser.setId(1); // Replace with the actual user ID you want to update
	        updatedUser.setName("Muthu");
	        updatedUser.setEmail("muthu@gmail.com");
	        updatedUser.setPassword("Karthik@123");

	        try {
	            boolean result = userService.updateUser(updatedUser);
	            assertTrue(result); // Assert that the update was successful
	        } catch (ServiceException e) {
	            e.printStackTrace();
	            fail("Exception should not be thrown during update");
	        }
	    }
} 
