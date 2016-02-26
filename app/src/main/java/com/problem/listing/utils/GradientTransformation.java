package com.problem.listing.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;

import com.squareup.picasso.Transformation;

/**
 * Created by avin on 26/02/16.
 */
public class GradientTransformation implements Transformation {

    private int[] mColors;

    public GradientTransformation(int[] colors) {
        this.mColors = colors;
    }

    @Override
    public Bitmap transform(Bitmap source) {

        int x = source.getWidth();
        int y = source.getHeight();

        Bitmap grandientBitmap = source.copy(source.getConfig(), true);
        Canvas canvas = new Canvas(grandientBitmap);
        LinearGradient grad =
                new LinearGradient(x / 2, 0, x / 2, y, mColors, null, Shader.TileMode.CLAMP);
        Paint p = new Paint(Paint.DITHER_FLAG);
        p.setShader(null);
        p.setDither(true);
        p.setFilterBitmap(true);
        p.setShader(grad);
        canvas.drawPaint(p);
        source.recycle();
        return grandientBitmap;
    }

    @Override
    public String key() {
        return "GradienTransformation";
    }

}