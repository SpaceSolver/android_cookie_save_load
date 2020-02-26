package com.example.cookiesaveload;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    private TextView logView;
    private WebView webView;
    private String URL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        URL = getString(R.string.url);
        setContentView(R.layout.activity_second);
        logView = (TextView)findViewById(R.id.SecondBrowseLogWindow);
        webView = (WebView)findViewById(R.id.SecondBrowseWebView);
        webView.getSettings().setJavaScriptEnabled(true);
    }

    public void log(CharSequence text) {
        logView.append(text + "\n");
    }

    public void onBrowseButtonClicked(View view) {
        log("2nd browse");
        CookieManager cm = CookieManager.getInstance();
        cm.setAcceptCookie(true);
        cm.setAcceptThirdPartyCookies(webView, true);
        webView.loadUrl(URL);
    }

    public void onSwitchToFirstBrowseButtonClicked(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
