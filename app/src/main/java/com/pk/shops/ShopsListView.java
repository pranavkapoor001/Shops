package com.pk.shops;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ShopsListView extends RecyclerView.Adapter<ShopsListView.ShopsListViewHolder> {

    // Vars
    private final ArrayList<Shop> mShopArrayList;
    // UI Components
    private ImageView ivShopImage;
    private TextView tvShopName;
    private Context mContext;

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

    public class ShopsListViewHolder extends RecyclerView.ViewHolder {

        public ShopsListViewHolder(@NonNull View itemView) {
            super(itemView);
            // set views here
            ivShopImage = itemView.findViewById(R.id.food_img);
            tvShopName = itemView.findViewById(R.id.shop_name);
        }
    }
}
