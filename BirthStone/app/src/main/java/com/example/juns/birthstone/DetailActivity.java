package com.example.juns.birthstone;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Juns on 2015-02-23.
 */
public class DetailActivity extends Activity {
    // Global mutable variables
    private String[] months = new String[12];
    private String[] icons = new String[12];
    private String[] details = new String[12];

    private TextView txtHeader;
    private Button btnLeft;
    private Button btnRight;
    private ImageView icon;
    private TextView txtDetails;

    private int curMonth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customlayout);

        // Get month from intent
        curMonth = getIntent().getIntExtra("month", 1);

        // Get list month and icon from resource
        months = getResources().getStringArray(R.array.months);
        icons = getResources().getStringArray(R.array.Icon);
        details = getResources().getStringArray(R.array.Detail);

        // Show header
        txtHeader = (TextView) findViewById(R.id.txtHeader);
        txtHeader.setText(months[curMonth]);

        // Show Icon
        icon = (ImageView) findViewById(R.id.imgIcon);
        icon.setImageResource(getResources().getIdentifier(icons[curMonth], "drawable", getPackageName()));

        txtDetails = (TextView) findViewById(R.id.txtDetail);
        txtDetails.setMovementMethod(new ScrollingMovementMethod());
        txtDetails.setText(details[curMonth]);

        // Click on button left to go previous month
        btnLeft = (Button) findViewById(R.id.btnLeft);
        btnLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                curMonth--;
                if (curMonth == -1) {
                    curMonth = 11;
                }

                txtHeader.setText(months[curMonth]);
                txtDetails.setText(details[curMonth]);
                icon.setImageResource(getResources().getIdentifier(icons[curMonth], "drawable", getPackageName()));
            }
        });

        // Click on button right to go next month
        btnRight = (Button) findViewById(R.id.btnRight);
        btnRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                curMonth++;
                if (curMonth == 12) {
                    curMonth = 0;
                }

                txtHeader.setText(months[curMonth]);
                txtDetails.setText(details[curMonth]);
                icon.setImageResource(getResources().getIdentifier(icons[curMonth], "drawable", getPackageName()));
            }
        });
    }
}
