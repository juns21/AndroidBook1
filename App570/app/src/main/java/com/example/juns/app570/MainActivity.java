package com.example.juns.app570;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void mOnClick(View v) {
        switch (v.getId()) {
            case R.id.btnad:
                Toast.makeText(this, "광고를 보았습니다.", 1).show();
                break;
            case R.id.btncard:
                Toast.makeText(this, "물품 대금을 카드 결제합니다.", 1).show();
                break;
            case R.id.calltoast:
                LinearLayout linear = (LinearLayout) View.inflate(this, R.layout.faketoast, null);
                Toast fake = new Toast(this);
                fake.setView(linear);
                fake.setGravity(Gravity.LEFT | Gravity.TOP, 0, 20);
                fake.setDuration(Toast.LENGTH_LONG);
                fake.show();
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
