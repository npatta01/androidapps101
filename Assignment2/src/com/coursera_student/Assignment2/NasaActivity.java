package com.coursera_student.Assignment2;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.webkit.WebView;

/**
 * Created by Nidhin on 1/25/14.
 */
public class NasaActivity extends Activity {

    WebView myWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        // Find that webView1
        myWebView = (WebView) findViewById(R.id.webView1);
        //myWebView.getSettings().setBuiltInZoomControls(true);
        // Not needed today...
        // myWebView.getSettings().setJavaScriptEnabled(true);


        myWebView.loadUrl("file:///android_asset/uofi-at-nasa.html");
    }


    // The onKeyDown code is from
    // http://developer.android.com/guide/webapps/webview.html
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // Check if the key event was the Back button and if there's history
        if ((keyCode == KeyEvent.KEYCODE_BACK) && myWebView.canGoBack()) {
            myWebView.goBack();
            return true;
        }
        // If it wasn't the Back key or there's no web page history, bubble up
        // to the default
        // system behavior (probably exit the activity)
        return super.onKeyDown(keyCode, event);
    }


}
