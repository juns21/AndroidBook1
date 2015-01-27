package com.example.juns.app612;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {
    ArrayList<ListItem> arItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        arItem = new ArrayList<ListItem>();
        arItem.add(new ListItem(0, "제목", "입력내용", "", 0));
        arItem.add(new ListItem(1, "", "", "push", R.drawable.ic_launcher));
        arItem.add(new ListItem(0, "이름을 입력하시오", "니 이름", "", 0));
        arItem.add(new ListItem(0, "나이도 입력하시오", "몇살이니", "", 0));
        arItem.add(new ListItem(1, "", "", "누르시오", R.drawable.ic_launcher));
        arItem.add(new ListItem(1, "", "", "업로드", R.drawable.ic_launcher));
        arItem.add(new ListItem(0, "주소", "어디사니?", "", 0));

        MultiAdapter MyAdapter = new MultiAdapter(this, arItem);

        ListView MyList;
        MyList = (ListView) findViewById(R.id.list);
        MyList.setAdapter(MyAdapter);
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