package com.example.xeylyne.project.Activitys;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.xeylyne.project.Fragments.HomeFragment;
import com.example.xeylyne.project.Fragments.ListGOR_UserFragment;
import com.example.xeylyne.project.R;

public class BadmintonActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_badminton);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);
        android.support.v4.app.Fragment fragment = null;
        fragment = new ListGOR_UserFragment();
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
            case R.id.navigation_status:
                fragment = new ListGOR_UserFragment();
                break;

            case R.id.navigation_dashboard:
                Intent intent = new Intent(BadmintonActivity.this, MapsBadmintonActivity.class);
                startActivity(intent);
                break;
        }

        return loadFragment(fragment);
    }
}
