package com.example.juns.koreaplace;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.devsmart.android.ui.HorizontalListView;

public class MainActivity extends Activity {
    // Global mutable variables;
    private ImageView btnContent;
    private ImageView btnHome;
    private ImageView image;
    private HorizontalListView listView;

    private String[] arData;
    private String[] arImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Init data from resource xml
        arData = getResources().getStringArray(R.array.Data);
        arImage = getResources().getStringArray(getResources().getIdentifier(arData[getIntent().getIntExtra("Category", -1)].split(" # ")[0], "array", getPackageName()));

        // Set title
        TextView txtTitle = (TextView) findViewById(R.id.txtTitle);
        txtTitle.setText(arData[getIntent().getIntExtra("Category", -1)].split(" # ")[1]);

        // Init button content, click to show popup
        btnContent = (ImageView) findViewById(R.id.btnContent);
        btnContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ContentActivity.class);
                intent.putExtra("Content", arData[getIntent().getIntExtra("Category", -1)].split(" # ")[2]);
                startActivity(intent);
            }
        });

        // Init button home, click to go to first screen
        btnHome = (ImageView) findViewById(R.id.btnHome);
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Init first Image
        image = (ImageView) findViewById(R.id.image);
        image.setImageResource(getResources().getIdentifier(arImage[0], "mipmap", getPackageName()));

        // Init horizontal listview
        listView = (HorizontalListView)findViewById(R.id.listview);
        listView.setAdapter(mAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                // CLick on listview item to view image
                image.setImageResource(getResources().getIdentifier(arImage[arg2], "mipmap", getPackageName()));
            }
        });
    }

    private BaseAdapter mAdapter = new BaseAdapter() {
        @Override
        public int getCount() {
            return arImage.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View retval = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_photo, null);
            ImageView imgThumb = (ImageView) retval.findViewById(R.id.imgThumb);
            imgThumb.setImageResource(getResources().getIdentifier(arImage[position], "mipmap", getPackageName()));

            int iDisplayWidth = getResources().getDisplayMetrics().widthPixels;
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(iDisplayWidth/3, ViewGroup.LayoutParams.MATCH_PARENT);
            imgThumb.setLayoutParams(lp);
            return retval;
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
