package com.example.pc1.store;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Toast;

public class WebView_Activity extends AppCompatActivity {


    WebView WebView;
    private String myUrl="https://ropsten.etherscan.io/tx/";
    private String passwordwallet;
    private String hhh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_);
        WebView=(WebView)findViewById(R.id.WebView);
        SwipeRefreshLayout refreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
        WebView.getSettings().setJavaScriptEnabled(true);
        //mWebView.loadUrl("http://www.pois.co.kr/mobile/login.do");

        Intent webview=getIntent();
        String Webview_transaction=webview.getStringExtra("send_token_transaction_result");
        passwordwallet=webview.getStringExtra("passwordwallet");

        WebView.loadUrl(myUrl +Webview_transaction); // 접속 URL

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                WebView.reload();
            }
        });


    }

    @Override
    public void onBackPressed() {

        Intent backIntent = new Intent(getApplicationContext(),token_wallet_Activity.class);
        backIntent.putExtra("tokennumber",passwordwallet);
        startActivity(backIntent);
    }


}
