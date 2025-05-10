package nibirutta.currencyconverter;

import nibirutta.currencyconverter.converter.CurrencyConverter;
import nibirutta.currencyconverter.model.Currency;

public class Main {
    public static void main(String[] args) {
        CurrencyConverter converter = new CurrencyConverter();

        try {
            for ( Currency currency : converter.getSupportedCurrencies() ) {
                System.out.println(currency);
            }

            double value = converter.convertValue("EUR", "BRL", 40);
            System.out.println(String.format("%.2f", value));
        } catch(Exception error) {
            System.out.println(error.getMessage());
        }
    }
}
