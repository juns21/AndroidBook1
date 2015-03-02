package com.example.juns.multitouchinput;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

public class MainActivity extends Activity {

    LinearLayout ln_main;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ln_main = (LinearLayout)findViewById(R.id.ln_main);

        ln_main.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MultiTouchInputActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });
    }
}
