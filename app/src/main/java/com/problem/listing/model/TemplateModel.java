package com.problem.listing.model;

import java.util.ArrayList;

/**
 * Created by avin on 23/02/16.
 */
public class TemplateModel {
    private String mLabel, mImage;
    private ArrayList<Item> mItems;
    private TEMPLATE_TYPE mTemplateType;

    public TemplateModel() {
    }

    public TemplateModel(String mLabel, String mImage, ArrayList<Item> mItems, TEMPLATE_TYPE mTemplateType) {
        this.mLabel = mLabel;
        this.mImage = mImage;
        this.mItems = mItems;
        this.mTemplateType = mTemplateType;
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

    public ArrayList<Item> getmItems() {
        return mItems;
    }

    public void setmItems(ArrayList<Item> mItems) {
        this.mItems = mItems;
    }

    public TEMPLATE_TYPE getmTemplateType() {
        return mTemplateType;
    }

    public void setmTemplateType(TEMPLATE_TYPE mTemplateType) {
        this.mTemplateType = mTemplateType;
    }
}
