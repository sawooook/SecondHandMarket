package com.example.pc1.store;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.LogoutResponseCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class MainActivity_test1 extends AppCompatActivity {
    private RecyclerView.LayoutManager LayoutManager;
    ArrayList<Mainpageitem> Mainarraylist;
    TextView Position_value,location_ok;
    private JSONArray jsonArray;
    private String fragment_one_load;
    SharedPreferences.Editor editor;
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
    private RecyclerView recyclerView;
    private String Myshared_id;
    private String Mypage_shared_img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_test1);
        LinearLayout Add_Activity=(LinearLayout)findViewById(R.id.Add_Activity);
        LinearLayout Chat_Activity=(LinearLayout)findViewById(R.id.Chat_Activity);
        LinearLayout Mypage_Activity=(LinearLayout)findViewById(R.id.Mypage_Activity);
        getSupportActionBar().setDisplayOptions(android.support.v7.app.ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.custom_bar);


        Add_Activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent Add_Activity_intent = new Intent(getApplicationContext(),Add_Main_Activity.class);
                startActivity(Add_Activity_intent);
                finish();


            }
        });

        Chat_Activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Chat_Activity_intent = new Intent(getApplicationContext(),Chat_Main_Activity.class);
                startActivity(Chat_Activity_intent);
                finish();


            }
        });


        Mypage_Activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Mypage_Activity_intent =new Intent(getApplicationContext(),Mypage_Activity.class);
                startActivity(Mypage_Activity_intent);
                finish();

            }
        });

        Mainarraylist= new ArrayList<Mainpageitem>();
        login = getSharedPreferences("hoho", Context.MODE_PRIVATE);
        editor=login.edit();

        recyclerView=findViewById(R.id.recycle);
        Spinner fagment_one_spiner=findViewById(R.id.fagment_one_spiner);
        LayoutManager= new LinearLayoutManager(MainActivity_test1.this);
        recyclerView.setLayoutManager(LayoutManager);

        FirebaseMessaging.getInstance().subscribeToTopic("news");

        if (FirebaseInstanceId.getInstance().getToken() != null) {
            Log.d( "token = " , FirebaseInstanceId.getInstance().getToken());
            FirebaseInstanceId.getInstance().getToken();
        }


        Myimg();
        onekm();
        shared_location=Double.parseDouble(login.getString("location_getLatitude","37.56"));
        shared_location_long=Double.parseDouble(login.getString("location_getLongtitde","126.97"));



        Typeface type = Typeface.createFromAsset(getAssets(),"fonts/jeju.ttf");
        TextView saoukkkk=(TextView)findViewById(R.id.saoukkkk);

        saoukkkk.setTypeface(type);
        saoukkkk.setText("물건살래");
        saoukkkk.setTextSize(19);

        Mypage_shared_nickname=login.getString("nickname","nooo");
        Myshared_id=login.getString("id","nooo");

        MyRecyclerAdapter =new MyRecyclerAdapter(getApplicationContext(),Mainarraylist);
        recyclerView.setAdapter(MyRecyclerAdapter);

        MyRecyclerAdapter.setOnClickListener(new MyRecyclerAdapter.MyRecyclerViewClickListener() {
            @Override
            public void onItemClicked(int position) {

                Intent intent = new Intent(getApplicationContext(),details_Activity.class);

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
                    Mainarraylist.clear();
                    MyRecyclerAdapter.notifyDataSetChanged();
                    onekm();

                }else if(position==1){
                    Mainarraylist.clear();
                    MyRecyclerAdapter.notifyDataSetChanged();
                    twokm();


                }else if(position==2){
                    Mainarraylist.clear();
                    MyRecyclerAdapter.notifyDataSetChanged();
                    threekm();

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private void Myimg() {
        StringRequest request = new StringRequest(Request.Method.POST, "http://52.14.144.55/myimg.php", new Response.Listener<String>() {

            //해당 mysql에 데이터를 저장함
            @Override
            public void onResponse(String response) {
//해당 데이터를 받아온다 1일시에는 로그인성공 0일시에는 로그인실패
       Log.e("logloglog",response);


                editor.putString("img",response);
                editor.commit();




            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();

                params.put("u_id", Myshared_id);
//                params.put("u_")
//                    Log.e("id", id);?


                return params;
            }
        };

        Volley.newRequestQueue(getApplicationContext()).add(request);

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

        Volley.newRequestQueue(getApplicationContext()).add(request);

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


                if(distance<5000){


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

        Volley.newRequestQueue(getApplicationContext()).add(request);



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

                if(distance<3000){


                    String num = String.format("%.0f" , distance);

                    Mainarraylist.add(new Mainpageitem(fragment_one_u_title,fragment_one_u_price,fragment_one_u_location,fragment_one_u_image,fragment_one_u_id,fragment_one_u_time,fragment_one_u_check,num));
                    DescendingObj descending = new DescendingObj();
                    Collections.sort(Mainarraylist, descending);
                    recyclerView.getRecycledViewPool().clear();
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

        Volley.newRequestQueue(getApplicationContext()).add(request);

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

                if(distance<1000){

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

}
