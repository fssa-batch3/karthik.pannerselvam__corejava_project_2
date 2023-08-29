package services.exception;

import dao.exception.DAOException;

public class ServiceException extends Exception {
	private static final long serialVersionUID = -8508529215117096666L;

	public ServiceException(String msg, DAOException e) {
		super(msg);
	}

	public ServiceException(Throwable e) {
		super(e);
	}

}

