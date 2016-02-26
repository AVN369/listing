package com.problem.listing.adapters;

import android.content.Context;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.PagerAdapter;
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
import com.problem.listing.utils.GradientTransformation;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by avin on 25/02/16.
 */
public class FullCarouselAdapter extends PagerAdapter {

    private ArrayList<Item> mItems;
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private OnItemClickListener mOnItemClickListener;
    private int[] colors;
    private GradientTransformation mGradientTransformation;

    public FullCarouselAdapter(ArrayList<Item> mItems, Context mContext, OnItemClickListener onItemClickListener) {
        this.mItems = mItems;
        this.mContext = mContext;
        this.mOnItemClickListener = onItemClickListener;
        colors = new int[3];
        colors[0] = ActivityCompat.getColor(mContext, R.color.scrim_color_top);
        colors[1] = ActivityCompat.getColor(mContext, R.color.scrim_color_middle);
        colors[2] = ActivityCompat.getColor(mContext, R.color.scrim_color_bottom);
        mGradientTransformation = new GradientTransformation(colors);
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == (RelativeLayout)object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        mLayoutInflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = mLayoutInflater.inflate(R.layout.full_carousel_item, container, false);
        Picasso.with(mContext).load(mItems.get(position).getmImage())
                .transform(mGradientTransformation)
                .into((ImageView) view.findViewById(R.id.image));
        TextView labelTV = (TextView) view.findViewById(R.id.label);
        labelTV.setText(mItems.get(position).getmLabel());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener == null) {
                    return;
                }

                Item item = mItems.get(position);
                if(item.getmWebUrl() != null && !"".equals(item.getmWebUrl())) {
                    mOnItemClickListener.onItemClicked(TEMPLATE_TYPE.ITEM_CAROUSEL,
                            item.getmLabel(),
                            item.getmWebUrl());
                }
            }
        });

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout) object);
    }
}
