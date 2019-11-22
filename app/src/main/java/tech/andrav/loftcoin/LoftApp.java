package tech.andrav.loftcoin;

import android.app.Application;
import android.os.StrictMode;

import tech.andrav.loftcoin.log.LoftTree;
import timber.log.Timber;

public class LoftApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {
            StrictMode.enableDefaults();    // turn on all checks
            Timber.plant(new LoftTree());
        }
    }
}
