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
            Connection c = UserDAO.connect();
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
	
}
