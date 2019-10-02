package com.example.xeylyne.project.ResultData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResultLap {

    @SerializedName("id_lap")
    @Expose
    private String idLap;
    @SerializedName("jns_lap")
    @Expose
    private String jnsLap;
    @SerializedName("indeks_lap")
    @Expose
    private String indeksLap;
    @SerializedName("harga_lap")
    @Expose
    private String hargaLap;
    @SerializedName("id_gor")
    @Expose
    private String idGor;

    public String getIdLap() {
        return idLap;
    }

    public void setIdLap(String idLap) {
        this.idLap = idLap;
    }

    public String getJnsLap() {
        return jnsLap;
    }

    public void setJnsLap(String jnsLap) {
        this.jnsLap = jnsLap;
    }

    public String getIndeksLap() {
        return indeksLap;
    }

    public void setIndeksLap(String indeksLap) {
        this.indeksLap = indeksLap;
    }

    public String getHargaLap() {
        return hargaLap;
    }

    public void setHargaLap(String hargaLap) {
        this.hargaLap = hargaLap;
    }

    public String getIdGor() {
        return idGor;
    }

    public void setIdGor(String idGor) {
        this.idGor = idGor;
    }
}
