package com.example.juns.streamguide;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Juns on 2015-02-24.
 */
public class PopupActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_popup);

        // Init data
        String[] arData = getResources().getStringArray(getResources().getIdentifier("Data_"+ getIntent().getStringExtra("Data"), "array", getPackageName()));

        TextView txtTile = (TextView) findViewById(R.id.txtTitle);
        txtTile.setText(arData[0]);

        ImageView image = (ImageView) findViewById(R.id.image);
        image.setImageResource(getResources().getIdentifier("image_"+ getIntent().getStringExtra("Data"), "drawable", getPackageName()));

        TextView txtTitle2 = (TextView) findViewById(R.id.txtTitle2);
        txtTitle2.setText(arData[1]);

        TextView txtContent = (TextView) findViewById(R.id.txtContent);
        txtContent.setText(arData[2]);
        txtContent.setMovementMethod(new ScrollingMovementMethod());

        findViewById(R.id.btnYes).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
