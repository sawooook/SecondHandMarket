package com.example.pc1.store;

import android.util.Log;

public class item {

    String room;
    private String name;
    private String content;
    private String img;
    private String profile;
    String contents;
    String content4;


    public item(String name, String content) {
        this.name = name;
        this.content = content;
}



    public String getroom()
    {

        return room;
    }


    public String getName()
    {

        return name;
    }

    public String getContent() {

        return content;
    }

    public String  getImg() {
        Log.e("imgg", img);
        return img;

    }
    public String  getProfile() {
        Log.e("imgg", img);
        return profile;

    }

}
