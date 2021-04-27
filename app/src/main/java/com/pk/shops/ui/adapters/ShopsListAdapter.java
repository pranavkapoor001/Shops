package com.pk.shops.ui.adapters;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
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
import java.util.List;

public class ShopsListAdapter extends RecyclerView.Adapter<ShopsListAdapter.ShopsListViewHolder>
        implements Filterable {

    // Vars
    private static final String TAG = "ShopsListView";
    // Duplicate list with all items
    private final ArrayList<Shop> mShopArrayListFull;
    private ArrayList<Shop> mShopArrayList;
    private final Filter shopFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Shop> filteredList = new ArrayList<>();

            // Search query is null, show all items
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(mShopArrayListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (Shop item : mShopArrayListFull) {
                    if (item.getShopProducts().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mShopArrayList.clear();
            mShopArrayList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };
    private Context mContext;
    // UI Components
    private ImageView ivShopImage;


    /*------------------------------- Filter Adapter ---------------------------------------------*/
    private TextView tvShopName;


    /*------------------------------- Adapter Methods ------------------------------------------------*/

    public ShopsListAdapter(ArrayList<Shop> shopArrayList) {
        mShopArrayList = shopArrayList;
        mShopArrayListFull = new ArrayList<>(mShopArrayList);
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

        Bundle bundle = new Bundle();
        bundle.putParcelable("current_shop", mShopArrayList.get(pos));
        fragment.setArguments(bundle);

        activity.getSupportFragmentManager()
                .beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.details_fragment_container, fragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public Filter getFilter() {
        return shopFilter;
    }


    /*------------------------------- View Holder ------------------------------------------------*/

    public class ShopsListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ShopsListViewHolder(@NonNull View itemView) {
            super(itemView);
            // set views here
            ivShopImage = itemView.findViewById(R.id.food_img);
            tvShopName = itemView.findViewById(R.id.shop_name);

            // TODO: Fix this workaround
            setIsRecyclable(false);

            // set onClick listener
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            launchShopDetailsFragment(getAdapterPosition());
        }

    }
}
