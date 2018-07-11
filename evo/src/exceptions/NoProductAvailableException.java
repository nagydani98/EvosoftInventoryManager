package exceptions;

@SuppressWarnings("serial")
public class NoProductAvailableException extends Exception{
	@Override
	public String getMessage() {
		return "No products available currently, please visit back later!\n";
	}
	@Override
	public void printStackTrace() {
		System.err.println("No products available currently, please visit back later!\n");
	}
}
