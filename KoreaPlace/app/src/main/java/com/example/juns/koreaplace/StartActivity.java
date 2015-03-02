package com.example.juns.koreaplace;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Juns on 2015-03-02.
 */
public class StartActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_start);
    }

    // OnClick method to start main activity
    public void Select(View view) {
        Intent intent = new Intent(StartActivity.this, MainActivity.class);
        intent.putExtra("Category", Integer.parseInt((String)view.getTag()) -1);
        startActivity(intent);
    }
}
