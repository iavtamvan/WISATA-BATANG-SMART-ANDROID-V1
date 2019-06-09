package com.iavariav.wisbasmartwisatabatangsmart.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.iavariav.wisbasmartwisatabatangsmart.R;
import com.iavariav.wisbasmartwisatabatangsmart.helper.Config;

public class DetailNewsAPIActivity extends AppCompatActivity {

    private TextView edtWebviewUrl;
    private WebView activityMainWebview;

    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_news_api);
        getSupportActionBar().hide();
        initView();
        url = getIntent().getStringExtra(Config.BUNDLE_URL_NEWS);
        // Enable Javascript
        WebSettings webSettings = activityMainWebview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        activityMainWebview.loadUrl(url);
        // TODO handle jumping to chrome
        activityMainWebview.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url){
                // do your handling codes here, which url is the requested url
                // probably you need to open that url rather than redirect:
                view.loadUrl(url);
                return false; // then it is not handled by default action
            }
        });

        edtWebviewUrl.setText(url);
        edtWebviewUrl.setFocusable(true);
        edtWebviewUrl.setEnabled(false);

    }

    private void initView() {
        edtWebviewUrl = findViewById(R.id.edt_webview_url);
        activityMainWebview = findViewById(R.id.activity_main_webview);
    }
}
