package nibirutta.currencyconverter.java.utilities;
import com.google.gson.*;
import nibirutta.currencyconverter.java.exceptions.CurrencyNotAvailableException;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public abstract class Converter {
    private static final String url = "https://v6.exchangerate-api.com/v6/";
    private static final String getCodesURL = "codes";
    private static final String getConvertedValuesURL = "pair";
    private static final String myKey = "36d190b3a23bdf04aaf47cd7/";
    private static final HttpClient client = HttpClient.newHttpClient();
    private static final List<String> allCurrencies = supportedCurrencies();



    public static double ConvertValue(double value, String fromThis, String toThis) {
        boolean firstValueIsValid = false;
        boolean secondValueIsValid = false;

        for (String currency : allCurrencies) {
            if (currency.startsWith(fromThis.toUpperCase())) {
                firstValueIsValid = true;
            }

            if (currency.startsWith(toThis.toUpperCase())) {
                secondValueIsValid = true;
            }
        }

        if (!(firstValueIsValid && secondValueIsValid)) {
            if (firstValueIsValid) {
                throw new CurrencyNotAvailableException("First currency code not available, try again");
            } else {
                throw new CurrencyNotAvailableException("Second currency code not available, try again");
            }
        }

        double conversionRate = makeConnection(getConvertedValuesURL + "/" + fromThis.toUpperCase() + "/" + toThis.toUpperCase())
                .get("conversion_rate")
                .getAsDouble();

        double convertedValue = value * conversionRate;

        return convertedValue;
    }

    public List<String> GetAllCurrencies() {
        return allCurrencies;
    }

    private static List<String> supportedCurrencies() {
        JsonObject currenciesJson = makeConnection(getCodesURL);
        JsonArray currenciesArray = currenciesJson.get("supported_codes").getAsJsonArray();
        List<String> currencies = new ArrayList<>();
        currencies.add("CODES - Currencies");

        for (int i = 0; i < currenciesArray.size(); i++) {
            JsonArray currencyJson = currenciesArray.get(i).getAsJsonArray();
            currencies.add(currencyJson.get(0).getAsString() + " - " + currencyJson.get(1).getAsString());
        }

        return currencies;
    }

    private static JsonObject makeConnection(String request) {
        try {
            HttpRequest currentRequest = HttpRequest.newBuilder().uri(URI.create(url + myKey + request)).build();
            HttpResponse<String> response = client.send(currentRequest, HttpResponse.BodyHandlers.ofString());

            return JsonParser.parseString(response.body()).getAsJsonObject();
        } catch (InterruptedException | IOException error) {
            System.out.println(error.getMessage());
        }

        return null;
    }
}
