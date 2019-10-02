package com.example.xeylyne.project.ListData;

import com.example.xeylyne.project.ResultData.ResultLap;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import javax.xml.transform.Result;

public class ListLap {

    @SerializedName("value")
    @Expose
    private Integer value;
    @SerializedName("result")
    @Expose
    private List<ResultLap> result = null;

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public List<ResultLap> getResult() {
        return result;
    }

    public void setResult(List<ResultLap> result) {
        this.result = result;
    }
}
