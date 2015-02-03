package com.example.juns.actparent;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by Juns on 2015-02-03.
 */
public class ActChild extends Activity {
    static final String TAG = "ActChild";

    public void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actchild);
    }

    public void onStart() {
        super.onStart();
        Log.i(TAG, "onStart");
    }

    public void onResume() {
        super.onResume();
        Log.i(TAG, "onResume");
    }

    public void onPause() {
        super.onPause();
        Log.i(TAG, "onPause");
    }

    public void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart");
    }

    public void onStop() {
        super.onStop();
        Log.i(TAG, "onStop");
    }

    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
    }
}
