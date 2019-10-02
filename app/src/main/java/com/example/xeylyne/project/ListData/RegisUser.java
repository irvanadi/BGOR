package com.example.xeylyne.project.ListData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegisUser {

    @SerializedName("value")
    @Expose
    private Integer value;
    @SerializedName("message")
    @Expose
    private String message;

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
