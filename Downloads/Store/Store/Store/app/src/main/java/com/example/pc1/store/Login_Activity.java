package com.example.pc1.store;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.CookieManager;
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
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;
import com.kakao.auth.ISessionCallback;
import com.kakao.auth.Session;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.LogoutResponseCallback;
import com.kakao.util.exception.KakaoException;
import com.kakao.util.helper.log.Logger;

import com.unity3d.player.UnityPlayer;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Login_Activity extends AppCompatActivity {

    private SessionCallback callback;
    Button MainActivity_loginBtn ,Button1;
    Button textView;
    EditText MainActivity_id,MainActivity_pw;
    String data;
    SharedPreferences.Editor editor;
    SharedPreferences login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_);

        textView=(Button)findViewById(R.id.sign);
        MainActivity_loginBtn =(Button)findViewById(R.id.button);
        MainActivity_id=(EditText)findViewById(R.id.editText);
        MainActivity_pw=(EditText)findViewById(R.id.editText3);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.hide();



        login = getSharedPreferences("hoho",MODE_PRIVATE);
        editor=login.edit();






        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(Login_Activity.this, Signup_Activity.class);
                startActivity(intent);


            }
        });



        MainActivity_loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(Login_Activity.this, Main_Activity.class);
//                startActivity(intent);
                login();
//                GetData task = new GetData();
//                task.execute("http://13.58.81.149/login.php");

            }
        });
        //        try {
//            PackageInfo info = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
//            for (Signature signature : info.signatures) {
//                MessageDigest md = MessageDigest.getInstance("SHA");
//                md.update(signature.toByteArray());
//                Log.e("KeyHash:", android.util.Base64.encodeToString(md.digest(), android.util.Base64.DEFAULT));
//            }
//        } catch (PackageManager.NameNotFoundException e) {
//            e.printStackTrace();
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        }
// 카카오 해쉬키 받아오는 부분 encode 할때 kakao.utill 말고 java.utill 써야함
// 해쉬키 뒤에 = 있는지 없는지 확인 없음 잘못된거



//        callback = new SessionCallback();                  // 이 두개의 함수 중요함
//        Session.getCurrentSession().addCallback(callback);

        //카카오톡 세션 유지

    }





    private void login() {
        StringRequest request = new StringRequest(Request.Method.POST, "http://52.14.144.55/login.php", new Response.Listener<String>() {
            //해당 mysql에 데이터를 저장함
            @Override
            public void onResponse(String response) {
//해당 데이터를 받아온다 1일시에는 로그인성공 0일시에는 로그인실패
                Log.e("RESULT",response);
                if(response.equals("1")){


//                    Log.e("로그인", String.valueOf(response.equals("1")));
                    Toast.makeText(getApplicationContext(),"로그인성공",Toast.LENGTH_SHORT).show();
                    Intent SuccessIntent = new Intent(Login_Activity.this,MainActivity_test1.class);
                    startActivity(SuccessIntent);
                    Log.e("RESULT","성공적으로 처리되었습니다!");
                    editor.putString("id", MainActivity_id.getText().toString());
                    editor.putString("pw", MainActivity_pw.getText().toString());
                    editor.commit();

                }else{
                    Log.e("RESULT","성공적으로 처리아님!");
//                    Log.e("로그인실패", String.valueOf(response.equals("0")));
                    Toast.makeText(getApplicationContext(),"로그인실패",Toast.LENGTH_SHORT).show();

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
                params.put("u_id", MainActivity_id.getText().toString());
                params.put("u_pw", MainActivity_pw.getText().toString());

                //이미지 저장함

                return params;
            }
        };

        Volley.newRequestQueue(Login_Activity.this).add(request);

    }





    private void hi() {
        UserManagement.requestLogout(new LogoutResponseCallback() {

            @Override

            public void onCompleteLogout() {

                Intent intent = new Intent(Login_Activity.this, Main_Activity.class);

                startActivity(intent);

            }

        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data)) {
            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        Session.getCurrentSession().removeCallback(callback);
//    }

    private class SessionCallback implements ISessionCallback {

        @Override
        public void onSessionOpened() {
            redirectSignupActivity();  // 세션 연결성공 시 redirectSignupActivity() 호출
        }

        @Override
        public void onSessionOpenFailed(KakaoException exception) {
            if(exception != null) {
                Logger.e(exception);
            }
            setContentView(R.layout.activity_login_); // 세션 연결이 실패했을때
        }                                            // 로그인화면을 다시 불러옴
    }

    protected void redirectSignupActivity() {       //세션 연결 성공 시 SignupActivity로 넘김
        final Intent intent = new Intent(this, kakaosig.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
        finish();
    }


    private void youtubeEnd() {
        StringRequest request = new StringRequest(Request.Method.POST, "http://52.14.144.55/SYtoken_add.php", new Response.Listener<String>() {
            //해당 mysql에 데이터를 저장함
            @Override
            public void onResponse(String response) {
//해당 데이터를 받아온다 1일시에는 로그인성공 0일시에는 로그인실패
                Log.e("RESULT",response);



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

//                login =getSharedPreferences("hoho", Context.MODE_PRIVATE);
//                Mypage_shared_id=login.getString("id","nooo");
//

                Map<String, String> params = new HashMap<>();

                params.put("u_id","saouk2");
                params.put("u_eth","0.5");
                params.put("u_ad","광고");
                params.put("sy_confirm","확인");

                return params;
            }
        };

        Volley.newRequestQueue(Login_Activity.this).add(request);


    }



}





