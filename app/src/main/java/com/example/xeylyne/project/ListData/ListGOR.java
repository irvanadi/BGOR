package com.example.xeylyne.project.ListData;

import com.example.xeylyne.project.ResultData.ResultGOR;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import javax.xml.transform.Result;

public class ListGOR {

    @SerializedName("value")
    @Expose
    private Integer value;
    @SerializedName("result")
    @Expose
    private List<ResultGOR> result = null;

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public List<ResultGOR> getResult() {
        return result;
    }

    public void setResult(List<ResultGOR> result) {
        this.result = result;
    }
}
