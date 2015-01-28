package com.example.juns.expandablelistview;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MainActivity extends ActionBarActivity {
    ExpandableListView mList;
    String[] arProv = new String[] {"충청도", "경기도", "강원도"};
    String[][] arCity = new String[][] {
            {"논산", "당진", "부여"},
            {"수원", "용인"},
            {"춘천", "원주", "홍천", "강릉"}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mList = (ExpandableListView) findViewById(R.id.list);
        List<Map<String, String>> provData = new ArrayList<Map<String, String>>();
        List<List<Map<String, String>>> cityData = new ArrayList<List<Map<String, String>>>();
        for (int i=0; i<arProv.length; i++) {
            Map<String, String> Prov = new HashMap<String, String>();
            Prov.put("prov", arProv[i]);
            provData.add(Prov);

            List<Map<String, String>> children = new ArrayList<Map<String, String>>();
            for (int j=0; j<arCity[i].length; j++) {
                Map<String, String> City = new HashMap<String, String>();
                City.put("city", arCity[i][j]);
                children.add(City);
            }
            cityData.add(children);
        }

        ExpandableListAdapter adapter = new SimpleExpandableListAdapter(
                this,
                provData,
                android.R.layout.simple_expandable_list_item_1,
                new String[] {"prov"},
                new int[] { android.R.id.text1},
                cityData,
                android.R.layout.simple_expandable_list_item_1,
                new String[] {"city"},
                new int[] {android.R.id.text1}
        );
        mList.setAdapter(adapter);
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
