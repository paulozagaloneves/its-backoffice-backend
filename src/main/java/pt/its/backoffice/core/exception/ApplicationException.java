package pt.its.backoffice.core.exception;

public class ApplicationException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	

    public ApplicationException(String msg) {
        super(msg);
    }
    
    public ApplicationException(Throwable t) {
        super(t);
    }
    
    public ApplicationException(String msg, Throwable t) {
        super(msg, t);
    }

}
