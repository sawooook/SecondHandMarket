package com.example.pc1.store;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

public class tokenAdapter extends BaseAdapter {
    private final Context applicationContext;
    private final ArrayList<token> tokenArraylist;

    public tokenAdapter(Context applicationContext, ArrayList<token> tokenArraylist) {
        this.tokenArraylist=tokenArraylist;
        this.applicationContext=applicationContext;
    }

    @Override
    public int getCount() {
        return tokenArraylist.size();
    }

    @Override
    public Object getItem(int i) {
        return tokenArraylist.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {


        if(view ==null) {

            LayoutInflater inflater = (LayoutInflater)applicationContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.token_list, null);

            TextView tokenname =(TextView)view.findViewById(R.id.tokenname);
            TextView save_token=(TextView)view.findViewById(R.id.save_token);
            TextView token_symbol=(TextView)view.findViewById(R.id.token_symbol);


            tokenname.setText(tokenArraylist.get(i).gettokenname());
            save_token.setText(tokenArraylist.get(i).getsavetoken());
            token_symbol.setText(tokenArraylist.get(i).getTokensymbol());


        }



        return view;
    }
}
