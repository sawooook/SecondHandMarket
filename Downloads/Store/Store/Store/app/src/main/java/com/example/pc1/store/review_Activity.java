package com.example.pc1.store;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class review_Activity extends AppCompatActivity {
    TextView review_title,review_content,review_date,review_realfinish;
    ImageView  review_image;
    RatingBar review_rating;
    reviewadapter reviewadapter;
    TextView review_Activity_name;
    ImageView review_Activity_img;
    //후기남겨진사람의 프로필을 보는곳
    Button review_write_btn;
    ListView review_Activity_listview;
    ArrayList<review> reviewActivity_item;
    private String review_id;
    private String load_review_response;
    private String review_Activity_img_img,review_Activity_id_to,review_Activity_id_from,review_Activity_certi,review_Activity_time,review_Activity_rating;
    private String review_Activity_content;
    private TextView review_amount_size,reviewActivity_id;
    private String next_image;
    private String review_Activity_img_img2,review_Activity_img_img1,review_Activity_img_img3;
    private String review1,review2,review3;
    private String fragment_one_title,fragment_one_id;
    private String fragment_one_img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.custom_bar);

        Typeface type = Typeface.createFromAsset(getAssets(),"fonts/jeju.ttf");
        TextView saoukkkk=(TextView)findViewById(R.id.saoukkkk);

        saoukkkk.setTypeface(type);
        saoukkkk.setText("리뷰");
        saoukkkk.setTextSize(19);


        reviewActivity_item = new ArrayList<review>();
        review_title=(TextView)findViewById(R.id.review_title);
        review_content=(TextView)findViewById(R.id.review_content);
        review_date=(TextView)findViewById(R.id.review_date);
        ImageView reviewActivity_img=(ImageView)findViewById(R.id.reviewActivity_img);
        review_image=(ImageView)findViewById(R.id.review_img);
        reviewActivity_id=(TextView)findViewById(R.id.reviewActivity_id);
        review_rating=(RatingBar)findViewById(R.id.review_ratingBar);
        review_Activity_listview=(ListView)findViewById(R.id.reviewActivity_list);
        Intent detail_Activity_id= getIntent();
        
        fragment_one_id=detail_Activity_id.getStringExtra("fragment_one_id");
        fragment_one_title=detail_Activity_id.getStringExtra("fragment_one_title");
        fragment_one_img=detail_Activity_id.getStringExtra("fragment_one_img");
        
        review_amount_size= (TextView)findViewById(R.id.textView25);


        review_id="saouk";
        next_image="http://52.14.144.55/mypage_1533368536866";


        review_write_btn=(Button)findViewById(R.id.review_write_btn);
        reviewadapter = new reviewadapter(getApplicationContext(),reviewActivity_item);
        review_Activity_listview.setAdapter(reviewadapter);
        RequestOptions myOptions = new RequestOptions()
                .fitCenter()
                .override(150, 150);
        Glide.with(getApplicationContext()).load(next_image).apply(myOptions).apply(new RequestOptions().circleCrop()).into(reviewActivity_img);


        reviewActivity_id.setText(review_id);

//        review_amount_size.setText(review_Activity_listview.getCount());

/////////////////////
        /*
         * 해야할일 aws현재 이번달 사용량 다했으니까 9월달 넘으면 php서버 파일만듬 그이후에 해당 파일
         * 넘겨서 불러오는작업 리스트뷰로 뿌려주기
         * 그담 어케 거래가 완료되면 후기를 남길수있도록하지?
         *
         *
         *
         * */

        load_review();
        review_write_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //리뷰작성 후기
                Intent review_write = new Intent(getApplicationContext(),sell_review.class);

                review_write.putExtra("review_id",review_id);
                review_write.putExtra("fragment_one_id",fragment_one_id);
                review_write.putExtra("fragment_one_title",fragment_one_title);
                review_write.putExtra("fragment_one_img",fragment_one_img);
                review_write.putExtra("next_image",next_image);



                startActivity(review_write);


            }
        });




    }


    private void load_review() {

        StringRequest request = new StringRequest(Request.Method.POST, "http://52.14.144.55/review_activity.php", new Response.Listener<String>() {
            //해당 mysql에 데이터를 저장함
            @Override
            public void onResponse(String response) {
//해당 데이터를 받아온다 1일시에는 로그인성공 0일시에는 로그인실패
                load_review_response=response;
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

                params.put("u_id", review_id);


//                params.put("u_")
//                    Log.e("id", id);?


                return params;
            }
        };

        Volley.newRequestQueue(getApplicationContext()).add(request);

    }

    private void threekm_result(){
        try {
            JSONObject jsonObject = new JSONObject(load_review_response);
            JSONArray jsonArray = jsonObject.getJSONArray("result");

            review_amount_size.setText(String.valueOf(jsonArray.length()));
            for(int i=0;i<jsonArray.length();i++){

                JSONObject item = jsonArray.getJSONObject(i);
                Log.e("jsonArray", String.valueOf(item));
                String img_address="http://52.14.144.55/";


                review_Activity_content = item.getString("review_content");
                review_Activity_id_from = item.getString("review_id_from");
                review_Activity_certi= item.getString("review_certi");
                review_Activity_time= item.getString("review_time");
                review_Activity_rating= item.getString("review_rating");
                Log.e("review_rating",review_Activity_rating);
                review_Activity_img_img= item.getString("review_img");



                review_Activity_img_img1= item.getString("image1");
                review1=img_address+review_Activity_img_img1;
                Log.e("revie1",review1);
                if(review_Activity_img_img1.equals("")){
                    review1="null";
                }

                review_Activity_img_img2= item.getString("image2");
                review2=img_address+review_Activity_img_img2;
                Log.e("revie2",review2);


                if(review_Activity_img_img2.equals("")){
                    review2="null";
                }


                review_Activity_img_img3 = item.getString("image3");
                review3=img_address+review_Activity_img_img3;
                Log.e("revie3",review3);
                if(review_Activity_img_img3.equals("")){
                    review3="null";
                }
                //후기남긴사람들의 이미지를 받아오는img
                Log.e("review_img",review_Activity_img_img);

                if(review_Activity_certi.equals("노인증")){
                    review_Activity_certi="";
                }else if(review_Activity_certi.equals("아아인증")){
                    review_Activity_certi="거래완료후기";
                }

                reviewActivity_item.add(new review(review_Activity_id_from,review_Activity_content,review_Activity_img_img,
                        Float.parseFloat(review_Activity_rating)
                        ,review_Activity_time,review_Activity_certi,review1,review2,review3));
//                    DescendingObj descending = new DescendingObj();
//                    Collections.sort(Mainarraylist, descending);
                reviewadapter.notifyDataSetChanged();


            }




        } catch (JSONException e) {


        }



    }

    }
///해야할일