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
import com.example.xeylyne.project.Adapter.LapAdapter_user;
import com.example.xeylyne.project.Api.ApiPackage;
import com.example.xeylyne.project.ListData.ListGOR;
import com.example.xeylyne.project.ListData.ListLap_user;
import com.example.xeylyne.project.R;
import com.example.xeylyne.project.ResultData.ResultGOR;
import com.example.xeylyne.project.ResultData.ResultLap_user;

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

public class List_LapUserActivity extends AppCompatActivity {

    @BindView(R.id.Recycle)
    RecyclerView recyclerView;

    private List<ResultLap_user> results = new ArrayList<>();
    private LapAdapter_user lapAdapter_user;

    Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl("https://irvanlyne.000webhostapp.com/")
            .addConverterFactory(GsonConverterFactory.create());

    Retrofit retrofit = builder.build();

    ApiPackage apiPackage= retrofit.create(ApiPackage.class);

    String id_gor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list__lap_user);
        ButterKnife.bind(this);

        lapAdapter_user = new LapAdapter_user(List_LapUserActivity.this, results);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(lapAdapter_user);

        Intent intent = getIntent();
        id_gor = intent.getStringExtra("id_gor");

        loaddata();
    }



    public void loaddata(){

        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("Loading ...");
        dialog.setCancelable(false);
        dialog.show();

        Call<ListLap_user> call = apiPackage.lap_user(id_gor,"1");

        call.enqueue(new Callback<ListLap_user>() {
            @Override
            public void onResponse(Call<ListLap_user> call, Response<ListLap_user> response) {

                if (response.isSuccessful()) {
                    dialog.hide();
                    results = response.body().getResult();
                    lapAdapter_user = new LapAdapter_user(List_LapUserActivity.this, results);
                    recyclerView.setAdapter(lapAdapter_user);

                } else {
                    dialog.hide();
                    Toast.makeText(List_LapUserActivity.this, "Try it", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ListLap_user> call, Throwable t) {
                dialog.hide();
            }
        });

    }
}
