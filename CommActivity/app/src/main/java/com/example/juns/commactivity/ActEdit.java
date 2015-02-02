package com.example.juns.commactivity;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


public class ActEdit extends ActionBarActivity {
    EditText mEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_edit);

        mEdit = (EditText) findViewById(R.id.stredit);

        Intent intent = getIntent();
        String text = intent.getStringExtra("TextIn");
        if (text != null) {
            mEdit.setText(text);
        }
    }

    public void mOnClick(View v) {
        switch (v.getId()) {
            case R.id.btnok:
                Intent intent = new Intent();
                intent.putExtra("TextOut", mEdit.getText().toString());
                setResult(RESULT_OK, intent);
                finish();
                break;
            case R.id.btncancel:
                setResult(RESULT_CANCELED);
                finish();
                break;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_act_edit, menu);
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
