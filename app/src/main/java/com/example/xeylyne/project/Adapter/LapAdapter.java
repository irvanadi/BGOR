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

import com.example.xeylyne.project.Api.ApiPackage;
import com.example.xeylyne.project.ListData.ListLap;
import com.example.xeylyne.project.ListData.ListLap_user;
import com.example.xeylyne.project.R;
import com.example.xeylyne.project.ResultData.ResultLap;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LapAdapter extends RecyclerView.Adapter<LapAdapter.ViewHolder>{

    private Context context;
    private List<ResultLap> results;

    public LapAdapter(Context context, List<ResultLap> results) {
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
    public void onBindViewHolder(LapAdapter.ViewHolder holder, int position) {
        ResultLap result = results.get(position);

        if(result.getJnsLap().equals("1")){
            holder.namalapangan.setText("Lapangan Badminton");
        } else if(result.getJnsLap().equals("2")){
            holder.namalapangan.setText("Lapangan Basket");
        } else if(result.getJnsLap().equals("3")){
            holder.namalapangan.setText("Lapangan Futsal");
        } else if(result.getJnsLap().equals("4")){
            holder.namalapangan.setText("Lapangan Volly");
        }
        holder.alamat.setText("Lapangan Ke -" + result.getIndeksLap());
        holder.harga.setText(result.getHargaLap());
        holder.id_lap = result.getIdLap();
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView namalapangan, alamat, harga;
        private ImageView delete1;
        String id_lap;

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://irvanlyne.000webhostapp.com/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();

        ApiPackage apiPackage = retrofit.create(ApiPackage.class);


        public ViewHolder(View itemView) {
            super(itemView);

            namalapangan = itemView.findViewById(R.id.txt_lpgn);
            alamat = itemView.findViewById(R.id.jln);
            harga = itemView.findViewById(R.id.harga3);
            delete1 = itemView.findViewById(R.id.delete);
            delete1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showDialog(id_lap);
                }
            });

        }

        private void showDialog(final String id_lap){
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                    context);

            // set title dialog
            alertDialogBuilder.setTitle("Hapus Lapangan ?");

            // set pesan dari dialog
            alertDialogBuilder
                    .setMessage("Anda yakin Ingin Menghapus Lapangan ?")
                    .setCancelable(false)
                    .setPositiveButton("Ya",new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int id) {

                            deleteToko(id_lap);
                        }
                    })
                    .setNegativeButton("Tidak",new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                            dialog.cancel();
                        }
                    });

            AlertDialog alertDialog = alertDialogBuilder.create();

            alertDialog.show();
        }

        public void deleteToko(String id_toko) {

            final ProgressDialog dialog = new ProgressDialog(context);
            dialog.setMessage("Loading ...");
            dialog.setCancelable(false);
            dialog.show();

            Call<ListLap> call = apiPackage.del_datalap(id_toko);

            call.enqueue(new Callback<ListLap>() {
                @Override
                public void onResponse(Call<ListLap> call, Response<ListLap> response) {
                    if (response.isSuccessful()) {
                        dialog.hide();
                        Toast.makeText(context, "Lapangan berhasil di hapus", Toast.LENGTH_SHORT).show();

                    } else {
                        dialog.hide();
                        Toast.makeText(context, "not Correct", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ListLap> call, Throwable t) {
                    dialog.hide();
                }
            });

        }

    }
}
