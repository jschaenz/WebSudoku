package exceptions;

public class MalformedJSONException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public MalformedJSONException (String message) {
        super (message);
    }

    public String getName () {
        return "MalformedJSONException";
    }
}