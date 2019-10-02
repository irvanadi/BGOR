package com.example.xeylyne.project.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.xeylyne.project.Activitys.BadmintonActivity;
import com.example.xeylyne.project.Activitys.BasketActivity;
import com.example.xeylyne.project.Activitys.FutsalActivity;
import com.example.xeylyne.project.R;
import com.example.xeylyne.project.Activitys.VollyActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, null);
        ButterKnife.bind(this, v);
        return v;
    }

    @OnClick(R.id.bultang)
    public void badminton(){

        Intent intent = new Intent(HomeFragment.this.getContext(), BadmintonActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.basket)
    public void basket(){

        Intent intent = new Intent(HomeFragment.this.getContext(), BasketActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.bola)
    public void futsal(){
        Intent intent = new Intent(HomeFragment.this.getContext(), FutsalActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.voly)
    public void volly(){
        Intent intent = new Intent(HomeFragment.this.getContext(), VollyActivity.class);
        startActivity(intent);
    }
}
