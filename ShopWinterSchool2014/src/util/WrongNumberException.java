package util;
@SuppressWarnings("serial")
public class WrongNumberException extends Exception {
	private String userEnteredString;
	
	public WrongNumberException(String entered)
	{
		this.userEnteredString = entered;
	}
	
	public String getMessage()
	{
		return userEnteredString;
	}
}
