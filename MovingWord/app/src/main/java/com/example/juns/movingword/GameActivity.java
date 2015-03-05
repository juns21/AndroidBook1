package com.example.juns.movingword;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Juns on 2015-03-02.
 */
public class GameActivity extends Activity {

    // Global mutable variables
    private Spinner spinWords;
    private Button btnStart;
    private RelativeLayout rlGame;

    private TextView txtTime;
    private TextView txtTimeMs;
    private TextView txtScore;

    /*
     * Timer tool + variables
     */
    private Timer timer;
    private TimerTask timerTask;
    private int time = 0;

    private String[] arWords;

    private int curWord = 0;

    // Check baloon is intersect with added baloon
    private boolean checkIntersect = false;

    private ArrayList<Baloon> arBaloon = new ArrayList<GameActivity.Baloon>();

    // Check to allow use click on baloon
    private boolean isStart = false;

    // Array character use to control list of string's character
    private String[] arCharacter;

    // Check to allow use continue click on next baloon or not (just one baloon per time)
    private boolean isPlaying = false;

    // No of list character
    private int curClick = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_game);

        // Init variables
        arWords = getResources().getStringArray(R.array.Words);

        rlGame = (RelativeLayout) findViewById(R.id.rlGame);
        txtTime = (TextView) findViewById(R.id.txtTime);
        txtTimeMs = (TextView) findViewById(R.id.txtTimeMs);
        txtScore = (TextView) findViewById(R.id.txtScore);
        btnStart = (Button) findViewById(R.id.btnStart);
        btnStart.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Click button start to init game
                if(!isStart && spinWords.getSelectedItemPosition() != 0) {
                    isStart = true;
                    initGame();
                    reScheduleTimer();
                }
            }
        });

        initSpinner();

    }

    // Refresh data and textview
    public void refresh() {
        curClick = 0;
        arCharacter = new String[0];

        rlGame.removeAllViews();
        arBaloon.clear();

        isStart = false;
        isPlaying = false;

        txtTime.setText("00:00");
        txtTimeMs.setText("00");
        txtScore.setText("0/0");

        time = 0;
        if(timer != null) {
            timer.cancel();
        }
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();

        // Reinit when use resume game
        spinWords.setSelection(0);

        refresh();
    }

    public void initSpinner() {
        spinWords = (Spinner) findViewById(R.id.spWords);
        WordAdapter adapter = new WordAdapter(getApplicationContext());
        spinWords.setAdapter(adapter);
        spinWords.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub

                // Click on title of spinword, refresh data
                if(arg2 != 0) {
                    refresh();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });
    }

    // Init baloon and show right text
    public void initGame() {

        txtScore.setText("0/" + arWords[spinWords.getSelectedItemPosition()].length());

        curWord = spinWords.getSelectedItemPosition();

        // Init list of character
        if(arWords[spinWords.getSelectedItemPosition()].length() <= 5) {
            arCharacter = new String[arWords[spinWords.getSelectedItemPosition()].length() * 3];
        } else {
            arCharacter = new String[arWords[spinWords.getSelectedItemPosition()].length() * 2];
        }

        for(int i = 0; i < arCharacter.length; i++) {
            if(i < arWords[spinWords.getSelectedItemPosition()].length()) {
                arCharacter[i] = Character.toString(arWords[spinWords.getSelectedItemPosition()].charAt(i));
            } else {
                Random r = new Random();
                char c = (char)(r.nextInt(26) + 'a');
                arCharacter[i] = Character.toString(c);
            }
        }

        // Create baloon follow character list
        Random ran = new Random();

        int iDisplayWidth = rlGame.getWidth();
        int iDisplayHeight = rlGame.getHeight();

        for(int i = 0 ; i < arCharacter.length; i++) {

            final int pos = i;

            int x = 0;
            int y = 0;

            int iBaloonWidth = iDisplayWidth/(ran.nextInt(7-5) + 5);
            // Make sure this button will not intersect added bomb
            RelativeLayout rlBaloon = new RelativeLayout(getApplicationContext());
            ImageView imgBaloon = new ImageView(getApplicationContext());
            TextView txtCharacter = new TextView(getApplicationContext());

            do {

                checkIntersect = false;

                imgBaloon.setScaleType(ImageView.ScaleType.CENTER_CROP);
                int img = ran.nextInt(6 - 1) + 1;
                imgBaloon.setBackgroundResource(getResources().getIdentifier("baloon_" + img, "drawable", getPackageName()));
                RelativeLayout.LayoutParams imgLp = new LayoutParams(iBaloonWidth,iBaloonWidth);
                imgLp.addRule(RelativeLayout.CENTER_IN_PARENT);
                imgBaloon.setLayoutParams(imgLp);

                RelativeLayout.LayoutParams txtLp = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
                txtLp.addRule(RelativeLayout.CENTER_IN_PARENT);
                txtCharacter.setLayoutParams(txtLp);
                txtCharacter.setText(arCharacter[i]);
                txtCharacter.setTextColor(Color.parseColor("white"));
                txtCharacter.setTextSize(getResources().getDimension(R.dimen.Character));

                RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(iBaloonWidth, iBaloonWidth);
                x = ran.nextInt(iDisplayWidth - iBaloonWidth);
                y = ran.nextInt(iDisplayHeight - iBaloonWidth);
                lp.setMargins(x, y, 0, 0);
                rlBaloon.setLayoutParams(lp);

                rlBaloon.addView(imgBaloon);
                rlBaloon.addView(txtCharacter);

                rlGame.addView(rlBaloon);


                for(int k = 0 ; k < arBaloon.size(); k++) {
                    if(checkIntersect(x + iBaloonWidth/2, y + iBaloonWidth/2, arBaloon.get(k).centerX, arBaloon.get(k).centerY, iBaloonWidth/2, arBaloon.get(k).width)) {
                        checkIntersect = true;
                        rlBaloon.removeAllViews();
                        rlGame.removeView(rlBaloon);
                    }
                }
            } while(checkIntersect);


            rlBaloon.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    if(isStart && !isPlaying) {
                        isPlaying = true;
                        arBaloon.get(pos).rlBaloon.removeView(arBaloon.get(pos).txtContent);
                        arBaloon.get(pos).getBaloon().setBackgroundResource(R.drawable.animation);
                        AnimationDrawable frameAnimation = (AnimationDrawable) arBaloon.get(pos).getBaloon().getBackground();
                        frameAnimation.start();

                        checkIfAnimationDone(frameAnimation, arBaloon.get(pos), pos);

                    }
                }
            });

            arBaloon.add(new Baloon(arCharacter[i], rlBaloon, imgBaloon, txtCharacter, x + iBaloonWidth/2, y + iBaloonWidth/2, iBaloonWidth/2));

        }

        spinWords.setSelection(0);

    }

    // Check animation is finish of not
    private void checkIfAnimationDone(AnimationDrawable anim, Baloon baloon, int pos){
        final AnimationDrawable a = anim;
        final Baloon b = baloon;
        final int posistion = pos;

        int timeBetweenChecks = 10;

        Handler h = new Handler();
        h.postDelayed(new Runnable(){
            public void run(){
                if (a.getCurrent() != a.getFrame(a.getNumberOfFrames() - 1)){ // Not finish, continue checking
                    checkIfAnimationDone(a, b, posistion);
                } else{ // finish, check condition to continue game or not
                    // Make sure this relativelayout cannot click more time
                    arBaloon.get(posistion).getRlBaloon().setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            // TODO Auto-generated method stub

                        }
                    });
                    // Remove relativelayout in it's parent
                    rlGame.removeView(arBaloon.get(posistion).getRlBaloon());

                    // Stop animation
                    a.stop();

                    // Check condition
                    if(b.getContent().toLowerCase().equals(arCharacter[curClick].toLowerCase())) {
                        txtScore.setText((Integer.parseInt(txtScore.getText().toString().split("/")[0]) + 1) + "/" + txtScore.getText().toString().split("/")[1]);

                        if(Integer.parseInt(txtScore.getText().toString().split("/")[0]) == Integer.parseInt(txtScore.getText().toString().split("/")[1])) {

                            if(timer != null) {
                                timer.cancel();
                            }

                            Intent intent = new Intent(GameActivity.this, WinActivity.class);
                            intent.putExtra("Content", arWords[curWord]);
                            startActivity(intent);
                        }

                        curClick++;
                    } else {
                        Intent intent = new Intent(GameActivity.this, GameOverActivity.class);
                        startActivity(intent);
                    }

                    isPlaying = false;

                }
            }
        }, timeBetweenChecks);
    }

    // Check is baloon intersect with added baloon or not, this function can instead by Rect, but it will not make sure about Check time
    public boolean checkIntersect(int centerX1, int centerY1, int centerX2, int centerY2, int width1, int width2) {

        if(centerX1 < centerX2) {
            if(centerY1 < centerY2) {
                if(centerX1 + width1 > centerX2 - width2 &&
                        centerY1 + width1 > centerY2 - width2) {
                    return true;
                }
            } else if(centerY1 == centerY2) {
                if(centerX1 + width1 > centerX2 - width2) {
                    return true;
                }
            } else {
                if(centerX1 + width1 > centerX2 - width2 &&
                        centerY1 - width1 < centerY2 + width2) {
                    return true;
                }
            }
        } else if(centerX1 == centerX2) {
            if(centerY1 < centerY2) {
                if(centerY1 + width1 > centerY2 - width2) {
                    return true;
                }
            } else if(centerY1 == centerY2) {
                return true;
            } else {
                if(centerY1 - width1 < centerY2 + width2) {
                    return true;
                }
            }
        } else {
            if(centerY1 < centerY2) {
                if(centerX1 - width1 < centerX2 + width2 &&
                        centerY1 + width1 > centerY2 - width2) {
                    return true;
                }
            } else if(centerY1 == centerY2) {
                if(centerX1 - width1 < centerX2 + width2) {
                    return true;
                }
            } else {
                if(centerX1 - width1 < centerX2 + width2 &&
                        centerY1 - width1 < centerY2 + width2) {
                    return true;
                }
            }
        }
        return false;
    }

    // Scheduler
    public void reScheduleTimer() {
        timer = new Timer();
        timerTask = new myTimerTask();
        timer.schedule(timerTask, 0, 10);
    }

    private class myTimerTask extends TimerTask {
        @Override
        public void run() {
            // TODO Auto-generated method stub
            time += 1;
            updateLabel.sendEmptyMessage(0);
        }
    }

    // Update text Time
    private Handler updateLabel = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            // super.handleMessage(msg);

            int miliseconds = (int) time % 100;
            int s = (int) (time / 100) % 60;
            int m = (int) (time / (100 * 60)) % 60;
            txtTime.setText(String.format("%02d:%02d", m, s));
            txtTimeMs.setText(String.format(".%02d", miliseconds));

        }
    };

    public class WordAdapter extends BaseAdapter {

        private final LayoutInflater mInflater;

        public WordAdapter(Context context) {
            mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        public int getCount() {
            return arWords.length;
        }

        public Object getItem(int position) {
            return position;
        }

        public long getItemId(int position) {
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder;
            View rowView = convertView;
            // Inflate converview
            if (convertView == null) {
                rowView = mInflater.inflate(R.layout.spinner_item, parent, false);
                holder = new ViewHolder();

                holder.txtWord = (TextView) rowView.findViewById(R.id.txtWord);

                rowView.setTag(holder);
            } else {
                holder = (ViewHolder) rowView.getTag();
            }

            holder.txtWord.setText(arWords[position]);

            return rowView;
        }
    }

    private class ViewHolder {
        TextView txtWord;
    }

    private class Baloon {
        private String content;
        private RelativeLayout rlBaloon;
        private ImageView baloon;
        private TextView  txtContent;

        public int centerX;
        public int centerY;

        public int width;

        public Baloon(String content, RelativeLayout rlBaloon, ImageView baloon, TextView  txtContent, int centerX, int centerY, int width) {
            super();
            this.content = content;
            this.rlBaloon = rlBaloon;
            this.baloon = baloon;
            this.txtContent = txtContent;
            this.centerX = centerX;
            this.centerY = centerY;
            this.width = width;

        }
        public String getContent() {
            return content;
        }
        public RelativeLayout getRlBaloon() {
            return rlBaloon;
        }
        public ImageView getBaloon() {
            return baloon;
        }

    }
}