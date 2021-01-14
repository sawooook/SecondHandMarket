package com.example.pc1.store;

import android.graphics.drawable.Drawable;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class viewpage_ac extends AppCompatActivity {
    List<Drawable> temp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpage_ac);

//        temp = new ArrayList<>();
//        temp.add(ContextCompat.getDrawable(this,R.drawable.add));
//        temp.add(ContextCompat.getDrawable(this,R.drawable.ic_accessible_black_24dp));
//        temp.add(ContextCompat.getDrawable(this,R.drawable.add));
//
//        Adapter8 a = new Adapter8(temp,this);
//
//        ViewPager pager = (ViewPager)findViewById(R.id.photos_viewpager);
//        pager.setAdapter(a);
//
//
//        TabLayout tabLayout = (TabLayout)findViewById(R.id.tab_layout);
//        tabLayout.setupWithViewPager(pager, true);


    }
}
