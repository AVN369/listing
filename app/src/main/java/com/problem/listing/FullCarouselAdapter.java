package com.problem.listing;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.problem.listing.model.Item;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by avin on 25/02/16.
 */
public class FullCarouselAdapter extends PagerAdapter {

    private ArrayList<Item> mItems;
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public FullCarouselAdapter(ArrayList<Item> mItems, Context mContext) {
        this.mItems = mItems;
        this.mContext = mContext;
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
    public Object instantiateItem(ViewGroup container, int position) {
        mLayoutInflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = mLayoutInflater.inflate(R.layout.full_carousel_item, container, false);
        Picasso.with(mContext).load(mItems.get(position).getmImage()).into((ImageView) view.findViewById(R.id.image));
        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout) object);
    }
}
