package com.example.pc1.store;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.kakao.auth.ErrorCode;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeResponseCallback;
import com.kakao.usermgmt.response.model.UserProfile;
import com.kakao.util.helper.log.Logger;

import java.util.HashMap;
import java.util.Map;

public class kakaosig  extends AppCompatActivity {
    SharedPreferences.Editor editor;
    SharedPreferences login;
    String kakaoID,profileImageURL,kakaoNickname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        login = getSharedPreferences("hoho",MODE_PRIVATE);
        editor=login.edit();
        requestMe();

    }
    protected void requestMe() {
        UserManagement.requestMe(new MeResponseCallback() {
            @Override
            public void onFailure(ErrorResult errorResult) {
                String message = "failed to get user info. msg=" + errorResult;
                Logger.d(message);

                ErrorCode result = ErrorCode.valueOf(errorResult.getErrorCode());
                if (result == ErrorCode.CLIENT_ERROR_CODE) {
                    finish();
                } else {
                    redirectLoginActivity();
                }
            }

            @Override
            public void onSessionClosed(ErrorResult errorResult) {
                redirectLoginActivity();
            }

            @Override
            public void onNotSignedUp() {
            }

            @Override
            public void onSuccess(UserProfile userProfile) {
                kakaoID = String.valueOf(userProfile.getId()); // userProfile에서 ID값을 가져옴
                kakaoNickname = userProfile.getNickname();     // Nickname 값을 가져옴
                profileImageURL = userProfile.getProfileImagePath();
                Log.e("Profile : ", kakaoID + "");

                Log.e("Profile : ", kakaoNickname + "");

                Log.e("Profile : ", profileImageURL  + "");

                login();

            }
        });
    }

    protected void redirectLoginActivity() {
        final Intent intent = new Intent(this, Main_Activity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
        finish();
    }

    public void login () {
        StringRequest request = new StringRequest(Request.Method.POST, "http://18.218.175.70/signup.php", new Response.Listener<String>() {



            @Override
            public void onResponse(String response) {


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();
                params.put("username", kakaoID);
                params.put("nickname", kakaoNickname);
                params.put("image", profileImageURL);



                editor.putString("id", kakaoID);
                Log.e("kakao",kakaoID);

                editor.commit();
                Log.e("Profile1 : ", kakaoID + "");

                Log.e("Profile1 : ", kakaoNickname + "");

                Log.e("Profile1 : ", profileImageURL  + "");

                return params;
            }
        };

        Volley.newRequestQueue(kakaosig.this).add(request);
        Intent intent = new Intent(kakaosig.this,Main_Activity.class);
        startActivity(intent);

    }
}

