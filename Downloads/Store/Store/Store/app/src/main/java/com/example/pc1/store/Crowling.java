package com.example.pc1.store;

class Crowling {

    private final String new_name;
    private final String new_link;
    private final String new_img;

    public Crowling(String new_name, String new_link, String new_img) {
        this.new_name = new_name;
        this.new_link = new_link;
        this.new_img=new_img;
    }


    public String getNew_name() {

        return new_name;
    }


    public String getNew_link() {

        return new_link;
    }

    public String getNew_img(){
        return new_img;
    }

}
