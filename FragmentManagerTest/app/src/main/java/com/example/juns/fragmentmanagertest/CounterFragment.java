package com.example.juns.fragmentmanagertest;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Juns on 2015-02-05.
 */
public class CounterFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View root = inflater.inflate(R.layout.counterfragment, container, false);

        Button btnIncrease = (Button) root.findViewById(R.id.btnincrease);
        final TextView textCounter = (TextView) root.findViewById(R.id.txtcounter);

        if (savedInstanceState != null) {
            textCounter.setText(Integer.toString(savedInstanceState.getInt("counter")));
        }

        btnIncrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = Integer.parseInt(textCounter.getText().toString());
                textCounter.setText(Integer.toString(count + 1));
            }
        });

        return root;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        TextView textCounter = (TextView) getView().findViewById(R.id.txtcounter);
        int a = Integer.parseInt(textCounter.getText().toString());
        outState.putInt("counter", a);
    }
}
