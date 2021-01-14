package com.example.pc1.store;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Sell_finish_Activity extends AppCompatActivity {

    private String sell_title;
    private String sell_finish;
    private String sell_finish_u_id,sell_finish_u_title,sell_finish__u_price,sell_finish_u_image;
    Button sell_finish_cancel,sell_finish_btn;
    TextView sell_finish_product_name,sell_finish_product_one,sell_finish_sell_name,sell_finish_buy_name;
    ImageView sell_finish_product_img;
    private String sell_myid;
    private String roomm;
    String reviewplz = "reviewplz";





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell_finish_);
        Intent sell_title_name = getIntent();
        sell_title=sell_title_name.getStringExtra("sell_title_name");
        sell_myid=sell_title_name.getStringExtra("myid");
        roomm=sell_title_name.getStringExtra("roomm");
        Log.e("roomm",roomm);

        sell_finish_cancel=(Button)findViewById(R.id.sell_finish_cancel);
        sell_finish_btn=(Button)findViewById(R.id.sell_finish_btn);
        sell_finish_product_name=(TextView)findViewById(R.id.sell_finish_product_name);
        sell_finish_product_one=(TextView)findViewById(R.id.sell_finish_product_one);
        sell_finish_sell_name=(TextView)findViewById(R.id.sell_finish_sell_name);
        sell_finish_buy_name=(TextView)findViewById(R.id.sell_finish_buy_name);
        sell_finish_product_img=(ImageView)findViewById(R.id.sell_finish_product_img);
        sell_finish_load();



        sell_finish_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogInterface.OnClickListener yes_finish_send = new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        yes_finish_send();
                    }
                };

                DialogInterface.OnClickListener cancelListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                };

                new AlertDialog.Builder(Sell_finish_Activity.this)
                        .setTitle("정말 거래를 완료하시겠습니까?")
                        .setPositiveButton("아니요", cancelListener)
                        .setNegativeButton("예", yes_finish_send)
                        .show();



            }
        });

        sell_finish_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cancel_intetn = new Intent(getApplicationContext(),Chatroom_Activity.class);
                cancel_intetn.putExtra("pos",roomm);
                cancel_intetn.putExtra("getproduct_index_id",sell_title);
                Log.e("getproduct_index_id",sell_title);
                cancel_intetn.putExtra("gogo","gogo");



                startActivity(cancel_intetn);

            }
        });

    }

    private void yes_finish_send() {


        StringRequest request = new StringRequest(Request.Method.POST, "http://52.14.144.55/sell_update2.php", new Response.Listener<String>() {
            //해당 mysql에 데이터를 저장a함
            @Override
            public void onResponse(String response) {
//해당 데이터를 받아온다 1일시에는 로그인성공 0일시에는 로그인실패
                Log.e("responsesesese",response);
//                twokm_result();


                if(response.equals("finish")){
                    Toast.makeText(getApplicationContext(),"이미 완료된 거래입니다",Toast.LENGTH_SHORT).show();
                    Intent cancel_intetn3 = new Intent(getApplicationContext(),Chatroom_Activity.class);
                    cancel_intetn3.putExtra("reviewplz",reviewplz);
                    cancel_intetn3.putExtra("pos",roomm);
                    cancel_intetn3.putExtra("getproduct_index_id",sell_title);
                    Log.e("getproduct_index_id",sell_title);
                    cancel_intetn3.putExtra("gogo","gogo");

                    startActivity(cancel_intetn3);

                }else{

                    Toast.makeText(getApplicationContext(),"거래가 완료되었습니다",Toast.LENGTH_SHORT).show();
                    sell_send();


                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();

                params.put("u_title", sell_title);
//                params.put("u_")
//                    Log.e("id", id);?


                return params;
            }
        };

        Volley.newRequestQueue(getApplicationContext()).add(request);





    }

    private void sell_finish_load() {

        StringRequest request = new StringRequest(Request.Method.POST, "http://52.14.144.55/sell_finish.php", new Response.Listener<String>() {
            //해당 mysql에 데이터를 저장함
            @Override
            public void onResponse(String response) {
//해당 데이터를 받아온다 1일시에는 로그인성공 0일시에는 로그인실패
                sell_finish=response;
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

                params.put("u_id", sell_title);
//                params.put("u_")
//                    Log.e("id", id);?


                return params;
            }
        };

        Volley.newRequestQueue(getApplicationContext()).add(request);



    }

    private void twokm_result(){
        try {
            JSONObject jsonObject = new JSONObject(sell_finish);
            JSONArray jsonArray = jsonObject.getJSONArray("result");


            for(int i=0;i<jsonArray.length();i++) {

                JSONObject item = jsonArray.getJSONObject(i);
                Log.e("jsonArray", String.valueOf(item));


                sell_finish_u_id = item.getString("u_id");
                sell_finish_u_title = item.getString("u_title");
                sell_finish__u_price = item.getString("u_price");
                String imgrecive = item.getString("u_image");
                sell_finish_u_image = "http://52.14.144.55/" + imgrecive;



            }

            //판매이미지
            Glide.with(getApplicationContext()).load(sell_finish_u_image).into(sell_finish_product_img);
            //판매아이템이름
            sell_finish_product_name.setText(sell_finish_u_title);
            //판매가격
            sell_finish_product_one.setText(sell_finish__u_price);
            //판매자 아이디
            sell_finish_sell_name.setText(sell_finish_u_id);
            //구매자 아이디
            sell_finish_buy_name.setText(sell_myid);





        } catch (JSONException e) {


        }

    }

    private void sell_send() {

        DialogInterface.OnClickListener cameraListener3 = new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                review_write_Activity();
            }
        };

        DialogInterface.OnClickListener cancelListener3 = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                review_cancel();
            }
        };

        new AlertDialog.Builder(Sell_finish_Activity.this)
                .setTitle("후기를 남기시겠습니까?")
                .setPositiveButton("아니요", cancelListener3)
                .setNegativeButton("네", cameraListener3)
                .show();





    }

    private void review_cancel() {
        Intent next_review_intent = new Intent(getApplicationContext(),Chatroom_Activity.class);
        next_review_intent.putExtra("pos",roomm);
        next_review_intent.putExtra("reviewplz",reviewplz);
        next_review_intent.putExtra("gogo","gogo");
        next_review_intent.putExtra("getproduct_index_id",sell_title);
        startActivity(next_review_intent);
    }

    private void review_write_Activity() {
        Intent next_review_intent = new Intent(getApplicationContext(),sell_review.class);
        next_review_intent.putExtra("pos",roomm);
        next_review_intent.putExtra("reviewplz",reviewplz);
        next_review_intent.putExtra("gogo","gogo");
        next_review_intent.putExtra("getproduct_index_id",sell_title);
        startActivity(next_review_intent);
    }

}
