package com.example.pc1.store;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class Main_Activity extends AppCompatActivity {
    FragmentOne FragmentOne;


    BottomNavigationView navigation;
    private TextView mTextMessage;
    Fragment selectedFragment = null;
    FragmentOne selectedFragment1;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_);




        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        BottomNavigationViewHelper.disableShiftMode(navigation);


        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.fragmentContainer, FragmentOne.newInstance()).commit();


    }


    private FragmentTransaction fragmentTransaction3;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    replaceFragment(FragmentOne.newInstance());
//                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//                    fragmentTransaction.add(R.id.fragmentContainer, FragmentOne.newInstance()).commit();
                    return true;

                case R.id.navigation_dashboard:
                    replaceFragment(FragmentTwo.newInstance());
//                    FragmentTransaction fragmentTransaction2= getSupportFragmentManager().beginTransaction();
//                    fragmentTransaction2.add(R.id.fragmentContainer, FragmentTwo.newInstance()).commit();
                    return true;

                case R.id.navigation_notifications1:
                    replaceFragment(ChatFragment.newInstance());
//                    FragmentTransaction fragmentTransaction3= getSupportFragmentManager().beginTransaction();
//                    fragmentTransaction3.add(R.id.fragmentContainer, ChatFragment.newInstance()).commit();
                    return true;

                case R.id.action_bottom_navigation_menu5:
                    replaceFragment(MypageFrageMent.newInstance());

//                    FragmentTransaction fragmentTransaction4 = getSupportFragmentManager().beginTransaction();
//                    fragmentTransaction4.add(R.id.fragmentContainer, MypageFrageMent.newInstance()).commit();
                    return true;


            }

            FragmentTransaction fragmentTransaction2 = getSupportFragmentManager().beginTransaction();
            fragmentTransaction2.replace(R.id.fragmentContainer, FragmentTwo.newInstance()).commit();

            fragmentTransaction3 = getSupportFragmentManager().beginTransaction();
            fragmentTransaction3.replace(R.id.fragmentContainer, ChatFragment.newInstance()).commit();

            FragmentTransaction fragmentTransaction4 = getSupportFragmentManager().beginTransaction();
            fragmentTransaction4.replace(R.id.fragmentContainer, MypageFrageMent.newInstance()).commit();

            return true;

        }
    };


    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainer, fragment).commit();
    }


//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        List<String> photos = null;
//        if (resultCode == RESULT_OK && requestCode == 1) {
//            if (data != null) {
//                photos = data.getStringArrayListExtra(PhotoPickerActivity.KEY_SELECTED_PHOTOS);
//            }
//        }
//    }


    
}



