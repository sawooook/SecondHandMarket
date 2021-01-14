package com.example.pc1.store;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class LayoutTwo extends Fragment {


    public static Fragment newInstance(Context context) {
        LayoutTwo f = new LayoutTwo();

        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.layout_two, null);
        return root;
    }
}
