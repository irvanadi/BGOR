package com.example.xeylyne.project.ListData;

import com.example.xeylyne.project.ResultData.ResultLapangan;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import javax.xml.transform.Result;

public class ListLapangan {


    @SerializedName("value")
    @Expose
    private Integer value;
    @SerializedName("result")
    @Expose
    private List<ResultLapangan> result = null;

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public List<ResultLapangan> getResult() {
        return result;
    }

    public void setResult(List<ResultLapangan> result) {
        this.result = result;
    }
}
