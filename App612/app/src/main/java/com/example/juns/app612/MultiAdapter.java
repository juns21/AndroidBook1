package com.example.juns.app612;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Juns on 2015-01-27.
 */
public class MultiAdapter extends BaseAdapter {
    LayoutInflater mInflater;
    ArrayList<ListItem> arSrc;

    public MultiAdapter(Context context, ArrayList<ListItem> arItem) {
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        arSrc = arItem;
    }

    public int getCount() {
        return arSrc.size();
    }

    public ListItem getItem(int position) {
        return arSrc.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    // getView가 생성하는 뷰의 개수를 리턴한다. 항상 같은 뷰를 생성하면 1을 리턴한다.
    // 이 메서드에서 개수를 제대로 조사해 주지 않으면 다운된다.
    public int getViewTypeCount() {
        return 2;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        // 최소 호출이면 항목 뷰를 생성한다.
        // 타입별로 뷰를 다르게 디자인할 수 있으면 높이가 달라도 상관없다.
        if (convertView == null) {
            int res = 0;
            switch (arSrc.get(position).Type) {
                case 0:
                    res = R.layout.textedit;
                    break;
                case 1:
                    res = R.layout.btnicon;
                    break;
            }
            convertView = mInflater.inflate(res, parent, false);
        }

        // 항목 뷰를 초기화한다.
        switch (arSrc.get(position).Type) {
            case 0:
                TextView txt = (TextView) convertView.findViewById(R.id.text);
                txt.setText(arSrc.get(position).Text);
                EditText edit = (EditText) convertView.findViewById(R.id.edit);
                edit.setText(arSrc.get(position).EditText);
                break;
            case 1:
                Button btn = (Button) convertView.findViewById(R.id.btn);
                btn.setText(arSrc.get(position).BtnText);
                ImageView img = (ImageView) convertView.findViewById(R.id.img);
                img.setImageResource(arSrc.get(position).IconRes);
                break;
        }
        return convertView;
    }
}
