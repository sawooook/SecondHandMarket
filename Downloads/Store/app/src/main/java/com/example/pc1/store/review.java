package com.example.pc1.store;

public class review {


    private final String from;
    private final String content;
    private final String img;
    private final float rating;
    private final String img11,img12,img13;
    private String date;
    private String certi;


    public review(String from, String content, String img , float rating, String date,String certi,String img11 ,String img12 ,String img13) {
        this.from = from;
        this.content = content;
        this.img = img;
        this.rating = rating;
        this.date = date;
        this.certi=certi;
        this.img11 =img11;
        this.img12 =img12;
        this.img13 =img13;


    }



    public String getFrom()
    {

        return from;
    }


    public String getcontent()
    {

        return content;
    }

    public String getimg() {

        return img;
    }

    public float getrating(){
        return rating;
    }

    public String getdate() {

        return date;
    }
    public String getCerti(){

        return certi;
    }
    public String getImg11(){

        return img11;
    }

    public String getImg12(){

        return img12;
    }


    public String getImg13(){

        return img13;
    }


}
