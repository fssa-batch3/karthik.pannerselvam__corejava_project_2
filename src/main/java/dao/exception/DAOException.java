package dao.exception;

public class DAOException extends Exception {

	private static final long serialVersionUID = -7798283981195321951L;

	public DAOException(String msg) {
		super(msg);
	}

	public DAOException(Throwable e) {
		super(e);
	}
	
	public DAOException(String msg, Throwable e) {
		super(msg,e);
	}

}