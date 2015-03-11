package com.example.juns.memorialday;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Juns on 2015-03-11.
 */
public class StartActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_start);
    }

    // Click image to show main screen
    public void StartActivity(View view) {
        startActivity(new Intent(StartActivity.this, MainActivity.class));
    }
}
