package com.problem.listing.listeners;

import com.problem.listing.model.TEMPLATE_TYPE;

/**
 * Created by avin on 26/02/16.
 */
public interface OnItemClickListener {
    void onItemClicked(TEMPLATE_TYPE templateType, String title, String uri);
}
