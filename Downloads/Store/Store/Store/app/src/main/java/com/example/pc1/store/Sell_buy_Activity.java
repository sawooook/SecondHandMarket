package com.example.pc1.store;

import android.content.SharedPreferences;
import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TabHost;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Sell_buy_Activity extends AppCompatActivity {


    buy_ing_adapter buy_ing_adapter;
    ArrayList<ing> buy_ing_arraylist;
    private String buy_ing_response;
    private String buy_ing_img,buy_ing_id,buy_ing_time;
    private String buy_ing_title;
    SharedPreferences.Editor editor;
    SharedPreferences login;
    private String idid;
    private String Mypage_shared_id;
    private ListView buy_ing,buy_finish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell_buy_);
        login = getSharedPreferences("hoho",MODE_PRIVATE);
        editor=login.edit();
        Mypage_shared_id=login.getString("id","nooo");

        idid=Mypage_shared_id;

        buy_ing_arraylist = new ArrayList<ing>();

        buy_finish =(ListView)findViewById(R.id.buy_finish_1);
        buy_ing =(ListView)findViewById(R.id.buy_ing_1);

        buy_ing_adapter = new buy_ing_adapter(getApplicationContext(),buy_ing_arraylist);
        buy_ing.setAdapter(buy_ing_adapter);

        TabHost tabHost = (TabHost) findViewById(R.id.tab1234);
        tabHost.setup();
        TabHost.TabSpec tabSpec1 = tabHost.newTabSpec("Tab1");

        tabSpec1.setIndicator("판매 진행중"); // Tab Subject

        tabSpec1.setContent(R.id.buy_ing); // Tab Content

        tabHost.addTab(tabSpec1);



        buy_ing();


        // Tab2 Setting

        TabHost.TabSpec tabSpec2 = tabHost.newTabSpec("Tab2");

        tabSpec2.setIndicator("판매 완료"); // Tab Subject

        tabSpec2.setContent(R.id.buy_finish); // Tab Content

        tabHost.addTab(tabSpec2);

    }

    private void buy_ing() {

        StringRequest request = new StringRequest(Request.Method.POST, "http://52.14.144.55/buy_ing.php", new Response.Listener<String>() {

            //해당 mysql에 데이터를 저장함
            @Override
            public void onResponse(String response) {
//해당 데이터를 받아온다 1일시에는 로그인성공 0일시에는 로그인실패
                buy_ing_response=response;
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

                params.put("u_id", idid);
//                params.put("u_")
//                    Log.e("id", id);?


                return params;
            }
        };

        Volley.newRequestQueue(getApplicationContext()).add(request);

    }

    private void threekm_result(){
        try {
            JSONObject jsonObject = new JSONObject(buy_ing_response);
            JSONArray jsonArray = jsonObject.getJSONArray("result");

            for(int i=0;i<jsonArray.length();i++){

                JSONObject item = jsonArray.getJSONObject(i);
                Log.e("jsonArray", String.valueOf(item));


                buy_ing_title = item.getString("u_title");
                buy_ing_time = item.getString("u_time");
                String imgrecive= item.getString("u_image");
                buy_ing_img= "http://52.14.144.55/"+imgrecive;

//                SimpleDateFormat dateFormat = new SimpleDateFormat("YYYYMMddHHmm");
//
//
//                Date curDate = new Date();
//                curDate = dateFormat.parse(dateFormat.format(curDate));
//                long curDateTime = curDate.getTime();
//

                buy_ing_arraylist.add(new ing(buy_ing_title,buy_ing_time,buy_ing_img));


            }

            buy_ing_adapter.notifyDataSetChanged();



        } catch (JSONException e) {


        }


    }
}
