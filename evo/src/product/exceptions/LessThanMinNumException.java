package product.exceptions;

@SuppressWarnings("serial")
public class LessThanMinNumException extends Exception{
	@Override
	public String getMessage() {
		return "The value less than minimum value";
	}
	@Override
	public void printStackTrace() {
		System.err.println("The value less than minimum value!");
	}
}
