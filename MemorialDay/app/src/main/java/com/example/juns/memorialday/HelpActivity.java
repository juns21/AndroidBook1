package com.example.juns.memorialday;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Juns on 2015-03-11.
 */
public class HelpActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_help);
    }

    // Click button to finish popup
    public void finish(View view) {
        finish();
    }
}
