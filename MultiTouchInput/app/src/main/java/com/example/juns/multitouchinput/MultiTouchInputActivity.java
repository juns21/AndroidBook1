package com.example.juns.multitouchinput;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Juns on 2015-03-02.
 */
public class MultiTouchInputActivity extends Activity implements View.OnClickListener, View.OnTouchListener {
    private char[] a = {'a', 'b', 'c', 'd', 'e'};
    private char[] f = {'f', 'g', 'h', 'i', 'j'};
    private char[] k = {'k', 'l', 'm', 'n', 'o'};
    private char[] p = {'p', 'q', 'r', 's', 't'};
    private char[] u = {'u', 'v', 'w', 'x', 'y'};
    private char[] z = {'z', '.', ',', '!', '?'};

    private char[] A = {'A', 'B', 'C', 'D', 'E'};
    private char[] F = {'F', 'G', 'H', 'I', 'J'};
    private char[] K = {'K', 'L', 'M', 'N', 'O'};
    private char[] P = {'P', 'Q', 'R', 'S', 'T'};
    private char[] U = {'U', 'V', 'W', 'X', 'Y'};
    private char[] Z = {'Z', '.', ',', '!', '?'};

    private char[] CAP = {'A', 'F', 'K', 'P', 'U', 'Z'};
    private char[] NOR = {'a', 'f', 'k', 'p', 'u', 'z'};

    ButtonCustom row1_1;
    ButtonCustom row1_2;
    ButtonCustom row1_3;
    ButtonCustom row1_4;
    ButtonCustom row1_5;
    ButtonCustom row1_6;
    ButtonCustom row2_1;
    ButtonCustom row2_2;
    ButtonCustom row2_3;
    ButtonCustom row2_4;
    ButtonCustom row2_5;
    ButtonCustom row2_6;
    ButtonCustom row3_6;

    TextView txt_result;
    View old;

    boolean cap = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2);
        initButtonCustom();
    }

    private void initButtonCustom() {
        row1_1 = (ButtonCustom) findViewById(R.id.row1_1);
        row1_2 = (ButtonCustom) findViewById(R.id.row1_2);
        row1_3 = (ButtonCustom) findViewById(R.id.row1_3);
        row1_4 = (ButtonCustom) findViewById(R.id.row1_4);
        row1_5 = (ButtonCustom) findViewById(R.id.row1_5);
        row1_6 = (ButtonCustom) findViewById(R.id.row1_6);

        row2_1 = (ButtonCustom) findViewById(R.id.row2_1);
        row2_2 = (ButtonCustom) findViewById(R.id.row2_2);
        row2_3 = (ButtonCustom) findViewById(R.id.row2_3);
        row2_4 = (ButtonCustom) findViewById(R.id.row2_4);
        row2_5 = (ButtonCustom) findViewById(R.id.row2_5);
        row2_6 = (ButtonCustom) findViewById(R.id.row2_6);

        row3_6 = (ButtonCustom) findViewById(R.id.row3_6);

        row2_1.setM_Activity(this);
        row2_2.setM_Activity(this);
        row2_3.setM_Activity(this);
        row2_4.setM_Activity(this);
        row2_5.setM_Activity(this);
        row2_6.setM_Activity(this);

        row2_1.setOnClickListener(row2_1);
        row2_2.setOnClickListener(row2_2);
        row2_3.setOnClickListener(row2_3);
        row2_4.setOnClickListener(row2_4);
        row2_5.setOnClickListener(row2_5);
        row2_6.setOnClickListener(row2_6);

        row1_1.setOnClickListener(this);
        row1_2.setOnClickListener(this);
        row1_3.setOnClickListener(this);
        row1_4.setOnClickListener(this);
        row1_5.setOnClickListener(this);
        row1_6.setOnClickListener(this);

        row1_1.setOnTouchListener(this);
        row1_2.setOnTouchListener(this);
        row1_3.setOnTouchListener(this);
        row1_4.setOnTouchListener(this);
        row1_5.setOnTouchListener(this);
        row1_6.setOnTouchListener(this);
        row3_6.setOnTouchListener(this);

        row3_6.setOnClickListener(this);

        txt_result = (TextView) findViewById(R.id.txt_result);
        old = row1_1;

        row1_1.setBackgroundDrawable(getResources().getDrawable(R.drawable.click3));

        setText(a);
        row1_1.setEnabled(false);
    }

    public void setText(char[] text) {
        row2_1.setText(String.valueOf(text[0]));
        row2_2.setText(String.valueOf(text[1]));
        row2_3.setText(String.valueOf(text[2]));
        row2_4.setText(String.valueOf(text[3]));
        row2_5.setText(String.valueOf(text[4]));
    }

    public void setTextRow1(char[] text) {
        row1_1.setText(String.valueOf(text[0]));
        row1_2.setText(String.valueOf(text[1]));
        row1_3.setText(String.valueOf(text[2]));
        row1_4.setText(String.valueOf(text[3]));
        row1_5.setText(String.valueOf(text[4]));
        row1_6.setText(String.valueOf(text[5]));
    }

    public void setEnable() {
        row1_1.refreshDrawableState();
        row1_2.refreshDrawableState();
        row1_3.refreshDrawableState();
        row1_4.refreshDrawableState();
        row1_5.refreshDrawableState();
        row1_6.refreshDrawableState();

        row1_1.setEnabled(true);
        row1_2.setEnabled(true);
        row1_3.setEnabled(true);
        row1_4.setEnabled(true);
        row1_5.setEnabled(true);
        row1_6.setEnabled(true);

        row1_1.setBackgroundDrawable(getResources().getDrawable(R.drawable.click3));
        row1_2.setBackgroundDrawable(getResources().getDrawable(R.drawable.click3));
        row1_3.setBackgroundDrawable(getResources().getDrawable(R.drawable.click3));
        row1_4.setBackgroundDrawable(getResources().getDrawable(R.drawable.click3));
        row1_5.setBackgroundDrawable(getResources().getDrawable(R.drawable.click3));
        row1_6.setBackgroundDrawable(getResources().getDrawable(R.drawable.click3));
    }

    @Override
    public void onClick(View v) {
        /*
        setEnable();
        if (v.equals(row3_6)) {
            cap = !cap;
            v=old;
        }

        if (!cap) {
            old = v;

            setTextRow1(NOR);
            if (v.equals(row1_1)) {
                row1_1.setBackgroundDrawable(getResources().getDrawable(R.drawable.click4));
                setText(a);
                row1_1.setEnabled(false);
            }else if (v.equals(row1_2)) {
                row1_2.setBackgroundDrawable(getResources().getDrawable(R.drawable.click4));
                setText(f);
                row1_2.setEnabled(false);
            }else if (v.equals(row1_3)) {
                row1_3.setBackgroundDrawable(getResources().getDrawable(R.drawable.click4));
                setText(k);
                row1_3.setEnabled(false);
            }else if (v.equals(row1_4)) {
                row1_4.setBackgroundDrawable(getResources().getDrawable(R.drawable.click4));
                setText(p);
                row1_4.setEnabled(false);
            }else if (v.equals(row1_5)) {
                row1_5.setBackgroundDrawable(getResources().getDrawable(R.drawable.click4));
                setText(u);
                row1_5.setEnabled(false);
            }else if (v.equals(row1_6)) {
                row1_6.setBackgroundDrawable(getResources().getDrawable(R.drawable.click4));
                setText(z);
                row1_6.setEnabled(false);
            }
        }else {
            old = v;

            setTextRow1(CAP);
            if (v.equals(row1_1)) {
                row1_1.setBackgroundDrawable(getResources().getDrawable(R.drawable.click4));
                row1_1.setEnabled(false);
                setText(A);
            }else if (v.equals(row1_2)) {
                row1_2.setBackgroundDrawable(getResources().getDrawable(R.drawable.click4));
                row1_2.setEnabled(false);
                setText(F);
            }else if (v.equals(row1_3)) {
                row1_3.setBackgroundDrawable(getResources().getDrawable(R.drawable.click4));
                row1_3.setEnabled(false);
                setText(K);
            }else if (v.equals(row1_4)) {
                row1_4.setBackgroundDrawable(getResources().getDrawable(R.drawable.click4));
                row1_4.setEnabled(false);
                setText(P);
            }else if (v.equals(row1_5)) {
                row1_5.setBackgroundDrawable(getResources().getDrawable(R.drawable.click4));
                row1_5.setEnabled(false);
                setText(U);
            }else if (v.equals(row1_6)) {
                row1_6.setBackgroundDrawable(getResources().getDrawable(R.drawable.click4));
                row1_6.setEnabled(false);
                setText(Z);
            }
        }
        */
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            setEnable();
            if (v.equals(row3_6)) {
                cap = !cap;
                v = old;
            }

            if (!cap) {
                old = v;

                setTextRow1(NOR);
                if (v.equals(row1_1)) {
                    row1_1.setBackgroundDrawable(getResources().getDrawable(R.drawable.click4));
                    setText(a);
                    row1_1.setEnabled(false);
                }else if (v.equals(row1_2)) {
                    row1_2.setBackgroundDrawable(getResources().getDrawable(R.drawable.click4));
                    setText(f);
                    row1_2.setEnabled(false);
                }else if (v.equals(row1_3)) {
                    row1_3.setBackgroundDrawable(getResources().getDrawable(R.drawable.click4));
                    setText(k);
                    row1_3.setEnabled(false);
                }else if (v.equals(row1_4)) {
                    row1_4.setBackgroundDrawable(getResources().getDrawable(R.drawable.click4));
                    setText(p);
                    row1_4.setEnabled(false);
                }else if (v.equals(row1_5)) {
                    row1_5.setBackgroundDrawable(getResources().getDrawable(R.drawable.click4));
                    setText(u);
                    row1_5.setEnabled(false);
                }else if (v.equals(row1_6)) {
                    row1_6.setBackgroundDrawable(getResources().getDrawable(R.drawable.click4));
                    setText(z);
                    row1_6.setEnabled(false);
                }
            }else {
                old = v;

                setTextRow1(CAP);
                if (v.equals(row1_1)) {
                    row1_1.setBackgroundDrawable(getResources().getDrawable(R.drawable.click4));
                    row1_1.setEnabled(false);
                    setText(A);
                }else if (v.equals(row1_2)) {
                    row1_2.setBackgroundDrawable(getResources().getDrawable(R.drawable.click4));
                    row1_2.setEnabled(false);
                    setText(F);
                }else if (v.equals(row1_3)) {
                    row1_3.setBackgroundDrawable(getResources().getDrawable(R.drawable.click4));
                    row1_3.setEnabled(false);
                    setText(K);
                }else if (v.equals(row1_4)) {
                    row1_4.setBackgroundDrawable(getResources().getDrawable(R.drawable.click4));
                    row1_4.setEnabled(false);
                    setText(P);
                }else if (v.equals(row1_5)) {
                    row1_5.setBackgroundDrawable(getResources().getDrawable(R.drawable.click4));
                    row1_5.setEnabled(false);
                    setText(U);
                }else if (v.equals(row1_6)) {
                    row1_6.setBackgroundDrawable(getResources().getDrawable(R.drawable.click4));
                    row1_6.setEnabled(false);
                    setText(Z);
                }
            }
        }
        return true;
    }
}
