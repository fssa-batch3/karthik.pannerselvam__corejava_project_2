package TaskApp.Validation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import model.Task;
import validation.TaskValidation;
import validation.exception.InvalidTaskException;

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
		task.setTaskDesc("Completed the demo task");
		try {
			assertTrue(taskValidator.validateNewTask(task));
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
		task.setTaskDesc("Attach the file while studying");

		try {
			taskValidator.validateNewTask(task);
		} catch (InvalidTaskException e) {
			assertEquals("Given TaskName is Empty", e.getMessage());
		}
	}

	/*
	 * Testing the Empty task 
	 */
	@Test
	public void testTaskValidatorOnTaskNull() {
		Task task = null;
		try {
			taskValidator.validateNewTask(task);
		}catch(InvalidTaskException e){
			assertEquals("Task is null",e.getMessage());
		}
	}
}
