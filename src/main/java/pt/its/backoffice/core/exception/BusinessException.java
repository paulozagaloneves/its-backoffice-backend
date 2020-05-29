package pt.its.backoffice.core.exception;

public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public BusinessException() {
	}

	public BusinessException(String msg) {
		super(msg);
	}

	public BusinessException(Throwable t) {
		super(t);
	}

	public BusinessException(String msg, Throwable t) {
		super(msg, t);
	}

	@Override
	public String toString() {
		return super.getMessage();
	}
}
