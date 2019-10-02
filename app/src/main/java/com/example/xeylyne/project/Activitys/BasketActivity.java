package com.example.xeylyne.project.Activitys;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.xeylyne.project.Adapter.LapanganAdapter;
import com.example.xeylyne.project.Api.ApiPackage;
import com.example.xeylyne.project.Fragments.HomeFragment;
import com.example.xeylyne.project.Fragments.ListGORBasket_userFragment;
import com.example.xeylyne.project.Fragments.ListGOR_UserFragment;
import com.example.xeylyne.project.ListData.ListLapangan;
import com.example.xeylyne.project.R;
import com.example.xeylyne.project.ResultData.ResultLapangan;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BasketActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);
        android.support.v4.app.Fragment fragment = null;
        fragment = new ListGORBasket_userFragment();
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
                fragment = new ListGORBasket_userFragment();
                break;

            case R.id.navigation_dashboard:
                Intent intent = new Intent(BasketActivity.this, MapsBasketActivity.class);
                startActivity(intent);
                break;
        }

        return loadFragment(fragment);
    }
}
