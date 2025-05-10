package nibirutta.currencyconverter.model;

public class Currency implements Comparable<Currency> {
    private String currencyCode;
    private String currencyName;

    public Currency(String currencyCode, String currencyName) {
        this.currencyCode = currencyCode;
        this.currencyName = currencyName;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    @Override
    public String toString() {
        return currencyCode + " - " + currencyName;
    }

    @Override
    public int compareTo(Currency other) {
        return this.currencyCode.compareTo(other.currencyCode);
    }
}
