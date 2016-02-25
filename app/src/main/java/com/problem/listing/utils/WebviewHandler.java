package com.problem.listing.utils;

import android.content.Context;
import android.os.Build;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
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

    private TranslateAnimation mTranlateUpAnimation;
    private TranslateAnimation mTranlateDownAnimation;
    private Animation.AnimationListener mTranslateAnimationListener;
    private Animation.AnimationListener mAnimationOverListener;
    private final int mTranslateDuration = 218;

    public WebviewHandler(View mIncludeView, Context mContext, Animation.AnimationListener animationOverListener) {
        this.mContext = mContext;
        mWebViewOverlay = mIncludeView;
        mWebView = (WebView) mIncludeView.findViewById(R.id.webview);
        mProgressBar = (ProgressBar) mIncludeView.findViewById(R.id.progress_bar);
        mWebView.setWebViewClient(new WebViewClientWithProgressBar(mProgressBar));
        this.mAnimationOverListener = animationOverListener;

        mTranslateAnimationListener = new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                if(!isVisible) {
                    mWebViewOverlay.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if(isVisible) {
                    mWebViewOverlay.setVisibility(View.GONE);
                }
                isVisible = !isVisible;
                if(mAnimationOverListener != null) {
                    mAnimationOverListener.onAnimationEnd(animation);
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        };

        mTranlateUpAnimation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0,
                Animation.RELATIVE_TO_SELF, 0,
                Animation.RELATIVE_TO_SELF, 1,
                Animation.RELATIVE_TO_SELF, 0);
        mTranlateUpAnimation.setDuration(mTranslateDuration);
        mTranlateUpAnimation.setAnimationListener(mTranslateAnimationListener);

        mTranlateDownAnimation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0,
                Animation.RELATIVE_TO_SELF, 0,
                Animation.RELATIVE_TO_SELF, 0,
                Animation.RELATIVE_TO_SELF, 1);
        mTranlateDownAnimation.setDuration(mTranslateDuration);
        mTranlateDownAnimation.setAnimationListener(mTranslateAnimationListener);

    }

    public void loadUrl(String url){
        mWebView.loadUrl(url);
        mWebViewOverlay.startAnimation(mTranlateUpAnimation);
    }

    public void hide(){
        mWebViewOverlay.startAnimation(mTranlateDownAnimation);
        mWebView.getSettings().setJavaScriptEnabled(false);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR2) {
            mWebView.clearView();
        } else {
            mWebView.loadUrl("about:blank");
        }
    }

    public boolean isVisible() {
        return isVisible;
    }

}
