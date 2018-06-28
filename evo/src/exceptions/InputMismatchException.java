package exceptions;

@SuppressWarnings("serial")
public class InputMismatchException extends Exception{
	@Override
	public String getMessage() {
		return "The input is invalid! Please write correct value!";
	}
	@Override
	public void printStackTrace() {
		System.err.println("The input is invalid! Please write correct value!\n");
	}
}
