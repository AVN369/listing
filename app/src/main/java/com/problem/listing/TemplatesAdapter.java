package com.problem.listing;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.problem.listing.model.Item;
import com.problem.listing.model.TEMPLATE_TYPE;
import com.problem.listing.model.TemplateModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by avin on 23/02/16.
 */
public class TemplatesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<TemplateModel> mTemplateModels;
    private Activity mActivity;

    public TemplatesAdapter(ArrayList<TemplateModel> mTemplateModels, Activity activity) {
        this.mTemplateModels = mTemplateModels;
        this.mActivity = activity;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder templatesViewHolder = null;
        View itemLayoutView = null;
        switch (viewType){
            case 1:
                itemLayoutView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.full_template_item, null);
                templatesViewHolder = new FullTemplateViewHolder(itemLayoutView);
                break;

            case 2:
                itemLayoutView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_template_carousel_item, null);
                templatesViewHolder = new ItemCarouselTemplateViewHolder(itemLayoutView);
                break;

            case 3:
                itemLayoutView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.full_carousel_template_item, null);
                templatesViewHolder = new FullTemplateViewHolder(itemLayoutView);
                break;
        }
        return templatesViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        TemplateModel templateModel = mTemplateModels.get(position);
        TEMPLATE_TYPE templateType = templateModel.getmTemplateType();
        switch (templateType){
            case FULL:
                FullTemplateViewHolder fullTemplateViewHolder = (FullTemplateViewHolder)holder;
                Item item = templateModel.getmItems().get(0);
                Picasso.with(mActivity).load(item.getmImage())
                        .into(fullTemplateViewHolder.mImage);
                fullTemplateViewHolder.mLabelTV.setText(templateModel.getmLabel());
                fullTemplateViewHolder.mItemLabelTV.setText(item.getmLabel());
                break;

            case ITEM_CAROUSEL:
                ItemCarouselTemplateViewHolder itemCarouselTemplateViewHolder = (ItemCarouselTemplateViewHolder)holder;
                itemCarouselTemplateViewHolder.initializeAdapter(templateModel.getmItems());
                itemCarouselTemplateViewHolder.mLabelTV.setText(templateModel.getmLabel());
                break;

            case FULL_CAROUSEL:
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mTemplateModels.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mTemplateModels.get(position).getmTemplateType().getKey();
    }

    //TemplateViewHolder for the template type 1
    public class FullTemplateViewHolder extends RecyclerView.ViewHolder {

        private ImageView mImage;
        private TextView mLabelTV;
        private TextView mItemLabelTV;

        public FullTemplateViewHolder(View itemView) {
            super(itemView);

            mImage = (ImageView) itemView.findViewById(R.id.item_image);
            mLabelTV = (TextView) itemView.findViewById(R.id.label);
            mItemLabelTV = (TextView) itemView.findViewById(R.id.item_label);
        }
    }

    //TemplateViewHolder for the template type 2 - carousel with small items
    public class ItemCarouselTemplateViewHolder extends RecyclerView.ViewHolder {

        private RecyclerView mItemsRV;
        private TextView mLabelTV;
        private LinearLayoutManager mLinearLayoutManager;
        private ItemAdapter mItemAdapter;
        private boolean isAdapterInitialized = false;

        public ItemCarouselTemplateViewHolder(View itemView) {
            super(itemView);

            mLabelTV = (TextView) itemView.findViewById(R.id.label);
            mItemsRV = (RecyclerView) itemView.findViewById(R.id.carousel);
            mLinearLayoutManager = new LinearLayoutManager(mActivity, LinearLayoutManager.HORIZONTAL, false);
            mItemsRV.setLayoutManager(mLinearLayoutManager);

        }

        public void initializeAdapter(ArrayList<Item> items){
            if(!isAdapterInitialized) {
                mItemAdapter = new ItemAdapter(items, mActivity);
                mItemsRV.setAdapter(mItemAdapter);
            }
        }
    }
}
