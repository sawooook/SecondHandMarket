package com.example.pc1.store;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.youtube.player.YouTubeApiServiceUtil;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;
import com.google.android.youtube.player.YouTubePlayerView;

import java.util.HashMap;
import java.util.Map;

public class Youtube_Activity extends YouTubeBaseActivity {
    YouTubePlayerView youtubeView;
    Button button;
    public static final String API_KEY = "PLKIKuXdn4ZMjuUAtdQfK1vwTZPQn_rgSv";
    public static final String VIDEO_ID = "A62pupVVnag";

    YouTubePlayer.OnInitializedListener listener;
    private static final int RECOVERY_DIALOG_REQUEST = 1;
    private String Mypage_shared_id;
    SharedPreferences login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube_);

        // Initializing YouTube player view
        FragmentManager fm = getFragmentManager();
        String tag = YouTubePlayerFragment.class.getSimpleName();
        YouTubePlayerFragment playerFragment = (YouTubePlayerFragment) fm.findFragmentByTag(tag);
        if (playerFragment == null) {
            FragmentTransaction ft = fm.beginTransaction();
            playerFragment = YouTubePlayerFragment.newInstance();
            ft.add(android.R.id.content, playerFragment, tag);
            ft.commit();
        }

        playerFragment.initialize(API_KEY, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.cueVideo(VIDEO_ID);
                youTubePlayer.setPlayerStateChangeListener(new YouTubePlayer.PlayerStateChangeListener() {
                    @Override
                    public void onLoading() {

                    }

                    @Override
                    public void onLoaded(String s) {

                    }

                    @Override
                    public void onAdStarted() {

                    }

                    @Override
                    public void onVideoStarted() {

                    }

                    @Override
                    public void onVideoEnded() {
                        youtubeEnd();


                    }

                    @Override
                    public void onError(YouTubePlayer.ErrorReason errorReason) {

                    }
                });
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                Toast.makeText(Youtube_Activity.this, "Error while initializing YouTubePlayer.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void youtubeEnd() {
        StringRequest request = new StringRequest(Request.Method.POST, "http:///52.14.144.55/SYtoken_add.php", new Response.Listener<String>() {
            //해당 mysql에 데이터를 저장함
            @Override
            public void onResponse(String response) {
//해당 데이터를 받아온다 1일시에는 로그인성공 0일시에는 로그인실패
                Log.e("RESULT",response);
                if(response.equals("time")){


//                    Log.e("로그인", String.valueOf(response.equals("1")));
                    Log.e("RESULT","성공적토큰!");
                    Toast.makeText(getApplicationContext(),"토큰신청완료",Toast.LENGTH_SHORT).show();


                }else if(response.equals("notime")){
                    Log.e("RESULT","성공적으로 처리아님!");
//                    Log.e("로그인실패", String.valueOf(response.equals("0")));
                    Toast.makeText(getApplicationContext(),"이미조회한 광고입니다",Toast.LENGTH_SHORT).show();

                }else if(response.equals("wallet")){
                    Log.e("RESULT","지갑없어요");
//                    Log.e("로그인실패", String.valueOf(response.equals("0")));
                    Toast.makeText(getApplicationContext(),"지갑을 만들어주세요",Toast.LENGTH_SHORT).show();

                }else if(response.equals("insert")){
                    Log.e("RESULT","토큰지급");
//                    Log.e("로그인실패", String.valueOf(response.equals("0")));
                    Toast.makeText(getApplicationContext(),"토큰신청완료",Toast.LENGTH_SHORT).show();

                }



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                login =getSharedPreferences("hoho", Context.MODE_PRIVATE);
                Mypage_shared_id=login.getString("id","nooo");

                Map<String, String> params = new HashMap<>();
                params.put("u_id", Mypage_shared_id.toString());
                params.put("u_eth","0.5");
                params.put("u_ad","광고");
                params.put("sy_confirm","확인");

                //이미지 저장함

                return params;
            }
        };

        Volley.newRequestQueue(Youtube_Activity.this).add(request);


    }
}
