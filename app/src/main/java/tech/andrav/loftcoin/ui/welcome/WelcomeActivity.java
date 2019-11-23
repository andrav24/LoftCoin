package tech.andrav.loftcoin.ui.welcome;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import ru.tinkoff.scrollingpagerindicator.ScrollingPagerIndicator;
import tech.andrav.loftcoin.R;
import tech.andrav.loftcoin.prefs.Settings;
import tech.andrav.loftcoin.ui.main.MainActivity;

public class WelcomeActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private SnapHelper snapHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        recyclerView = findViewById(R.id.pager);
        recyclerView.setLayoutManager(new LinearLayoutManager(
                this, RecyclerView.HORIZONTAL, false));
        snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);
        recyclerView.swapAdapter(new WelcomePageAdapter(), false);
        ScrollingPagerIndicator recyclerIndicator = findViewById(R.id.dot_indicator);
        recyclerIndicator.attachToRecyclerView(recyclerView);

        final Settings settings = new Settings(getApplicationContext());
        findViewById(R.id.btn_start).setOnClickListener((v) -> {
            startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
            settings.doNotShowWelcomeScreenNextTime();
            finish();
        });

    }

    @Override
    protected void onDestroy() {
        recyclerView.swapAdapter(null, false);
        snapHelper.attachToRecyclerView(null);
        super.onDestroy();

    }
}
