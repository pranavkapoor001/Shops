package com.pk.shops.ui.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.pk.shops.R;
import com.pk.shops.models.Shop;
import com.pk.shops.ui.fragments.ShopDetailsFragment;

import java.util.ArrayList;

public class ShopsListView extends RecyclerView.Adapter<ShopsListView.ShopsListViewHolder> {

    private static final String TAG = "ShopsListView";
    // Vars
    private final ArrayList<Shop> mShopArrayList;
    private Context mContext;
    // UI Components
    private ImageView ivShopImage;
    private TextView tvShopName;

    public ShopsListView(ArrayList<Shop> shopArrayList) {
        mShopArrayList = shopArrayList;
    }

    @NonNull
    @Override
    public ShopsListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Get context
        mContext = parent.getContext();

        // inflate view
        View listItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.shop_item,
                parent, false);
        return new ShopsListViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull ShopsListViewHolder holder, int position) {
        // update ui here
        bindTo(position);
    }

    @Override
    public int getItemCount() {
        // get shop item array size here
        return mShopArrayList.size();
    }

    // Update UI
    private void bindTo(int pos) {
        Glide.with(mContext)
                .load(mShopArrayList.get(pos).getShopImageUri())
                .into(ivShopImage);
        tvShopName.setText(mShopArrayList.get(pos).getShopName());
    }

    private void launchShopDetailsFragment(int pos) {
        AppCompatActivity activity = (AppCompatActivity) mContext;
        Fragment fragment = new ShopDetailsFragment();
        activity.getSupportFragmentManager()
                .beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.details_fragment_container, fragment)
                .addToBackStack(null)
                .commit();
    }

    public class ShopsListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ShopsListViewHolder(@NonNull View itemView) {
            super(itemView);
            // set views here
            ivShopImage = itemView.findViewById(R.id.food_img);
            tvShopName = itemView.findViewById(R.id.shop_name);

            // set onClick listener
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Log.e(TAG, "onClick for pos: " + getAdapterPosition());
            launchShopDetailsFragment(getAdapterPosition());
        }
    }
}
