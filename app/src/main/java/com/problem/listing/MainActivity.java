package com.problem.listing;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;

import com.problem.listing.adapters.TemplatesAdapter;
import com.problem.listing.listeners.OnItemClickListener;
import com.problem.listing.model.TEMPLATE_TYPE;
import com.problem.listing.model.TemplatesParser;
import com.problem.listing.utils.WebviewHandler;

import org.json.JSONArray;
import org.json.JSONException;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements OnItemClickListener {

    @Bind(R.id.templates_rv) RecyclerView mTemplatesRV;
    @Bind(R.id.webview_container) View mWebviewContainer;
    @Bind(R.id.loading_container) View mLoadingCOntainer;
    @Bind(R.id.tap_to_retry_container) View mTapToRetryContainer;
    private WebviewHandler mWebviewHandler;
    private LinearLayoutManager mLinearLayoutManager;
    private TemplatesAdapter mTemplatesAdapter;
    private Context mContext;
    private Toolbar mToolbar;
    private Menu mMenu;
    private Animation.AnimationListener mTranslateAnimationListener;
    private MyReceiver mMyReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mContext = this;
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        mLinearLayoutManager = new LinearLayoutManager(mContext);
        mTemplatesRV.setLayoutManager(mLinearLayoutManager);

        mTranslateAnimationListener = new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                onPrepareOptionsMenu(mMenu);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        };
        mWebviewHandler = new WebviewHandler(mWebviewContainer, mContext, mTranslateAnimationListener);
        loadData();
        mTapToRetryContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadData();
            }
        });
    }

    private void loadData(){
        //Need to pass the server API url here
        mLoadingCOntainer.setVisibility(View.VISIBLE);
        mTapToRetryContainer.setVisibility(View.GONE);
        DealsFetcherService.startActionGetDealData(mContext, null);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        menu.findItem(R.id.action_close).setVisible(false);
        mMenu = menu;
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem menuItem = menu.findItem(R.id.action_close);
        menuItem.setVisible(mWebviewHandler.isVisible());
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_close) {
            mWebviewHandler.hide();
            mToolbar.setTitle(R.string.app_name);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if(mWebviewHandler.isVisible()){
            mWebviewHandler.hide();
            mToolbar.setTitle(R.string.app_name);
            return;
        }
        super.onBackPressed();
    }

    @Override
    public void onItemClicked(TEMPLATE_TYPE templateType, String title, String uri) {
        mWebviewHandler.loadUrl(uri);
        mToolbar.setTitle(title);
    }

    @Override
    protected void onStart() {

        super.onStart();

        if(mMyReceiver == null) {
            mMyReceiver = new MyReceiver();
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(DealsFetcherService.ACTION_DEAL_DATA);
        registerReceiver(mMyReceiver, intentFilter);
    }

    @Override
    protected void onStop() {
        unregisterReceiver(mMyReceiver);
        super.onStop();
    }

    public class MyReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {

            if(!intent.getBooleanExtra("status", false)){
                mTapToRetryContainer.setVisibility(View.VISIBLE);
                mLoadingCOntainer.setVisibility(View.GONE);
                return;
            }

            if(intent.hasExtra("data")) {
                String data = intent.getStringExtra("data");
                JSONArray jsonArray = null;
                try {
                    jsonArray = new JSONArray(data);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                mTemplatesAdapter = new TemplatesAdapter(TemplatesParser.getTemplates(mContext, jsonArray),
                        MainActivity.this, MainActivity.this);
                mTemplatesRV.setAdapter(mTemplatesAdapter);
                mLoadingCOntainer.setVisibility(View.GONE);
            }
        }

    }
}
