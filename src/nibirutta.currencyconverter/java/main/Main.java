package nibirutta.currencyconverter.java.main;
import nibirutta.currencyconverter.java.exceptions.CurrencyNotAvailableException;
import nibirutta.currencyconverter.java.utilities.Converter;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            double convertedValue = Converter.ConvertValue(4, "USD", "BRL");
            System.out.println(convertedValue);
        } catch(Exception error) {
            System.out.println(error.getMessage());
        }
    }
}
