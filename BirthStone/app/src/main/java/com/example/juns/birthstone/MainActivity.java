package com.example.juns.birthstone;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends Activity {
    // Global mutable variables
    private GridView gridView;

    private String[] months = new String[12];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView = (GridView) findViewById(R.id.gridview);
        MonthAdapter adapter = new MonthAdapter(getApplicationContext());
        gridView.setAdapter(adapter);

        months = getResources().getStringArray(R.array.months);
    }

    // Adapter to show detail in gridview
    public class MonthAdapter extends BaseAdapter {
        private final LayoutInflater mInflater;

        public MonthAdapter(Context c) {
            mInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return months.length;
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
            // Show gridview's item as imageview
            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.item, parent, false);
            }

            final TextView txtMonth = (TextView) convertView.findViewById(R.id.txtMonth);
            ImageView imgIcon = (ImageView) convertView.findViewById(R.id.imgIcon);

            // Show detail follow position in gridview
            switch (position) {
                case 0:
                    txtMonth.setBackgroundResource(R.drawable.header1);
                    txtMonth.setText(months[0]);
                    imgIcon.setImageResource(R.drawable.stone1);
                    break;
                case 1:
                    txtMonth.setBackgroundResource(R.drawable.header2);
                    txtMonth.setText(months[1]);
                    imgIcon.setImageResource(R.drawable.stone2);
                    break;
                case 2:
                    txtMonth.setBackgroundResource(R.drawable.header3);
                    txtMonth.setText(months[2]);
                    imgIcon.setImageResource(R.drawable.stone3);
                    break;
                case 3:
                    txtMonth.setBackgroundResource(R.drawable.header4);
                    txtMonth.setText(months[3]);
                    imgIcon.setImageResource(R.drawable.stone4);
                    break;
                case 4:
                    txtMonth.setBackgroundResource(R.drawable.header5);
                    txtMonth.setText(months[4]);
                    imgIcon.setImageResource(R.drawable.stone5);
                    break;
                case 5:
                    txtMonth.setBackgroundResource(R.drawable.header6);
                    txtMonth.setText(months[5]);
                    imgIcon.setImageResource(R.drawable.stone6);
                    break;
                case 6:
                    txtMonth.setBackgroundResource(R.drawable.header7);
                    txtMonth.setText(months[6]);
                    imgIcon.setImageResource(R.drawable.stone7);
                    break;
                case 7:
                    txtMonth.setBackgroundResource(R.drawable.header8);
                    txtMonth.setText(months[7]);
                    imgIcon.setImageResource(R.drawable.stone8);
                    break;
                case 8:
                    txtMonth.setBackgroundResource(R.drawable.header9);
                    txtMonth.setText(months[8]);
                    imgIcon.setImageResource(R.drawable.stone9);
                    break;
                case 9:
                    txtMonth.setBackgroundResource(R.drawable.header10);
                    txtMonth.setText(months[9]);
                    imgIcon.setImageResource(R.drawable.stone10);
                    break;
                case 10:
                    txtMonth.setBackgroundResource(R.drawable.header11);
                    txtMonth.setText(months[10]);
                    imgIcon.setImageResource(R.drawable.stone11);
                    break;
                case 11:
                    txtMonth.setBackgroundResource(R.drawable.header12);
                    txtMonth.setText(months[11]);
                    imgIcon.setImageResource(R.drawable.stone12);
                    break;
                default:
                    break;
            }

            final int selectedMonth = position;
            // Click on item to go to detail page
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                    intent.putExtra("month", selectedMonth);
                    startActivity(intent);
                }
            });

            return convertView;
        }
    }
}
