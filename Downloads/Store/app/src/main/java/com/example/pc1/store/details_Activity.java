package com.example.pc1.store;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class details_Activity extends AppCompatActivity {
    SharedPreferences login;
    private String Mypage_shared_id,Mypage_shared_img;
    String fragment_one_id;
    Button fragment_detail_btn;
    LinearLayout detail_sell_review_btn;
    String[] images;
    private String next_image;
    ArrayList<chat> chat_arrayList;
    ViewPager viewPager;
    long now;
    ArrayList<String> arrayList;
    TextView detail_content,detail_title,detail_price,detail_location,detail_check,detail_time,detail_id;
    String roomnum,fragment_one_title;
    private JSONArray jsonArray;
    private String fragment_detail;
    Button webrtc_btn;
    ImageView imageView,detail_img;
    private String fragment_detail_view;
    Adapter8 a;
    private ArrayList<String> temp;
    private String fragment_two_u_id,fragment_two_u_title,fragment_two_u_price,fragment_two_u_location,fragment_two_u_time,fragment_two_u_content,fragment_two_u_image;
    private boolean hoho,fragment_one_u_check;
    private String fragment_one_img;
    private String fragment_two_u_image3,fragment_two_u_image2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_);
        chat_arrayList = new ArrayList<chat>();
        now = System.currentTimeMillis();
        roomnum = String.valueOf(now);
        fragment_detail_btn=(Button)findViewById(R.id.fragment_detail_btn);
        detail_content=(TextView)findViewById(R.id.detail_content);
        detail_title=(TextView)findViewById(R.id.detail_title);
        detail_price=(TextView)findViewById(R.id.detail_price);
        detail_location=(TextView)findViewById(R.id.detail_location);
        detail_check=(TextView)findViewById(R.id.detail_check);
        detail_time=(TextView)findViewById(R.id.detail_time);
        detail_id=(TextView)findViewById(R.id.detail_user);
        detail_sell_review_btn=(LinearLayout)findViewById(R.id.sell_review);
        login = getSharedPreferences("hoho", MODE_PRIVATE);
        detail_img=(ImageView)findViewById(R.id.detail_img);
        Mypage_shared_id=login.getString("id","nooo");
        Mypage_shared_img=login.getString("img","nooo");
        listload();

//        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
//        actionBar.hide();

        temp = new ArrayList<>();


        a = new Adapter8(temp,this);

        ViewPager pager = (ViewPager)findViewById(R.id.photos_viewpager);
        pager.setAdapter(a);



        TabLayout tabLayout = (TabLayout)findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(pager, true);

        arrayList = new ArrayList<String>();
        detail_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent newlocation = new Intent(getApplicationContext(),ViroActivity.class);
//                startActivity(newlocation);
//                Log.e("arar","arara");
            }
        });

        Intent inn =getIntent();


        next_image=inn.getStringExtra("next_image");
        fragment_one_img=inn.getStringExtra("fragment_one_img");
        fragment_one_title=inn.getStringExtra("fragment_one_title");
        fragment_one_id=inn.getStringExtra("fragment_one_id");


//        Glide.with(getApplicationContext()).load(fragment_one_img).into(imageView);


//        detail();
        detail_sell_review_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent sell_Activity=new Intent(getApplicationContext(),review_Activity.class);
                sell_Activity.putExtra("next_image",next_image);
                Log.e("next_image",next_image);

                sell_Activity.putExtra("fragment_one_title",fragment_one_title);
                sell_Activity.putExtra("fragment_one_img",fragment_one_img);
                sell_Activity.putExtra("fragment_one_id",fragment_one_id);
                sell_Activity.putExtra("review_id",fragment_two_u_id);
                sell_Activity.putExtra("next_image",next_image);
                Log.e("next_image",next_image);

                Log.e("sssssssssssoukkk4",fragment_one_img);
                Log.e("sssssssssssoukkk4",fragment_one_title);
                Log.e("sssssssssssoukkk4",fragment_one_id);

                startActivity(sell_Activity);


            }
        });



        detail_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



            }
        });

        fragment_detail_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                listviewload();
                listviewload2();

                Toast.makeText(getApplicationContext(),"추가완료",Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void imgload() {

        StringRequest request = new StringRequest(Request.Method.POST, "http://52.14.144.55/img_load.php", new Response.Listener<String>() {
            //해당 mysql에 데이터를 저장함
            @Override
            public void onResponse(String response) {
//해당 데이터를 받아온다 1일시에는 로그인성공 0일시에는 로그인실패
                next_image=response;
                Glide.with(getApplicationContext()).load(next_image).apply(new RequestOptions().circleCrop()).into(detail_img);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();

                params.put("u_fragment_two_u_id", fragment_two_u_id);



                return params;
            }
        };

        Volley.newRequestQueue(getApplicationContext()).add(request);

    }

    private void listviewload2() {
        StringRequest request = new StringRequest(Request.Method.POST, "http://52.14.144.55/list_save.php", new Response.Listener<String>() {
            //해당 mysql에 데이터를 저장함
            @Override
            public void onResponse(String response) {
//해당 데이터를 받아온다 1일시에는 로그인성공 0일시에는 로그인실패


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();

                params.put("u_id", fragment_one_id);
                params.put("u_user", Mypage_shared_id);
                params.put("u_content", "새로운채팅방");
                params.put("u_img", Mypage_shared_img);
                params.put("user_time", roomnum);
                params.put("product_index",fragment_two_u_title);



                return params;
            }
        };

        Volley.newRequestQueue(getApplicationContext()).add(request);

    }



    private void listviewload() {
        StringRequest request = new StringRequest(Request.Method.POST, "http://52.14.144.55/list_save.php", new Response.Listener<String>() {
            //해당 mysql에 데이터를 저장함
            @Override
            public void onResponse(String response) {
//해당 데이터를 받아온다 1일시에는 로그인성공 0일시에는 로그인실패
                Log.e("idddd2", response);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {


                Map<String, String> params = new HashMap<>();

                params.put("u_id", Mypage_shared_id);
                params.put("u_user", fragment_one_id);
                params.put("u_content", "새로운채팅방");
                params.put("u_img", Mypage_shared_img);
                params.put("user_time",roomnum);
                params.put("u_num", "1");
                params.put("product_index",fragment_two_u_title);


                return params;
            }
        };

        Volley.newRequestQueue(getApplicationContext()).add(request);

    }





    private void listload() {

        StringRequest request = new StringRequest(Request.Method.POST, "http://52.14.144.55/fragmentone_load.php", new Response.Listener<String>() {
            //해당 mysql에 데이터를 저장함
            @Override
            public void onResponse(String response) {
//해당 데이터를 받아온다 1일시에는 로그인성공 0일시에는 로그인실패
                fragment_detail_view=response;
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

                params.put("u_title", fragment_one_title);
//                params.put("u_")
//                    Log.e("id", id);?


                return params;
            }
        };

        Volley.newRequestQueue(getApplicationContext()).add(request);

    }

    private void threekm_result(){
        try {
            JSONObject jsonObject = new JSONObject(fragment_detail_view);
            jsonArray = jsonObject.getJSONArray("result");

            for(int i=0;i<jsonArray.length();i++){

                JSONObject item = jsonArray.getJSONObject(i);
                Log.e("jsonArray", String.valueOf(item));


                fragment_two_u_id = item.getString("u_id");
                Log.e("fragment_u_id",fragment_two_u_id);
                fragment_two_u_title = item.getString("u_title");
                fragment_two_u_price= item.getString("u_price");
                fragment_two_u_location= item.getString("u_location");
                fragment_two_u_time= item.getString("u_time");
                fragment_two_u_content= item.getString("u_content");
                String imgrecive= item.getString("u_image");
                fragment_two_u_image= "http://52.14.144.55/"+imgrecive;
                if(imgrecive.equals("null")){
                    Log.e("hello","hello");
                }else{
                    temp.add(fragment_two_u_image);

                }


                String imgrecive2= item.getString("u_image1");
                fragment_two_u_image2= "http://52.14.144.55/"+imgrecive2;

                if(imgrecive2.equals("")){
                    Log.e("hello","hello");
                }else{
                    temp.add(fragment_two_u_image2);

                }

                String imgrecive3= item.getString("u_image2");
                fragment_two_u_image3= "http://52.14.144.55/"+imgrecive3;



                if(imgrecive3.equals("")) {
                    Log.e("hello","hello");
                }else{
                    temp.add(fragment_two_u_image3);

                }




                if(item.getString("u_check").equals("true")){
                    hoho=true;
                }else{
                    hoho=false;
                }



                fragment_one_u_check= hoho;
                detail_title.setText(fragment_two_u_title);
                detail_content.setText(fragment_two_u_content);
                detail_price.setText(fragment_two_u_price);
                detail_location.setText(fragment_two_u_location);
                imgload();
                if(hoho==true){
                    detail_check.setText("흥정가능");
                }else{
                    detail_check.setText("흥정불가능");

                }

//                Glide.with(getApplicationContext()).load(fragment_two_u_image).into(imageView);
                detail_time.setText(fragment_two_u_time);
                detail_id.setText(fragment_two_u_id);

                // 아까 만든 view



                a.notifyDataSetChanged();
            }



        } catch (JSONException e) {


        }

    }

}
