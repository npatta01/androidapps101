package apps101.Emily_s_Song;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.VideoView;

public class MyActivity extends Activity {

    private VideoView videoView;
    private boolean videoStarted;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.e("Pickle", "oncreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        videoView = (VideoView) findViewById(R.id.videoView);
        Uri uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.eben_upton_iceland_2012);
        videoView.setVideoURI(uri);
       videoView.setBackgroundResource(R.drawable.eben_upton_raspberry_pi);
       // videoView.setVideoPath("http://videos.poptech.org/2012/video/EbenUpton-Iceland-2012.m4v");
        //mediaPlayer.start();
        videoView.setOnTouchListener(playerListener);
        videoStarted=false;

    }


    private VideoView.OnTouchListener playerListener = new View.OnTouchListener() {

        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {

            if (videoView.isPlaying()){
                videoView.pause();
            }else if (videoStarted){

                videoView.resume();

            }else{
                videoView.start();
            }

            return true;
        }
    };

    public void openFB(View v) {

        String url = "https://www.facebook.com/poptech";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);

    }


    public void openCC(View v) {

        String url = "http://creativecommons.org/licenses/by-nc-sa/3.0/";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);

    }


    public void openPopTech(View v) {

        String url = "http://poptech.org/";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);

    }



    @Override
    protected void onResume() {
        Log.e("Pickle", "onresume");
        videoView.start();
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.e("Pickle", "onpause");
        videoView.pause();
        super.onPause();
    }

    public void openHomePage(View v) {
        String url = "http://poptech.org/popcasts/eben_upton_raspberry_pi";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);

    }
}
