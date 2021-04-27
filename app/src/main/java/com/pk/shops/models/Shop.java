package com.pk.shops.models;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * This class stores individual shop details
 */
public class Shop implements Parcelable {
    private final String shopName;
    private final String shopLatitude;
    private final String shopLongitude;
    private final Uri shopImageUri;
    private final String shopOffers;
    private final String shopProducts;

    public Shop(String shopName, String shopLatitude,
                String shopLongitude, Uri shopImageUri,
                String shopOffers, String shopProducts) {
        this.shopName = shopName;
        this.shopLatitude = shopLatitude;
        this.shopLongitude = shopLongitude;
        this.shopImageUri = shopImageUri;
        this.shopOffers = shopOffers;
        this.shopProducts = shopProducts;
    }

    // Getters
    public String getShopName() {
        return shopName;
    }

    public String getShopLatitude() {
        return shopLatitude;
    }

    public String getShopLongitude() {
        return shopLongitude;
    }

    public Uri getShopImageUri() {
        return shopImageUri;
    }

    public String getShopOffers() {
        return shopOffers;
    }

    public String getShopProducts() {
        return shopProducts;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}
