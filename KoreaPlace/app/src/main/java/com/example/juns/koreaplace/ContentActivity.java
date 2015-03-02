package com.example.juns.koreaplace;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Juns on 2015-03-02.
 */
public class ContentActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_popup);

        // Init textview content from intent
        TextView txtContent = (TextView) findViewById(R.id.txtContent);
        txtContent.setText(getIntent().getStringExtra("Content"));
        txtContent.setMovementMethod(new ScrollingMovementMethod());

        findViewById(R.id.btnClose).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
