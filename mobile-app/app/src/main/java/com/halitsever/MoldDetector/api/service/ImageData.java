package com.halitsever.MoldDetector.api.service;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ImageData {

    @SerializedName("base64Image")
    @Expose
    private String base64Image;

    public String getBase64Image() {
        return base64Image;
    }

    public void setBase64Image(String base64Image) {
        this.base64Image = base64Image;
    }

}