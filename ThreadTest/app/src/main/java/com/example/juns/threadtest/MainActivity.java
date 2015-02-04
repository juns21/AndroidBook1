package com.example.juns.threadtest;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {
    int mMainValue = 0;
    int mBackValue = 0;
    TextView mMainText;
    TextView mBackText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMainText = (TextView) findViewById(R.id.mainvalue);
        mBackText = (TextView) findViewById(R.id.backvalue);

        BackThread thread = new BackThread();
        thread.setDaemon(true);
        thread.start();
    }

    public void mOnClick(View v) {
        mMainValue++;
        mMainText.setText("MainValue : "+ mMainValue);
        mBackText.setText("BackValue : "+ mBackValue);
    }

    class BackThread extends Thread {
        public void run() {
            //super.run();
            while (true) {
                mBackValue++;
                //mBackText.setText("BackVallue : "+ mBackValue);;
                try {
                    Thread.sleep(1000);
                }catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
