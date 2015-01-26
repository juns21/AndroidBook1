package com.example.juns.app566;

import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ToggleButton;


public class MainActivity extends ActionBarActivity {
    TextView mSample;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSample = (TextView) findViewById(R.id.txtSample);

        RadioGroup ColGroup = (RadioGroup) findViewById(R.id.colorgroup);
        ColGroup.setOnCheckedChangeListener(mRadioCheck);

        CheckBox chkWhite = (CheckBox) findViewById(R.id.chkwhiteback);
        chkWhite.setOnCheckedChangeListener(mCheckChange);

        ToggleButton tgLang = (ToggleButton) findViewById(R.id.tglanguage);
        tgLang.setOnCheckedChangeListener(mCheckChange);
    }

    RadioGroup.OnCheckedChangeListener mRadioCheck = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (group.getId() == R.id.colorgroup) {
                switch (checkedId) {
                    case R.id.rared:
                        mSample.setTextColor(Color.RED);
                        break;
                    case R.id.ragreen:
                        mSample.setTextColor(Color.GREEN);
                        break;
                    case R.id.rablue:
                        mSample.setTextColor(Color.BLUE);
                        break;
                }
            }
        }
    };

    CompoundButton.OnCheckedChangeListener mCheckChange = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (buttonView.getId() == R.id.chkwhiteback) {
                if (isChecked) {
                    mSample.setBackgroundColor(Color.WHITE);
                }else {
                    mSample.setBackgroundColor(Color.TRANSPARENT);
                }
            }
            if (buttonView.getId() == R.id.tglanguage) {
                if (isChecked) {
                    mSample.setText("샘플");
                }else {
                    mSample.setText("Sample");
                }
            }
        }
    };


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
