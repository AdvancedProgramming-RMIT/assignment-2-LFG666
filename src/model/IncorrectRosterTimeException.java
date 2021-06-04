package model;

public class IncorrectRosterTimeException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4657900843974716514L;
	public IncorrectRosterTimeException(String errorMessage) {
		super(errorMessage);
	}
	public IncorrectRosterTimeException(String errorMessage, Throwable err) {
	    super(errorMessage, err);
	}
}
