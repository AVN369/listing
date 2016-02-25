package com.problem.listing;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.problem.listing.model.Item;
import com.problem.listing.utils.Utility;

import java.util.ArrayList;

/**
 * Created by avin on 24/02/16.
 */
public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {

    private ArrayList<Item> mItems;
    private Activity mActivity;
    private int mScreenWidth;

    public ItemAdapter(ArrayList<Item> mItems, Activity activity) {
        this.mItems = mItems;
        this.mActivity = activity;
        Double screenWidth = Utility.getScreenWidth(mActivity);
        mScreenWidth = screenWidth.intValue();
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.full_template_item, null);
        ItemViewHolder itemViewHolder;
        return null;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        public ItemViewHolder(View itemView) {
            super(itemView);
        }
    }
}
