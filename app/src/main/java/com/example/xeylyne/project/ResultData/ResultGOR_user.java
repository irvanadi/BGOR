package com.example.xeylyne.project.ResultData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResultGOR_user {

    @SerializedName("g.id_gor")
    @Expose
    private String gIdGor;
    @SerializedName("nama_gor")
    @Expose
    private String namaGor;
    @SerializedName("alamat_gor")
    @Expose
    private String alamatGor;
    @SerializedName("minharga_lap")
    @Expose
    private String minhargaLap;
    @SerializedName("maxharga_lap")
    @Expose
    private String maxhargaLap;

    public String getGIdGor() {
        return gIdGor;
    }

    public void setGIdGor(String gIdGor) {
        this.gIdGor = gIdGor;
    }

    public String getNamaGor() {
        return namaGor;
    }

    public void setNamaGor(String namaGor) {
        this.namaGor = namaGor;
    }

    public String getAlamatGor() {
        return alamatGor;
    }

    public void setAlamatGor(String alamatGor) {
        this.alamatGor = alamatGor;
    }

    public String getMinhargaLap() {
        return minhargaLap;
    }

    public void setMinhargaLap(String minhargaLap) {
        this.minhargaLap = minhargaLap;
    }

    public String getMaxhargaLap() {
        return maxhargaLap;
    }

    public void setMaxhargaLap(String maxhargaLap) {
        this.maxhargaLap = maxhargaLap;
    }
}
