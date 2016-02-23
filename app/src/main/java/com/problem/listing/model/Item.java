package com.problem.listing.model;

/**
 * Created by avin on 23/02/16.
 */
public class Item {
    private String mLabel, mImage, mWebUrl;

    public Item(String mLabel, String mImage, String mWebUrl) {
        this.mLabel = mLabel;
        this.mImage = mImage;
        this.mWebUrl = mWebUrl;
    }

    public String getmLabel() {
        return mLabel;
    }

    public void setmLabel(String mLabel) {
        this.mLabel = mLabel;
    }

    public String getmImage() {
        return mImage;
    }

    public void setmImage(String mImage) {
        this.mImage = mImage;
    }

    public String getmWebUrl() {
        return mWebUrl;
    }

    public void setmWebUrl(String mWebUrl) {
        this.mWebUrl = mWebUrl;
    }
}
