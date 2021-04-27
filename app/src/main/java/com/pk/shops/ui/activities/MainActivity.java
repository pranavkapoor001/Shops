package com.pk.shops.ui.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.pk.shops.R;
import com.pk.shops.ui.fragments.ShopsFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // setup shops list fragment containing recycler view
        initShopsListFragment();
    }

    private void initShopsListFragment() {
        Fragment shopsFragment = new ShopsFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.details_fragment_container, shopsFragment)
                .commit();
    }
}