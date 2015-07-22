package com.example.juns.loginsharedpreferences;

/**
 * Created by Juns on 2015-03-30.
 */
public class Withfeed {
    private String title;
    private String thumbnailUrl;

    public String getDdSubject() {
        return ddSubject;
    }

    public void setDdSubject(String ddSubject) {
        this.ddSubject = ddSubject;
    }

    private String ddSubject;
    private int idx, recom;

    public Withfeed() {
    }

    public Withfeed(String title, String thumbnailUrl, int idx, int recom) {
        this.title = title;
        this.thumbnailUrl = thumbnailUrl;
        this.idx = idx;
        this.recom = recom;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public int getRecom() {
        return recom;
    }

    public void setRecom(int recom) {
        this.recom = recom;
    }
}
