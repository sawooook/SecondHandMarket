package com.example.pc1.store;

public class token {
    public String tokenname;
    public String tokensave;
    public String tokensymbol;

    public token(String tokenname, String tokensave, String tokensymbol) {
        this.tokenname = tokenname;
        this.tokensave = tokensave;
        this.tokensymbol=tokensymbol;
    }


    public String gettokenname() {

        return tokenname;
    }


    public String getsavetoken() {

        return tokensave;
    }

    public String getTokensymbol(){
        return tokensymbol;
    }
}