package com.example.pc1.store;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import org.spongycastle.asn1.x509.Holder;

import java.util.ArrayList;

public class Crowling_adapter extends BaseAdapter {
    ArrayList<Crowling> arrayList;
    Context applicationContext;
    ViewHolder holder;


    public Crowling_adapter(Context applicationContext, ArrayList<Crowling> arrayList) {

        this.arrayList = arrayList;
        this.applicationContext = applicationContext;
    }


    //    LayoutInflater inflater = (LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;

        if (view == null) {
            holder = new ViewHolder();

            LayoutInflater inflater = (LayoutInflater)applicationContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.crowling_list, null);
            holder.news_title=view.findViewById(R.id.news_title);
            holder.news_link=view.findViewById(R.id.news_link);
            holder.news_img=view.findViewById(R.id.news_img);


            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
            RequestOptions myOptions = new RequestOptions()
                    .fitCenter()
                    .override(300, 300);

            holder.news_title.setText(arrayList.get(i).getNew_name());
            holder.news_link.setText(arrayList.get(i).getNew_link());
            Glide.with(applicationContext).load(arrayList.get(i).getNew_img()).apply(myOptions).into(holder.news_img);





        return view;

    }
    static class ViewHolder {
        TextView news_title;
        TextView news_link;
        ImageView news_img;
    }


}