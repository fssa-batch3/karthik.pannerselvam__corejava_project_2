package services;

import java.util.List;

import dao.TaskDao;
import dao.exception.DAOException;
import model.Task;
import services.exception.ServiceException;
import validation.TaskValidation;
import validation.exception.InvalidTaskException;

public class TaskService {

	public boolean newTask(Task task) throws ServiceException {
		TaskDao taskdao = new TaskDao();
		TaskValidation taskvalidation = new TaskValidation();
		try {
			taskvalidation.taskName(task.getTaskName());
			if (taskdao.createTask(task)) {
				return true;
			} else {
				return false;
			}
		} catch (InvalidTaskException | DAOException e) {
			throw new ServiceException(e);
		}
	}

	/*
	 * List all task
	 */
	public List<Task> getAllTasks() throws ServiceException {
		List<Task> tasksFromDB;
		try {
			tasksFromDB = TaskDao.getAllTasks();
			for (Task task : tasksFromDB) {
				System.out.println(task.toString());
			}
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(),e);
		}
		return tasksFromDB;

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
