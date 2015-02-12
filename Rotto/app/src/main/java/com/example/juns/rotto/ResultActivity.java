package com.example.juns.rotto;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

/**
 * Created by Juns on 2015-02-12.
 */
public class ResultActivity extends Activity {
    ListView mListViewResult;
    int[] mProbAbility;
    ResultListView mResultListViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        mProbAbility = this.getIntent().getIntArrayExtra("result");
        mListViewResult = (ListView) findViewById(R.id.list1);
        Integer[] a = new Integer[46];
        for (int i=0; i<46; i++) {
            a[i] = mProbAbility[i];
        }

        mResultListViewAdapter = new ResultListView(this, a);
        mListViewResult.setAdapter(mResultListViewAdapter);
    }
}
