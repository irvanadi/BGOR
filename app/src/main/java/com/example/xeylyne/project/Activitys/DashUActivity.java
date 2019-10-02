package com.example.xeylyne.project.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.xeylyne.project.Fragments.HomeFragment;
import com.example.xeylyne.project.R;

public class DashUActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dasb);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);

        Intent intent = getIntent();
        username = intent.getStringExtra("username");

        android.support.v4.app.Fragment fragment = null;
        fragment = new HomeFragment();
        loadFragment(fragment);
    }
    private boolean loadFragment(android.support.v4.app.Fragment fragment) {
        //switching fragment
        if (fragment != null) {

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        android.support.v4.app.Fragment fragment = null;

        switch (item.getItemId()) {
            case R.id.navigation_home:
                fragment = new HomeFragment();
                break;

            case R.id.navigation_dashboard:
                Intent intent = new Intent(DashUActivity.this, MapsAllActivity.class);
                startActivity(intent);
                break;
        }

        return loadFragment(fragment);
    }
}
