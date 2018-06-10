package product.exceptions;

@SuppressWarnings("serial")
public class ShorterThanMinTextExeption extends Exception{
	@Override
	public String getMessage() {
		return "The text is shorter than minimum lenght!";
	}
	@Override
	public void printStackTrace() {
		System.err.println("The text is shorter than minimum lenght!");
	}
}
