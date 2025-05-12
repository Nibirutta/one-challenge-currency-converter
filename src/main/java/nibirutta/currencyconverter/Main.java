package nibirutta.currencyconverter;

import nibirutta.currencyconverter.converter.CurrencyConverter;
import nibirutta.currencyconverter.ui.ConverterUI;

public class Main {
    public static void main(String[] args) {
        CurrencyConverter converter = new CurrencyConverter();
        ConverterUI converterUI = new ConverterUI(converter);

        converterUI.StartUserInteraction();
    }
}
