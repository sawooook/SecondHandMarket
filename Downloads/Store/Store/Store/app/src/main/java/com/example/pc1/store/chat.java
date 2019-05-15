package com.example.pc1.store;

public class chat {

    private final String room;
    private String chat_num;
    private String chat_content;
    private String user_name;
    private String chat_img;
    private String product_index;
    private String read_num;


    public chat(String chat_num, String user_name, String chat_content, String chat_img, String room, String product_index, String read_num) {
        this.chat_num = chat_num;
        this.chat_content = chat_content;
        this.user_name = user_name;
        this.chat_img=chat_img;
        this.room=room;
        this.product_index=product_index;
        this.read_num = read_num;
    }

    public  String getroom(){
        return room;
    }
    public String getnum()
    {

        return chat_num;
    }

    public String getContent() {

        return chat_content;
    }

    public String  getcharImg() {
        return chat_img;

    }

    public String getusername() {
        return user_name;

    }
    public String getproduct_index(){
        return product_index;
    }

    public String getRead_num(){
        return read_num;
    }

}
