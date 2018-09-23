package com.example.pelt.studentportal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.List;

public class PortalActivity extends AppCompatActivity {

    private int mModifyPosition;
    private List<Portal> mPortals;
    private PortalAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portal);

        mModifyPosition = MainActivity.getmModifyPosition();
        mPortals = MainActivity.getmPortals();
        mAdapter = MainActivity.getmAdapter();

        String url = mPortals.get(mModifyPosition).toString();

        WebView myWebView = (WebView) findViewById(R.id.webview);
        myWebView.setWebViewClient(new WebViewClient());
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        myWebView.loadUrl(url);
    }
}
