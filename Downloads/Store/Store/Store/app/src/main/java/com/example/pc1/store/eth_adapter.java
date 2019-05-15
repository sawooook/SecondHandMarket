package com.example.pc1.store;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class eth_adapter extends BaseAdapter {
    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }

//    private final Context applicationContext;
//    private final ArrayList<eth_transaction> ethArraylist;
//
//    ViewHolder viewHolder=null;
//    public eth_adapter(Context applicationContext, ArrayList<eth_transaction> ethArraylist) {
//        this.applicationContext = applicationContext;
//        this.ethArraylist = ethArraylist;
//    }
//
//    @Override
//    public int getCount() {
//        return ethArraylist.size();
//    }
//
//    @Override
//    public Object getItem(int i) {
//        return ethArraylist.get(i);
//    }
//
//    @Override
//    public long getItemId(int i) {
//        return 0;
//    }
//
//    @Override
//    public View getView(int i, View view, ViewGroup viewGroup) {
//
//
//
//
//
//        if(view==null){
//            viewHolder = new eth_adapter.ViewHolder();
//            LayoutInflater inflater = (LayoutInflater) applicationContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            view = inflater.inflate(R.layout.eth_list_view, null);
//            viewHolder.transaction_tokenname = (TextView) view.findViewById(R.id.eth_tokenname);
//            viewHolder.transaction_token_value = (TextView) view.findViewById(R.id.eth_token_value);
//            viewHolder.transaction_token_symbol = (TextView) view.findViewById(R.id.eth_token_symbol);
//            viewHolder.transaction_token_time = (TextView) view.findViewById(R.id.transaction_token_time);
//            viewHolder.transaction_token_from = (TextView) view.findViewById(R.id.transaction_token_from);
//            viewHolder.transaction_image=(ImageView)view.findViewById(R.id.imageView3);
//
//
//
//            view.setTag(viewHolder);
//
//        }else{
//            viewHolder=(eth_adapter.ViewHolder)view.getTag();
//
//        }
//
//
////            TextView transaction_in_out = (TextView) view.findViewById(R.id.transaction_in_out);
//
//
////            transaction_in_out.setText(token_transaction.get(i).get_transaction_in_out());
//
//
//
//
//        viewHolder.transaction_tokenname.setText(token_transaction.get(i).get_Transaction_tokenname());
//        viewHolder.transaction_token_value.setText(token_transaction.get(i).get_transaction_token_value());
//        viewHolder.transaction_token_symbol.setText(token_transaction.get(i).get_transaction_token_symbol());
//        viewHolder.transaction_token_time.setText(token_transaction.get(i).get_transaction_token_time());
//        viewHolder.transaction_token_from.setText(token_transaction.get(i).get_transaction_token_from());
//        Glide.with(applicationContext).load(token_transaction.get(i).get_transaction_token_bitmap()).into(viewHolder.transaction_image);
//        return view;
//    }
//
//    private class ViewHolder {
//
//        TextView transaction_tokenname ;
//        TextView transaction_token_value;
//        TextView transaction_token_symbol;
//        TextView transaction_token_time;
//        TextView transaction_token_from ;
//        ImageView transaction_image;
//    }
}
