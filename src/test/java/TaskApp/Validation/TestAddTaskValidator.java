package TaskApp.Validation;

import validation.TaskValidation;
import validation.exception.InvalidTaskException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import model.Task;

public class TestAddTaskValidator {
	TaskValidation taskValidator = new TaskValidation();

	/*
	 * Testing the Task are validated
	 */
	@Test
	public void testTaskValidatorSuccess() {
		Task task = new Task();
		task.setTaskName("Demo Task");
		task.setTaskStatus("COMPLETED");
		try {
			taskValidator.validateNewTask(task);
		} catch (InvalidTaskException e) {
			fail();
		}
	}

	@Test
	/*
	 * Testing the Empty task Name
	 */
	public void testTaskValidatorOnTaskNameEmpty() {
		Task task = new Task();
		task.setTaskName("");
		task.setTaskStatus("PENDING");

		try {
			taskValidator.validateNewTask(task);
		} catch (InvalidTaskException e) {
			assertEquals("Given TaskName is Empty", e.getMessage());
		}
	}

	/*
	 * Testing the Empty task Name
	 */
	@Test
	public void testTaskValidatorOnTaskNull() {
		Task task = null;
		try {
			taskValidator.validateNewTask(task);
		}catch(InvalidTaskException e){
			assertEquals("Task is Null",e.getMessage());
		}
	}
}
