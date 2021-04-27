package com.pk.shops.Utils;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

import com.pk.shops.models.Shop;

public class ShopUtils {

    private final Shop mCurrentShop;
    private final Context mContext;

    public ShopUtils(Shop currentShop, Context context) {
        mCurrentShop = currentShop;
        mContext = context;
    }

    /**
     * Launches google maps showing current shop location
     */
    public void openMaps() {
        Uri mapUri = Uri.parse(getShopLocationForMaps());
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, mapUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        try {
            mContext.startActivity(mapIntent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(mContext, "Please install maps app", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Launches android activity chooser for sharing shop location
     */
    public void shareShop() {
        String text = "Hey, Checkout this shop, " + mCurrentShop.getShopName() + " \n\n"
                + "https://www.google.com/maps/search/?api=1&query="
                + getFormattedShopName();

        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, text);
        mContext.startActivity(Intent.createChooser(sharingIntent, "Share via"));
    }

    /**
     * builds a request query for google maps with location and place name
     *
     * @return google maps request query
     */
    private String getShopLocationForMaps() {
        return "geo:" + mCurrentShop.getShopLatitude() + ","
                + mCurrentShop.getShopLongitude()
                + "?q=" + getFormattedShopName();
    }

    /**
     * Replaces spaces in shop name with + symbol
     * i.e: encodes spaces in shop name
     *
     * @return shop name with spaces replaced by + symbol
     */
    private String getFormattedShopName() {
        String shopName = mCurrentShop.getShopName();
        if (shopName.contains(" "))
            shopName = shopName.replace(" ", "+");

        return shopName;
    }
}
