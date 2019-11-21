package tech.andrav.loftcoin.ui.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import tech.andrav.loftcoin.R;
import tech.andrav.loftcoin.ui.welcome.WelcomeActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // после задержки запустить лямбду
        new Handler().postDelayed(() -> {
            startActivity(new Intent(this, WelcomeActivity.class));
        }, 2000);
    }
}
