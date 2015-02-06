package com.example.juns.fragmentmanagertest;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Juns on 2015-02-06.
 */
public class TextFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View root = inflater.inflate(R.layout.textfragment, container, false);
        TextView text = (TextView) root.findViewById(R.id.text);
        text.setSaveEnabled(true);
        return root;
    }
}
