package com.example.pc1.store;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class Setting_Activity extends Fragment {
    public static Fragment newInstance() {
        return new Setting_Activity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_setting_, container, false); // 여기서 UI를 생성해서 View를 return


    }
}