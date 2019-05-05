package com.iavariav.wisbasmartwisatabatangsmart.model.googlePlaceMaps;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PhotosItem {

    @SerializedName("photo_reference")
    private String photoReference;

    @SerializedName("width")
    private int width;

    @SerializedName("html_attributions")
    private List<String> htmlAttributions;

    @SerializedName("height")
    private int height;

    public void setPhotoReference(String photoReference) {
        this.photoReference = photoReference;
    }

    public String getPhotoReference() {
        return photoReference;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getWidth() {
        return width;
    }

    public void setHtmlAttributions(List<String> htmlAttributions) {
        this.htmlAttributions = htmlAttributions;
    }

    public List<String> getHtmlAttributions() {
        return htmlAttributions;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getHeight() {
        return height;
    }
}