package com.example.juns.app606;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {
    ArrayList<MyItem> arItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listviewtest);

        arItem = new ArrayList<MyItem>();
        MyItem mi;
        mi = new MyItem(R.drawable.ic_launcher, "삼성 노트북"); arItem.add(mi);
        mi = new MyItem(R.drawable.ic_launcher, "LG 세탁기"); arItem.add(mi);
        mi = new MyItem(R.drawable.ic_launcher, "대우 마티즈"); arItem.add(mi);

        MyListAdapter MyAdapter = new MyListAdapter(this, R.layout.activity_main, arItem);

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

// 리스트뷰에 출력할 항목
class MyItem {
    int Icon;
    String Name;

    MyItem(int aIcon, String aName) {
        Icon = aIcon;
        Name = aName;
    }
}

// 어댑터 클래스
class MyListAdapter extends BaseAdapter {
    Context maincon;
    LayoutInflater Inflater;
    ArrayList<MyItem> arSrc;
    int layout;

    public MyListAdapter(Context context, int alayout, ArrayList<MyItem> aarSrc) {
        maincon = context;
        Inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        arSrc = aarSrc;
        layout = alayout;
    }

    public int getCount() {
        return arSrc.size();
    }

    public String getItem(int position) {
        return arSrc.get(position).Name;
    }

    public long getItemId(int position) {
        return position;
    }

    // 각 항목의 뷰 생성
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        if (convertView == null) {
            convertView = Inflater.inflate(layout, parent, false);
        }
        ImageView img = (ImageView) convertView.findViewById(R.id.img);
        img.setImageResource(arSrc.get(position).Icon);

        TextView txt = (TextView) convertView.findViewById(R.id.text);
        txt.setText(arSrc.get(position).Name);

        Button btn = (Button) convertView.findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = arSrc.get(pos).Name +"을 주문합니다.";
                Toast.makeText(maincon, str, Toast.LENGTH_SHORT).show();
            }
        });
        return  convertView;
    }
}
