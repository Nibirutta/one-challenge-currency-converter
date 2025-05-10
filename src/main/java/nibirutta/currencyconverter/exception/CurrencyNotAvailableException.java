package nibirutta.currencyconverter.exception;

public class CurrencyNotAvailableException extends RuntimeException {
    public CurrencyNotAvailableException(String message) {
        super(message);
    }
}
