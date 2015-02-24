package com.example.juns.streamguide;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
        import android.view.Menu;
        import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Init blink animation
        for (int i=0; i<12; i++) {
            ImageView img = (ImageView) findViewById(getResources().getIdentifier("img"+(i+1), "id", getPackageName()));
            Animation fadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.blink_anim);
            img.startAnimation(fadeInAnimation);
        }
    }

    // Click on button to view content
    public void myOnClick(View view) {
        Intent intent = new Intent(MainActivity.this, PopupActivity.class);
        intent.putExtra("Data", (String)view.getTag());
        startActivity(intent);
    }
}
