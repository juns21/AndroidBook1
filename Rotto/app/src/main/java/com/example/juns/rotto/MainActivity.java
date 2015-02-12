package com.example.juns.rotto;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.Arrays;
import java.util.Random;


public class MainActivity extends Activity {
    public static int s_NumRow = 7;
    ListView m_LeftList;
    ListView m_RightList;
    int[][] m_ArrLotto;
    int[] m_ProbAbility;
    Integer[] m_Result;
    RightListAdapter m_RightAdapter;
    LeftListAdapter m_LeftAdapter;
    Button m_BtnResultShow;
    Spinner m_Sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        m_Result = new Integer[1];
        m_ArrLotto = new int[s_NumRow][6];
        m_ProbAbility = new int[100];
        Arrays.fill(m_ProbAbility, 0);
        initComponent();
    }

    void initComponent() {
        m_BtnResultShow = (Button) findViewById(R.id.btn_resultloto);
        m_LeftList = (ListView) findViewById(R.id.leftlist);
        m_RightList = (ListView) findViewById(R.id.rightlist);
        m_Sp = (Spinner) findViewById(R.id.spinnerInput);
        m_RightList.setEnabled(false);
        m_LeftList.setEnabled(false);
    }

    @Override
    protected void onResume() {
        super.onResume();

        updateListView();
    }

    void updateListView() {
        m_LeftAdapter = new LeftListAdapter(this, m_ArrLotto);
        m_RightAdapter = new RightListAdapter(this, m_Result);
        m_LeftList.setAdapter(m_LeftAdapter);
        m_RightList.setAdapter(m_RightAdapter);

        m_BtnResultShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putIntArray("result", m_ProbAbility);
                Intent intent = new Intent(v.getContext(), ResultActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        m_Sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                s_NumRow = position+1;
                m_ArrLotto = new int[s_NumRow][6];
                m_ProbAbility = new int[100];
                m_Result = new Integer[s_NumRow];
                initgame(position+1);

                updateListView();
                if (m_LeftAdapter != null && m_RightAdapter != null) {
                    m_LeftAdapter.notifyDataSetChanged();
                    m_RightAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    /**
     * Init all array to show lotto and result
     * @param numberRow
     */
    void initgame(int numberRow) {
        int min = 1;
        int max = 45;

        Random random = new Random();
        Arrays.fill(m_ProbAbility, 0);
        for (int i=0; i<numberRow; i++) {
            int a[] = new int[7];
            for (int j=0; j<7; j++) {
                int temp = random.nextInt(max-min + 1) + min;
                if (j == 6) {
                    m_Result[i] = temp;
                }else {
                    a[j] = temp;
                    m_ArrLotto[i][j] = temp;
                    m_ProbAbility[temp] +=1;
                }

            }
        }
    }
}
