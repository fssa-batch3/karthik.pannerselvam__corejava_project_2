package dao;
/**
 * @author karthik

 *
 */
import model.Task;

import java.sql.*;

import dao.exception.DAOException;

public class TaskDao {
	
//	Create Task
	public boolean createTask(Task task) throws DAOException {
		try {
			  // Get connection
            Connection c = UserDao.connect();
            // Prepare SQL statement
            String insertQuery = "INSERT INTO Tasks (taskId,taskName,taskStatus) VALUES(?,?,?)";
            PreparedStatement statement = c.prepareStatement(insertQuery);
            statement.setInt(1, task.getId());
            statement.setString(2, task.getTaskName());
            statement.setString(3, task.getTaskStatus());
            
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
	public boolean deleteTask(Task task) throws DAOException{
		try {
			Connection c = UserDao.connect();
//			prepare sql Statement
			String deleteQuery = "DELETE FROM tasks where taskId=?";
			PreparedStatement statement = c.prepareStatement(deleteQuery);
			statement.setInt(1, task.getId());
			
			 // Execute the query
            int rows = statement.executeUpdate();
            
            statement.close();
            c.close();
            return(rows==1);
		}catch(SQLException e) {
			throw new DAOException(e);
		}
	}
	
}
