package com.example.xeylyne.project.ListData;

import com.example.xeylyne.project.ResultData.ResultLogin;
import com.example.xeylyne.project.ResultData.ResultUser;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import javax.xml.transform.Result;

public class Login {


    @SerializedName("value")
    @Expose
    private Integer value;
    @SerializedName("stats_user")
    @Expose
    private String statsUser;
    @SerializedName("result")
    @Expose
    private List<ResultLogin> result = null;

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getStatsUser() {
        return statsUser;
    }

    public void setStatsUser(String statsUser) {
        this.statsUser = statsUser;
    }

    public List<ResultLogin> getResult() {
        return result;
    }

    public void setResult(List<ResultLogin> result) {
        this.result = result;
    }
}