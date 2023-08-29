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
            String insertQuery = "INSERT INTO tasks (taskname,task_status,task_description) VALUES(?,?,?)";
            PreparedStatement statement = c.prepareStatement(insertQuery);
            statement.setString(1, task.getTaskName());
            statement.setString(2, task.getTaskStatus());
            statement.setString(3, task.getTaskDesc());
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

        try {
            // Get connection
            Connection c = ConnectionDB.getConnect();
            
            // Prepare SQL statement
            String selectQuery = "SELECT * FROM tasks";
            PreparedStatement statement = c.prepareStatement(selectQuery);
            
            // Execute the query
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Task task = new Task();
                task.setTaskName(resultSet.getString("taskname"));
                task.setTaskStatus(resultSet.getString("task_status"));
                task.setTaskDesc(resultSet.getString("task_description"));
        
                // Add the task to the list
                tasks.add(task);
            }

            resultSet.close();
            statement.close();
            c.close();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
     
        return tasks;
    }
}
