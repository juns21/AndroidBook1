package com.example.juns.entertainment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void myOnClick(View view) {
        // Start detail activity when click on zodiac
        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
        intent.putExtra("Type", (String)view.getTag());
        startActivity(intent);
    }
}
