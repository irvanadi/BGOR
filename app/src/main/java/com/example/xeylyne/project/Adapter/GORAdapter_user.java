package com.example.xeylyne.project.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.xeylyne.project.Activitys.List_LapUserActivity;
import com.example.xeylyne.project.R;
import com.example.xeylyne.project.ResultData.ResultGOR_user;

import java.util.List;

public class GORAdapter_user extends RecyclerView.Adapter<GORAdapter_user.ViewHolder>{

    private Context context;
    private List<ResultGOR_user> results;

    public GORAdapter_user(Context context, List<ResultGOR_user> results) {
        this.context = context;
        this.results = results;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gor_user, parent, false);
        ViewHolder holder = new ViewHolder(v);

        return holder;
    }


    @Override
    public void onBindViewHolder(GORAdapter_user.ViewHolder holder, int position) {
        ResultGOR_user result = results.get(position);

        holder.namalapangan.setText(result.getNamaGor());
        holder.alamat.setText(result.getAlamatGor());
        holder.id_gor = result.getGIdGor();

    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView namalapangan;
        private TextView alamat;

        String id_gor;


        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            namalapangan = itemView.findViewById(R.id.txt_lpgn);
            alamat = itemView.findViewById(R.id.jln);

        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, List_LapUserActivity.class);
            intent.putExtra("id_gor",id_gor);
            context.startActivity(intent);
    }
    }
}
