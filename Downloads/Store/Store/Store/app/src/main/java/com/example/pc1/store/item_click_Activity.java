package com.example.pc1.store;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class item_click_Activity extends AppCompatActivity {


    private WebView webview;
    WebSettings webSettings;
    String String_loadurl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_click_);

        Intent loadurl = getIntent();
        String_loadurl=loadurl.getStringExtra("load_url");

        webview=(WebView)findViewById(R.id.webview);
        webview.setWebViewClient(new WebViewClient());
        webSettings=webview.getSettings();
        webSettings.setJavaScriptEnabled(true);

        webview.loadUrl(String_loadurl);




    }
}
