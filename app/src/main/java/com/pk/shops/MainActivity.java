package com.pk.shops;

import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Setup dummy data in views
        initShopList();
    }

    /**
     * Initializes recycler view containing shops list
     */
    private void initShopList() {
        RecyclerView recyclerView;
        ShopsListView shopsListView;

        // Get handle to recycler view element
        recyclerView = findViewById(R.id.shop_list_recycler_view);

        // Set LayoutManager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        // Init the adapter
        shopsListView = new ShopsListView(getShopData());
        recyclerView.setAdapter(shopsListView);
    }

    /**
     * Initializes dummy array list with shop data including name, location and image
     *
     * @return ArrayList of type Shop
     */
    private ArrayList<Shop> getShopData() {
        String[] shopNames = getResources().getStringArray(R.array.shop_names);
        String[] shopLatitudes = getResources().getStringArray(R.array.shop_latitudes);
        String[] shopLongitudes = getResources().getStringArray(R.array.shop_longitudes);
        String[] shopImageUris = getResources().getStringArray(R.array.shop_images_uri);

        ArrayList<Shop> shopArrayList = new ArrayList<>();

        // Build array of all shops
        for (int i = 0; i < shopImageUris.length; i++) {
            shopArrayList.add(new Shop(shopNames[i],
                    shopLatitudes[i],
                    shopLongitudes[i], getImagePath(i)));
        }

        return shopArrayList;
    }

    /**
     * Gets path to drawable resource for given index
     *
     * @param pos index for string-array of shop images
     * @return Uri path for drawable image resource
     */
    private Uri getImagePath(int pos) {
        return Uri.parse("android.resource://" + BuildConfig.APPLICATION_ID + "/drawable/" +
                getResources().getStringArray(R.array.shop_images_uri)[pos]);
    }
}