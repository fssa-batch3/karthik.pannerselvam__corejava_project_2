/**
 * This class contains JUnit tests for the validation of tasks using the TaskValidation class.
 * It tests various scenarios for task validation, including success cases and cases with validation failures.
 */
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

    /**
     * Test case to validate that a task with valid information is successfully validated.
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

    /**
     * Test case to validate that an empty task name results in a validation failure.
     * It checks whether the InvalidTaskException is thrown with the expected error message.
     */
    @Test
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

    /**
     * Test case to validate that a null task results in a validation failure.
     * It checks whether the InvalidTaskException is thrown with the expected error message.
     */
    @Test
    public void testTaskValidatorOnTaskNull() {
        Task task = null;
        try {
            taskValidator.validateNewTask(task);
        } catch (InvalidTaskException e) {
            assertEquals("Task is null", e.getMessage());
        }
    }
}
