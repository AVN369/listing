package com.problem.listing.utils;

import android.content.Context;

import com.problem.listing.R;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by avin on 23/02/16.
 */
public class Utility {
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
}
