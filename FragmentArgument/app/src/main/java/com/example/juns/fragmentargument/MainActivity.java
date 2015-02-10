package com.example.juns.fragmentargument;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends Activity {
    EditText mStartNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mStartNum = (EditText) findViewById(R.id.startnum);
    }

    public void mOnClick(View view) {
        switch (view.getId()) {
            case R.id.btnAdd:
                FragmentManager fm = getFragmentManager();
                FragmentTransaction tr = fm.beginTransaction();
                int start = Integer.parseInt(mStartNum.getText().toString());
                CounterFragment cf = CounterFragment.newInstance(start);
                tr.add(R.id.frame, cf, "counter");
                tr.commit();
                break;
        }
    }


    public static class CounterFragment extends Fragment {
        public static CounterFragment newInstance(int start) {
            CounterFragment cf = new CounterFragment();

            Bundle args = new Bundle();
            args.putInt("start", start);
            cf.setArguments(args);

            return cf;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            //return super.onCreateView(inflater, container, savedInstanceState);
            View root = inflater.inflate(R.layout.counterfragment, container, false);

            Button btnIncrease = (Button) root.findViewById(R.id.btnincrease);
            final TextView textCounter = (TextView) root.findViewById(R.id.txtcounter);

            int start = 0;
            Bundle args = getArguments();
            if (args != null) {
                start = args.getInt("start");
            }
            textCounter.setText(Integer.toString(start));

            btnIncrease.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int count = Integer.parseInt(textCounter.getText().toString());
                    textCounter.setText(Integer.toString(count));
                }
            });

            return root;
        }
    }
}
