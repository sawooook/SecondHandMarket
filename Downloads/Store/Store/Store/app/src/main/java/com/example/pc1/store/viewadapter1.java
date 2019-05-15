package com.example.pc1.store;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class viewadapter1 extends PagerAdapter {
    private final FragmentActivity activity;
    private final ArrayList<String> selectedPhotos;

    public viewadapter1(FragmentActivity activity, ArrayList<String> selectedPhotos) {
        this.activity=activity;
        this.selectedPhotos=selectedPhotos;
    }

    @Override
    public int getCount() {
        return selectedPhotos.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == ((View)object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        //초기화
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.slider, container, false);
        ImageView imageView333 = (ImageView) v.findViewById(R.id.imageView);
        Log.e("selectedPhotos2", String.valueOf(selectedPhotos.size()));

        Glide.with(activity).load(selectedPhotos.get(position)).into(imageView333);
//            Log.e("positionwhat",images[position]);
        container.addView(v);
        return v;
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.invalidate();
//        super.destroyItem(container, position, object);
    }

}








