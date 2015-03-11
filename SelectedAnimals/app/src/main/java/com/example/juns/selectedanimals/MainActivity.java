package com.example.juns.selectedanimals;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void myOnclick(View v) {
        // Click to go to main screen
        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
        intent.putExtra("Animal", (String)v.getTag());
        startActivity(intent);
    }
}
