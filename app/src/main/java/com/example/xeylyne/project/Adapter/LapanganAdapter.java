package com.example.xeylyne.project.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xeylyne.project.R;
import com.example.xeylyne.project.ResultData.ResultLapangan;

import java.util.List;

public class LapanganAdapter extends RecyclerView.Adapter<LapanganAdapter.ViewHolder> {

    private Context context;
    private List<ResultLapangan> results;

    public LapanganAdapter(Context context, List<ResultLapangan> results) {
        this.context = context;
        this.results = results;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemlapangan, parent, false);
        ViewHolder holder = new ViewHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ResultLapangan result = results.get(position);

       holder.namalapangan.setText(result.getNamaLap());

    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView namalapangan;
        private TextView alamat;


        public ViewHolder(View itemView) {
            super(itemView);

            namalapangan = itemView.findViewById(R.id.txt_lpgn);
            alamat = itemView.findViewById(R.id.jln);
        }

    }
}
