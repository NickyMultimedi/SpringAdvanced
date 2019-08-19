package be.multimedi.springAdvanced.beer.exceptions;

public class InvalidBeerException extends RuntimeException {
    public InvalidBeerException() {
        super();
    }
    public InvalidBeerException(String message) {
        super(message);
    }
    public InvalidBeerException(Throwable thrown) {
        super(thrown);
    }
    public InvalidBeerException(String message, Throwable thrown) {
        super(message, thrown);
    }
}
