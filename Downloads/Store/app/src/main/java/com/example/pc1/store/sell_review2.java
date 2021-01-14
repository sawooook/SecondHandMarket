package com.example.pc1.store;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
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

public class sell_review2 extends AppCompatActivity {


    Button sell_review_photo_btn2,sell_review_review_finish_btn2;
    EditText sell_review_et2;
    RatingBar sell_review_ratingBar2;
    SharedPreferences.Editor editor;
    SharedPreferences login;
    private String Mypage_shared_id;
    private String review_id;
    Float rating_num2;
    TextView ratingbar_num2;
    private String sell_review2_pos,sell_review2_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell_review2);

        Intent sell_review_intent2 =getIntent();
        login = getSharedPreferences("hoho",MODE_PRIVATE);

        login = getApplicationContext().getSharedPreferences("hoho", Context.MODE_PRIVATE);
        Mypage_shared_id=login.getString("id","nooo");
        review_id=sell_review_intent2.getStringExtra("review_id");

        //방값
        sell_review2_pos=sell_review_intent2.getStringExtra("pos");
        //제품아이디값
        sell_review2_id=sell_review_intent2.getStringExtra("getproduct_index_id");


        ratingbar_num2=(TextView)findViewById(R.id.ratingbar_num2);
        sell_review_photo_btn2=(Button)findViewById(R.id.sell_review_photo_btn2);
        sell_review_review_finish_btn2=(Button)findViewById(R.id.sell_review_review_finish_btn2);
        sell_review_et2=(EditText)findViewById(R.id.sell_review_et2);
        sell_review_ratingBar2=(RatingBar)findViewById(R.id.sell_review_ratingBar2);

        sell_review_review_finish_btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



//                review_write2();
                //리뷰 서버접속
                Intent sell_review = new Intent(getApplicationContext(),Chatroom_Activity.class);
                sell_review.putExtra("pos",sell_review2_pos);
                sell_review.putExtra("getproduct_index_id",sell_review2_id);

                startActivity(sell_review);




                Toast.makeText(getApplicationContext(),"후기등록을 완료하였습니다",Toast.LENGTH_SHORT).show();
            }
        });

    }

//    private void review_write2() {
//        StringRequest request = new StringRequest(Request.Method.POST, "http://52.14.144.55/normal_review.php", new Response.Listener<String>() {
//            //해당 mysql에 데이터를 저장함
//            @Override
//            public void onResponse(String response) {
//                Log.e("response",response);
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
//                params.put("review_id_to", review_id);
//                //상대방아이디
//                Log.e("response2",review_id);
//                params.put("review_id_from", Mypage_shared_id);
//                Log.e("response3",Mypage_shared_id);
//                //내아이디
//                params.put("review_certi", "노인증");
//                //일반후기니까 마크가 없어야함
//                params.put("review_rating", String.valueOf(rating_num));
//                params.put("review_content", sell_review_et.getText().toString());
//
//                Log.e("response5", String.valueOf(rating_num));
////                params.put("u_")
////                    Log.e("id", id);?
//
//
//                return params;
//            }
//        };
//
//        Volley.newRequestQueue(getApplicationContext()).add(request);
//
//
//    }
}
