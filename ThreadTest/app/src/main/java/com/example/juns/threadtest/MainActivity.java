package com.example.juns.threadtest;

import android.os.Handler;
import android.os.Message;
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
        //mBackText.setText("BackValue : "+ mBackValue);
    }

    class BackThread extends Thread {
        public void run() {
            //super.run();
            while (true) {
                mBackValue++;
                /**
                 * 스레드는 백그라운드에서만 작동하기 때문에
                 * 내부 컨트롤을 하면 에러가 발생한다.
                 * 내부 컨트롤을 하고 싶다면 핸들러를 사용한다.
                 */
                //mBackText.setText("BackVallue : "+ mBackValue);;
                mHandler.sendEmptyMessage(0);
                try {
                    Thread.sleep(1000);
                }catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    Handler mHandler = new Handler() {
        public  void handleMessage(Message msg) {
            if (msg.what == 0) {
                mBackText.setText("BackValue : "+ mBackValue);
            }
        }
    };
}
