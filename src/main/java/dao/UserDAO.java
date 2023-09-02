package dao;

/**
 * @author karthik

 *
 */
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.exception.DAOException;
import model.Task;
import model.User;
import util.ConnectionDB;
public class UserDAO {

	

//	Creating Statement and inserting the user's value
	public boolean createUser(User user) throws DAOException {

		final String query = "INSERT INTO users (username,email,password) VALUES (?,?,?)";
		int row = 0;
		
		try (PreparedStatement pst = ConnectionDB.getConnect().prepareStatement(query)) {

			pst.setString(1, user.getName());
			pst.setString(2, user.getEmail());
			pst.setString(3, user.getPassword());

			row = pst.executeUpdate();

			System.out.println("Rows affected: " + row);
			return row > 0;
		} catch (SQLException e) {
			throw new DAOException(e);
		}

	}

//	Delete the user
	public boolean deleteUser(String email) throws DAOException {

		final String deleteQuery = "DELETE FROM users where email=?";

		int row = 0;

		try (PreparedStatement std = ConnectionDB.getConnect().prepareStatement(deleteQuery)) {

			std.setString(1, email);

			row = std.executeUpdate();

			System.out.println("Deleted row: " + row);

		} catch (SQLException e) {
			throw new DAOException(e);
		}

		return row > 0;

	}

//	Getting the user's details by email id
	public boolean isEmailAlreadyExists(String email) throws DAOException {
		final String selectQuery = "SELECT username,email FROM users WHERE email = ?";

		try (PreparedStatement pstmt = ConnectionDB.getConnect().prepareStatement(selectQuery)) {

			pstmt.setString(1, email);

			try (ResultSet rs = pstmt.executeQuery()) {
				return rs.next(); // Return true if the email exists
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

//	Getting the email and password for log in
	private String userPasswordFromDb;

	public String getUserPasswordFromDb() {
		return userPasswordFromDb;
	}

	public void setUserPasswordFromDb(String userPasswordFromDb) {
		this.userPasswordFromDb = userPasswordFromDb;
	}

	public boolean isLogin(User user) throws DAOException {

		final String selectQuery = "SELECT email, password FROM user WHERE email = ?";

		try (PreparedStatement pstmt = ConnectionDB.getConnect().prepareStatement(selectQuery)) {

			pstmt.setString(1, user.getEmail());

			try (ResultSet rs = pstmt.executeQuery()) {
				String passwordfromDb = rs.getString("password");
				setUserPasswordFromDb(passwordfromDb);
				return rs.next();
			}

		} catch (SQLException e) {
			throw new DAOException("Error in loggin in", e);
		}

	}
	
	

	/*
	 * Getting all users
	 */

	public List<User> getAllUsers() throws DAOException {
	    List<User> users = new ArrayList<>();

	    try (Connection c = ConnectionDB.getConnect();
	         PreparedStatement statement = c.prepareStatement("SELECT * FROM user");
	         ResultSet resultSet = statement.executeQuery()) {

	        while (resultSet.next()) {
	            String userName = resultSet.getString("username");
	            String userEmail = resultSet.getString("email");

	            // create user object using retrieved data
	            users.add(new User(userName, userEmail));
	        }
	    } catch (SQLException e) {
	        throw new DAOException(e);
	    }

	    return users;
	}

}