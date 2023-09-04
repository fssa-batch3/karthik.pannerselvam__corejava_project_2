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
	
//	Create Task
	public boolean createTask(Task task) throws DAOException {
		try {
			  // Get connection
            Connection c = ConnectionDB.getConnect();
            // Prepare SQL statement
            String insertQuery = "INSERT INTO tasks (taskname,task_status,task_description,user_email) VALUES(?,?,?,?)";
            PreparedStatement statement = c.prepareStatement(insertQuery);
            statement.setString(1, task.getTaskName());
            statement.setString(2, task.getTaskStatus());
            statement.setString(3, task.getTaskDesc());
            statement.setString(4, task.getUserEmail());

            // Execute the query
            int rows = statement.executeUpdate();
            
            statement.close();
            c.close();
            return(rows==1);
		}catch (SQLException e) {
			throw new DAOException(e);
		}
	}
	
//	Delete task
//	public boolean deleteTask(Task task) throws DAOException{
//		try {
//			Connection c = UserDAO.connect();
////			prepare sql Statement
//			String deleteQuery = "DELETE FROM tasks where task_id=?";
//			PreparedStatement statement = c.prepareStatement(deleteQuery);
//			statement.setInt(1, task.gettask_id());
//			
//			 // Execute the query
//            int rows = statement.executeUpdate();
//            
//            statement.close();
//            c.close();
//            return(rows==1);
//		}catch(SQLException e) {
//			throw new DAOException(e);
//		}
//	}
	public static List<Task> getAllTasks() throws DAOException {
	    List<Task> tasks = new ArrayList<>();
	    try (Connection c = ConnectionDB.getConnect();
	         PreparedStatement statement = c.prepareStatement("SELECT * FROM tasks");
	         ResultSet resultSet = statement.executeQuery()) {

	        while (resultSet.next()) {
	            Task task = new Task();
	            task.setTaskName(resultSet.getString("taskname"));
	            task.setTaskStatus(resultSet.getString("task_status"));
	            task.setTaskDesc(resultSet.getString("task_description"));
	            task.setUserEmail(resultSet.getString("user_email"));

	            // Add the task to the list
	            tasks.add(task);
	        }
	    } catch (SQLException e) {
	        throw new DAOException(e);
	    }

	    return tasks;
	}
	
	public static void main(String args[])  {
		TaskDao task = new TaskDao();
		Task newTask = new Task();
		newTask.setTaskName("Finish the project backend");
		newTask.setTaskStatus("PENDING");
		newTask.setTaskDesc("Complete the test case part of the Add task feature");
		newTask.setUserEmail("Karthik@gmail.com");
		try {
			task.createTask(newTask);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
