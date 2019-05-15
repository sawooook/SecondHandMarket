package com.example.pc1.store;

import android.app.Activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
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
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.LogoutResponseCallback;
import com.unity3d.player.UnityPlayer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MypageFrageMent extends Fragment {
    private TextView MypageFrageMent_nickname,MypageFrageMent_id;

    public static Fragment newInstance() {
        return new MypageFrageMent();
    }
    private static final String TAG_JSON="result";
    SharedPreferences login;

    String fagmentmypage_id, fagmentmypage_name, address,idd,mJsonString,Mypage_shared_nickname,Mypage_shared_img,Mypage_shared_id;
    TextView b,c;
    private static final int PICK_FROM_CAMERA = 0;
    private static final int PICK_FROM_ALBUM = 1;
    private static final int CROP_FROM_CAMERA = 2;
    private Uri mImageCaptureUri;
    ImageView mypagefragment_img;
    Button Logout ,Chat_button,mypagefragment_update_button,Wallet_load,sell_buy_btn;
    RequestManager mGlideRequestManager;
    ArrayList<chat> chat_arrayList;
    AlertDialog.Builder ad;
    SharedPreferences.Editor editor;
    Button location,youtube_button,Minigame;

    adapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_mypage_frage_ment, container, false);
        MypageFrageMent_id = (TextView) v.findViewById(R.id.mypaget1);
        MypageFrageMent_nickname = (TextView) v.findViewById(R.id.mypaget2);
        location=(Button)v.findViewById(R.id.location);
        Button dance_btn = (Button)v.findViewById(R.id.button10);
        Minigame=(Button)v.findViewById(R.id.Minigame);
        login = getActivity().getSharedPreferences("hoho",Context.MODE_PRIVATE);
        youtube_button=(Button)v.findViewById(R.id.button12);
        mypagefragment_update_button=(Button)v.findViewById(R.id.mypagefragment_update_button);
        mypagefragment_img = (ImageView) v.findViewById(R.id.imageView2);
//        Chat_button=(Button)v.findViewById(R.id.chat_button);
        Logout =(Button)v.findViewById(R.id.Logout_btn);
        Wallet_load=(Button)v.findViewById(R.id.button11);
        Mypage_shared_id=login.getString("id","nooo");
        Mypage_shared_nickname=login.getString("nickname","nooo");
        Mypage_shared_img=login.getString("img","nooo");
        sell_buy_btn=(Button)v.findViewById(R.id.sell_buy_btn);

        ad = new AlertDialog.Builder(getActivity());

        ad.setTitle("지갑비밀번호를 입력해주세요");       // 제목 설정

        final EditText et = new EditText(getActivity());
        ad.setView(et);

        et.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

        sell_buy_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sell_buy =new Intent(getActivity(),Sell_buy_Activity.class);
                startActivity(sell_buy);


            }
        });


        youtube_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent youtube = new Intent(getActivity(),Youtube_Activity.class);
                startActivity(youtube);

            }
        });

        Minigame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            Log.e("Mypage_shared_id1",Mypage_shared_id);
                id_save();
//                UnityPlayer.UnitySendMessage("Manager","AndroidToUnity",contents);

                Intent minigamge= new Intent(getActivity(),Unity_Basketball_Acitivity.class);
                startActivity(minigamge);


//                Intent minigame = new Intent(getActivity(),UnityPlayerActivity.class);
//                minigame.putExtra("")
//                startActivity(minigame);

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


                        Intent token = new Intent(getActivity(),token_wallet_Activity.class);
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
                Intent dance = new Intent(getActivity(),UnityPlayerActivity.class);
                startActivity(dance);


            }
        });
        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ggg = new Intent(getActivity(),location_Activity.class);
                startActivity(ggg);

            }
        });
        Log.e("what",Mypage_shared_id);
        Log.e("what",Mypage_shared_nickname);
        Log.e("what",Mypage_shared_img);

//아이디와 닉네임 이미지를 보여주는곳
        MypageFrageMent_id.setText(Mypage_shared_id);
        MypageFrageMent_nickname.setText(Mypage_shared_nickname);
        Glide.with(getActivity())
                .load(Mypage_shared_img)
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

                Intent mypagefragment_intent = new Intent(getActivity(),Mypage_update_Activity.class);
                startActivity(mypagefragment_intent);

            }
        });

        login = getActivity().getSharedPreferences("hoho", Context.MODE_PRIVATE);
        editor=login.edit();


        chat_arrayList = new ArrayList<chat>();

        adapter = new adapter(getActivity(),chat_arrayList);



//





                Logout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                UserManagement.requestLogout(new LogoutResponseCallback() {

                    @Override

                    public void onCompleteLogout() {

                        Intent intent = new Intent(getActivity(), Login_Activity.class);

                        startActivity(intent);

                    }

                });

            }
        });
        return v;

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

        Volley.newRequestQueue(getActivity()).add(request);
    }


}