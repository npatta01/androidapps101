package apps101.Emily_s_Song;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MyActivity extends Activity {

    private MediaPlayer mediaPlayer;
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.e("Pickle", "oncreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mediaPlayer = MediaPlayer.create(this, R.raw.twit_new);
        //mediaPlayer.start();

    }

    public void openFB(View v){

        String url = "https://www.facebook.com/TWiTNetwork";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);

    }


    @Override
    protected void onResume(){
        Log.e("Pickle","onresume");
        mediaPlayer.start();
        super.onResume();
    }

    @Override
    protected void onPause(){
        Log.e("Pickle","onpause");
        mediaPlayer.stop();
        super.onPause();
    }

    public void openHomePage(View v){
        String url = "http://twit.tv/";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);

    }
}
