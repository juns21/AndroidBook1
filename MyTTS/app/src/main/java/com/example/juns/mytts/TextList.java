package com.example.juns.mytts;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Juns on 2015-03-05.
 */
public class TextList extends Activity implements TextToSpeech.OnInitListener {
    ListView listView;
    private TextToSpeech tts;
    private List<String> myList = new ArrayList<String>();
    private List<String> my = new ArrayList<String>();
    int img = R.drawable.icon_s;
    private dataListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Intent intent=new Intent();
        // intent.setDataAndType(Uri.fromFile(new File("file:///android_asset/myapk.apk")), "application/vnd.android.package-archive");
        // startActivity(intent);
        // my.add("0");
        // my.add("1");my.add("2");my.add("3");my.add("4");my.add("5");my.add("6");my.add("7");my.add("8");my.add("9");my.add("10");
        // my.add("11");my.add("12");my.add("13");my.add("14");my.add("15");my.add("16");my.add("17");my.add("18");my.add("19");my.add("20");
        myList.add("Love is merely madness. ");
        myList.add("Hate the sin, love the sinner.");
        myList.add("If you would be loved, love and be lovable.");
        myList.add("We can only learn to love by loving.");
        myList.add("To love is to receive a glimpse of heaven.");
        myList.add("Friends have all things in common.");
        myList.add("A friend is a second self.");
        myList.add("My friends, there are no friends.");
        myList.add("A friend to all is a friend to none.");
        myList.add("United we stand, divided we fall.");
        myList.add("Nothing is as far away as one minute ago.");
        myList.add("Regret for wasted time is more wasted time.");
        myList.add("Time is an illusion. Lunchtime doubly so.");
        myList.add("So little time and so little to do. ");
        myList.add("Employ thy time well, if thou meanest to get leisure.");
        myList.add("Hope is a waking dream. ");
        myList.add("While there's life, there's hope.");
        myList.add("Great hopes make great men. ");
        myList.add("Hope is necessary in every condition.");
        myList.add("Hope is only the love of life.");
        // Intent checkIntent = new Intent();
        // checkIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
        // startActivityForResult(checkIntent, 0);
        tts = new TextToSpeech(this, this);
        onInit(0);
        mAdapter = new dataListAdapter(myList, img);
        setContentView(R.layout.activity_text_list);
        listView = (ListView) findViewById(R.id.txtList);
        listView.setAdapter(mAdapter);
        setButtonHandlers();
    }

    private void setButtonHandlers() {
        ((ImageView)findViewById(R.id.toMain)).setOnClickListener(btnClick);
    }
    private View.OnClickListener btnClick = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            finishFromChild(getParent());
        }
    };

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
           /* int result = tts.setLanguage(Locale.ENGLISH);
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "This Language is not supported");
            }else {
                speakOut(new String("Hiiiiii"));
            }*/
        }else {
            Log.e("TTS", "Initilization Failed!");
        }
    }

    class dataListAdapter extends BaseAdapter {
        List<String> Title;
        int imge;

        dataListAdapter() {
            Title = null;
            imge = (Integer) null;
        }

        public dataListAdapter(List<String> text, int text3) {
            Title = text;
            imge = text3;
        }

        @Override
        public int getCount() {
            return Title.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            final Holder mHolder;
            TextView title, detail;
            ImageView img;
            LayoutInflater inflater;
            if (convertView == null) {
                inflater = getLayoutInflater();
                convertView = inflater.inflate(R.layout.list, parent, false);
            }else {
            }
            title = (TextView) convertView.findViewById(R.id.txtname);
            mHolder = new Holder();
            mHolder.id = position;
            mHolder.name = (TextView) convertView.findViewById(R.id.txtname);
            //mHolder.duration = (TextView) convertView.findViewById(R.id.duration);
            mHolder.img = (ImageView) convertView.findViewById(R.id.img);

            convertView.setTag(mHolder);
            // On Click Listener to play file when File Name is clicked
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String txt = myList.get(position);
                    speakOut(txt);
                }
            });
            mHolder.name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String txt = myList.get(position);
                    speakOut(txt);
                }
            });
            // On Click Listener to play file when play button is clicked
            mHolder.img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String txt = myList.get(position);
                    speakOut(txt);
                }
            });
            img = (ImageView) convertView.findViewById(R.id.img);
            title.setText(Title.get(position).toString());
            img.setImageResource(imge);
            convertView.setTag(mHolder);
            return (convertView);
        }
    }

    class Holder {
        int id;
        TextView name;
        TextView duration;
        ImageView img;
    }

    private void speakOut(String text) {
        int a = tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
        int b = 10;
    }

    @Override
    protected void onDestroy() {
        // Don't forget to sutdown tts;
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }

    /*@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0) {
            if (resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS) {
                Toast.makeText(getApplicationContext(), "Alerady Installed", Toast.LENGTH_LONG).show();
            }else {
                Intent installIntent = new Intent();
                installIntent.setAction(TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
                startActivity(installIntent);
                Toast.makeText(getApplicationContext(), "Installed Now", Toast.LENGTH_LONG).show();
            }
        }
    }*/
}
