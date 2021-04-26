package com.pk.shops;

import android.net.Uri;

/**
 * This class stores individual shop details
 */
public class Shop {
    private final String shopName;
    private final String shopLatitude;
    private final String shopLongitude;
    private final Uri shopImageUri;

    public Shop(String shopName, String shopLatitude,
                String shopLongitude, Uri shopImageUri) {
        this.shopName = shopName;
        this.shopLatitude = shopLatitude;
        this.shopLongitude = shopLongitude;
        this.shopImageUri = shopImageUri;
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
}
