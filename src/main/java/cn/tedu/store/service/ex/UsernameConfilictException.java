package cn.tedu.store.service.ex;

public class UsernameConfilictException extends ServiceException {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5996455405214109043L;

	public UsernameConfilictException() {
		super();
	}

	public UsernameConfilictException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public UsernameConfilictException(String message, Throwable cause) {
		super(message, cause);
	}

	public UsernameConfilictException(String message) {
		super(message);
	}

	public UsernameConfilictException(Throwable cause) {
		super(cause);
	}
	
	
}
