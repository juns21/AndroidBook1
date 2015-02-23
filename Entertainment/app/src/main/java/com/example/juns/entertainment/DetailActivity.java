package com.example.juns.entertainment;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Juns on 2015-02-23.
 */
public class DetailActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_detail);

        findViewById(R.id.btnHome).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Init image
        findViewById(R.id.llHeader).setBackgroundResource(getResources().getIdentifier("header_"+ getIntent().getStringExtra("Type"), "drawable", getPackageName()));
        findViewById(R.id.llBackground).setBackgroundResource(getResources().getIdentifier("bg_"+ getIntent().getStringExtra("Type"), "drawable", getPackageName()));
    }

    // Click on Zodiac to change background and header
    public void myOnClick(View view) {
        String tag = (String) view.getTag();
        findViewById(R.id.llHeader).setBackgroundResource(getResources().getIdentifier("header_"+ tag, "drawable", getPackageName()));
        findViewById(R.id.llBackground).setBackgroundResource(getResources().getIdentifier("bg_"+ tag, "drawable", getPackageName()));
    }
}
