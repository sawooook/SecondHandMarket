package com.example.pc1.store;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class buy_ing_adapter extends BaseAdapter {
    private final ArrayList<ing> buy_ing_arraylist;
    private final Context applicationContext;
    public buy_ing_adapter(Context applicationContext, ArrayList<ing> buy_ing_arraylist) {
    this.buy_ing_arraylist =buy_ing_arraylist;
    this.applicationContext=applicationContext;
    }


    @Override
    public int getCount() {
        return buy_ing_arraylist.size();
    }

    @Override
    public Object getItem(int i) {
        return buy_ing_arraylist.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int  i, View view, ViewGroup viewGroup) {

        final int checkBoxPosition = i;
        ViewHolder viewHolder = null;


        if(view ==null) {
            viewHolder=new ViewHolder();

            LayoutInflater inflater = (LayoutInflater)applicationContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.finish_list, null);

            viewHolder.buy_ing_title=(TextView)view.findViewById(R.id.sell_title);
            viewHolder.buy_ing_data = (TextView) view.findViewById(R.id.sell_data);
            viewHolder.buy_ing_img = (ImageView) view. findViewById(R.id.sell_img);
            viewHolder.buy_ing_btn =(Button)view.findViewById(R.id.finish_btn_delete);






        view.setTag(viewHolder);

        }else{
            viewHolder =(ViewHolder)view.getTag();
        }



        viewHolder.buy_ing_title.setText(buy_ing_arraylist.get(i).getTitle2());
        viewHolder.buy_ing_data.setText(buy_ing_arraylist.get(i).getData2());
        Glide.with(applicationContext).load(buy_ing_arraylist.get(i).getImg2()).into(viewHolder.buy_ing_img);
        viewHolder.buy_ing_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//
                Intent product_buy = new Intent(applicationContext,Sell_finish_Activity.class);
                product_buy.putExtra("image_picture",buy_ing_arraylist.get(i).getImg2());
                product_buy.putExtra("buy_ing_data",buy_ing_arraylist.get(i).getData2());
                product_buy.putExtra("buy_ing_title",buy_ing_arraylist.get(i).getTitle2());

                applicationContext.startActivity(product_buy.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));



            }
        });


        return view;
    }



    private class ViewHolder {


        public TextView buy_ing_title;
        public TextView buy_ing_data;
        public Button buy_ing_btn;
        public ImageView buy_ing_img;
    }
}
