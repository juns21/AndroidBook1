package kr.appcenter.education.myFriends;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

/**
 * Created by Juns on 2015-10-21.
 */
public class DetailActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_detail);

        Animation fadeOutAnimation = AnimationUtils.loadAnimation(DetailActivity.this, R.anim.rotate);

        // Now Set your animation
        ImageView image = (ImageView) findViewById(R.id.image);
        image.setImageResource(getResources().getIdentifier("img_" + getIntent().getStringExtra("Animal") + "_2", "mipmap", getPackageName()));

        image.startAnimation(fadeOutAnimation);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        try {
            MediaPlayer mPlayer = MediaPlayer.create(DetailActivity.this, getResources().getIdentifier(getIntent().getStringExtra("Animal"), "raw", getPackageName()));
            mPlayer.start();
            mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mp.release();
                };
            });
        }catch (Exception e) {

        }
    }
}
