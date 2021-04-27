package com.pk.shops.ui.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.pk.shops.R;

public class ShopDetailsFragment extends Fragment {

    private static final String TAG = "ShopDetailsFragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Log.e(TAG, "Fragment onCreateView");
        return inflater.inflate(R.layout.shop_details_fragment, container, false);
    }
}
