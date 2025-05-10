package nibirutta.currencyconverter.util;

import nibirutta.currencyconverter.exception.CurrencyNotAvailableException;

import nibirutta.currencyconverter.model.Currency;
import java.util.List;

public class CurrencyValidator {
    private final List<Currency> supportedCurrencies;

    public CurrencyValidator(List<Currency> supportedCurrencies) {
        this.supportedCurrencies = supportedCurrencies;
    }

    public void validateCurrency(String currencyCode) {
        boolean isValid = supportedCurrencies.stream()
                .anyMatch(element -> element.getCurrencyCode().equalsIgnoreCase(currencyCode));

        if (!isValid) {
            throw new CurrencyNotAvailableException("Currency code not available: " + currencyCode);
        }
    }
}
