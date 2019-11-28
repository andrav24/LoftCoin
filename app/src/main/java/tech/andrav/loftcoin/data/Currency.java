package tech.andrav.loftcoin.data;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class Currency {
    public abstract String symbol();
    public abstract String name();
    public abstract String code();

    public static Currency create(String symbol, String name, String code) {
        return new AutoValue_Currency(symbol, name, code);
    }
}