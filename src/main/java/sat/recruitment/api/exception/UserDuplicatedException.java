package sat.recruitment.api.exception;

public class UserDuplicatedException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public UserDuplicatedException(String message) {
	    super(message);
	}
}
