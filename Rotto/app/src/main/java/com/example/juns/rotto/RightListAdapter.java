package com.example.juns.rotto;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

/**
 * Created by Juns on 2015-02-12.
 */
public class RightListAdapter extends ArrayAdapter<Integer> {
    private final Context context;
    private final Integer[] values;

    public RightListAdapter(Context context, Integer[] objects) {
        super(context, R.layout.listviewresult, objects);

        this.context = context;
        this.values = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //return super.getView(position, convertView, parent);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.listviewresult, parent, false);
        ImageView imageView1 = (ImageView) rowView.findViewById(R.id.imgIcon);

        String s = "number_"+ values[position];         // image name is needed without extention
        int RID = context.getResources().getIdentifier(s, "drawable", context.getPackageName());
        imageView1.setImageResource(RID);

        return rowView;
    }
}
