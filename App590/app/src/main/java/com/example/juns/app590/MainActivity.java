package com.example.juns.app590;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {
    ArrayList<String> Items;
    ArrayAdapter<String> Adapter;
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Items = new ArrayList<String>();
        Items.add("First");
        Items.add("Second");
        Items.add("Third");

        Adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, Items);
        list = (ListView) findViewById(R.id.list);
        list.setAdapter(Adapter);
        list.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
    }

    public void mOnClick(View v) {
        EditText ed = (EditText) findViewById(R.id.newitem);
        switch (v.getId()) {
            case R.id.add:
                String text = ed.getText().toString();
                if (text.length() != 0) {
                    Items.add(text);
                    ed.setText("");
                    Adapter.notifyDataSetChanged();
                }
                break;
            case R.id.delete:
                int pos;
                pos = list.getCheckedItemPosition();
                if (pos != ListView.INVALID_POSITION) {
                    Items.remove(pos);
                    list.clearChoices();
                    Adapter.notifyDataSetChanged();
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
