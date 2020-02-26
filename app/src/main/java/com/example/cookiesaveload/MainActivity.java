package com.example.cookiesaveload;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView logView;
    private WebView webView;
    private String URL;
    public final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        URL = getString(R.string.url);
        logView = (TextView)findViewById(R.id.FirstBrowseLogWindow);
        webView = (WebView)findViewById(R.id.FirstWebView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new MyWebViewClient());
    }

    public void log(CharSequence text) {
        logView.append(text + "\n");
    }

    public void onBrowseButtonClicked(View view) {
        log("browse");
        CookieManager cm = CookieManager.getInstance();
        cm.setAcceptCookie(true);
        cm.setAcceptThirdPartyCookies(webView, true);
        webView.loadUrl(URL);
    }

    public void onNonCookieBrowseButtonClicked(View view) {
        log("browse");
        CookieManager cm = CookieManager.getInstance();
        cm.setAcceptCookie(false);
        cm.setAcceptThirdPartyCookies(webView, false);
        webView.loadUrl(URL);
    }

    public void onClearCookieButtonClicked(View view) {
        log("clear");
        CookieManager cm = CookieManager.getInstance();
        cm.removeAllCookies(new ValueCallback<Boolean>() {
                                @Override
                                public void onReceiveValue(Boolean value) {
                                    log("callback");
                                }
                            });
        log("done");
    }

    public void onFlushCookieButtonClicked(View view) {
        CookieManager cm = CookieManager.getInstance();
        cm.flush();
    }

    public void onDumpCookieButtonClicked(View view) {
        CookieManager cm = CookieManager.getInstance();
        String body = cm.getCookie(URL);
        Log.d(TAG, body);
        log(body);
    }

    public void onSwitch2ndBrowseButtonClicked(View view) {
        log("2nd");
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }

}
