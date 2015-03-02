package com.example.juns.multitouchinput;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView.BufferType;

public class ButtonCustom extends Button implements OnClickListener{


    public ButtonCustom(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
    }
    private Context m_Context;
    private Activity m_Activity;




    public Activity getM_Activity() {
        return m_Activity;
    }


    public void setM_Activity(Activity m_Activity) {
        this.m_Activity = m_Activity;
    }

    @Override
    public void onClick(View v) {
        String temp;
        MultiTouchInputActivity multi =(MultiTouchInputActivity)m_Activity;
        if (this.getText().equals(""))
            temp  = multi.txt_result.getText().toString() +" ";
        else temp  = multi.txt_result.getText().toString() + this.getText().toString();
        multi.txt_result.setText(temp);
    }
}
