package com.example.juns.app612;

/**
 * Created by Juns on 2015-01-27.
 */
public class ListItem {
    int Type;
    String Text;
    String EditText;
    String BtnText;
    int IconRes;

    public ListItem(int aType, String aText, String aEditText, String aBtnText, int aIconRes) {
        Type = aType;
        Text = aText;
        EditText = aEditText;
        BtnText = aBtnText;
        IconRes = aIconRes;
    }
}
