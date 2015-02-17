package com.example.juns.flowerlanguage;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Juns on 2015-02-16.
 */
public class DetailActivity extends Activity {

    // Global mutable variables
    private TextView txtName;
    private Button btnClose;
    private ImageView imgFlower;
    private TextView txtContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set popup background is transparent
        getWindow().setBackgroundDrawable(new ColorDrawable(0));
        setContentView(R.layout.layout_detail);

        // Show information
        txtName = (TextView) findViewById(R.id.txtName);
        txtName.setText(getIntent().getStringExtra("Name"));
        txtName.setMovementMethod(new ScrollingMovementMethod());

        btnClose = (Button) findViewById(R.id.btnClose);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        imgFlower = (ImageView) findViewById(R.id.imgFlower);
        imgFlower.setImageResource(getResources().getIdentifier(getIntent().getStringExtra("Image"), "drawable", getPackageName()));

        txtContent = (TextView) findViewById(R.id.txtContent);
        txtContent.setText(getIntent().getStringExtra("Content"));
        txtContent.setMovementMethod(new ScrollingMovementMethod());
    }
}
