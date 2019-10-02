package com.example.xeylyne.project.Activitys;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.xeylyne.project.Adapter.GORAdapter;
import com.example.xeylyne.project.Adapter.LapanganAdapter;
import com.example.xeylyne.project.Api.ApiPackage;
import com.example.xeylyne.project.ListData.ListGOR;
import com.example.xeylyne.project.ListData.ListLapangan;
import com.example.xeylyne.project.R;
import com.example.xeylyne.project.ResultData.ResultGOR;
import com.example.xeylyne.project.ResultData.ResultLapangan;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DashOActivity extends AppCompatActivity {


    @BindView(R.id.Recycle)
    RecyclerView recyclerView;

    private List<ResultGOR> results = new ArrayList<>();
    private GORAdapter gorAdapter;

    Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl("https://irvanlyne.000webhostapp.com/")
            .addConverterFactory(GsonConverterFactory.create());

    Retrofit retrofit = builder.build();

    ApiPackage apiPackage= retrofit.create(ApiPackage.class);

    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_o);
        ButterKnife.bind(this);

        gorAdapter = new GORAdapter(DashOActivity.this, results);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(gorAdapter);

        Intent intent = getIntent();
        username = intent.getStringExtra("username");

        loaddata();
    }



    public void loaddata(){

        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("Loading ...");
        dialog.setCancelable(false);
        dialog.show();

        Call<ListGOR> call = apiPackage.gor(username);

        call.enqueue(new Callback<ListGOR>() {
            @Override
            public void onResponse(Call<ListGOR> call, Response<ListGOR> response) {

                if (response.isSuccessful()) {
                    dialog.hide();
                    results = response.body().getResult();
                    gorAdapter = new GORAdapter(DashOActivity.this, results);
                    recyclerView.setAdapter(gorAdapter);

                } else {
                    dialog.hide();
                    Toast.makeText(DashOActivity.this, "Try it", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ListGOR> call, Throwable t) {
                dialog.hide();
            }
        });

    }
    @OnClick(R.id.fab)
    public void gotoInsert(){

        Intent intent = new Intent(DashOActivity.this, InsertDataGORActivity.class);
        intent.putExtra("username", username);
        startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();
        loaddata();
        gorAdapter.notifyDataSetChanged();

    }
}
