package tech.andrav.loftcoin.data;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;

import java.util.Map;

@AutoValue
public abstract class Coin {

    public abstract long id();

    public abstract String name();

    public abstract String symbol();

    abstract Map<String, AutoValue_Coin_Quote> quote();

    @AutoValue
    abstract static class Quote {

        public abstract double price();

        @AutoValue.CopyAnnotations
        @Json(name = "percent_change_24h")
        public abstract double change24h();

    }
}
