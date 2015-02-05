package com.example.juns.fragmenttest;

import android.app.*;
import android.graphics.*;
import android.os.*;
import android.view.*;
import android.widget.*;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragmenttest);
    }

    public static class CounterFragment extends Fragment {
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View root = inflater.inflate(R.layout.activity_main, container, false);

            Button btnIncrease = (Button) root.findViewById(R.id.btnincrease);
            final TextView textCounter = (TextView) root.findViewById(R.id.txtcounter);
            btnIncrease.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int count = Integer.parseInt(textCounter.getText().toString());
                    textCounter.setText(Integer.toString(count+1));
                }
            });

            return root;
        }
    }
}
