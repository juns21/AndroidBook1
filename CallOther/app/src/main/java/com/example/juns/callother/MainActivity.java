package com.example.juns.callother;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.io.File;

import static android.view.View.OnClickListener;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnWeb = (Button) findViewById(R.id.web);
        Button btnDial = (Button) findViewById(R.id.dial);
        Button btnPicture = (Button) findViewById(R.id.picture);
        Button btnOther = (Button) findViewById(R.id.other);

        btnWeb.setOnClickListener(mOnClick);
        btnDial.setOnClickListener(mOnClick);
        btnPicture.setOnClickListener(mOnClick);
        btnOther.setOnClickListener(mOnClick);
    }

    OnClickListener mOnClick = new OnClickListener() {

        @Override
        public void onClick(View v) {
            Intent intent;
            switch (v.getId()) {
                case R.id.web:
                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.co.kr"));
                    startActivity(intent);
                    break;
                case R.id.dial:
                    intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:015-123-4567"));
                    startActivity(intent);
                    break;
                case R.id.picture:
                    intent = new Intent(Intent.ACTION_VIEW);
                    String sd = Environment.getExternalStorageDirectory().getAbsolutePath();
                    Uri uri = Uri.fromFile(new File(sd +"/test.jpg"));
                    intent.setDataAndType(uri, "image/jpeg");
                    startActivity(intent);
                    break;
                case R.id.other:
                    intent = new Intent(Intent.ACTION_MAIN);
                    intent.setComponent(new ComponentName("exam.memo", "exam.memo.Memo"));
                    startActivity(intent);
                    break;
            }
        }
    };


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
