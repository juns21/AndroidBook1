package com.example.juns.rotto;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Juns on 2015-02-12.
 */
public class ResultListView extends ArrayAdapter<Integer> {
    private final Context mContex;
    private final Integer[] mValues;
    private final static Integer[] mArrayNumber = new Integer[7];

    public ResultListView(Context context, Integer[] objects) {
        super(context, R.layout.listviewitemgoodlook, mArrayNumber);

        mContex = context;
        mValues = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //return super.getView(position, convertView, parent);
        LayoutInflater inflater = (LayoutInflater) mContex.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.listviewitemgoodlook, parent, false);

        ArrayList<ImageView> arrImage = new ArrayList<ImageView>();
        ArrayList<TextView> arrText = new ArrayList<TextView>();
        ArrayList<LinearLayout> arrLin = new ArrayList<LinearLayout>();

        ImageView imageView1 = (ImageView) rowView.findViewById(R.id.imgResultPos1);
        ImageView imageView2 = (ImageView) rowView.findViewById(R.id.imgResultPos2);
        ImageView imageView3 = (ImageView) rowView.findViewById(R.id.imgResultPos3);
        ImageView imageView4 = (ImageView) rowView.findViewById(R.id.imgResultPos4);
        ImageView imageView5 = (ImageView) rowView.findViewById(R.id.imgResultPos5);
        ImageView imageView6 = (ImageView) rowView.findViewById(R.id.imgResultPos6);
        ImageView imageView7 = (ImageView) rowView.findViewById(R.id.imgResultPos7);

        TextView textResult1 = (TextView) rowView.findViewById(R.id.txt_result1);
        TextView textResult2 = (TextView) rowView.findViewById(R.id.txt_result2);
        TextView textResult3 = (TextView) rowView.findViewById(R.id.txt_result3);
        TextView textResult4 = (TextView) rowView.findViewById(R.id.txt_result4);
        TextView textResult5 = (TextView) rowView.findViewById(R.id.txt_result5);
        TextView textResult6 = (TextView) rowView.findViewById(R.id.txt_result6);
        TextView textResult7 = (TextView) rowView.findViewById(R.id.txt_result7);

        LinearLayout ln_bg1 = (LinearLayout)rowView.findViewById(R.id.lin1);
        LinearLayout ln_bg2 = (LinearLayout)rowView.findViewById(R.id.lin2);
        LinearLayout ln_bg3 = (LinearLayout)rowView.findViewById(R.id.lin3);
        LinearLayout ln_bg4 = (LinearLayout)rowView.findViewById(R.id.lin4);
        LinearLayout ln_bg5 = (LinearLayout)rowView.findViewById(R.id.lin5);
        LinearLayout ln_bg6 = (LinearLayout)rowView.findViewById(R.id.lin6);
        LinearLayout ln_bg7 = (LinearLayout)rowView.findViewById(R.id.lin7);

        arrImage.add(imageView1);
        arrImage.add(imageView2);
        arrImage.add(imageView3);
        arrImage.add(imageView4);
        arrImage.add(imageView5);
        arrImage.add(imageView6);
        arrImage.add(imageView7);

        arrText.add(textResult1);
        arrText.add(textResult2);
        arrText.add(textResult3);
        arrText.add(textResult4);
        arrText.add(textResult5);
        arrText.add(textResult6);
        arrText.add(textResult7);

        arrLin.add(ln_bg1);
        arrLin.add(ln_bg2);
        arrLin.add(ln_bg3);
        arrLin.add(ln_bg4);
        arrLin.add(ln_bg5);
        arrLin.add(ln_bg6);
        arrLin.add(ln_bg7);

        int j=0;
        for (int i=(position)*7; i<(position+1)*7; i++) {
            if (i+1 >= mValues.length) {
                arrLin.get(j).setVisibility(View.INVISIBLE);
            }else {
                String s = "number_"+ String.valueOf(i+1);          // iamge name is needed without extention
                int RID = mContex.getResources().getIdentifier(s, "drawable", mContex.getPackageName());
                arrImage.get(j).setImageResource(RID);
                arrText.get(j).setText(mValues[i+1].toString() +"번");
            }
            j++;
        }
        return rowView;
    }
}
