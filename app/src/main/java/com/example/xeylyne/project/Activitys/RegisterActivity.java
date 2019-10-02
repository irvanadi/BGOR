package com.example.xeylyne.project.Activitys;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.xeylyne.project.Api.ApiPackage;
import com.example.xeylyne.project.ListData.RegisUser;
import com.example.xeylyne.project.R;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterActivity extends AppCompatActivity {


    @BindView(R.id.nama)
    AutoCompleteTextView user;

    @BindView(R.id.psd1)
    AutoCompleteTextView pass_rgs;

    @BindView(R.id.email2)
    AutoCompleteTextView email;

    @BindView(R.id.nm)
    AutoCompleteTextView no_telp;

    @BindView(R.id.radioButton2)
    RadioButton owner5;

    @BindView(R.id.radioButton3)
    RadioButton user1;

    String jns_user;

    Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl("https://irvanlyne.000webhostapp.com/")
            .addConverterFactory(GsonConverterFactory.create());

    Retrofit retrofit = builder.build();

    ApiPackage apiPackage = retrofit.create(ApiPackage.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);


        owner5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jns_user = "O";
            }
        });
        user1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jns_user = "U";
            }
        });
    }
    private void saveData() {

        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("Loading ...");
        dialog.setCancelable(false);
        dialog.show();

        String Username = user.getText().toString();
        String Password = pass_rgs.getText().toString();
        String Email = email.getText().toString();
        String No_telp = no_telp.getText().toString();

        Call<RegisUser> call = apiPackage.register(Username,Password,Email,No_telp,jns_user);

        call.enqueue(new Callback<RegisUser>() {
            @Override
            public void onResponse(Call<RegisUser> call, Response<RegisUser> response) {
                if (response.isSuccessful()) {
                    dialog.hide();
                    Toast.makeText(RegisterActivity.this,response.body().getMessage(),Toast.LENGTH_SHORT).show();
                } else {
                    dialog.hide();
                    Toast.makeText(RegisterActivity.this, "Try it", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RegisUser> call, Throwable t) {
                dialog.hide();
                Toast.makeText(RegisterActivity.this, "Check Your Internet Connection", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @OnClick(R.id.button2)
    public void pindah_regis(View v){

        if(user.getText().toString().length() == 0){
            user.setError("Masukkan Username");
        } else if (pass_rgs.getText().toString().length() == 0) {
            pass_rgs.setError("Masukkan Password");
        } else if (email.getText().toString().length() == 0) {
            email.setError("Masukkan Email");
        } else {
            saveData();
        }

    }
}
