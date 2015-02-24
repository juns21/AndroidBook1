package com.example.juns.streamguide;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Juns on 2015-02-24.
 */
public class StartActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_start);
    }

    // Click button start to go to main screen
    public void startActivity(View view) {
        startActivity(new Intent(StartActivity.this, MainActivity.class));
    }
}
