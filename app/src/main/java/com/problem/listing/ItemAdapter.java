package com.problem.listing;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.problem.listing.model.Item;
import com.problem.listing.utils.Utility;
import com.squareup.picasso.Picasso;

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
                .inflate(R.layout.item_carousel_item, null);
        ItemViewHolder itemViewHolder = new ItemViewHolder(itemLayoutView);
        return itemViewHolder;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        Item item = mItems.get(position);
        Picasso.with(mActivity).load(item.getmImage()).into(holder.mImageIV);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        private ImageView mImageIV;

        public ItemViewHolder(View itemView) {
            super(itemView);
            mImageIV = (ImageView) itemView.findViewById(R.id.image);
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) mImageIV.getLayoutParams();
            layoutParams.width = mScreenWidth/3;
            mImageIV.setLayoutParams(layoutParams);
        }
    }
}
