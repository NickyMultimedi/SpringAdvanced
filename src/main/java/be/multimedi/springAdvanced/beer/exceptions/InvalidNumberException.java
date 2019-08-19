package be.multimedi.springAdvanced.beer.exceptions;

public class InvalidNumberException extends RuntimeException {
    public InvalidNumberException() {
        super();
    }
    public InvalidNumberException(String message) {
        super(message);
    }
    public InvalidNumberException(Throwable thrown) {
        super(thrown);
    }
    public InvalidNumberException(String message, Throwable thrown) {
        super(message, thrown);
    }
}
