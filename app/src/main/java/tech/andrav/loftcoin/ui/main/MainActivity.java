package tech.andrav.loftcoin.ui.main;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import tech.andrav.loftcoin.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final NavController navController = Navigation.findNavController(this, R.id.main_host);
        final BottomNavigationView navView = findViewById(R.id.bottom_nav);
        NavigationUI.setupWithNavController(navView, navController);
        final Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        NavigationUI.setupWithNavController(toolbar, navController,
                new AppBarConfiguration
                        .Builder(navView.getMenu())
                        .build());
        final NavDestination node = navController.getGraph().findNode(navController.getGraph().getStartDestination());
        if (node != null) {
            setTitle(node.getLabel());
        }
    }
}
