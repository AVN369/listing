package com.problem.listing.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.view.Display;

import com.problem.listing.R;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by avin on 23/02/16.
 */
public class Utility {

    /**
     * @param context
     *
     * Returns the string after reading from the file.
     * */
    public static String getJSONFromFile(Context context){
        InputStream stream = context.getResources().openRawResource(R.raw.sample);
        String xml = null;
        try {
            byte[] buffer = new byte[stream.available()];
            stream.read(buffer);
            stream.close();
            xml = new String(buffer, "UTF-8");
        } catch (IOException e) {
            // Error handling
            e.printStackTrace();
        }

        return xml;
    }

    /**
     * @param activity
     *
     * Returns the width of the device screen
     * */
    public static double getScreenWidth(@NonNull Activity activity) {
        Display display = activity.getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);

        float density = Resources.getSystem().getDisplayMetrics().density;
        float dpHeight = outMetrics.heightPixels / density;
        float dpWidth = outMetrics.widthPixels / density;

        return outMetrics.widthPixels;
    }
}
