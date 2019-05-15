package com.example.pc1.store;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public class Signup_Activity extends AppCompatActivity {
    EditText Signup_id_et, Signup_pass_et, Signup_pass2_et , nickname_et;
    Button Signup_btn ,checkbtn;
    ImageView profile_imageview;
    TextView passcheck,passcheck3;
    private WebView webView;
    private Handler handler;
    private static final int PICK_FROM_CAMERA = 0;
    private static final int PICK_FROM_ALBUM = 1;
    private static final int CROP_FROM_CAMERA = 2;
    private Uri mImageCaptureUri;
    String path;
    Uri photoUri;
    SharedPreferences.Editor editor;
    SharedPreferences login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_);

        Signup_id_et = (EditText) findViewById(R.id.id_edittext); //아이디 입력
        Signup_pass_et = (EditText) findViewById(R.id.pass_editext); //첫번째 비밀번호 입력
        Signup_pass2_et = (EditText) findViewById(R.id.pass2_edittext); //두번째 비밀번호 입력
        Signup_btn = (Button) findViewById(R.id.signup_button); //회원가입완료
        passcheck = (TextView) findViewById(R.id.check); //패스워드 체크

//        profile_imageview = (ImageView) findViewById(R.id.imageViewff);
        nickname_et=(EditText) findViewById(R.id.nickname_ed);

        nickname_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            nickname();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        Signup_id_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                idcheck();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        Signup_pass2_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String redPasscolor = "#FF0000";
                if (!Signup_pass_et.getText().toString().equals(Signup_pass2_et.getText().toString())) {
                    passcheck.setText("비밀번호 불일치");
                    passcheck.setTextColor(Color.parseColor(redPasscolor));
                } else {
                    String greenPasscolor = "#00FF00";
                    passcheck.setText("비밀번호  일치");
                    passcheck.setTextColor(Color.parseColor(greenPasscolor));

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

//        profile_imageview.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//
//                DialogInterface.OnClickListener cameraListener = new DialogInterface.OnClickListener() {
//
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.dismiss();
//                    }
//                };
//                DialogInterface.OnClickListener albumListener = new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        doTakeAlbumAction();
//                    }
//                };
//
//                DialogInterface.OnClickListener cancelListener = new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.dismiss();
//                    }
//                };
//
//                new AlertDialog.Builder(Signup_Activity.this)
//                        .setTitle("업로드할 이미지 선택")
//                        .setPositiveButton("사진촬영", cameraListener)
//                        .setNeutralButton("앨범선택", albumListener)
//                        .setNegativeButton("취소", cancelListener)
//                        .show();
//
//
//            }
//        });


        Signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                login();
                editor.putString("nickname", nickname_et.getText().toString());
                editor.commit();

                Toast.makeText(getApplicationContext(), "회원가입완료", Toast.LENGTH_SHORT).show();
                Intent signup_intent = new Intent(getApplicationContext(), Login_Activity.class);
                startActivity(signup_intent);
            }
        });

    }

    private void nickname() {

        StringRequest request = new StringRequest(Request.Method.POST, "http://52.14.144.55/nickname.php", new Response.Listener<String>() {
            //해당 mysql에 데이터를 저장함
            @Override
            public void onResponse(String response) {
                Log.e("RESULT",response);
                if (response.equals("success")) {

                    String greenPasscolor = "#00FF00";
                    //닉네임이 없습니다 사용가능
                    Log.e("RESULT","성공적으로 처리되었습니다!");
                    passcheck.setText("닉네임 사용 가능 ");
                    passcheck.setTextColor(Color.parseColor(greenPasscolor));


                } else {

                    Log.e("RESULT","성공적으로 처리아님!");

                    String redPasscolor = "#FF0000";
                    //유저아이디가없음 사용불가
                    passcheck.setText("닉네임 사용 불가능");
                    passcheck.setTextColor(Color.parseColor(redPasscolor));


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
                params.put("nickname", nickname_et.getText().toString());
                //아이디값을 넘김


                return params;
            }
        };

        Volley.newRequestQueue(Signup_Activity.this).add(request);


    }

    private void idcheck() {
        StringRequest request = new StringRequest(Request.Method.POST, "http://52.14.144.55/id.php", new Response.Listener<String>() {
            //해당 mysql에 데이터를 저장함
            @Override
            public void onResponse(String response) {
                Log.e("RESULT",response);
                if (response.equals("success")) {

                    String greenPasscolor = "#00FF00";
                    //유저 아이디가 없습니다 사용가능
                    Log.e("RESULT","성공적으로 처리되었습니다!");
                    passcheck.setText("아이디 사용 가능 ");
                    passcheck.setTextColor(Color.parseColor(greenPasscolor));


                } else {

                    Log.e("RESULT","성공적으로 처리아님!");

                    String redPasscolor = "#FF0000";
                    //유저아이디가없음 사용불가
                    passcheck.setText("아이디 사용 불가능");
                    passcheck.setTextColor(Color.parseColor(redPasscolor));


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
                params.put("u_id", Signup_id_et.getText().toString());
                //아이디값을 넘김


                return params;
            }
        };

        Volley.newRequestQueue(Signup_Activity.this).add(request);

    }


    public void login() {
        StringRequest request = new StringRequest(Request.Method.POST, "http://52.14.144.55/signup.php", new Response.Listener<String>() {
            //해당 mysql에 데이터를 저장함
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
                params.put("username", Signup_id_et.getText().toString());
                params.put("password", Signup_pass_et.getText().toString());
                params.put("image", String.valueOf(photoUri));
                params.put("nickname",nickname_et.getText().toString());
                //이미지 저장함

                return params;
            }
        };

        Volley.newRequestQueue(Signup_Activity.this).add(request);


    }

    private void doTakePhotoAction() {
        /*
         * 참고 해볼곳
         * http://2009.hfoss.org/Tutorial:Camera_and_Gallery_Demo
         * http://stackoverflow.com/questions/1050297/how-to-get-the-url-of-the-captured-image
         * http://www.damonkohler.com/2009/02/android-recipes.html
         * http://www.firstclown.us/tag/android/
         */

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        // 임시로 사용할 파일의 경로를 생성
        String url = "tmp_" + String.valueOf(System.currentTimeMillis()) + ".jpg";
        mImageCaptureUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(), url));

        intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, mImageCaptureUri);
        // 특정기기에서 사진을 저장못하는 문제가 있어 다음을 주석처리 합니다.
        //intent.putExtra("return-data", true);
        startActivityForResult(intent, PICK_FROM_CAMERA);
    }

    /**
     * 앨범에서 이미지 가져오기
     */
    private void doTakeAlbumAction() {
        // 앨범 호출
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType(android.provider.MediaStore.Images.Media.CONTENT_TYPE);
        startActivityForResult(intent, PICK_FROM_ALBUM);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK) {
            return;
        }

        switch (requestCode) {
            case CROP_FROM_CAMERA: {

//
//                if (extras != null) {
//                    photo = extras.getParcelable("data");
//                    main4_image.setImageBitmap(photo);
//                    path = MediaStore.Images.Media.insertImage(getContentResolver(), photo,null, null);
//                    uri5 = Uri.parse(path);
//
//
//                }


                final Bundle extras = data.getExtras();

                if (extras != null) {
                    Bitmap photo = extras.getParcelable("data");
                    profile_imageview.setImageBitmap(photo);
                    path = MediaStore.Images.Media.insertImage(getContentResolver(), photo, null, null);
                    photoUri = Uri.parse(path);
                }

                // 임시 파일 삭제
                File f = new File(mImageCaptureUri.getPath());
                if (f.exists()) {
                    f.delete();
                }

                break;
            }

            case PICK_FROM_ALBUM: {
                // 이후의 처리가 카메라와 같으므로 일단  break없이 진행합니다.
                // 실제 코드에서는 좀더 합리적인 방법을 선택하시기 바랍니다.

                mImageCaptureUri = data.getData();
            }

            case PICK_FROM_CAMERA: {
                // 이미지를 가져온 이후의 리사이즈할 이미지 크기를 결정합니다.
                // 이후에 이미지 크롭 어플리케이션을 호출하게 됩니다.

                Intent intent = new Intent("com.android.camera.action.CROP");
                intent.setDataAndType(mImageCaptureUri, "image/*");

                intent.putExtra("outputX", 90);
                intent.putExtra("outputY", 90);
                intent.putExtra("aspectX", 1);
                intent.putExtra("aspectY", 1);
                intent.putExtra("scale", true);
                intent.putExtra("return-data", true);
                startActivityForResult(intent, CROP_FROM_CAMERA);

                break;
            }
        }
    }


}