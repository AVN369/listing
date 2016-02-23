package com.problem.listing.model;

/**
 * Created by avin on 23/02/16.
 */
public enum TEMPLATE_TYPE {
    FULL(1), ITEM_CAROUSEL(2), FULL_CAROUSEL(3);

    private int mKey;

    TEMPLATE_TYPE(int key) {
        mKey = key;
    }

    public int getKey(){
        return mKey;
    }

    public static TEMPLATE_TYPE getTemplateTypeFromKey(int key){
        switch (key){
            case 1:
                return FULL;

            case 2:
                return ITEM_CAROUSEL;

            case 3:
                return FULL_CAROUSEL;
        }

        return null;
    }

    public static TEMPLATE_TYPE getTemplateTypeFromString(String key){
        if("product-template-1".equals(key)){
            return FULL;
        }else if("product-template-2".equals(key)){
            return ITEM_CAROUSEL;
        }else if("product-template-3".equals(key)){
            return FULL_CAROUSEL;
        }

        return null;
    }
}
