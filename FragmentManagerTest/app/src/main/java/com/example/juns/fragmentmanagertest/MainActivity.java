package com.example.juns.fragmentmanagertest;


import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void mOnClick(View v) {
        FragmentManager fm = getFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.frame);

        switch (v.getId()) {
            case R.id.btnAdd:
                if (fragment == null) {
                    FragmentTransaction tr = fm.beginTransaction();
                    CounterFragment cf = new CounterFragment();
                    tr.add(R.id.frame, cf, "counter");
                    tr.commit();
                    //fm.beginTransaction().add(R.id.frame, cf, "counter").commit();
                }else {
                    Toast.makeText(this, "이미 추가되어 있습니다.", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnRemove:
                if (fragment == null) {
                    Toast.makeText(this, "프래그먼트가 없습니다.", Toast.LENGTH_SHORT).show();
                }else {
                    FragmentTransaction tr = fm.beginTransaction();
                    tr.remove(fragment);
                    tr.commit();
                }
                break;
            case R.id.btnReplace:
                if (fragment == null) {
                    Toast.makeText(this, "프래그먼트가 없습니다.", Toast.LENGTH_SHORT).show();
                }else {
                    FragmentTransaction tr = fm.beginTransaction();
                    if (fragment.getTag() == "counter") {
                        TextFragment tf = new TextFragment();
                        tr.replace(R.id.frame, tf, "text");
                    }else {
                        CounterFragment cf = new CounterFragment();
                        tr.replace(R.id.frame, cf, "counter");
                    }
                    tr.commit();
                }
                break;
            case R.id.btnHideShow:
                if (fragment == null) {
                    Toast.makeText(this, "프래그먼트가 없습니다.", Toast.LENGTH_SHORT).show();
                }else {
                    FragmentTransaction tr = fm.beginTransaction();
                    if (fragment.isHidden()) {
                        tr.show(fragment);
                    }else {
                        tr.hide(fragment);
                    }
                    tr.commit();
                }
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
