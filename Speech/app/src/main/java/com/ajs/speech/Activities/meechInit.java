package com.ajs.speech.Activities;

public class meechInit {
    private String mUid;
    private String title;
    private String content;
    private String uniqId;

    public meechInit() {

    }

    // ^ Firebase needs meechInit() here ^

    public meechInit(String mUid, String title, String content, String uniqId) {
        this.mUid = mUid;
        this.title = title;
        this.content = content;
        this.uniqId = uniqId;
    }

    public String getMUid() {
        return mUid;
    }
    public void setMUid(String mUid) {
        this.mUid = mUid;
    }


    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }

    public String getUniqId() {
        return uniqId;
    }
    public void setUniqId(String uniqId) {
        this.uniqId = uniqId;
    }
}

