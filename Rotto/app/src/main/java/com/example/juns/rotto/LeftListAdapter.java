package com.example.juns.rotto;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Juns on 2015-02-12.
 */
public class LeftListAdapter extends ArrayAdapter<int[]> {
    private final Context context;
    private final List<int[]> arrvalues;

    public LeftListAdapter(Context context, int[][] objects) {
        super(context, R.layout.listview_item, objects);
        this.context = context;
        this.arrvalues = Arrays.asList(objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //return super.getView(position, convertView, parent);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.listview_item, parent, false);

        ArrayList<ImageView> arrImage = new ArrayList<ImageView>();

        ImageView imageView1 = (ImageView) rowView.findViewById(R.id.imgIconPos1);
        ImageView imageView2 = (ImageView) rowView.findViewById(R.id.imgIconPos2);
        ImageView imageView3 = (ImageView) rowView.findViewById(R.id.imgIconPos3);
        ImageView imageView4 = (ImageView) rowView.findViewById(R.id.imgIconPos4);
        ImageView imageView5 = (ImageView) rowView.findViewById(R.id.imgIconPos5);
        ImageView imageView6 = (ImageView) rowView.findViewById(R.id.imgIconPos6);

        arrImage.add(imageView1);
        arrImage.add(imageView2);
        arrImage.add(imageView3);
        arrImage.add(imageView4);
        arrImage.add(imageView5);
        arrImage.add(imageView6);

        int a[] = this.arrvalues.get(position);
        // Number of left item always is 6
        for (int i=0; i<6; i++) {
            String s = "number_"+ a[i];         // image name is needed without extention
            int RID = context.getResources().getIdentifier(s, "drawable", context.getPackageName());

            arrImage.get(i).setImageResource(RID);
        }

        return rowView;
    }
}
