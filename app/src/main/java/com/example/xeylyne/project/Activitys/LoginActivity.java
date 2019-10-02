package com.example.xeylyne.project.Activitys;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.example.xeylyne.project.Api.ApiPackage;
import com.example.xeylyne.project.ListData.Login;
import com.example.xeylyne.project.R;
import com.example.xeylyne.project.ResultData.ResultLogin;

import java.security.PublicKey;
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

public class LoginActivity extends AppCompatActivity{

    @BindView(R.id.email)
    AutoCompleteTextView user;

    @BindView(R.id.password)
    AutoCompleteTextView pass;

    Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl("https://irvanlyne.000webhostapp.com/")
            .addConverterFactory(GsonConverterFactory.create());

    Retrofit retrofit = builder.build();

    ApiPackage apiPackage = retrofit.create(ApiPackage.class);

    private List<ResultLogin> s = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

    }

    private void loginUser() {

        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("Loading ...");
        dialog.setCancelable(false);
        dialog.show();

        Call<Login> call = apiPackage.login(user.getText().toString(), pass.getText().toString());

        call.enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {
                if (response.body().getValue().equals(1)) {
                    if(response.body().getStatsUser().equals("O")){
                        dialog.hide();
                        Intent intent = new Intent(LoginActivity.this, DashOActivity.class);
                        intent.putExtra("username", user.getText().toString());
                        startActivity(intent);
                        finish();

                    } else if(response.body().getStatsUser().equals("U")){

                        Intent intent = new Intent(LoginActivity.this, DashUActivity.class);
                        intent.putExtra("username", user.getText().toString());
                        startActivity(intent);
                        finish();
                    }

                } else {
                    dialog.hide();
                    Toast.makeText(LoginActivity.this, "Try it", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {
                dialog.hide();
                Toast.makeText(LoginActivity.this, "Username/Password Salah", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @OnClick(R.id.dft)
    public void pindahregis(){
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.masuk)
    public void pindahlogin(){
        if(user.getText().toString().length() == 0){
            user.setError("Masukkan Username");
        } else if (pass.getText().toString().length() == 0) {
            pass.setError("Masukkan Password");
        } else {
            loginUser();
        }
    }
}

