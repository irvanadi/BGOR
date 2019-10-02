package com.example.xeylyne.project.Adapter;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xeylyne.project.Activitys.List_LapUserActivity;
import com.example.xeylyne.project.Activitys.TransActivity;
import com.example.xeylyne.project.Api.ApiPackage;
import com.example.xeylyne.project.ListData.ListInsertGOR;
import com.example.xeylyne.project.ListData.ListLap_user;
import com.example.xeylyne.project.R;
import com.example.xeylyne.project.ResultData.ResultGOR_user;
import com.example.xeylyne.project.ResultData.ResultLap_user;
import com.google.android.gms.common.api.Api;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LapAdapter_user extends RecyclerView.Adapter<LapAdapter_user.ViewHolder>{

    private Context context;
    private List<ResultLap_user> results;

    public LapAdapter_user(Context context, List<ResultLap_user> results) {
        this.context = context;
        this.results = results;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.iteminsertshow, parent, false);
        ViewHolder holder = new ViewHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(LapAdapter_user.ViewHolder holder, int position) {
        ResultLap_user result = results.get(position);

        if(result.getJnsLap().equals(1)){
            holder.namalapangan.setText("Lapangan Badminton");
        } else if(result.getJnsLap().equals(2)){
            holder.namalapangan.setText("Lapangan Basket");
        } else if(result.getJnsLap().equals(3)){
            holder.namalapangan.setText("Lapangan Futsal");
        } else if(result.getJnsLap().equals(4)){
            holder.namalapangan.setText("Lapangan Volly");
        }
        holder.alamat.setText("Lapangan Ke - "+result.getIndeksLap());
        holder.harga.setText(result.getHargaLap());
        holder.id_gor = result.getIdGor();
        holder.no_telp = result.getNoTelp();
        holder.harga_lap = result.getHargaLap();
        holder.id_lap = result.getIdLap();
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView namalapangan;
        private TextView alamat;
        private TextView harga;
        private ImageView delete1;

        String id_gor,no_telp, harga_lap, id_lap;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            namalapangan = itemView.findViewById(R.id.txt_lpgn);
            alamat = itemView.findViewById(R.id.jln);
            harga = itemView.findViewById(R.id.harga3);

        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, TransActivity.class);
            intent.putExtra("no_telp",no_telp);
            intent.putExtra("harga_lap",harga_lap);
            context.startActivity(intent);
        }
    }
}
