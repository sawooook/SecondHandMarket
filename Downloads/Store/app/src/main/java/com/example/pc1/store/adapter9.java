package com.example.pc1.store;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class adapter9 extends PagerAdapter {

    Context activity;
    ArrayList<String> temp2;






    public adapter9(ArrayList<String> temp2, Context activity) {
        this.temp2=temp2;
        this.activity=activity;
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        View view = null;
        LayoutInflater inflater = LayoutInflater.from(activity);
        view = inflater.inflate(R.layout.pager_adapter2, container, false);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView34);
        Glide.with(activity).load(temp2.get(position)).into(imageView);


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
        return temp2.size();
    }

}
