package com.problem.listing.utils;

import android.graphics.Bitmap;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

/**
 * Created by avin on 26/02/16.
 */
public class WebViewClientWithProgressBar extends WebViewClient {

    private ProgressBar progress;

    public WebViewClientWithProgressBar(ProgressBar prg) {
        progress = prg;
        if (progress != null) {
            progress.setVisibility(View.GONE);
        }
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        if (progress != null) {
            progress.setVisibility(View.GONE);
            progress.setProgress(100);
        }
        super.onPageFinished(view, url);
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        if (progress != null) {
            progress.setVisibility(View.VISIBLE);
            progress.setProgress(0);
        }
        super.onPageStarted(view, url, favicon);
    }

}
