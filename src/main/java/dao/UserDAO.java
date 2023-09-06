package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.exception.DAOException;
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

	/**
	 * 
	 * @param email
	 * @return Exception if there is email already exist while registering
	 * @throws DAOException
	 */
	public boolean isEmailAlreadyExists(String email) throws DAOException {
		final String selectQuery = "SELECT email FROM users WHERE email = ?";

		try (PreparedStatement pstmt = ConnectionDB.getConnect().prepareStatement(selectQuery)) {

			pstmt.setString(1, email);

			try (ResultSet rs = pstmt.executeQuery()) {
				return rs.next(); // Return true if the email exists
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	public User getUserByIdForUserDetails(int id) throws DAOException {

		final String selectQuery = "SELECT * From users WHERE user_id= ?";
		User user = new User();
		try (PreparedStatement pstmt = ConnectionDB.getConnect().prepareStatement(selectQuery)) {

			pstmt.setInt(1, id);

			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					user.setName(rs.getString("username"));
					user.setEmail(rs.getString("email"));
					user.setId(rs.getInt("user_id"));
					user.setPassword(rs.getString("password"));
				}

			}
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		return user;
	}

	private String userPasswordFromDb;

	public String getUserPasswordFromDb() {
		return userPasswordFromDb;
	}

	public void setUserPasswordFromDb(String userPasswordFromDb) {
		this.userPasswordFromDb = userPasswordFromDb;
	}

	public User isLogin(User user) throws DAOException {

		final String selectQuery = "SELECT user_id,email,password FROM users WHERE email = ?";

		try (PreparedStatement pstmt = ConnectionDB.getConnect().prepareStatement(selectQuery)) {

			pstmt.setString(1, user.getEmail());
			User use = new User();

			try (ResultSet rs = pstmt.executeQuery()) {

				if (rs.next()) {
					use.setEmail(rs.getString("email"));
					use.setPassword(rs.getString("password"));
					use.setId(rs.getInt("user_id"));
					return use;
				}

			}
			return use;
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
				PreparedStatement statement = c.prepareStatement("SELECT * FROM users");
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

	/**
	 * 
	 * @param user
	 * @return boolean
	 * @throws DAOException
	 */
	public boolean updateUser(User user) throws DAOException {
		final String updateQuery = "UPDATE users SET username=?, password=? WHERE email=?";

		int rowsUpdated = 0;

		try (PreparedStatement pstmt = ConnectionDB.getConnect().prepareStatement(updateQuery)) {
			pstmt.setString(1, user.getName());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getEmail());

			rowsUpdated = pstmt.executeUpdate();

			System.out.println("Rows updated: " + rowsUpdated);
		} catch (SQLException e) {
			throw new DAOException(e);
		}

		return rowsUpdated > 0;
	}
}