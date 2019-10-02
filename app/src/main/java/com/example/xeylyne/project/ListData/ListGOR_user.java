package com.example.xeylyne.project.ListData;

import com.example.xeylyne.project.ResultData.ResultGOR_user;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListGOR_user {

    @SerializedName("value")
    @Expose
    private Integer value;
    @SerializedName("result")
    @Expose
    private List<ResultGOR_user> result = null;

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public List<ResultGOR_user> getResult() {
        return result;
    }

    public void setResult(List<ResultGOR_user> result) {
        this.result = result;
    }
}
