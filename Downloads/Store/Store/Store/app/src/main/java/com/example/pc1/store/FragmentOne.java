package com.example.pc1.store;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.location.Location;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Map;


public class FragmentOne extends Fragment  {
    private RecyclerView.LayoutManager LayoutManager;
    ArrayList<Mainpageitem> Mainarraylist;
    TextView Position_value,location_ok;
    private JSONArray jsonArray;
    private String fragment_one_load;
    Boolean hoho,fragment_one_u_check;
    MyRecyclerAdapter MyRecyclerAdapter;
    private String fragment_one_load_listview,fragment_one_u_id,fragment_one_u_title,fragment_one_u_content,fragment_one_u_price;
    private String fragment_one_u_location,fragment_one_u_image,fragment_one_u_time,Mypage_shared_nickname;
    SharedPreferences login;
    private String spiner_responde_onekm,spiner_response_location_Latitude,spiner_response_location_Longtitde,spiner_response_location_marker_title,spiner_response_location;
    private Location locationA,locationB;
    private double distance;
    MainService mService;

    boolean isService = false;
    private String fragment_one_u_Latitude,fragment_one_u_Longtitude;
    private double shared_location,shared_location_long;
    private ServiceConnection mconnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            MainService.LocalBinder binder =(MainService.LocalBinder)iBinder;
            mService = binder.getService();
            isService =true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            isService = false;
        }
    };


    public static Fragment newInstance() {

        return new FragmentOne();
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.activity_fragment_one, container, false);
        Mainarraylist= new ArrayList<Mainpageitem>();
        login = getActivity().getSharedPreferences("hoho", Context.MODE_PRIVATE);


        final RecyclerView recyclerView=v.findViewById(R.id.recycle);
        Spinner fagment_one_spiner=v.findViewById(R.id.fagment_one_spiner);
        LayoutManager= new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(LayoutManager);

        FirebaseMessaging.getInstance().subscribeToTopic("news");

        if (FirebaseInstanceId.getInstance().getToken() != null) {
            Log.d( "token = " , FirebaseInstanceId.getInstance().getToken());
            FirebaseInstanceId.getInstance().getToken();
        }




        onekm();
        shared_location=Double.parseDouble(login.getString("location_getLatitude","37.56"));
        shared_location_long=Double.parseDouble(login.getString("location_getLongtitde","126.97"));




        Mypage_shared_nickname=login.getString("nickname","nooo");


        MyRecyclerAdapter =new MyRecyclerAdapter(getActivity(),Mainarraylist);
        recyclerView.setAdapter(MyRecyclerAdapter);

        MyRecyclerAdapter.setOnClickListener(new MyRecyclerAdapter.MyRecyclerViewClickListener() {
            @Override
            public void onItemClicked(int position) {

                Intent intent = new Intent(getActivity(),details_Activity.class);

                String text1 = Mainarraylist.get(position).getTitle();
                String user_id = Mainarraylist.get(position).getPerson();
                String user_img = Mainarraylist.get(position).getImg();
                intent.putExtra("fragment_one_id",user_id);
                intent.putExtra("fragment_one_title",text1);
                intent.putExtra("fragment_one_img",user_img);
                startActivity(intent);

//              Log.e("value", String.valueOf(hh));

            }
        });

        fagment_one_spiner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                if(position==0){
                    onekm();

                }else if(position==1){
                    twokm();


                }else if(position==2){
                    threekm();

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        return v;
    }

    private void threekm() {

        StringRequest request = new StringRequest(Request.Method.POST, "http://52.14.144.55/main_load.php", new Response.Listener<String>() {

            //해당 mysql에 데이터를 저장함
            @Override
            public void onResponse(String response) {
//해당 데이터를 받아온다 1일시에는 로그인성공 0일시에는 로그인실패
                spiner_responde_onekm=response;
                threekm_result();


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();

                params.put("u_id", "1");
//                params.put("u_")
//                    Log.e("id", id);?


                return params;
            }
        };

        Volley.newRequestQueue(getActivity()).add(request);

    }

    private void threekm_result(){
        try {
            Mainarraylist.clear();
            JSONObject jsonObject = new JSONObject(spiner_responde_onekm);
            jsonArray = jsonObject.getJSONArray("result");

            for(int i=0;i<jsonArray.length();i++){

                JSONObject item = jsonArray.getJSONObject(i);


                fragment_one_u_id = item.getString("u_id");
                fragment_one_u_title = item.getString("u_title");
                fragment_one_u_price= item.getString("u_price");
                fragment_one_u_location= item.getString("u_location");
                fragment_one_u_Longtitude = item.getString("u_Longtitude");
                fragment_one_u_Latitude = item.getString("u_Latitude");
                fragment_one_u_time= item.getString("u_time");
                fragment_one_u_content= item.getString("u_content");
                String imgrecive= item.getString("u_image");
                fragment_one_u_image= "http://52.14.144.55/"+imgrecive;
                if(item.getString("u_check").equals("true")){
                    hoho=true;
                }else{
                    hoho=false;
                }
                fragment_one_u_check= hoho;

                locationA = new Location("point A");
                locationA.setLatitude(Double.parseDouble(fragment_one_u_Latitude));
                locationA.setLongitude(Double.parseDouble(fragment_one_u_Longtitude));

                getDistance();
//                SimpleDateFormat dateFormat = new SimpleDateFormat("YYYYMMddHHmm");
//
//
//                Date curDate = new Date();
//                curDate = dateFormat.parse(dateFormat.format(curDate));
//                long curDateTime = curDate.getTime();
//


                if(distance<100000){


                    String num = String.format("%.0f" , distance);

//                    Log.e("num",num);

                    Mainarraylist.add(new Mainpageitem(fragment_one_u_title,fragment_one_u_price,fragment_one_u_location,fragment_one_u_image,fragment_one_u_id,fragment_one_u_time,fragment_one_u_check,num));
                    DescendingObj descending = new DescendingObj();
                    Collections.sort(Mainarraylist, descending);
                    MyRecyclerAdapter.notifyDataSetChanged();








                }else {
                }

            }




        } catch (JSONException e) {


        }

    }

    private void twokm() {

        StringRequest request = new StringRequest(Request.Method.POST, "http://52.14.144.55/main_load.php", new Response.Listener<String>() {
            //해당 mysql에 데이터를 저장함
            @Override
            public void onResponse(String response) {
//해당 데이터를 받아온다 1일시에는 로그인성공 0일시에는 로그인실패
                spiner_responde_onekm=response;
                twokm_result();


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();

                params.put("u_id", "1");
//                params.put("u_")
//                    Log.e("id", id);?


                return params;
            }
        };

        Volley.newRequestQueue(getActivity()).add(request);



    }

    private void twokm_result(){
        try {
            Mainarraylist.clear();
            JSONObject jsonObject = new JSONObject(spiner_responde_onekm);
            jsonArray = jsonObject.getJSONArray("result");

            for(int i=0;i<jsonArray.length();i++){

                JSONObject item = jsonArray.getJSONObject(i);


                fragment_one_u_id = item.getString("u_id");
                fragment_one_u_title = item.getString("u_title");
                fragment_one_u_price= item.getString("u_price");
                fragment_one_u_location= item.getString("u_location");
                fragment_one_u_Longtitude = item.getString("u_Longtitude");
                fragment_one_u_Latitude = item.getString("u_Latitude");
                fragment_one_u_time= item.getString("u_time");
                fragment_one_u_content= item.getString("u_content");
                String imgrecive= item.getString("u_image");
                fragment_one_u_image= "http://52.14.144.55/"+imgrecive;
                if(item.getString("u_check").equals("true")){
                    hoho=true;
                }else{
                    hoho=false;
                }
                fragment_one_u_check= hoho;

                locationA = new Location("point A");
                locationA.setLatitude(Double.parseDouble(fragment_one_u_Latitude));
                locationA.setLongitude(Double.parseDouble(fragment_one_u_Longtitude));

                getDistance();

                if(distance<200){


                    String num = String.format("%.0f" , distance);

                    Mainarraylist.add(new Mainpageitem(fragment_one_u_title,fragment_one_u_price,fragment_one_u_location,fragment_one_u_image,fragment_one_u_id,fragment_one_u_time,fragment_one_u_check,num));
                    DescendingObj descending = new DescendingObj();
                    Collections.sort(Mainarraylist, descending);

                    MyRecyclerAdapter.notifyDataSetChanged();




                }else {
                    Log.e("nono","nono");
                }
            }




        } catch (JSONException e) {


        }
//        for(int i=0;i<jsonArray.length();i++) {
//            Log.e("json", String.valueOf(jsonArray));
//            Mainarraylist.add(new Mainpageitem(fragment_one_u_title,fragment_one_u_price,fragment_one_u_location,fragment_one_u_id,fragment_one_u_image,fragment_one_u_time,fragment_one_u_check));
//            MyRecyclerAdapter.notifyDataSetChanged();
    }


//
//    private void sold_data_list() {
//        StringRequest request = new StringRequest(Request.Method.POST, "http://13.58.3.24/main_load.php", new Response.Listener<String>() {
//            //해당 mysql에 데이터를 저장함
//            @Override
//            public void onResponse(String response) {
////해당 데이터를 받아온다 1일시에는 로그인성공 0일시에는 로그인실패
//                fragment_one_load = response;
//                Log.e("idddd2", response);
//                showResult();
//
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        }) {
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//
//                Map<String, String> params = new HashMap<>();
//
//                params.put("u_id", "1");
////                params.put("u_")
////                    Log.e("id", id);?
//
//
//                return params;
//            }
//        };
//
//        Volley.newRequestQueue(getActivity()).add(request);
//
//    }
//    private void showResult(){
//        try {
//            JSONObject jsonObject = new JSONObject(fragment_one_load);
//            jsonArray = jsonObject.getJSONArray("result");
//
//            for(int i=0;i<jsonArray.length();i++){
//
//                JSONObject item = jsonArray.getJSONObject(i);
//                Log.e("item", String.valueOf(item));
//
//                fragment_one_u_id = item.getString("u_id");
//                fragment_one_u_title = item.getString("u_title");
//                fragment_one_u_price= item.getString("u_price");
//                fragment_one_u_location= item.getString("u_location");
//
//                String imgrecive= item.getString("u_image");
//                fragment_one_u_image= "http://13.58.3.24/"+imgrecive;
//                Log.e("ggg",fragment_one_u_image);
//
//                if(item.getString("u_check").equals("true")){
//                     hoho=true;
//                }else{
//                     hoho=false;
//                }
//                fragment_one_u_check= hoho;
//
//                fragment_one_u_time= item.getString("u_time");
//                fragment_one_u_content= item.getString("u_content");
//                Mainarraylist.add(new Mainpageitem(fragment_one_u_title,fragment_one_u_price,fragment_one_u_location,fragment_one_u_image,fragment_one_u_id,fragment_one_u_time,fragment_one_u_check));
//                MyRecyclerAdapter.notifyDataSetChanged();
//
//            }
//
//
//
//
//        } catch (JSONException e) {
//
//
//        }
////        for(int i=0;i<jsonArray.length();i++) {
////            Log.e("json", String.valueOf(jsonArray));
////            Mainarraylist.add(new Mainpageitem(fragment_one_u_title,fragment_one_u_price,fragment_one_u_location,fragment_one_u_id,fragment_one_u_image,fragment_one_u_time,fragment_one_u_check));
////            MyRecyclerAdapter.notifyDataSetChanged();
//        }

    private void onekm() {
        StringRequest request = new StringRequest(Request.Method.POST, "http://52.14.144.55/main_load.php", new Response.Listener<String>() {
            //해당 mysql에 데이터를 저장함
            @Override
            public void onResponse(String response) {
//해당 데이터를 받아온다 1일시에는 로그인성공 0일시에는 로그인실패
                spiner_responde_onekm=response;
                onekm_result();


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();

                params.put("u_id", "1");
//                params.put("u_")
//                    Log.e("id", id);?


                return params;
            }
        };

        Volley.newRequestQueue(getActivity()).add(request);

    }

    private void onekm_result(){
        try {
            JSONObject jsonObject = new JSONObject(spiner_responde_onekm);
            jsonArray = jsonObject.getJSONArray("result");
            Mainarraylist.clear();

            for(int i=0;i<jsonArray.length();i++){

                JSONObject item = jsonArray.getJSONObject(i);


                fragment_one_u_id = item.getString("u_id");
                fragment_one_u_title = item.getString("u_title");
                fragment_one_u_price= item.getString("u_price");
                fragment_one_u_location= item.getString("u_location");
                fragment_one_u_Longtitude = item.getString("u_Longtitude");
                fragment_one_u_Latitude = item.getString("u_Latitude");
                fragment_one_u_time= item.getString("u_time");
                fragment_one_u_content= item.getString("u_content");
                String imgrecive= item.getString("u_image");
                fragment_one_u_image= "http://52.14.144.55/"+imgrecive;
                if(item.getString("u_check").equals("true")){
                    hoho=true;
                }else{
                    hoho=false;
                }
                fragment_one_u_check= hoho;


                locationA = new Location("point A");
                locationA.setLatitude(Double.parseDouble(fragment_one_u_Latitude));
                locationA.setLongitude(Double.parseDouble(fragment_one_u_Longtitude));

                getDistance();

                if(distance<40){

                    String num = String.format("%.0f" , distance);
                    Log.e("num",num);


                    Mainarraylist.add(new Mainpageitem(fragment_one_u_title,fragment_one_u_price,fragment_one_u_location,fragment_one_u_image,fragment_one_u_id,fragment_one_u_time,fragment_one_u_check,num));
                    DescendingObj descending = new DescendingObj();

                    Log.e("num", String.valueOf(descending));
                    Collections.sort(Mainarraylist, descending);

                    MyRecyclerAdapter.notifyDataSetChanged();




                }else {
                }
            }




        } catch (JSONException e) {


        }
//        for(int i=0;i<jsonArray.length();i++) {
//            Log.e("json", String.valueOf(jsonArray));
//            Mainarraylist.add(new Mainpageitem(fragment_one_u_title,fragment_one_u_price,fragment_one_u_location,fragment_one_u_id,fragment_one_u_image,fragment_one_u_time,fragment_one_u_check));
//            MyRecyclerAdapter.notifyDataSetChanged();
    }

    public void getDistance(){

        shared_location=Double.parseDouble(login.getString("location_getLatitude","37.56"));
        shared_location_long=Double.parseDouble(login.getString("location_getLongtitde","126.97"));

        locationB = new Location("point B");
        locationB.setLatitude(shared_location);
        locationB.setLongitude(shared_location_long);


        distance = locationB.distanceTo(locationA);

    }
    public void onStart(){
        super.onStart();
//        Intent intent = new Intent(getActivity(),MainService.class);
//        bindService(intent,mconnection,Context.BIND_AUTO_CREATE);

    }


//
//    private void bindService(Intent intent, ServiceConnection mconnection, int bindAutoCreate) {
//
//    }


}

