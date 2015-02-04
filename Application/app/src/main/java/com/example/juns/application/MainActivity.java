package com.example.juns.application;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnBeginner = (Button) findViewById(R.id.beginner);
        Button btnProfessional = (Button) findViewById(R.id.professional);

        btnBeginner.setOnClickListener(mOnClick);
        btnProfessional.setOnClickListener(mOnClick);

        UpdateNowNode();
    }

    void UpdateNowNode() {
        TextView txtView = (TextView) findViewById(R.id.mode);
        AndExam_Application app = (AndExam_Application) getApplication();
        if (app.getMode() == AndExam_Application.BEGINNER) {
            txtView.setText("현재 모드 : 초보자 모드");
        }else {
            txtView.setText("현재 모드 : 숙련자 모드");
        }
    }

    View.OnClickListener mOnClick = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            AndExam_Application app = (AndExam_Application) getApplication();
            switch (v.getId()) {
                case R.id.beginner:
                    app.setMode(AndExam_Application.BEGINNER);
                    break;
                case R.id.professional:
                    app.setMode(AndExam_Application.PREFESSIONAL);
                    break;
            }
            UpdateNowNode();
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
