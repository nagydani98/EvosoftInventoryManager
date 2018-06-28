package product.exceptions;

@SuppressWarnings("serial")
public class LargerThanMaxNumException extends Exception{
	@Override
	public String getMessage() {
		
		return "The value larger than maximum value!";
	}
	@Override
	public void printStackTrace() {
		System.err.println("The value larger than maximum value!\n");
	}
}
