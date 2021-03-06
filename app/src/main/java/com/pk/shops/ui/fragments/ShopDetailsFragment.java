package com.pk.shops.ui.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.pk.shops.R;
import com.pk.shops.Utils.ShopUtils;
import com.pk.shops.models.Shop;

public class ShopDetailsFragment extends Fragment implements View.OnClickListener {

    // vars
    private static final String TAG = "ShopDetailsFragment";
    private Bundle bundle;
    private Shop currentShop;
    private ShopUtils shopUtils;

    // UI Components
    private TextView tvShopName, tvShopOffers, tvShopProducts;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /* Options menu should be disable here
         * setHasOptionsMenu(false) is not working
         *
         * Enable the menu so we get onPrepareOptionsMenu() callback
         * and clear there!
         */
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Get Shop object passed by ShopsFragment parent fragment
        bundle = this.getArguments();
        if (bundle != null)
            currentShop = bundle.getParcelable("current_shop");

        return inflater.inflate(R.layout.shop_details_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (bundle == null) {
            Log.e(TAG, " bundle is null!, skipping UI update");
            return;
        }

        // find views
        tvShopName = view.findViewById(R.id.details_shop_name);
        tvShopOffers = view.findViewById(R.id.details_shop_offers);
        tvShopProducts = view.findViewById(R.id.details_shop_products);
        Button btnOpenMaps = view.findViewById(R.id.details_open_map);
        Button btnShareShop = view.findViewById(R.id.details_share_shop);

        // Set onClick listener
        btnOpenMaps.setOnClickListener(this);
        btnShareShop.setOnClickListener(this);

        // Initialize helper
        shopUtils = new ShopUtils(currentShop, view.getContext());

        // Update UI
        updateUI();
    }

    private void updateUI() {
        tvShopName.setText(currentShop.getShopName());
        tvShopOffers.setText(currentShop.getShopOffers());
        tvShopProducts.setText(currentShop.getShopProducts());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.details_open_map:
                shopUtils.openMaps();
                break;
            case R.id.details_share_shop:
                shopUtils.shareShop();
                break;
        }
    }

    @Override
    public void onPrepareOptionsMenu(@NonNull Menu menu) {
        // Menu should not be shown in details fragment, clear it
        menu.clear();
    }
}
