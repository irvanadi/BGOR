package com.example.xeylyne.project.ResultData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResultLapangan {

    @SerializedName("id_lap")
    @Expose
    private String idLap;
    @SerializedName("jns_lap")
    @Expose
    private String jnsLap;
    @SerializedName("nama_lap")
    @Expose
    private String namaLap;
    @SerializedName("indeks_lap")
    @Expose
    private String indeksLap;
    @SerializedName("username")
    @Expose
    private String username;

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

    public String getNamaLap() {
        return namaLap;
    }

    public void setNamaLap(String namaLap) {
        this.namaLap = namaLap;
    }

    public String getIndeksLap() {
        return indeksLap;
    }

    public void setIndeksLap(String indeksLap) {
        this.indeksLap = indeksLap;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}