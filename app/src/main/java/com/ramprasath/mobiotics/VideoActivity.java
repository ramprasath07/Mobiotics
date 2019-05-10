package com.ramprasath.mobiotics;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;

import java.net.URI;

public  class VideoActivity extends AppCompatActivity {

    SimpleExoPlayerView exoPlayerView;
     SimpleExoPlayer exoPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videoview);
        String VideoUrl = getIntent().getExtras().getString("videoUrl", "defaultKey");
        exoPlayerView = (SimpleExoPlayerView) findViewById(R.id.videoplayer);

        String titletext = getIntent().getExtras().getString("videoTitle","defaultKey");
        TextView titleView = (TextView)findViewById(R.id.titletext);
        titleView.setText(titletext);

        String descript = getIntent().getExtras().getString("videodescrption", "defaultKey");
        TextView textView = (TextView)findViewById(R.id.detailview);
        textView.setText(descript);


        try {
            BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();            TrackSelector trackSelector = new DefaultTrackSelector(new AdaptiveTrackSelection.Factory(bandwidthMeter));
            exoPlayer = ExoPlayerFactory.newSimpleInstance(this, trackSelector);

            Uri videoUri = Uri.parse(VideoUrl);
            DefaultHttpDataSourceFactory dataSourceFactory = new DefaultHttpDataSourceFactory("exoplayer_video");
            ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();
            MediaSource mediaSource = new ExtractorMediaSource(videoUri, dataSourceFactory, extractorsFactory, null, null);

            exoPlayerView.setPlayer(exoPlayer);
            exoPlayer.prepare(mediaSource);
            exoPlayer.setPlayWhenReady(true);

        } catch (Exception e) {
            Log.e("VideoView", " exoplayer error " + e.toString());
        }

    }@Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        exoPlayer.stop();
    }

}