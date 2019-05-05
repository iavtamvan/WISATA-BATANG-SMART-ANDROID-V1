package com.iavariav.wisbasmartwisatabatangsmart.model.googlePlaceMaps;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResultsItem {

    @SerializedName("formatted_address")
    private String formattedAddress;

    @SerializedName("types")
    private List<String> types;

    @SerializedName("icon")
    private String icon;

    @SerializedName("rating")
    private double rating;

    @SerializedName("photos")
    private List<PhotosItem> photos;

    @SerializedName("reference")
    private String reference;

    @SerializedName("user_ratings_total")
    private int userRatingsTotal;

    @SerializedName("name")
    private String name;

    @SerializedName("opening_hours")
    private OpeningHours openingHours;

    @SerializedName("geometry")
    private Geometry geometry;

    @SerializedName("id")
    private String id;

    @SerializedName("plus_code")
    private PlusCode plusCode;

    @SerializedName("place_id")
    private String placeId;

    public void setFormattedAddress(String formattedAddress) {
        this.formattedAddress = formattedAddress;
    }

    public String getFormattedAddress() {
        return formattedAddress;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getIcon() {
        return icon;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public double getRating() {
        return rating;
    }

    public void setPhotos(List<PhotosItem> photos) {
        this.photos = photos;
    }

    public List<PhotosItem> getPhotos() {
        return photos;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getReference() {
        return reference;
    }

    public void setUserRatingsTotal(int userRatingsTotal) {
        this.userRatingsTotal = userRatingsTotal;
    }

    public int getUserRatingsTotal() {
        return userRatingsTotal;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setOpeningHours(OpeningHours openingHours) {
        this.openingHours = openingHours;
    }

    public OpeningHours getOpeningHours() {
        return openingHours;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setPlusCode(PlusCode plusCode) {
        this.plusCode = plusCode;
    }

    public PlusCode getPlusCode() {
        return plusCode;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public String getPlaceId() {
        return placeId;
    }
}