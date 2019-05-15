package com.example.pc1.store;

import android.content.Context;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

//import com.bumptech.glide.Glide;
//import com.bumptech.glide.request.RequestOptions;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

class adapter extends BaseAdapter {
    ArrayList<chat> chat_arrayList;

    private Context context;
    private LayoutInflater mInflater;
    Context activity;
    Context context1;


    public adapter(Context activity, ArrayList<chat> chat_arrayList) {

        this.chat_arrayList=chat_arrayList;
        mInflater =LayoutInflater.from(activity);
        this.activity=activity;
    }


    //    LayoutInflater inflater = (LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    @Override
    public int getCount() {
        return chat_arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if(view ==null)
        {




            view = mInflater.inflate(R.layout.chat_list, null);

            TextView chat_num = (TextView)view.findViewById(R.id.textView9);
            TextView chat_content = (TextView)view.findViewById(R.id.textView4);
            TextView chdt_username = (TextView)view.findViewById(R.id.readnum);
            ImageView chat_img =(ImageView)view.findViewById(R.id.imageView4);
            TextView room_num =(TextView)view.findViewById(R.id.textView11);
            TextView product_index=(TextView)view.findViewById(R.id.product_index);
            TextView noread_msg = (TextView)view.findViewById(R.id.textView000);

            room_num.setText(chat_arrayList.get(i).getroom());
            chat_num.setText(chat_arrayList.get(i).getnum());
            chat_content.setText(chat_arrayList.get(i).getContent());
            chdt_username.setText(chat_arrayList.get(i).getusername());
            product_index.setText(chat_arrayList.get(i).getproduct_index());
            noread_msg.setText(chat_arrayList.get(i).getRead_num());
            Glide.with(activity).load(chat_arrayList.get(i).getcharImg()).apply(new RequestOptions().circleCrop()).into(chat_img);
        }



        return view;

    }
}
