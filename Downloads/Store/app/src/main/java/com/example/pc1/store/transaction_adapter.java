package com.example.pc1.store;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class transaction_adapter extends BaseAdapter {


    private final Context applicationContext;
    private final ArrayList<transaction> token_transaction;
    ViewHolder viewHolder=null;

    public transaction_adapter(Context applicationContext, ArrayList<transaction> token_transaction) {
        this.applicationContext = applicationContext;
        this.token_transaction = token_transaction;
    }


    @Override
    public int getCount() {
        return token_transaction.size();
    }

    @Override
    public Object getItem(int i) {
        return token_transaction.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

            final int checkBoxPosition = i;




            if(view==null){
                viewHolder = new ViewHolder();
                LayoutInflater inflater = (LayoutInflater) applicationContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.transactionlist, null);
                viewHolder.transaction_tokenname = (TextView) view.findViewById(R.id.transaction_tokenname);
                viewHolder.transaction_token_value = (TextView) view.findViewById(R.id.transaction_token_value);
                viewHolder.transaction_token_symbol = (TextView) view.findViewById(R.id.transaction_token_symbol);
                viewHolder.transaction_token_time = (TextView) view.findViewById(R.id.transaction_token_time);
                viewHolder.transaction_token_from = (TextView) view.findViewById(R.id.transaction_token_from);
                viewHolder.transaction_image=(ImageView)view.findViewById(R.id.imageView3);



                view.setTag(viewHolder);

            }else{
                viewHolder=(ViewHolder)view.getTag();

            }


//            TextView transaction_in_out = (TextView) view.findViewById(R.id.transaction_in_out);


//            transaction_in_out.setText(token_transaction.get(i).get_transaction_in_out());




        viewHolder.transaction_tokenname.setText(token_transaction.get(i).get_Transaction_tokenname());
        viewHolder.transaction_token_value.setText(token_transaction.get(i).get_transaction_token_value());
        viewHolder.transaction_token_symbol.setText(token_transaction.get(i).get_transaction_token_symbol());
        viewHolder.transaction_token_time.setText(token_transaction.get(i).get_transaction_token_time());
        viewHolder.transaction_token_from.setText(token_transaction.get(i).get_transaction_token_from());
        Glide.with(applicationContext).load(token_transaction.get(i).get_transaction_token_bitmap()).into(viewHolder.transaction_image);
        return view;
    }

    private class ViewHolder {

        TextView transaction_tokenname ;
        TextView transaction_token_value;
        TextView transaction_token_symbol;
        TextView transaction_token_time;
        TextView transaction_token_from ;
        ImageView transaction_image;
    }
}
