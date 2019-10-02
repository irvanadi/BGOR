package com.example.xeylyne.project.ListData;

import com.example.xeylyne.project.ResultData.ResultLap_user;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListLap_user {

    @SerializedName("value")
    @Expose
    private Integer value;
    @SerializedName("result")
    @Expose
    private List<ResultLap_user> result = null;

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public List<ResultLap_user> getResult() {
        return result;
    }

    public void setResult(List<ResultLap_user> result) {
        this.result = result;
    }

}
