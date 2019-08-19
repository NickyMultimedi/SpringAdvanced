package be.multimedi.springAdvanced.beer.exceptions;

public class BeerOutOfStockException extends RuntimeException {
    public BeerOutOfStockException() {
        super();
    }
    public BeerOutOfStockException(String message) {
        super(message);
    }
    public BeerOutOfStockException(Throwable thrown) {
        super(thrown);
    }
    public BeerOutOfStockException(String message, Throwable thrown) {
        super(message, thrown);
    }
}
