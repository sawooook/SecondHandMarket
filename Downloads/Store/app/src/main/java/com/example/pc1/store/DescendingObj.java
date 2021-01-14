package com.example.pc1.store;

import android.graphics.Point;

import java.util.Comparator;

class DescendingObj implements Comparator<Mainpageitem> {

    @Override
    public int compare(Mainpageitem mainpageitem, Mainpageitem t1) {
        return mainpageitem.getdistance().compareTo(t1.getdistance());
    }
}


