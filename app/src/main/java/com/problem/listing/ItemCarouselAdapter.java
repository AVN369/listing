package com.problem.listing;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by avin on 25/02/16.
 */
public class ItemCarouselAdapter extends RecyclerView.Adapter<ItemCarouselAdapter.ItemCarouselViewHolder>{
    @Override
    public ItemCarouselAdapter.ItemCarouselViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ItemCarouselAdapter.ItemCarouselViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ItemCarouselViewHolder extends RecyclerView.ViewHolder {
        public ItemCarouselViewHolder(View itemView) {
            super(itemView);
        }
    }
}
