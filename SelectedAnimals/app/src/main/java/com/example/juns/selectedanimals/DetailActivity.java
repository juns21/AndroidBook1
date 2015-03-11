package com.example.juns.selectedanimals;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Juns on 2015-03-11.
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

        /*
         *   Show detail about animal
        */
        RelativeLayout rlMain = (RelativeLayout) findViewById(R.id.rlMain);
        rlMain.setBackgroundResource(getResources().getIdentifier("image_"+ getIntent().getStringExtra("Animal"), "mipmap", getPackageName()));

        ImageView imgCode = (ImageView) findViewById(R.id.imgCode);
        imgCode.setImageResource(getResources().getIdentifier("code"+ getIntent().getStringExtra("Animal"), "mipmap", getPackageName()));

        TextView txtContent = (TextView) findViewById(R.id.txtContent);
        txtContent.setText(getResources().getIdentifier("Content_"+ getIntent().getStringExtra("Animal"), "string", getPackageName()));
        txtContent.setMovementMethod(new ScrollingMovementMethod());
    }
}
