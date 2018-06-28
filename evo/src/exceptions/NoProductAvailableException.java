package exceptions;

@SuppressWarnings("serial")
public class NoProductAvailableException extends Exception{
	@Override
	public String getMessage() {
		return "We don't have products!";
	}
	@Override
	public void printStackTrace() {
		System.err.println("We don't have products!\n");
	}
}
