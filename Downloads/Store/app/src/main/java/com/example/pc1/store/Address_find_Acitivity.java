package com.example.pc1.store;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Address_find_Acitivity extends AppCompatActivity {

    EditText address_id_find_edittext;
    TextView address_id_find_id,address_id_text,address_id_use_btn,address_id_text_wallet;
    Button address_id_find_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_find__acitivity);
        Typeface type = Typeface.createFromAsset(getAssets(),"fonts/jeju.ttf");

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.custom_bar);
        TextView saoukkkk=(TextView)findViewById(R.id.saoukkkk);
        saoukkkk.setTypeface(type);
        saoukkkk.setText("주소찾기");
        saoukkkk.setTextSize(19);
        address_id_find_id=(TextView)findViewById(R.id.address_id_find_id);
        address_id_find_edittext=(EditText)findViewById(R.id.address_id_find_edittext);
        address_id_find_btn=(Button)findViewById(R.id.address_id_find_btn);
        address_id_text=(TextView)findViewById(R.id.address_id_text);
        address_id_use_btn=(TextView)findViewById(R.id.address_id_use_btn);
        address_id_text_wallet=(TextView)findViewById(R.id.address_id_text_wallet);

        address_id_use_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent address_intent = new Intent();
                address_intent.putExtra("address_id_find",address_id_find_id.getText().toString());
                setResult(RESULT_OK,address_intent);
                finish();




            }
        });


        address_id_find_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addres_find_id();





            }
        });


    }

    private void addres_find_id() {


        StringRequest request = new StringRequest(Request.Method.POST, "http://52.14.144.55/find_id.php", new Response.Listener<String>() {
            //해당 mysql에 데이터를 저장함
            @Override
            public void onResponse(String response) {
//해당 데이터를 받아온다 1일시에는 로그인성공 0일시에는 로그인실패
                Log.e("RESULT",response);
                if(response.equals("2")){


////                    Log.e("로그인", String.valueOf(response.equals("1")));
//                    Toast.makeText(getApplicationContext(),"저장안합니다 있어요",Toast.LENGTH_SHORT).show();
//                    Intent SuccessIntent = new Intent(Login_Activity.this,Main_Activity.class);
//                    startActivity(SuccessIntent);
//                    Log.e("RESULT","성공적으로 처리되었습니다!");
//                    editor.putString("id", MainActivity_id.getText().toString());
//                    editor.putString("pw", MainActivity_pw.getText().toString());
//                    editor.commit();

                }else{
//                    Log.e("RESULT","성공적으로 처리아님!");
////                    Log.e("로그인실패", String.valueOf(response.equals("0")));
//                    Toast.makeText(getApplicationContext(),response.toString(),Toast.LENGTH_SHORT).show();
                    address_id_find_id.setText(response.toString());
                    address_id_text.setText(address_id_find_edittext.getText().toString());

                    address_id_find_id.setVisibility(View.VISIBLE);
                    address_id_text_wallet.setVisibility(View.VISIBLE);
                    address_id_use_btn.setVisibility(View.VISIBLE);
                    address_id_text.setVisibility(View.VISIBLE);
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
                params.put("u_id", address_id_find_edittext.getText().toString());

                //이미지 저장함

                return params;
            }
        };

        Volley.newRequestQueue(Address_find_Acitivity.this).add(request);



    }
}
