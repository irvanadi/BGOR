package com.example.xeylyne.project.ListData;

import com.example.xeylyne.project.ResultData.ResultAllGOR;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListAllGOR {

    @SerializedName("value")
    @Expose
    private Integer value;
    @SerializedName("result")
    @Expose
    private List<ResultAllGOR> result = null;

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public List<ResultAllGOR> getResult() {
        return result;
    }

    public void setResult(List<ResultAllGOR> result) {
        this.result = result;
    }
}
