package com.example.juns.mygesture;

import android.app.Activity;
import android.gesture.Gesture;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.os.Bundle;
import android.os.Environment;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Juns on 2015-02-13.
 */
public class CreateActivity extends Activity {
    private static final float LENGTH_THRESHOLD = 50.0f;

    private Gesture mGesture;
    private View mDoneButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_create_gesture);

        mDoneButton = findViewById(R.id.done);

        GestureOverlayView overlay = (GestureOverlayView) findViewById(R.id.gesture_overlay);
        overlay.addOnGestureListener(new GesturesProcessor());
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        if (mGesture != null) {
            outState.putParcelable("gesture", mGesture);
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        mGesture = savedInstanceState.getParcelable("gesture");
        if (mGesture != null) {
            final GestureOverlayView overlay = (GestureOverlayView) findViewById(R.id.gesture_overlay);
            overlay.post(new Runnable() {
                @Override
                public void run() {
                    overlay.setGesture(mGesture);
                }
            });
            mDoneButton.setEnabled(true);
        }
    }

    /**
     * Call by button Done onClick
     */
    public void addGesture(View v) {
        EditText edtName = (EditText) findViewById(R.id.gesture_name);

        final GestureLibrary store = MainActivity.getsStore();

        Calendar c = Calendar.getInstance();
        Date date = c.getTime();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String formattedDate = format.format(date);

        if (!edtName.getText().toString().equals("") && mGesture != null) {
            store.addGesture(edtName.getText().toString() +"//"+ formattedDate, mGesture);
            store.save();

            setResult(RESULT_OK);

            final String path = new File(Environment.getExternalStorageDirectory(), "gestures").getAbsolutePath();
            Toast.makeText(this, getString(R.string.save_success, path), Toast.LENGTH_LONG).show();

            finish();
        }else {
            setResult(RESULT_CANCELED);
        }
    }

    /**
     * Call by button Cancel onClick
     */
    public void clearGesture(View v) {
        GestureOverlayView overlay = (GestureOverlayView) findViewById(R.id.gesture_overlay);
        overlay.cancelClearAnimation();
        overlay.clear(true);
        mGesture = null;
    }

    /**
     * Implement class onGestureListener
     */
    private class GesturesProcessor implements GestureOverlayView.OnGestureListener {

        @Override
        public void onGestureStarted(GestureOverlayView overlay, MotionEvent event) {
            mDoneButton.setEnabled(false);
            mGesture = null;
        }

        @Override
        public void onGesture(GestureOverlayView overlay, MotionEvent event) {

        }

        @Override
        public void onGestureEnded(GestureOverlayView overlay, MotionEvent event) {
            mGesture = overlay.getGesture();
            if (mGesture.getLength() < LENGTH_THRESHOLD) {
                overlay.clear(false);
            }else {
                mDoneButton.setEnabled(true);
            }
        }

        @Override
        public void onGestureCancelled(GestureOverlayView overlay, MotionEvent event) {

        }
    }
}
