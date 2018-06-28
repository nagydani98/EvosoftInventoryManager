package exceptions;

@SuppressWarnings("serial")
public class NoSuchElementException extends Exception{
	@Override
	public String getMessage() {
		return "Sorry. I didn't find any such product which fit the criteria!";
	}
	@Override
	public void printStackTrace() {
		System.err.println("Sorry. I didn't find any such product which fit the criteria!\n");
	}
}
