package com.stream.viewstream.app;

import android.app.Activity;
import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends Activity {

    private static ProgressDialog progressDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            //displays "loading" circle during buffering
        progressDialog = ProgressDialog.show(this, "", "Buffering...", true);
        setContentView(R.layout.activity_main);
        final VideoView player = (VideoView) findViewById(R.id.player);
            //specify the HLS stream URL here
        String streamUrl = "http://170.obj.netromedia.net/ODExtreme/mp4:Extremists.m4v/playlist.m3u8";
        player.setVideoURI(Uri.parse(streamUrl));
            //keep the screen on during viewing
        player.setKeepScreenOn(true);
            //loads the media controller/scrubber and sets skip/rewind to disabled
        player.setMediaController(new MediaController(this, false));
        player.requestFocus();
        player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
                 //clears loading circle when the stream is ready and starts the stream
            public void onPrepared(MediaPlayer mp) {
                progressDialog.dismiss();
                player.start();
            }
        });
    }
}