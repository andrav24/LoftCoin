package tech.andrav.loftcoin.data;

import com.google.auto.value.AutoValue;

import java.util.Collections;
import java.util.List;

@AutoValue
public abstract class Listings {

    // парсим сгенерированный класс, а не абстрактный
    abstract List<AutoValue_Coin> data();

    public List<Coin> coins() {
        return Collections.unmodifiableList(data());
    }

}
