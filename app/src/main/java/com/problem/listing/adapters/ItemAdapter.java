package com.problem.listing.adapters;

import android.app.Activity;
import android.graphics.Paint;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.problem.listing.R;
import com.problem.listing.listeners.OnItemClickListener;
import com.problem.listing.model.Item;
import com.problem.listing.model.TEMPLATE_TYPE;
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
    private OnItemClickListener mOnItemClickListener;

    public ItemAdapter(ArrayList<Item> mItems, Activity activity, OnItemClickListener onItemClickListener) {
        this.mItems = mItems;
        this.mActivity = activity;
        Double screenWidth = Utility.getScreenWidth(mActivity);
        mScreenWidth = screenWidth.intValue();
        this.mOnItemClickListener = onItemClickListener;
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
        holder.mLabelTV.setText(item.getmLabel());
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        private ImageView mImageIV;
        private CardView mCardView;
        private TextView mLabelTV, mOldPriceTV, mNewPriceTV;

        public ItemViewHolder(View itemView) {
            super(itemView);
            mCardView = (CardView) itemView.findViewById(R.id.item_container);
            mImageIV = (ImageView) itemView.findViewById(R.id.image);
            mLabelTV = (TextView) itemView.findViewById(R.id.label);
            mOldPriceTV = (TextView) itemView.findViewById(R.id.old_price_tv);
            mOldPriceTV.setPaintFlags(mOldPriceTV.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            mNewPriceTV = (TextView) itemView.findViewById(R.id.new_price_tv);
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) mCardView.getLayoutParams();
            layoutParams.width = mScreenWidth/3;
            mCardView.setLayoutParams(layoutParams);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mOnItemClickListener == null){
                        return;
                    }

                    Item item = mItems.get(getAdapterPosition());
                    if(item.getmWebUrl() != null && !"".equals(item.getmWebUrl())) {
                        mOnItemClickListener.onItemClicked(TEMPLATE_TYPE.ITEM_CAROUSEL,
                                item.getmLabel(),
                                item.getmWebUrl());
                    }
                }
            });
        }
    }
}
