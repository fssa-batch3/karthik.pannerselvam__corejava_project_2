package dao;

/**
 * @author karthik

 *
 */
import model.Task;
import util.ConnectionDB;

import java.util.ArrayList;

import java.sql.*;
import java.util.List;

import dao.exception.DAOException;

public class TaskDao {
 
	/**
	 * 
	 * @param task
	 * @return boolean
	 * @throws DAOException
	 */
	// Create Task
	public boolean createTask(Task task) throws DAOException {
		try (Connection c = ConnectionDB.getConnect();
				PreparedStatement statement = c.prepareStatement(
						"INSERT INTO tasks (taskname, task_status, task_description, user_email) VALUES (?, ?, ?, ?)")) {
			statement.setString(1, task.getTaskName());
			statement.setString(2, task.getTaskStatus());
			statement.setString(3, task.getTaskDesc());
			statement.setString(4, task.getUserEmail()); 

			// Execute the query
			int rows = statement.executeUpdate();
			return (rows == 1);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	/**
	 * 
	 * @param email
	 * @return List
	 * @throws DAOException
	 */
	public static List<Task> getAllTasks(String email) throws DAOException {
		List<Task> tasks = new ArrayList<>();
		try (Connection c = ConnectionDB.getConnect();
				PreparedStatement statement = c.prepareStatement("SELECT * FROM tasks WHERE user_email=?");) {
			statement.setString(1, email);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Task task = new Task();
				task.setTaskName(resultSet.getString("taskname"));
				task.setTaskStatus(resultSet.getString("task_status"));
				task.setTaskDesc(resultSet.getString("task_description"));
				task.setUserEmail(resultSet.getString("user_email"));
				task.setId(resultSet.getInt("task_id"));
 
				// Add the task to the list
				tasks.add(task);
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		}

		return tasks;
	}

}
