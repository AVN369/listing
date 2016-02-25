package com.problem.listing.utils;

import android.content.Context;
import android.os.Build;
import android.view.View;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.problem.listing.R;

/**
 * Created by avin on 26/02/16.
 */
public class WebviewHandler {
    private View mWebViewOverlay;
    private WebView mWebView;
    private ProgressBar mProgressBar;
    private Context mContext;
    private boolean isVisible = false;

    public WebviewHandler(View mIncludeView, Context mContext) {
        this.mContext = mContext;
        mWebViewOverlay = mIncludeView;
        mWebView = (WebView) mIncludeView.findViewById(R.id.webview);
        mProgressBar = (ProgressBar) mIncludeView.findViewById(R.id.progress_bar);
        mWebView.setWebViewClient(new WebViewClientWithProgressBar(mProgressBar));
    }

    public void loadUrl(String url){
        mWebView.loadUrl(url);
        mWebViewOverlay.setVisibility(View.VISIBLE);
        isVisible = true;
    }

    public void hide(){
        mWebViewOverlay.setVisibility(View.GONE);
        mWebView.getSettings().setJavaScriptEnabled(false);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR2) {
            mWebView.clearView();
        } else {
            mWebView.loadUrl("about:blank");
        }
        isVisible = false;
    }

    public boolean isVisible() {
        return isVisible;
    }

}
