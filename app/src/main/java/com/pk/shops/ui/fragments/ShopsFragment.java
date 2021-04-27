package com.pk.shops.ui.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pk.shops.BuildConfig;
import com.pk.shops.R;
import com.pk.shops.models.Shop;
import com.pk.shops.ui.adapters.ShopsListAdapter;

import java.util.ArrayList;

public class ShopsFragment extends Fragment {

    public static final String TAG = "ShopsFragment";
    private ShopsListAdapter shopsListAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Enable search bar options menu
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.shop_list_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Setup recycler view
        initShopList(view);
    }

    /**
     * Initializes recycler view containing shops list
     */
    private void initShopList(View view) {
        RecyclerView recyclerView;

        // Get handle to recycler view element
        recyclerView = view.findViewById(R.id.shop_list_recycler_view);

        // Set LayoutManager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        // Init the adapter
        shopsListAdapter = new ShopsListAdapter(getShopData());
        recyclerView.setAdapter(shopsListAdapter);
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
        String[] shopOffers = getResources().getStringArray(R.array.shop_offers);
        String[] shopProducts = getResources().getStringArray(R.array.shop_products);

        ArrayList<Shop> shopArrayList = new ArrayList<>();

        // Build array of all shops
        for (int i = 0; i < shopImageUris.length; i++) {
            shopArrayList.add(new Shop(shopNames[i],
                    shopLatitudes[i],
                    shopLongitudes[i], getImagePath(i), shopOffers[i], shopProducts[i]));
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


    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        MenuItem searchItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                shopsListAdapter.getFilter().filter(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }
}
