package com.example.pc1.store;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

class Adapter8 extends PagerAdapter {

    Context context;
    ArrayList<String> temp;



    public Adapter8(ArrayList<String> temp, details_Activity context) {
        this.temp=temp;
        this.context=context;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        View view = null;
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.pager_adapter, container, false);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        Glide.with(context).load(temp.get(position)).into(imageView);


        container.addView(view);

        return view;

    }

    @Override
    public void destroyItem(View container, int position, Object object) {
        ((ViewPager) container).removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return (view == object);
    }

    public int getCount() {
        return temp.size();
    }


}