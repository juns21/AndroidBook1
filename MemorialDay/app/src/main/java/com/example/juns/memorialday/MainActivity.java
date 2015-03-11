package com.example.juns.memorialday;

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

    // Click button help to show popup
    public void help(View view) {
        startActivity(new Intent(MainActivity.this, HelpActivity.class));
    }

    // Click number button to show item
    public void myOnClick(View view) {
        String tag = (String) view.getTag();

        findViewById(getResources().getIdentifier(tag, "id", getPackageName())).setVisibility(View.VISIBLE);
        view.setBackgroundResource(R.mipmap.bg_btn_selected);
    }

    // Click button clear to clear all view
    public void clear(View view) {
        for (int i=0; i<32; i++) {
            findViewById(getResources().getIdentifier("img" + (i+1), "id", getPackageName())).setVisibility(View.INVISIBLE);
            findViewById(getResources().getIdentifier("btn" + (i+1), "id", getPackageName())).setBackgroundResource(R.mipmap.bg_btn);
        }
    }
}
