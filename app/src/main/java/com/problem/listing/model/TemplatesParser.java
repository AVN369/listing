package com.problem.listing.model;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by avin on 23/02/16.
 */
public class TemplatesParser {

    private static final String KEY_LABEL = "label";
    private static final String KEY_IMAGE = "image";
    private static final String KEY_TEMPLATE = "template";
    private static final String KEY_WEB_URL = "web-url";
    private static final String KEY_ITEMS = "items";

    public static ArrayList<TemplateModel> getTemplates(Context context, JSONArray jsonArray){
        ArrayList<TemplateModel> templateModels = new ArrayList<>();
        for(int i = 0; i < jsonArray.length(); i++){
            templateModels.add(getTemplate(context, jsonArray.optJSONObject(i)));

        }
        return templateModels;
    }

    public static TemplateModel getTemplate(Context context, JSONObject jsonObject){
        String label = jsonObject.optString(KEY_LABEL);
        String image = jsonObject.optString(KEY_IMAGE);
        TEMPLATE_TYPE templateType = TEMPLATE_TYPE.getTemplateTypeFromString(jsonObject.optString(KEY_TEMPLATE));
        ArrayList<Item> items = getTemplateItems(context, jsonObject.optJSONArray(KEY_ITEMS));

        return new TemplateModel(label, image, items, templateType);
    }

    private static ArrayList<Item> getTemplateItems(Context context, JSONArray jsonArray){
        ArrayList<Item> items = new ArrayList<>();
        for(int i = 0; i < jsonArray.length(); i++){
            items.add(getTemplateItem(context, jsonArray.optJSONObject(i)));

        }
        return items;
    }

    private static Item getTemplateItem(Context context, JSONObject jsonObject){
        String label = jsonObject.optString(KEY_LABEL);
        String image = jsonObject.optString(KEY_IMAGE);
        String webUrl = jsonObject.optString(KEY_WEB_URL);
        return new Item(label, image, webUrl);
    }
}
