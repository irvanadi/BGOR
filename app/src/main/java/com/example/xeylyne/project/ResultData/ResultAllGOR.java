package com.example.xeylyne.project.ResultData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResultAllGOR {
    @SerializedName("id_gor")
    @Expose
    private String idGor;
    @SerializedName("nama_gor")
    @Expose
    private String namaGor;
    @SerializedName("alamat_gor")
    @Expose
    private String alamatGor;
    @SerializedName("jam_buka")
    @Expose
    private String jamBuka;
    @SerializedName("jam_tutup")
    @Expose
    private String jamTutup;
    @SerializedName("username")
    @Expose
    private String username;

    public String getIdGor() {
        return idGor;
    }

    public void setIdGor(String idGor) {
        this.idGor = idGor;
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

    public String getJamBuka() {
        return jamBuka;
    }

    public void setJamBuka(String jamBuka) {
        this.jamBuka = jamBuka;
    }

    public String getJamTutup() {
        return jamTutup;
    }

    public void setJamTutup(String jamTutup) {
        this.jamTutup = jamTutup;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
