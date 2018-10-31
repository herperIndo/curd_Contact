package com.test.contact_jenius.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class wraperContectById {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private contentById data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public contentById getData() {
        return data;
    }

    public void setData(contentById data) {
        this.data = data;
    }
}
