package nibirutta.currencyconverter.ui;

import nibirutta.currencyconverter.converter.CurrencyConverter;

import nibirutta.currencyconverter.exception.CurrencyNotAvailableException;
import nibirutta.currencyconverter.model.Currency;
import java.util.List;
import java.util.Scanner;

public class ConverterUI {
    Scanner scanner = new Scanner(System.in);
    CurrencyConverter converter;

    public ConverterUI(CurrencyConverter converter) {
        this.converter = converter;
    }

    public void ShowAvailableCurrencies() {
        StringBuilder availableCurrencies = new StringBuilder();
        List<Currency> availableCurrenciesList = converter.getSupportedCurrencies();

        availableCurrencies.append("------------------------------------------------------------------------------------------\n\n");
        availableCurrencies.append("CODE - Currencies\n\n");

        int counter = 0;

        for (int i = 0; i < converter.getSupportedCurrencies().size(); i++) {
            if (counter < 2) {
                availableCurrencies.append(availableCurrenciesList.get(i)).append("\t\t----\t\t");
            } else {
                availableCurrencies.append(availableCurrenciesList.get(i))
                        .append("\n");
            }

            counter++;

            if (counter == 3) {
                counter = 0;
            }
        }

        availableCurrencies.append("\n\n------------------------------------------------------------------------------------------");

        System.out.println(availableCurrencies);
    }

    public void StartUserInteraction() {
        System.out.println("\n\nWelcome to Currency Converter\n\n");
        ShowAvailableCurrencies();
        System.out.println("\n\n");

        System.out.println("Do you want to convert a currency? (Y/N): ");
        String input = scanner.nextLine();

        if (input.equalsIgnoreCase("Y")) {
            do {
                System.out.println("Please enter the currency code (FROM) : ");
                String from = scanner.nextLine();

                System.out.println("Please enter the currency code (TO) : ");
                String to = scanner.nextLine();

                System.out.println("Please enter the amount to be converted: ");
                double amount = scanner.nextDouble();

                try {
                    String convertedValue = String.format("%.2f", converter.convertValue(from, to, amount));
                    System.out.println("The value of "
                            + amount + "[" + from.toUpperCase() + "] is "
                            + convertedValue + "[" + to.toUpperCase() + "]");
                } catch (CurrencyNotAvailableException error) {
                    System.out.println(error.getMessage());
                }

                scanner.nextLine();

                System.out.println("Do you still want to convert a currency? (Y/N): ");
                input = scanner.nextLine();
            } while (input.equalsIgnoreCase("Y"));
        }

        System.out.println("\n\nThank you for using my Currency Converter\n");
    }
}
