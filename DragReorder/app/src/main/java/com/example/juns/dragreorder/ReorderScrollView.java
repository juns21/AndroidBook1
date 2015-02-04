package com.example.juns.dragreorder;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * Created by Juns on 2015-02-04.
 * 드래그를 지원하는 스크롤뷰. 터치 가로채기 메서드를
 * 재정의하여 부모에게 보내주는 역할만 한다.
 */
public class ReorderScrollView extends ScrollView {
    MainActivity mParent;

    public ReorderScrollView(Context context) {
        super(context);
    }

    public ReorderScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ReorderScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    // 좌표는 리스트뷰 좌상단 기준 좌표이며 스크롤 상태는 고려 안됨.
    public boolean onInterceptTouchEvent(MotionEvent event) {
        if (mParent.onInterceptTouchEvent(this, event) == false) {
            return false;
        }
        boolean result = super.onInterceptTouchEvent(event);
        Log.d(MainActivity.TAG, "onInterceptTouchEvent return = "+ result);
        return result;
    }
}
