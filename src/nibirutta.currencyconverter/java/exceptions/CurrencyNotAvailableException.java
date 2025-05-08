package nibirutta.currencyconverter.java.exceptions;

public class CurrencyNotAvailableException extends RuntimeException {
    public CurrencyNotAvailableException(String message) {
        super(message);
    }
}
