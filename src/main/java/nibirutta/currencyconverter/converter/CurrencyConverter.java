package nibirutta.currencyconverter.converter;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import nibirutta.currencyconverter.api.ExchangeRateApiClient;
import nibirutta.currencyconverter.exception.CurrencyNotAvailableException;
import nibirutta.currencyconverter.util.CurrencyValidator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import nibirutta.currencyconverter.model.Currency;

public class CurrencyConverter {
    private final ExchangeRateApiClient apiClient;
    private final CurrencyValidator validator;
    private final List<Currency> supportedCurrencies;

    private static final String endpointForCurrencyCodes = "codes";
    private static final String endpointCodesArray = "supported_codes";

    private static final String endpointConversionMethod = "pair/";
    private static final String endpointConversionRate = "conversion_rate";

    public CurrencyConverter() {
        this.apiClient = new ExchangeRateApiClient();
        this.supportedCurrencies = loadSupportedCurrencies();
        this.validator = new CurrencyValidator(supportedCurrencies);
    }

    public double convertValue(String fromCurrencyCode, String toCurrencyCode, double amount) throws CurrencyNotAvailableException {
        validator.validateCurrency(fromCurrencyCode);
        validator.validateCurrency(toCurrencyCode);

        JsonObject response = apiClient.fetchData(endpointConversionMethod + fromCurrencyCode + "/" + toCurrencyCode);
        double conversionRate = response.get(endpointConversionRate).getAsDouble();

        return amount * conversionRate;
    }

    private List<Currency> loadSupportedCurrencies() {
        JsonObject response = apiClient.fetchData(endpointForCurrencyCodes);
        JsonArray allCurrenciesAvailable = response.get(endpointCodesArray).getAsJsonArray();

        List<Currency> currencies = new ArrayList<>();

        for (int i = 0; i < allCurrenciesAvailable.size(); i++) {
            JsonArray currency = allCurrenciesAvailable.get(i).getAsJsonArray();

            currencies.add(new Currency(currency.get(0).getAsString(), currency.get(1).getAsString()));
        }

        Collections.sort(currencies);
        return currencies;
    }

    public List<Currency> getSupportedCurrencies() {
        return supportedCurrencies;
    }
}
