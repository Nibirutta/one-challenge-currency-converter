package nibirutta.currencyconverter.api;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ExchangeRateApiClient {
    private static final String API_BASE_URL = "https://v6.exchangerate-api.com/v6/36d190b3a23bdf04aaf47cd7/";
    private final HttpClient httpClient;

    public ExchangeRateApiClient() {
        this.httpClient = HttpClient.newHttpClient();
    }

    public JsonObject fetchData(String endpoint) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(API_BASE_URL + endpoint))
                    .build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            return JsonParser.parseString(response.body()).getAsJsonObject();
        } catch (IOException | InterruptedException error) {
            System.out.println(error.getMessage());
        }

        return null;
    }
}
