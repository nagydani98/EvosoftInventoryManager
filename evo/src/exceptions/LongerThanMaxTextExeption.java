package exceptions;

@SuppressWarnings("serial")
public class LongerThanMaxTextExeption extends Exception{
	@Override
	public String getMessage() {
		return "The text is longer than maximum lenght!";
	}
	@Override
	public void printStackTrace() {
		System.err.println("The text is longer than maximum lenght!\n");
	}
}
