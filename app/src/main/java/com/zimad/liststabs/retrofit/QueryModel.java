package com.zimad.liststabs.retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by igor on 09.03.18.
 */

public class QueryModel {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private List<ItemData> data = null;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ItemData> getData() {
        return data;
    }

    public void setData(List<ItemData> data) {
        this.data = data;
    }
}