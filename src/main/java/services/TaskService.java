package services;
import dao.TaskDao;
import dao.exception.DAOException;
import model.Task;
import services.exception.ServiceException;
import validation.TaskValidation;
import validation.exception.InvalidTaskException;


public class TaskService {
	
	public boolean newTask(Task task) throws ServiceException{
		TaskDao taskdao = new TaskDao();
		TaskValidation taskvalidation = new TaskValidation();
		try {
			taskvalidation.taskName(task.getTaskName());
			if(taskdao.createTask(task)) {
				return true;
			}else {
				return false;
			}
		}catch(InvalidTaskException | DAOException e){
			throw new ServiceException(e);
		}
	} 
}
