package com.problem.listing;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.problem.listing.adapters.TemplatesAdapter;
import com.problem.listing.listeners.OnItemClickListener;
import com.problem.listing.model.TEMPLATE_TYPE;
import com.problem.listing.model.TemplatesParser;
import com.problem.listing.utils.Utility;
import com.problem.listing.utils.WebviewHandler;

import org.json.JSONArray;
import org.json.JSONException;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements OnItemClickListener {

    @Bind(R.id.templates_rv) RecyclerView mTemplatesRV;
    @Bind(R.id.webview_container) View mWebviewContainer;
    private WebviewHandler mWebviewHandler;
    private LinearLayoutManager mLinearLayoutManager;
    private TemplatesAdapter mTemplatesAdapter;
    private Context mContext;
    private Toolbar mToolbar;
    private Menu mMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mContext = this;
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        mLinearLayoutManager = new LinearLayoutManager(mContext);
        JSONArray jsonArray = null;
        try {
            jsonArray = new JSONArray(Utility.getJSONFromFile(mContext));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mTemplatesAdapter = new TemplatesAdapter(TemplatesParser.getTemplates(mContext, jsonArray),
                this, this);
        mTemplatesRV.setLayoutManager(mLinearLayoutManager);
        mTemplatesRV.setAdapter(mTemplatesAdapter);

        mWebviewHandler = new WebviewHandler(mWebviewContainer, mContext);
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
            onPrepareOptionsMenu(mMenu);
            mToolbar.setTitle(R.string.app_name);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if(mWebviewHandler.isVisible()){
            mWebviewHandler.hide();
            onPrepareOptionsMenu(mMenu);
            mToolbar.setTitle(R.string.app_name);
            return;
        }
        super.onBackPressed();
    }

    @Override
    public void onItemClicked(TEMPLATE_TYPE templateType, String title, String uri) {
        mWebviewHandler.loadUrl(uri);
        onPrepareOptionsMenu(mMenu);
        mToolbar.setTitle(title);
    }
}
