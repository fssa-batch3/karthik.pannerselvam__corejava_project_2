package validation.exception;

public class InvalidTaskException extends Exception {
	private static final long  serialVersionUID = -1194860954774008955L;
	public InvalidTaskException(String msg) {
		super(msg);
	}
	public InvalidTaskException(Throwable e) {
		super(e);
	}	
}
