package com.example.biorelais_android.ui;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Menu;

import com.example.biorelais_android.R;
import com.example.biorelais_android.lib.Display;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


public class AccueilActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.accueil,R.id.clientcommande,R.id.vosRetraits,R.id.votrecompte)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        // Ajoute les encoches
        Display.addNotch(this, R.color.biorelais_lightgreen);

        // Events
        findViewById(R.id.imageView2).setOnClickListener(v -> {
            drawer.openDrawer(Gravity.LEFT);
        });

        // Events
        findViewById(R.id.button3).setOnClickListener(v -> {
            MainActivity.currentSession = null;
            Display.openAct(this, ConnexionActivity.class);
            finishAffinity();
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.accueil, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
        for (Fragment fragment : getSupportFragmentManager().getFragments()) {
            if (fragment.isVisible() && hasBackStack(fragment)) {
                if (popFragment(fragment))
                    return;
            }
        }
        super.onBackPressed();
    }

    private boolean hasBackStack(Fragment fragment) {
        FragmentManager childFragmentManager = fragment.getChildFragmentManager();
        return childFragmentManager.getBackStackEntryCount() > 0;
    }

    private boolean popFragment(Fragment fragment) {
        FragmentManager fragmentManager = fragment.getChildFragmentManager();
        for (Fragment childFragment : fragmentManager.getFragments()) {
            if (childFragment.isVisible()) {
                if (hasBackStack(childFragment)) {
                    return popFragment(childFragment);
                } else {
                    fragmentManager.popBackStack();
                    return true;
                }
            }
        }
        return false;
    }
}