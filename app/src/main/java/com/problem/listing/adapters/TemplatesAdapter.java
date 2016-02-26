package com.problem.listing.adapters;

import android.app.Activity;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.problem.listing.R;
import com.problem.listing.listeners.OnItemClickListener;
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
    private OnItemClickListener mOnItemClickListener;

    public TemplatesAdapter(ArrayList<TemplateModel> mTemplateModels, Activity activity,
                            OnItemClickListener onItemClickListener) {
        this.mTemplateModels = mTemplateModels;
        this.mActivity = activity;
        this.mOnItemClickListener = onItemClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder templatesViewHolder = null;
        View itemLayoutView = null;
        switch (viewType){
            case 1:
                itemLayoutView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.template_item_full, null);
                templatesViewHolder = new FullTemplateViewHolder(itemLayoutView);
                break;

            case 2:
                itemLayoutView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.template_item_carousel, null);
                templatesViewHolder = new ItemCarouselTemplateViewHolder(itemLayoutView);
                break;

            case 3:
                itemLayoutView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.template_item_full_carousel, null);
                templatesViewHolder = new FullCarouselTemplateViewHolder(itemLayoutView);
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
                FullCarouselTemplateViewHolder fullCarouselTemplateViewHolder =
                        (FullCarouselTemplateViewHolder)holder;
                fullCarouselTemplateViewHolder.initializeAdapter(templateModel.getmItems());
                fullCarouselTemplateViewHolder.mLabelTV.setText(templateModel.getmLabel());
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

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mOnItemClickListener == null){
                        return;
                    }
                    Item item = mTemplateModels.get(getAdapterPosition()).getmItems().get(0);
                    if(item.getmWebUrl() != null && !"".equals(item.getmWebUrl())) {
                        mOnItemClickListener.onItemClicked(TEMPLATE_TYPE.FULL,
                                item.getmLabel(),
                                item.getmWebUrl());
                    }
                }
            });
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
                mItemAdapter = new ItemAdapter(items, mActivity, mOnItemClickListener);
                mItemsRV.setAdapter(mItemAdapter);
                isAdapterInitialized = true;
            }
        }
    }

    //TemplateViewHolder for the template type 3
    public class FullCarouselTemplateViewHolder extends RecyclerView.ViewHolder {

        private FullCarouselAdapter mFullCarouselAdapter;
        private ViewPager mViewPager;
        private TextView mLabelTV;
        private boolean isAdapterInitialized = false;
        private LinearLayout mPVIndicatorContainer;
        private ArrayList<View> mIndicatorViews;
        private int mSelectedIndex = 0;

        public FullCarouselTemplateViewHolder(View itemView) {
            super(itemView);
            mViewPager = (ViewPager) itemView.findViewById(R.id.carousel);
            mLabelTV = (TextView) itemView.findViewById(R.id.label);
            mPVIndicatorContainer = (LinearLayout) itemView.findViewById(R.id.pv_indicator_container);
        }

        public void initializeAdapter(ArrayList<Item> items){
            if(!isAdapterInitialized) {
                mFullCarouselAdapter = new FullCarouselAdapter(items, mActivity, mOnItemClickListener);
                mViewPager.setAdapter(mFullCarouselAdapter);

                int size = items.size();
                mIndicatorViews = new ArrayList<>();
                LayoutInflater layoutInflater = mActivity.getLayoutInflater();
                for(int i = 0; i < size; i++){
                    View view = layoutInflater.inflate(R.layout.pager_indicator_item, mPVIndicatorContainer, false);
                    mIndicatorViews.add(view.findViewById(R.id.pager_indicator_circle_full));
                    mPVIndicatorContainer.addView(view);
                }
                mIndicatorViews.get(0).setVisibility(View.VISIBLE);
                mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                    }

                    @Override
                    public void onPageSelected(int position) {
                        mIndicatorViews.get(position).setVisibility(View.VISIBLE);
                        if(mSelectedIndex != -1) {
                            mIndicatorViews.get(mSelectedIndex).setVisibility(View.GONE);
                        }
                        mSelectedIndex = position;
                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {

                    }
                });

                isAdapterInitialized = true;
            }
        }
    }
}
