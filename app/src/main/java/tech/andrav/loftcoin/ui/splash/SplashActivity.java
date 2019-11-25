package tech.andrav.loftcoin.ui.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import tech.andrav.loftcoin.BuildConfig;
import tech.andrav.loftcoin.R;
import tech.andrav.loftcoin.prefs.Settings;
import tech.andrav.loftcoin.ui.main.MainActivity;
import tech.andrav.loftcoin.ui.welcome.WelcomeActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        final Settings settings = new Settings(getApplicationContext());
        // после задержки запустить лямбду
        new Handler().postDelayed(() -> {
            if (BuildConfig.DEBUG) {
                startActivity(new Intent(this, WelcomeActivity.class));
            } else {
                if (settings.shouldShowWelcomeScreen()) {
                    startActivity(new Intent(this, WelcomeActivity.class));
                } else {
                    startActivity(new Intent(this, MainActivity.class));
                }
            }
        }, 1000);
    }
}
