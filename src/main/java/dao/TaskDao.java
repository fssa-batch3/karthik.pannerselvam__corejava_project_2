package dao;

import model.Task;
import util.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import dao.exception.DAOException;

/**
 * The TaskDao class provides data access methods for tasks, including creating,
 * updating, and retrieving tasks from the database. It interacts with the
 * database using JDBC and handles exceptions using the DAOException class.
 */
public class TaskDao {

	/**
	 * Creates a new task in the database.
	 *
	 * @param task The task to be created.
	 * @return True if the task is successfully created in the database, false
	 *         otherwise.
	 * @throws DAOException If there is an issue with the database operation.
	 */
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
	 * Updates an existing task in the database.
	 *
	 * @param task The task to be updated.
	 * @return True if the task is successfully updated in the database, false
	 *         otherwise.
	 * @throws DAOException If there is an issue with the database operation.
	 */
	public boolean updateTask(Task task) throws DAOException {
		try (Connection c = ConnectionDB.getConnect();
				PreparedStatement statement = c.prepareStatement(
						"UPDATE tasks SET taskname=?, task_status=?, task_description=? WHERE task_id=?")) {
			statement.setString(1, task.getTaskName());
			statement.setString(2, task.getTaskStatus());
			statement.setString(3, task.getTaskDesc());
			statement.setInt(4, task.getId());

			// Execute the query
			int rows = statement.executeUpdate();
			return (rows == 1);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	public Task getTaskById(int task_id) throws DAOException {
	    final String SELECTQUERY = "SELECT * FROM tasks WHERE task_id=?";
	    try (Connection c = ConnectionDB.getConnect(); PreparedStatement statement = c.prepareStatement(SELECTQUERY)) {
	        statement.setInt(1, task_id);
	        try (ResultSet rs = statement.executeQuery()) {
	            if (rs.next()) {
	                int id = rs.getInt("task_id");
	                String taskName = rs.getString("taskName");
	                String taskDescription = rs.getString("task_description");
	                String taskStatus = rs.getString("task_Status"); // Corrected typo here
	                Task task = new Task();
	                task.setId(id);
	                task.setTaskName(taskName);
	                task.setTaskDesc(taskDescription);
	                task.setTaskStatus(taskStatus);

	                return task;
	            }
	        }
	    } catch (SQLException e) {
	        // Handle the exception appropriately, e.g., log it or throw a DAOException
	        throw new DAOException("Error retrieving task by ID", e);
	    }
	    return null;
	}


	/**
	 * Retrieves a list of tasks associated with a user from the database.
	 *
	 * @param email The email of the user for whom tasks are to be retrieved.
	 * @return A list of tasks associated with the specified user.
	 * @throws DAOException If there is an issue with retrieving tasks from the
	 *                      database.
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
