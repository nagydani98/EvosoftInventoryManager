package exceptions;

@SuppressWarnings("serial")
public class NoSuchElementException extends Exception{
	@Override
	public String getMessage() {
		return "Sorry. I haven't found any products which is fit for the criterion!";
	}
	@Override
	public void printStackTrace() {
		System.err.println("Sorry. I haven't found any products which is fit for the criterion!\n");
	}
}
