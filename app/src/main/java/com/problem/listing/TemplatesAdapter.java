package com.problem.listing;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.problem.listing.model.Item;
import com.problem.listing.model.TEMPLATE_TYPE;
import com.problem.listing.model.TemplateModel;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by avin on 23/02/16.
 */
public class TemplatesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<TemplateModel> mTemplateModels;
    private Context mContext;

    public TemplatesAdapter(ArrayList<TemplateModel> mTemplateModels, Context mContext) {
        this.mTemplateModels = mTemplateModels;
        this.mContext = mContext;
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
                templatesViewHolder = new FullTemplateViewHolder(itemLayoutView);
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
                Picasso.with(mContext).load(item.getmImage())
                        .into(fullTemplateViewHolder.mImage, new Callback() {
                            @Override
                            public void onSuccess() {

                            }

                            @Override
                            public void onError() {

                            }
                        });
                fullTemplateViewHolder.mLabelTV.setText(templateModel.getmLabel());
                fullTemplateViewHolder.mItemLabelTV.setText(item.getmLabel());
                break;

            case ITEM_CAROUSEL:
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
}
