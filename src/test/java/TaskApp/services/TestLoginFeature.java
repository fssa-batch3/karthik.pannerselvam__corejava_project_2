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
	 void testLoginSuccess() {
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
	 void testEmptyEmailId() {

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
	 void testEmptyPassword() {

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
	 void testLoginEmailNotFound() {
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
	 void testGetUserByEmail() {
		 UserService UserService = new UserService();
	        // Arrange
	        String userId = "Karthik9@gmail.com";

	        // Act
	        User user;
			try {
				user = UserService.getUserByEmail(userId);
				 assertNotNull(user);
			        assertEquals(userId, user.getEmail());
			        assertNotNull(user.getName());
			        assertNotNull(user.getPassword());
			} catch (ServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}	       
	    }
	 
	    @Test
	     void testUpdateUser() {
	        // Creating a user object with updated information
	        User updatedUser = new User();
	        UserService userService = new UserService();
	        updatedUser.setId(1); // Replace with the actual user ID you want to update
	        updatedUser.setName("muthuSelvam");
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
