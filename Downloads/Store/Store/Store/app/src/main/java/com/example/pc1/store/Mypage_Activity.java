package com.example.pc1.store;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.LogoutResponseCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Mypage_Activity extends AppCompatActivity  {

    SharedPreferences login;
    String fagmentmypage_id, fagmentmypage_name, address,idd,mJsonString,Mypage_shared_nickname,Mypage_shared_img,Mypage_shared_id;
    TextView b,c;
    private static final int PICK_FROM_CAMERA = 0;
    private static final int PICK_FROM_ALBUM = 1;
    private static final int CROP_FROM_CAMERA = 2;
    private Uri mImageCaptureUri;
    ImageView mypagefragment_img,mypagefragment_update_button;
    Button  Chat_button,sell_buy_btn;
    RequestManager mGlideRequestManager;
    LinearLayout Logout;
    ArrayList<chat> chat_arrayList;
    LinearLayout Wallet_load;
    AlertDialog.Builder ad;
    SharedPreferences.Editor editor;
    LinearLayout youtube_button,Minigame,News_button;
    LinearLayout location;

    private TextView MypageFrageMent_nickname,MypageFrageMent_id;
    private TextView mypage_location;
    private String shared_location_tiltle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage_);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.custom_bar);
        LinearLayout Main_Mypage_Home=(LinearLayout)findViewById(R.id.Main_Mypage_Home);
        LinearLayout Main_Mypage_add=(LinearLayout)findViewById(R.id.Main_Mypage_add);
        LinearLayout Main_Mypage_Chat=(LinearLayout)findViewById(R.id.Main_Mypage_Chat);
        MypageFrageMent_id = (TextView) findViewById(R.id.mypaget1);
        MypageFrageMent_nickname = (TextView) findViewById(R.id.mypaget2);
        News_button=(LinearLayout)findViewById(R.id.News);
        location=(LinearLayout)findViewById(R.id.location);
        LinearLayout dance_btn = (LinearLayout)findViewById(R.id.button10);
        Minigame=(LinearLayout)findViewById(R.id.Minigame);
        mypage_location=(TextView)findViewById(R.id.mypage_location);
        login = getSharedPreferences("hoho", Context.MODE_PRIVATE);
        youtube_button=(LinearLayout)findViewById(R.id.button12);
        mypagefragment_update_button=(ImageView)findViewById(R.id.mypagefragment_update_button);
        mypagefragment_img = (ImageView) findViewById(R.id.imageView2);
//        Chat_button=(Button)v.findViewById(R.id.chat_button);
        TextView settings =(TextView)findViewById(R.id.textView545);
        Logout =(LinearLayout) findViewById(R.id.Logout_btn);
        Wallet_load=(LinearLayout)findViewById(R.id.button11);
        Mypage_shared_id=login.getString("id","nooo");
        Mypage_shared_nickname=login.getString("nickname","nooo");
        Mypage_shared_img=login.getString("img","nooo");
//        sell_buy_btn=(Button)findViewById(R.id.sell_buy_btn);
        Glide.with(getApplicationContext()).load(R.drawable.kakao_editable_profile).apply(new RequestOptions().circleCrop()).into(mypagefragment_update_button);

        Typeface type = Typeface.createFromAsset(getAssets(),"fonts/jeju.ttf");
        TextView saoukkkk=(TextView)findViewById(R.id.saoukkkk);
        shared_location_tiltle=login.getString("location_markerTitle","위치인증을 해주세요");
        saoukkkk.setTypeface(type);
        saoukkkk.setText("마이페이지");
        saoukkkk.setTextSize(19);
        mypage_location.setText(shared_location_tiltle);

        Main_Mypage_Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Main_Mypage_Home_intent = new Intent(getApplicationContext(),MainActivity_test1.class);
                startActivity(Main_Mypage_Home_intent);
                finish();


            }
        });

        Main_Mypage_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Main_Mypage_add = new Intent(getApplicationContext(),Add_Main_Activity.class);
                startActivity(Main_Mypage_add);
                finish();

            }
        });


        Main_Mypage_Chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Main_Mypage_Chat_intent =new Intent(getApplicationContext(),Chat_Main_Activity.class);
                startActivity(Main_Mypage_Chat_intent);
                finish();

            }
        });

        ad = new AlertDialog.Builder(Mypage_Activity.this);

        ad.setTitle("지갑비밀번호를 입력해주세요");       // 제목 설정

        final EditText et = new EditText(Mypage_Activity.this);
        ad.setView(et);

        et.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

//        sell_buy_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent sell_buy =new Intent(getApplicationContext(),Sell_buy_Activity.class);
//                startActivity(sell_buy);
//
//
//            }
//        });


        youtube_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent youtube = new Intent(getApplicationContext(),Youtube_Activity.class);
                startActivity(youtube);

            }
        });

        Minigame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.e("Mypage_shared_id1",Mypage_shared_id);
                id_save();
//                UnityPlayer.UnitySendMessage("Manager","AndroidToUnity",contents);

                Intent minigamge= new Intent(getApplicationContext(),Unity_Basketball_Acitivity.class);
                startActivity(minigamge);


//                Intent minigame = new Intent(getActivity(),UnityPlayerActivity.class);
//                minigame.putExtra("")
//                startActivity(minigame);

            }
        });


        News_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent news_Intent = new Intent(getApplicationContext(),Crowling_Activity.class);
                startActivity(news_Intent);


            }
        });

        Wallet_load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ad.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.v("dialog", "Yes Btn Click");

                        // Text 값 받아서 로그 남기기
                        String value = et.getText().toString();
                        Log.v("dialog", value);


                        Intent token = new Intent(getApplicationContext(),token_wallet_Activity.class);
                        token.putExtra("tokennumber",value);
                        startActivity(token);
                        dialog.dismiss();     //닫기
                        // Event
                    }
                });



// 취소 버튼 설정
                ad.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.v("dialog","No Btn Click");
                        dialog.dismiss();     //닫기
                        // Event
                    }
                });

// 창 띄우기
                ad.show();




            }
        });
        dance_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent dance = new Intent(getApplicationContext(),UnityPlayerActivity.class);
                startActivity(dance);


            }
        });
        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ggg = new Intent(getApplicationContext(),location_Activity.class);
                startActivity(ggg);

            }
        });
        Log.e("what",Mypage_shared_id);
        Log.e("what",Mypage_shared_nickname);
        Log.e("what",Mypage_shared_img);

//아이디와 닉네임 이미지를 보여주는곳
        MypageFrageMent_id.setText(Mypage_shared_id);
        MypageFrageMent_nickname.setText(Mypage_shared_nickname);
        Glide.with(getApplicationContext())
                .load(Mypage_shared_img)
                .apply(new RequestOptions().circleCrop())
                .into(mypagefragment_img);

//        testbtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent iii =new Intent(getActivity(),FaceActivity.class);
//                startActivity(iii);
//            }
//        });


        mypagefragment_update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent mypagefragment_intent = new Intent(getApplicationContext(),Mypage_update_Activity.class);
                mypagefragment_intent.putExtra("imageFile_uri",Mypage_shared_img);
                startActivity(mypagefragment_intent);

            }
        });

        login = getSharedPreferences("hoho", Context.MODE_PRIVATE);
        editor=login.edit();


        chat_arrayList = new ArrayList<chat>();




//





        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                UserManagement.requestLogout(new LogoutResponseCallback() {

                    @Override

                    public void onCompleteLogout() {

                        Intent intent = new Intent(getApplicationContext(), Login_Activity.class);
                        startActivity(intent);


                    }

                });

            }
        });

    }

    private void id_save() {


        StringRequest request = new StringRequest(Request.Method.POST, "http://52.14.144.55/id.php", new Response.Listener<String>() {
            //해당 mysql에 데이터를 저장함
            @Override
            public void onResponse(String response) {
//해당 데이터를 받아온다 1일시에는 로그인성공 0일시에는 로그인실패
                Log.e("RESULT",response);
                if(response.equals("1")){

                    Log.e("RESULT","성공적으로 처리되었습니다!");


                }else{
                    Log.e("RESULT","성공적으로 처리아님!");


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
                params.put("u_id", Mypage_shared_id.toString());

                Log.e("Mypage_shared_id2",Mypage_shared_id);
                //이미지 저장함

                return params;
            }
        };

        Volley.newRequestQueue(getApplicationContext()).add(request);
    }






}
