package com.example.xeylyne.project.Activitys;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xeylyne.project.Api.ApiPackage;
import com.example.xeylyne.project.ListData.ListInsertGOR;
import com.example.xeylyne.project.R;

import java.lang.reflect.Array;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InsertDataActivity extends AppCompatActivity {

    @BindView(R.id.batmin)
    RadioButton badminton;

    @BindView(R.id.basket)
    RadioButton basket;

    @BindView(R.id.futsal)
    RadioButton futsal;

    @BindView(R.id.voly)
    RadioButton volly;

    @BindView(R.id.idks)
    AutoCompleteTextView indeks;

    @BindView(R.id.hrg)
    AutoCompleteTextView harga;

    String input_jns;
    String id_gor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_data);
        ButterKnife.bind(this);

/*
        ArrayAdapter<String> AdapterJam = new ArrayAdapter<String>(InsertDataActivity.this, android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.array_jam));
        AdapterJam.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(AdapterJam);
*/

        badminton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input_jns = "1";
            }
        });
        basket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input_jns = "2";
            }
        });
        futsal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input_jns = "3";
            }
        });
        volly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input_jns = "4";
            }
        });

        Intent intent = getIntent();
        id_gor = intent.getStringExtra("id_gor");


    }


    Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl("http://irvanlyne.000webhostapp.com/")
            .addConverterFactory(GsonConverterFactory.create());

    Retrofit retrofit = builder.build();

    ApiPackage apiPackage = retrofit.create(ApiPackage.class);

    private void saveData() {

        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("Loading ...");
        dialog.setCancelable(false);
        dialog.show();

        String Indeks_lap = indeks.getText().toString();
        String Harga_lap = harga.getText().toString();

        Call<ListInsertGOR> call = apiPackage.insertlap(input_jns,Indeks_lap,Harga_lap,id_gor);

        call.enqueue(new Callback<ListInsertGOR>() {
            @Override
            public void onResponse(Call<ListInsertGOR> call, Response<ListInsertGOR> response) {
                if (response.isSuccessful()) {
                    dialog.hide();
                    Toast.makeText(InsertDataActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                } else {
                    dialog.hide();
                    Toast.makeText(InsertDataActivity.this, "Try it", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ListInsertGOR> call, Throwable t) {
                dialog.hide();
                Toast.makeText(InsertDataActivity.this, "Check Your Internet Connection", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @OnClick(R.id.tambah1)
    public void registerUserToko() {
        if(indeks.getText().toString().length() == 0){
            indeks.setError("Masukkan Username");
        } else if (harga.getText().toString().length() == 0) {
            harga.setError("Masukkan Password");
        } else {
            saveData();
        }
    }

}
