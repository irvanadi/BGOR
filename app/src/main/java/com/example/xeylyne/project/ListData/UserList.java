package com.example.xeylyne.project.ListData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import javax.xml.transform.Result;

public class UserList {


        @SerializedName("value")
        @Expose
        private Integer value;
        @SerializedName("result")
        @Expose
        private List<Result> result = null;

        public Integer getValue() {
            return value;
        }

        public void setValue(Integer value) {
            this.value = value;
        }

        public List<Result> getResult() {
            return result;
        }

        public void setResult(List<Result> result) {
            this.result = result;
        }
}
