package com.example.juns.flowerlanguage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Vector;


public class MainActivity extends Activity {
    // Global mutable variables
    private Context mContext;

    /**
     * Pager Tool
     */
    private Button btnPrev;
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    private Button btn5;
    private Button btnNext;

    /**
     * Search Filter
     */
    private EditText edtFilter;

    private boolean inited = false;
    private int totalpages = 0;
    private int noOfItem = 0;
    private ViewPager viewPager;
    private String[] arFlower;
    private String[] arFlower_Description;
    private String[] arFlower_Mean;
    private String[] arFlower_Image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;

        // Init data from resource xml
        arFlower = getResources().getStringArray(R.array.Flower);
        arFlower_Description = getResources().getStringArray(R.array.Flower_Description);
        arFlower_Mean = getResources().getStringArray(R.array.Flower_Mean);
        arFlower_Image = getResources().getStringArray(R.array.Flower_Image);

        // Init viewpager
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        edtFilter = (EditText) findViewById(R.id.edtFilter);
        edtFilter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                // Reinit pager when user input in the filter
                totalpages = 0;
                if (edtFilter.getText().toString().equals("")) { // Input "", show full list of flower
                    initPager(arFlower, arFlower_Description, arFlower_Mean, arFlower_Image);
                }else {
                    // Init with flower name contains string in filter;
                    ArrayList<String> arFlowerTemp = new ArrayList<String>();
                    ArrayList<String> arFlowerDescTemp = new ArrayList<String>();
                    ArrayList<String> arFlowerMeanTemp = new ArrayList<String>();
                    ArrayList<String> arFlowerImageTemp = new ArrayList<String>();

                    for (int i=0; i<arFlower.length; i++) {
                        if (arFlower[i].contains(edtFilter.getText().toString())) {
                            arFlowerTemp.add(arFlower[i]);
                            arFlowerDescTemp.add(arFlower_Description[i]);
                            arFlowerMeanTemp.add(arFlower_Mean[i]);
                            arFlowerImageTemp.add(arFlower_Image[i]);
                        }
                    }

                    String[] arStrFlower = new String[arFlowerTemp.size()];
                    arStrFlower = arFlowerTemp.toArray(arStrFlower);

                    String[] arStrFlowerDesc = new String[arFlowerDescTemp.size()];
                    arStrFlowerDesc = arFlowerTemp.toArray(arStrFlowerDesc);

                    String[] arStrFlowerMean = new String[arFlowerMeanTemp.size()];
                    arStrFlowerMean = arFlowerTemp.toArray(arStrFlowerMean);

                    String[] arStrFlowerImage = new String[arFlowerImageTemp.size()];
                    arStrFlowerImage = arFlowerTemp.toArray(arStrFlowerImage);

                    initPager(arStrFlower, arStrFlowerDesc, arStrFlowerMean, arStrFlowerImage);
                }
            }
        });
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        // Just init one time in first time open application with all information from string.xml
        if (!inited) {
            initPager(arFlower, arFlower_Description, arFlower_Mean, arFlower_Image);
        }
    }

    // Init pager with listview
    public void initPager(String[] arFlower, String[] arFlower_Desc, String[] arFlower_Mean, String[] arFlower_Image) {
        float rate = viewPager.getHeight() / getResources().getDimension(R.dimen.item_height);
        // Calculate number of listview item depends on screen's height
        noOfItem = Math.round(rate * 10) / 10;

        Vector<View> pages = new Vector<View>();

        int index = 0;

        // Split full array to smaller array for listview in each page
        while (index < arFlower.length) {
            ArrayList<String> arFlowerTemp = new ArrayList<String>();
            ArrayList<String> arFlowerDescTemp = new ArrayList<String>();
            ArrayList<String> arFlowerMeanTemp = new ArrayList<String>();
            ArrayList<String> arFlowerImageTemp = new ArrayList<String>();

            for (int x=0; x<noOfItem; x++) {
                if (arFlower.length > index && arFlower[index] != null) {
                    arFlowerTemp.add(arFlower[index]);
                    arFlowerDescTemp.add(arFlower_Desc[index]);
                    arFlowerMeanTemp.add(arFlower_Mean[index]);
                    arFlowerImageTemp.add(arFlower_Image[index]);
                }
                index++;
            }
            totalpages++;

            ListView lv = new ListView(mContext);
            FlowerAdapter adapter = new FlowerAdapter(mContext, arFlowerTemp, arFlowerDescTemp, arFlowerMeanTemp, arFlowerImageTemp);
            lv.setAdapter(adapter);

            pages.add(lv);
        }

        // Set adapter for viewpager
        CustomPagerAdapter adapter = new CustomPagerAdapter(mContext, pages);
        viewPager.setAdapter(adapter);

        // Update control board state when viewpager's page is changed
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                // Reset button background
                if (String.valueOf(viewPager.getCurrentItem() +1).equals(btn1.getText().toString())) {
                    btn1.setBackgroundResource(R.drawable.cell_number_click);
                    btn2.setBackgroundResource(R.drawable.btn_pager_bg);
                    btn3.setBackgroundResource(R.drawable.btn_pager_bg);
                    btn4.setBackgroundResource(R.drawable.btn_pager_bg);
                    btn5.setBackgroundResource(R.drawable.btn_pager_bg);
                }else if (String.valueOf(viewPager.getCurrentItem() + 1).equals(btn2.getText().toString())) {
                    btn1.setBackgroundResource(R.drawable.btn_pager_bg);
                    btn2.setBackgroundResource(R.drawable.cell_number_click);
                    btn3.setBackgroundResource(R.drawable.btn_pager_bg);
                    btn4.setBackgroundResource(R.drawable.btn_pager_bg);
                    btn5.setBackgroundResource(R.drawable.btn_pager_bg);
                }else if (String.valueOf(viewPager.getCurrentItem() + 1).equals(btn3.getText().toString())) {
                    btn1.setBackgroundResource(R.drawable.btn_pager_bg);
                    btn2.setBackgroundResource(R.drawable.btn_pager_bg);
                    btn3.setBackgroundResource(R.drawable.cell_number_click);
                    btn4.setBackgroundResource(R.drawable.btn_pager_bg);
                    btn5.setBackgroundResource(R.drawable.btn_pager_bg);
                }else if (String.valueOf(viewPager.getCurrentItem() + 1).equals(btn4.getText().toString())) {
                    btn1.setBackgroundResource(R.drawable.btn_pager_bg);
                    btn2.setBackgroundResource(R.drawable.btn_pager_bg);
                    btn3.setBackgroundResource(R.drawable.btn_pager_bg);
                    btn4.setBackgroundResource(R.drawable.cell_number_click);
                    btn5.setBackgroundResource(R.drawable.btn_pager_bg);
                }else if (String.valueOf(viewPager.getCurrentItem() + 1).equals(btn5.getText().toString())) {
                    btn1.setBackgroundResource(R.drawable.btn_pager_bg);
                    btn2.setBackgroundResource(R.drawable.btn_pager_bg);
                    btn3.setBackgroundResource(R.drawable.btn_pager_bg);
                    btn4.setBackgroundResource(R.drawable.btn_pager_bg);
                    btn5.setBackgroundResource(R.drawable.cell_number_click);
                }

                // Reset button's text
                if (viewPager.getCurrentItem()+1 == Integer.parseInt(btn1.getText().toString())) {
                    if (!btn1.getText().toString().equals("1")) {
                        btn1.setText((Integer.parseInt(btn1.getText().toString()) - 1) + "");
                        btn2.setText((Integer.parseInt(btn2.getText().toString()) - 1) + "");
                        btn3.setText((Integer.parseInt(btn3.getText().toString()) - 1) + "");
                        btn4.setText((Integer.parseInt(btn4.getText().toString()) - 1) + "");
                        btn5.setText((Integer.parseInt(btn5.getText().toString()) - 1) + "");
                    }
                }

                if (viewPager.getCurrentItem() + 1 == Integer.parseInt(btn5.getText().toString())) {
                    if (!btn5.getText().toString().equals(totalpages + "")) {
                        btn1.setText((Integer.parseInt(btn1.getText().toString()) + 1) + "");
                        btn2.setText((Integer.parseInt(btn2.getText().toString()) + 1) + "");
                        btn3.setText((Integer.parseInt(btn3.getText().toString()) + 1) + "");
                        btn4.setText((Integer.parseInt(btn4.getText().toString()) + 1) + "");
                        btn5.setText((Integer.parseInt(btn5.getText().toString()) + 1) + "");
                    }
                }
            }

            @Override
            public void onPageSelected(int i) {

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        // Go to previous page if current page is's 0
        btnPrev = (Button) findViewById(R.id.btnPrev);
        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (viewPager.getCurrentItem() > 0) {
                    viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
                }
            }
        });

        // Reset button page 1 state
        btn1 = (Button) findViewById(R.id.btn1);
        btn1.setText("1");
        btn1.setVisibility(View.VISIBLE);
        btn1.setBackgroundResource(R.drawable.cell_number_click);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(Integer.parseInt(btn1.getText().toString()) -1);
            }
        });

        // Reset button page 1 state
        btn2 = (Button) findViewById(R.id.btn2);
        btn2.setText("2");
        btn2.setVisibility(View.VISIBLE);
        btn2.setBackgroundResource(R.drawable.cell_number_click);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(Integer.parseInt(btn2.getText().toString()) -1);
            }
        });

        // Reset button page 1 state
        btn3 = (Button) findViewById(R.id.btn3);
        btn3.setText("3");
        btn3.setVisibility(View.VISIBLE);
        btn3.setBackgroundResource(R.drawable.cell_number_click);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(Integer.parseInt(btn3.getText().toString()) -1);
            }
        });

        // Reset button page 1 state
        btn4 = (Button) findViewById(R.id.btn4);
        btn4.setText("4");
        btn4.setVisibility(View.VISIBLE);
        btn4.setBackgroundResource(R.drawable.cell_number_click);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(Integer.parseInt(btn4.getText().toString()) -1);
            }
        });

        // Reset button page 1 state
        btn5 = (Button) findViewById(R.id.btn5);
        btn5.setText("5");
        btn5.setVisibility(View.VISIBLE);
        btn5.setBackgroundResource(R.drawable.cell_number_click);
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(Integer.parseInt(btn5.getText().toString()) -1);
            }
        });

        // Set gone for button if viewpage's number of page is smaller than 5
        if (totalpages == 1) {
            btn2.setVisibility(View.GONE);
            btn3.setVisibility(View.GONE);
            btn4.setVisibility(View.GONE);
            btn5.setVisibility(View.GONE);
        }else if (totalpages == 2) {
            btn3.setVisibility(View.GONE);
            btn4.setVisibility(View.GONE);
            btn5.setVisibility(View.GONE);
        }else if (totalpages == 3) {
            btn4.setVisibility(View.GONE);
            btn5.setVisibility(View.GONE);
        }else if (totalpages == 4) {
            btn5.setVisibility(View.GONE);
        }

        // Go to next page
        btnNext = (Button) findViewById(R.id.btnNext);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (viewPager.getCurrentItem() < totalpages) {
                    viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                }
            }
        });

        inited = true;
    }

    // Adapter to show detail in listview
    public class FlowerAdapter extends BaseAdapter {
        Context mContext;
        private final LayoutInflater mInflater;
        ArrayList<String> arFlower;
        ArrayList<String> arFlower_Description;
        ArrayList<String> arFlower_Mean;
        ArrayList<String> arFlower_Image;

        public FlowerAdapter(Context c, ArrayList<String> arFlower, ArrayList<String> arFlower_Description, ArrayList<String> arFlower_Mean, ArrayList<String> arFlower_Image) {
            mContext = c;
            this.arFlower = arFlower;
            this.arFlower_Description = arFlower_Description;
            this.arFlower_Mean = arFlower_Mean;
            this.arFlower_Image = arFlower_Image;

            mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return arFlower.size();
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
            final int pos = position;

            ViewHolder holder;

            View rowView = convertView;

            if (convertView == null) {
                holder = new ViewHolder();

                rowView = mInflater.inflate(R.layout.item_flower, parent, false);

                holder.llItem = (LinearLayout) rowView.findViewById(R.id.llItem);
                holder.txtName = (TextView) rowView.findViewById(R.id.txtName);
                holder.txtDesc = (TextView) rowView.findViewById(R.id.txtMean);

                rowView.setTag(holder);
            }else {
                holder = (ViewHolder) rowView.getTag();
            }

            holder.txtName.setText(arFlower.get(position));
            holder.txtDesc.setText(arFlower_Description.get(position));
            holder.llItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                    intent.putExtra("Name", arFlower.get(pos));
                    intent.putExtra("Image", arFlower_Image.get(pos));
                    intent.putExtra("Content", arFlower_Mean.get(pos));
                    startActivity(intent);
                }
            });
            return rowView;
        }
    }

    // Use this viewholder to increase listview performance
    private class ViewHolder {
        public LinearLayout llItem;
        public TextView txtName;
        public TextView txtDesc;
    }

    // Viewpager's adapter
    private class CustomPagerAdapter extends PagerAdapter {
        private Vector<View> pages;

        private CustomPagerAdapter(Context context, Vector<View> pages) {
            this.pages = pages;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            //return super.instantiateItem(container, position);
            View page = pages.get(position);
            container.addView(page);
            return page;
        }

        @Override
        public int getCount() {
            return pages.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object o) {
            return view.equals(o);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
