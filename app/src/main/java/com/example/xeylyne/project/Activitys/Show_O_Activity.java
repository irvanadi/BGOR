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
import com.example.xeylyne.project.Adapter.LapAdapter;
import com.example.xeylyne.project.Api.ApiPackage;
import com.example.xeylyne.project.ListData.ListGOR;
import com.example.xeylyne.project.ListData.ListLap;
import com.example.xeylyne.project.R;
import com.example.xeylyne.project.ResultData.ResultGOR;
import com.example.xeylyne.project.ResultData.ResultLap;

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

public class Show_O_Activity extends AppCompatActivity {

    @BindView(R.id.Recycle)
    RecyclerView recyclerView;

    private List<ResultLap> results = new ArrayList<>();
    private LapAdapter lapAdapter;

    Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl("https://irvanlyne.000webhostapp.com/")
            .addConverterFactory(GsonConverterFactory.create());

    Retrofit retrofit = builder.build();

    ApiPackage apiPackage = retrofit.create(ApiPackage.class);

     String id_gor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show__o_);
        ButterKnife.bind(this);

        lapAdapter = new LapAdapter(Show_O_Activity.this, results);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(lapAdapter);

        Intent intent = getIntent();
        id_gor = intent.getStringExtra("id_gor");
        loaddata();
    }

    public void loaddata(){

        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("Loading ...");
        dialog.setCancelable(false);
        dialog.show();

        Call<ListLap> call = apiPackage.lap(id_gor);

        call.enqueue(new Callback<ListLap>() {
            @Override
            public void onResponse(Call<ListLap> call, Response<ListLap> response) {

                if (response.isSuccessful()) {
                    dialog.hide();
                    results = response.body().getResult();
                    lapAdapter = new LapAdapter(Show_O_Activity.this, results);
                    recyclerView.setAdapter(lapAdapter);

                } else {
                    dialog.hide();
                    Toast.makeText(Show_O_Activity.this, "Try it", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ListLap> call, Throwable t) {
                dialog.hide();
            }
        });

    }
    @OnClick(R.id.fab)
    public void gotoInsert(){

        Intent intent = new Intent(Show_O_Activity.this, InsertDataActivity.class);
        intent.putExtra("id_gor",id_gor);
        startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();
        loaddata();
        lapAdapter.notifyDataSetChanged();
    }

}
