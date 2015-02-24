package com.example.juns.eyeball;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;


public class MainActivity extends Activity {

    // Global mutable variables
    private RelativeLayout rlParent;
    private RelativeLayout rlLeft;
    private RelativeLayout rlRight;

    private Button btnMouth;
    private ImageView imgFace;

    private MediaPlayer mPlayer;

    private float parentWidth;
    private float parentHeight;

    private float leftWidth;
    private float leftHeight;
    private float rightWidth;
    private float rightHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rlParent = (RelativeLayout)findViewById(R.id.rlParent);
        rlParent.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            @Override
            public void onGlobalLayout() {
                // Ensure you call it only once :
                rlParent.getViewTreeObserver().removeGlobalOnLayoutListener(this);

                // Get parent size
                parentWidth = rlParent.getWidth();
                parentHeight = rlParent.getHeight();

            }
        });

        rlParent.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                // TODO Auto-generated method stub
                final int X = (int) arg1.getRawX();
                final int Ytmp = (int) arg1.getRawY();
                final float Y = Ytmp - parentHeight/7;
                switch (arg1.getAction() & MotionEvent.ACTION_MASK) {
                    case MotionEvent.ACTION_DOWN:

                        // Click on parent to update eye
                        leftEye(parentHeight/Y, parentWidth/X);
                        rightEye(parentHeight/Y, parentWidth/X);

                        imgFace.setImageResource(R.drawable.face);

                        break;
                    case MotionEvent.ACTION_UP:
                        break;
                    case MotionEvent.ACTION_POINTER_DOWN:
                        break;
                    case MotionEvent.ACTION_POINTER_UP:
                        break;
                    case MotionEvent.ACTION_MOVE:
                        break;
                }
                return true;
            }
        });

        rlLeft = (RelativeLayout)findViewById(R.id.rlLeft);
        rlLeft.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            @Override
            public void onGlobalLayout() {
                // Ensure you call it only once :
                rlLeft.getViewTreeObserver().removeGlobalOnLayoutListener(this);

                // Get size of left eye
                leftWidth = rlLeft.getWidth();
                leftHeight = rlLeft.getHeight();

            }
        });
        rlRight = (RelativeLayout)findViewById(R.id.rlRight);
        rlRight.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            @Override
            public void onGlobalLayout() {
                // Ensure you call it only once :
                rlRight.getViewTreeObserver().removeGlobalOnLayoutListener(this);

                // Get size of right eye
                rightWidth = rlRight.getWidth();
                rightHeight = rlRight.getHeight();

            }
        });

        imgFace = (ImageView)findViewById(R.id.imgFace);
        btnMouth = (Button)findViewById(R.id.btnMouth);
        btnMouth.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Click on mouth to play sound, wear mask
                imgFace.setImageResource(R.drawable.face_mask);

                mPlayer = MediaPlayer.create(MainActivity.this, R.raw.fartingnoise);
                mPlayer.start();
                mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    public void onCompletion(MediaPlayer mp) {
                        mp.release();
                    };
                });

            }
        });

    }

    // Init left eye follow rate size
    public void leftEye(float rate1, float rate2) {
        rlLeft.removeAllViews();

        ImageView imgLeft = new ImageView(getApplicationContext());

        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(Math.round(leftWidth/5), Math.round(leftWidth/5));

        imgLeft.setImageResource(R.drawable.eye);

        int i1 = Math.round(leftWidth/rate2);
        int i2 = Math.round(leftHeight/rate1);
        if(i1 + lp.width > leftWidth) {
            i1 = Math.round(leftWidth - lp.width);
        }

        if(i2 + lp.height > leftHeight) {
            i2 = Math.round(leftHeight - lp.height);
        }
        lp.setMargins(i1, i2, 0, 0);
        imgLeft.setLayoutParams(lp);

        rlLeft.addView(imgLeft);
    }

    // Init right eye follow rate size
    public void rightEye(float rate1, float rate2) {
        rlRight.removeAllViews();

        ImageView imgRight = new ImageView(getApplicationContext());

        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(Math.round(rightWidth/5), Math.round(rightWidth/5));

        imgRight.setImageResource(R.drawable.eye);
        int i1 = Math.round(rightWidth/rate2);
        int i2 = Math.round(rightHeight/rate1);

        if(i1 + lp.width > rightWidth) {
            i1 = Math.round(rightWidth - lp.width);
        }

        if(i2 + lp.height > rightHeight) {
            i2 = Math.round(rightHeight - lp.height);
        }

        lp.setMargins(i1, i2, 0, 0);
        imgRight.setLayoutParams(lp);

        rlRight.addView(imgRight);
    }
}
