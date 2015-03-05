package com.example.juns.movingword;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Juns on 2015-03-02.
 */
public class WinActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_win);

        TextView txtContent = (TextView) findViewById(R.id.txtContent);
        txtContent.setText(getIntent().getStringExtra("Content"));

        LinearLayout lResult = (LinearLayout) findViewById(R.id.llResult);
        lResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
