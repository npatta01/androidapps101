package com.coursera_student.Assignment2;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;


public class Jabberwocky extends Activity {

    WebView myWebView;
    boolean MPplaying = false;
    int posTime = 0;

    MediaPlayer mplayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jabberwocky);
        // Find that webView1
        myWebView = (WebView) findViewById(R.id.webView1);
        //myWebView.getSettings().setBuiltInZoomControls(true);
        // Not needed today...
        // myWebView.getSettings().setJavaScriptEnabled(true);

        myWebView.getSettings().setBuiltInZoomControls(true);


        myWebView.loadUrl("file:///android_asset/jabberwocky.html");

        Button openImage = (Button) findViewById(R.id.openImage);
        openImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setWebViewTImage();
            }
        });

        Button openWikipedia = (Button) findViewById(R.id.openWikipedia);
        openWikipedia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openWikipediaPage();
            }
        });
    }

    private void openWikipediaPage() {
        startActivity(new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://en.wikipedia.org/wiki/Jabberwocky")));
    }


    private void setWebViewTImage() {

        myWebView.loadUrl("file:///android_asset/jabberwocky.png");

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


    protected void onResume() {

        if (!MPplaying) {
            mplayer = MediaPlayer.create(this,
                    R.raw.mattia_goglio_scary_night);
            mplayer.seekTo(posTime);
            mplayer.setLooping(true);
            mplayer.start();
            MPplaying = true;
        }

        super.onResume();

    }

    @Override
    protected void onPause() {

        if (MPplaying) {
            posTime = mplayer.getCurrentPosition();
            mplayer.stop();
            mplayer.release();
            MPplaying = false;

        }
        super.onPause();

    }


}
