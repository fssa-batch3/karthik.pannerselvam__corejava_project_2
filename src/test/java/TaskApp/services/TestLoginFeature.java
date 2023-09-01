package TaskApp.services;

import org.junit.jupiter.api.Test;

import model.User;
import services.UserService;

public class TestLoginFeature {
@Test
public void testLoginSuccess() {
	UserService loginUser = new UserService();
	User user = new User();
	user.setName("Ishu");
	user.setPassword("Ishu0@1");
}
}
