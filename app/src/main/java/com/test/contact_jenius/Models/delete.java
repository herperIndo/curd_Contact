package com.test.contact_jenius.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class delete {
    @SerializedName("message")
    @Expose
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
