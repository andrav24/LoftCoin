package tech.andrav.loftcoin;

import android.app.Application;
import android.os.StrictMode;

public class LoftApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {
            StrictMode.enableDefaults();    // turn on all checks
        }
    }
}
