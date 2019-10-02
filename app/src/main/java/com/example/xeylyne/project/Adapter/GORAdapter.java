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
import android.view.ViewManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xeylyne.project.Activitys.DashOActivity;
import com.example.xeylyne.project.Activitys.InsertDataActivity;
import com.example.xeylyne.project.Activitys.Show_O_Activity;
import com.example.xeylyne.project.Activitys.UpdateDataGORActivity;
import com.example.xeylyne.project.Api.ApiPackage;
import com.example.xeylyne.project.ListData.ListGOR;
import com.example.xeylyne.project.ListData.ListLap_user;
import com.example.xeylyne.project.R;
import com.example.xeylyne.project.ResultData.ResultGOR;
import com.example.xeylyne.project.ResultData.ResultLapangan;

import java.util.List;

import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

    public class GORAdapter extends RecyclerView.Adapter<GORAdapter.ViewHolder> {

    private Context context;
    private List<ResultGOR> results;

    public GORAdapter(Context context, List<ResultGOR> results) {
        this.context = context;
        this.results = results;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gor, parent, false);
        ViewHolder holder = new ViewHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(GORAdapter.ViewHolder holder, int position) {
        ResultGOR result = results.get(position);

        holder.namalapangan.setText(result.getNamaGor());
        holder.alamat.setText(result.getAlamatGor());
        holder.jam.setText(result.getJamBuka() + " - " + result.getJamTutup());
        holder.id_gor = result.getIdGor();

    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView namalapangan, alamat, jam, harga;
        private ImageView delete1,edit1;
        String id_gor;

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://irvanlyne.000webhostapp.com/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();

        ApiPackage apiPackage = retrofit.create(ApiPackage.class);

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            namalapangan = itemView.findViewById(R.id.txt_lpgn);
            alamat = itemView.findViewById(R.id.jln);
            jam = itemView.findViewById(R.id.time);

            delete1 = itemView.findViewById(R.id.delete);
            delete1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showDialog(id_gor);
                }
            });
            edit1 = itemView.findViewById(R.id.edit);
            edit1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    updateData(id_gor);
                }
            });
        }

        private void showDialog(final String id_gor) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                    context);

            // set title dialog
            alertDialogBuilder.setTitle("Hapus GOR ?");

            // set pesan dari dialog
            alertDialogBuilder
                    .setMessage("Anda yakin Ingin Menghapus GOR ?")
                    .setCancelable(false)
                    .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                            deleteGOR(id_gor);
                        }
                    })
                    .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                            dialog.cancel();
                        }
                    });

            AlertDialog alertDialog = alertDialogBuilder.create();

            alertDialog.show();
        }

        public void deleteGOR(String id_gor) {

            final ProgressDialog dialog = new ProgressDialog(context);
            dialog.setMessage("Loading ...");
            dialog.setCancelable(false);
            dialog.show();

            Call<ListGOR> call = apiPackage.del_data(id_gor);

            call.enqueue(new Callback<ListGOR>() {
                @Override
                public void onResponse(Call<ListGOR> call, Response<ListGOR> response) {
                    if (response.isSuccessful()) {
                        dialog.hide();
                        Toast.makeText(context, "GOR berhasil di hapus, silahkan Re-Login", Toast.LENGTH_LONG).show();

                    } else {
                        dialog.hide();
                        Toast.makeText(context, "not Correct", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ListGOR> call, Throwable t) {
                    dialog.hide();
                }
            });
        }

        public void updateData(String id_gor) {

            Intent intent = new Intent(context, UpdateDataGORActivity.class);
            intent.putExtra("id_gor", id_gor);
            context.startActivity(intent);

        }

        @OnClick
        public void onClick(View v) {
            Intent intent = new Intent(context, Show_O_Activity.class);
            intent.putExtra("id_gor", id_gor);
            context.startActivity(intent);
        }
    }
}
