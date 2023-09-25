	package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import dao.exception.DAOException;
import model.Task;
import services.TaskService;
import services.exception.ServiceException;
import util.ConnectionDB;

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
						"INSERT INTO tasks (taskname, task_status, task_description,task_priority,user_email,assigned_to,start_date,end_date) VALUES (?, ?, ?, ?,?,?,?,?)")) {
			statement.setString(1, task.getTaskName());
			statement.setString(2, task.getTaskStatus());
			statement.setString(3, task.getTaskDesc());
			statement.setString(4, task.getTaskPriority());
			statement.setString(5, task.getUserEmail());
			statement.setString(6, task.getAssignee());
			statement.setDate(7, Date.valueOf(task.getStartDate()));
			statement.setDate(8, Date.valueOf(task.getEndDate()));

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
						"UPDATE tasks SET taskname=?, task_status=?, task_description=?, task_priority=?, start_date=?, end_date=? WHERE task_id=?")) {
			statement.setString(1, task.getTaskName());
			statement.setString(2, task.getTaskStatus());
			statement.setString(3, task.getTaskDesc());
			statement.setString(4, task.getTaskPriority());
			statement.setDate(5, Date.valueOf(task.getStartDate()));
			statement.setDate(6, Date.valueOf(task.getEndDate()));
			statement.setInt(7, task.getId());

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
	                String taskStatus = rs.getString("task_Status");
	                String taskPriority = rs.getString("task_priority");
	                Date startDate = rs.getDate("start_date"); // Assuming start_date is of DATE type in your database
	                Date endDate = rs.getDate("end_date");
	                LocalDate localStartDate = startDate.toLocalDate();
	                LocalDate localEndDate = endDate.toLocalDate();// Assuming end_date is of DATE type in your database
	                
	                Task task = new Task();
	                task.setId(id);
	                task.setTaskName(taskName);
	                task.setTaskDesc(taskDescription);
	                task.setTaskStatus(taskStatus);
	                task.setTaskPriority(taskPriority);
	                task.setStartDate(localStartDate);
	                task.setEndDate(localEndDate);
	                
	                
	                
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
	         PreparedStatement statement = c.prepareStatement("SELECT * FROM tasks WHERE user_email=? OR assigned_to=?");) {
	        statement.setString(1, email);
	        statement.setString(2, email);
	        ResultSet rst = statement.executeQuery();
	        while (rst.next()) {
	            Task task = new Task();
	            task.setTaskName(rst.getString("taskname"));
	            task.setTaskStatus(rst.getString("task_status"));
	            task.setTaskDesc(rst.getString("task_description"));
	            task.setUserEmail(rst.getString("user_email"));
	            task.setId(rst.getInt("task_id"));
	            task.setTaskPriority(rst.getString("task_priority"));
	            task.setAssignee(rst.getString("assigned_to"));

	            // Check if the start_date and end_date are not null before converting
	            Date startDateSql = rst.getDate("start_date");
	            Date endDateSql = rst.getDate("end_date");

	            if (startDateSql != null) {
	                task.setStartDate(startDateSql.toLocalDate());
	            }
	            
	            if (endDateSql != null) {
	                task.setEndDate(endDateSql.toLocalDate());
	            }

	            // Add the task to the list
	            tasks.add(task);
	        }
	    } catch (SQLException e) {
	        throw new DAOException(e);
	    }

	    return tasks;
	}
}

