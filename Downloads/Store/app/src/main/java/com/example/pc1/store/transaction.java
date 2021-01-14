package com.example.pc1.store;

public class transaction {
    private final String transaction_token_from;

    private final int transaction_token_bitmap;

    public String transaction_tokenname;
    public String transaction_token_value;
    public String transaction_token_symbol;
    public String transaction_token_time;

    public transaction(String transaction_tokenname, String transaction_token_value, String transaction_token_symbol, String transaction_token_time, String transaction_token_from, int transaction_token_bitmap) {

        this.transaction_tokenname = transaction_tokenname;
        this.transaction_token_value = transaction_token_value;
        this.transaction_token_symbol = transaction_token_symbol;
        this.transaction_token_time = transaction_token_time;
        this.transaction_token_from=transaction_token_from;
        this.transaction_token_bitmap=transaction_token_bitmap;
    }



    public int get_transaction_token_bitmap() {

        return transaction_token_bitmap;
    }
    public String get_transaction_token_from() {

        return transaction_token_from;
    }


    public String get_Transaction_tokenname() {

        return transaction_tokenname;
    }

    public String get_transaction_token_value() {

        return transaction_token_value;
    }

    public String get_transaction_token_symbol() {

        return transaction_token_symbol;
    }

    public String get_transaction_token_time() {

        return transaction_token_time;
    }


}
