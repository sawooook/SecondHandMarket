package com.example.pc1.store;

public class Mainpageitem {
    private String title;
    private String sold;
    private String where;
    private String person;
    private String img;
    private String time;
    private boolean check;
    private String distance;
    public Mainpageitem(String title  , String sold, String where, String img, String person,String time, boolean check,String distance) {
        this.title =title;
        this.sold = sold;
        this.where = where;
        this.person=person;
        this.img=img;
        this.time=time;
        this.check=check;
        this.distance=distance;

    }


    public String getTitle() {
        return title;
    }

    public String getTime() {
        return time;
    }
    public boolean getCheck() {return check; }



    public String getSold() {
        return sold;
    }



    public String getWhere() {
        return where;
    }
    public String getPerson() {
        return person;
    }

    public String getImg() {
        return img;
    }
    public String getdistance(){
        return  distance;
    }



}
