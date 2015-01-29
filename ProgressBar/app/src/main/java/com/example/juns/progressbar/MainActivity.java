package com.example.juns.progressbar;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;


public class MainActivity extends ActionBarActivity {
    ProgressBar mProgBar;
    ProgressBar mProgCircle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mProgBar = (ProgressBar) findViewById(R.id.progress);
        mProgCircle = (ProgressBar) findViewById(R.id.progcircle);
    }

    public void mOnClick(View v) {
        switch (v.getId()) {
            case R.id.decfirst:
                mProgBar.incrementProgressBy(-2);
                break;
            case R.id.incfirst:
                mProgBar.incrementProgressBy(2);
                break;
            case R.id.decsecond:
                mProgBar.incrementSecondaryProgressBy(-2);
                break;
            case R.id.incsecond:
                mProgBar.incrementSecondaryProgressBy(2);
                break;
            case R.id.start:
                mProgCircle.setVisibility(View.VISIBLE);
                break;
            case R.id.stop:
                mProgCircle.setVisibility(View.INVISIBLE);
                break;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
