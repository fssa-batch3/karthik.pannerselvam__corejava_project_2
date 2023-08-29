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
			System.out.format("%-8s | %-25s | %-15s | %-10s%n", "Sr.No.", "Task Name", "Description", "Status");
			for (Task task : tasksFromDB) {
				System.out.format("%-8d | %-25s | %-15s | %s%n", task.getTaskName(), task.getTaskDesc(), task.getTaskStatus());
			}
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(),e);
		}
		return tasksFromDB;

	}
}
