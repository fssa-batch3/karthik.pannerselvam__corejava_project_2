package dao;

/**
 * @author karthik

 *
 */

import java.sql.*;

import io.github.cdimascio.dotenv.Dotenv;


import java.util.ArrayList;

import dao.exception.DAOException;
import model.User;

public class UserDAO {

// Getting Connection
    public static Connection connect() throws DAOException {
        Connection connect = null;
        
		String DB_URL;
		String DB_USER;
		String DB_PASSWORD;
 
		if (System.getenv("CI") != null) {
			DB_URL = System.getenv("DB_URL");
			DB_USER = System.getenv("DB_USER");
			DB_PASSWORD = System.getenv("DB_PASSWORD");
		} else {
			Dotenv env = Dotenv.load();
			DB_URL = env.get("DB_URL");
			DB_USER = env.get("DB_USER");
			DB_PASSWORD = env.get("DB_PASSWORD");
		}

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/taskapp", "root", "123456");
        } catch (ClassNotFoundException | SQLException e) {
            throw new DAOException(e);
        }

        return connect;
    }

//	Creating Statement and inserting the user's value
	public boolean createUser(User user) throws DAOException {

		final String QUERY = "INSERT INTO users (name,email,password) VALUES (?,?,?)";
		int row = 0;
		try (PreparedStatement std = connect().prepareStatement(QUERY)) {

			std.setString(1, user.getName());
			std.setString(2, user.getEmail());
			std.setString(3, user.getPassword());

			row = std.executeUpdate();
		
			System.out.println("Rows affected: " + row);
			return row > 0;
		} catch (SQLException e) {
			throw new DAOException(e);
		}

	}

//	Delete the user
	public boolean deleteUser(String email) throws DAOException {

		final String DELETEQUERY = "DELETE FROM users where email=?";

		int row = 0;

		try (PreparedStatement std = connect().prepareStatement(DELETEQUERY)) {

			std.setString(1, email);

			row = std.executeUpdate();

			System.out.println("Deleted row: " + row);

		} catch (SQLException e) {
			throw new DAOException(e);
		}

		return row > 0;

	}

////	Getting the register user's details
//	public ArrayList<User> regiteredUsersList() throws DAOException {
//
//		ArrayList<User> users = new ArrayList<>();
//		final String SELECTQUERY = "Select * from users";
//		try (Statement std = connect().createStatement(); ResultSet rs = std.executeQuery(SELECTQUERY)) {
//
//			while (rs.next()) {
//
//				String email = rs.getString("email");
//				String password = rs.getString("password");
//				users.add(new User(email, password));
//			}
//
//		} catch (SQLException e) {
//			throw new DAOException(e);
//		}
//
//		return users;
//
//	}

//	Getting the user's details by email id
	public boolean isEmailAlreadyExists(String email) throws DAOException {
		final String SELECTQUERY = "SELECT email FROM users WHERE email = ?";

		try (PreparedStatement pstmt = connect().prepareStatement(SELECTQUERY)) {

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

		final String SELECTQUERY = "SELECT email, password FROM user WHERE email = ?";

		try (PreparedStatement pstmt = connect().prepareStatement(SELECTQUERY)) {

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

}