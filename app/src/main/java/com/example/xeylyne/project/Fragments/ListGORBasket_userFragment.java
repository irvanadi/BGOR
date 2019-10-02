package com.example.xeylyne.project.Fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.xeylyne.project.Adapter.GORAdapter_user;
import com.example.xeylyne.project.Api.ApiPackage;
import com.example.xeylyne.project.ListData.ListGOR_user;
import com.example.xeylyne.project.R;
import com.example.xeylyne.project.ResultData.ResultGOR_user;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListGORBasket_userFragment extends Fragment {

    @BindView(R.id.Recycle)
    RecyclerView recyclerView;

    private List<ResultGOR_user> results = new ArrayList<>();
    private GORAdapter_user gorAdapter;

    Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl("https://irvanlyne.000webhostapp.com/")
            .addConverterFactory(GsonConverterFactory.create());

    Retrofit retrofit = builder.build();

    ApiPackage apiPackage= retrofit.create(ApiPackage.class);


    public ListGORBasket_userFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list_gorbasket_user, container, false);

        ButterKnife.bind(this,view);

        gorAdapter = new GORAdapter_user(getContext(), results);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(gorAdapter);


        loaddata();

        return view;
    }

    public void loaddata(){

        final ProgressDialog dialog = new ProgressDialog(getContext());
        dialog.setMessage("Loading ...");
        dialog.setCancelable(false);
        dialog.show();


        Call<ListGOR_user> call = apiPackage.gor_user("2");

        call.enqueue(new Callback<ListGOR_user>() {
            @Override
            public void onResponse(Call<ListGOR_user> call, Response<ListGOR_user> response) {

                if (response.isSuccessful()) {
                    dialog.hide();
                    results = response.body().getResult();
                    gorAdapter = new GORAdapter_user(getContext(), results);
                    recyclerView.setAdapter(gorAdapter);

                } else {
                    Toast.makeText(getContext(), "Try it", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<ListGOR_user> call, Throwable t) {
                dialog.hide();
            }
        });
    }
}
