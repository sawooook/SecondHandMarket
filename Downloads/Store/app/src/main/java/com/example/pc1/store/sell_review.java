package com.example.pc1.store;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TabWidget;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.yongbeam.y_photopicker.util.photopicker.PhotoPickerActivity;
import com.yongbeam.y_photopicker.util.photopicker.utils.YPhotoPickerIntent;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class sell_review extends AppCompatActivity {

    Button sell_review_photo_btn,sell_review_review_finish_btn;
    EditText sell_review_et;
    RatingBar sell_review_ratingBar;
    SharedPreferences.Editor editor;
    private String lineEnd = "\r\n";
    private int COUNT;
    private String twoHyphens = "--";
    private String boundary = "*****";
    private static ArrayList<String> selectedPhotos = new ArrayList<>();
    SharedPreferences login;
    public final static int REQUEST_CODE = 1;
    private String Mypage_shared_id;
    private String review_id;
    private ImageView review_image1,review_image2,review_image3;
    Float rating_num;
    TextView ratingbar_num;
    private String urlString="http://52.14.144.55/normal_review.php";
    private String urlEncode_title;
    private BufferedReader rd;
    private String line;
    private String fragment_one_img,fragment_one_title,fragment_one_id;
    private String next_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell_review);
        Intent sell_review_intent =getIntent();
        login = getSharedPreferences("hoho",MODE_PRIVATE);

        review_image1=(ImageView)findViewById(R.id.review_image1);
        review_image2=(ImageView)findViewById(R.id.review_image2);
        review_image3=(ImageView)findViewById(R.id.review_image3);


        login = getApplicationContext().getSharedPreferences("hoho", Context.MODE_PRIVATE);
        Mypage_shared_id=login.getString("id","nooo");


        review_id=sell_review_intent.getStringExtra("review_id");
        next_image=sell_review_intent.getStringExtra("next_image");
        fragment_one_img=sell_review_intent.getStringExtra("fragment_one_img");
        fragment_one_title=sell_review_intent.getStringExtra("fragment_one_title");
        fragment_one_id=sell_review_intent.getStringExtra("fragment_one_id");



        //후기를 적을사람의 아이디를 받아옴

        ratingbar_num=(TextView)findViewById(R.id.ratingbar_num);
        sell_review_photo_btn=(Button)findViewById(R.id.sell_review_photo_btn);
        sell_review_review_finish_btn=(Button)findViewById(R.id.sell_review_review_finish_btn);
        sell_review_et=(EditText)findViewById(R.id.sell_review_et);
        sell_review_ratingBar=(RatingBar)findViewById(R.id.sell_review_ratingBar);

        sell_review_ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean b) {

                rating_num=rating;
                Log.e("ratingbar_num", String.valueOf(rating_num));

            }
        });

        sell_review_photo_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogInterface.OnClickListener cameraListener = new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        doTakeCamera();
                    }
                };
                DialogInterface.OnClickListener albumListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        doTakeAlbumAction();
                    }
                };

                DialogInterface.OnClickListener cancelListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                };

                new AlertDialog.Builder(sell_review.this)
                        .setTitle("원하시는것을 선택해주세요")
                        .setPositiveButton("카메라촬영", cameraListener)
                        .setNeutralButton("갤러리", albumListener)
                        .setNegativeButton("취소", cancelListener)
                        .show();
            }
        });

        sell_review_review_finish_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Runnable runnable1 = new sell_review.background2();
                Thread thread = new Thread(runnable1);
                thread.start();




                Intent sell_review = new Intent(getApplicationContext(),review_Activity.class);



                sell_review.putExtra(fragment_one_img,"fragment_one_img");
                sell_review.putExtra(fragment_one_title,"fragment_one_title");
                sell_review.putExtra(fragment_one_id,"fragment_one_id");
                sell_review.putExtra(next_image,"next_image");
                sell_review.putExtra(review_id,"review_id");

                Log.e("review_idfsdfsdfsd3",review_id);
                startActivity(sell_review);




                Toast.makeText(getApplicationContext(),"후기등록을 완료하였습니다",Toast.LENGTH_SHORT).show();
            }


        });




    }

    private void doTakeCamera() {

        YPhotoPickerIntent intent = new YPhotoPickerIntent(getApplicationContext());
        intent.setMaxSelectCount(3);
        intent.setShowCamera(true);
        intent.setShowGif(true);
        intent.setSelectCheckBox(false);
        intent.setMaxGrideItemCount(3);
        startActivityForResult(intent, REQUEST_CODE);
    }

    private void doTakeAlbumAction() {

        YPhotoPickerIntent intent = new YPhotoPickerIntent(getApplicationContext());
        intent.setMaxSelectCount(3);
        intent.setShowCamera(true);
        intent.setShowGif(true);
        intent.setSelectCheckBox(false);
        intent.setMaxGrideItemCount(3);
        startActivityForResult(intent, REQUEST_CODE);


    }




//    private void review_write() {
//
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
//    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        List<String> photos = null;
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
            if (data != null) {
                photos = data.getStringArrayListExtra(PhotoPickerActivity.KEY_SELECTED_PHOTOS);
                Log.e("photo", String.valueOf(photos));
                selectedPhotos.addAll(photos);


                Log.e("selectedPhotos", String.valueOf(selectedPhotos));

//                    String path = MediaStore.Images.Media.insertImage(getActivity().getContentResolver(), photos, null, null);

                if(selectedPhotos.size()==1){
                    Glide.with(getApplicationContext()).load(selectedPhotos.get(0)).into(review_image1);
                    review_image1.setVisibility(View.VISIBLE);
                }

                else if(selectedPhotos.size()==2){

                    Glide.with(getApplicationContext()).load(selectedPhotos.get(0)).into(review_image1);
                    Glide.with(getApplicationContext()).load(selectedPhotos.get(1)).into(review_image2);
                    review_image1.setVisibility(View.VISIBLE);
                    review_image2.setVisibility(View.VISIBLE);


                }else if(selectedPhotos.size()==3){
                    Glide.with(getApplicationContext()).load(selectedPhotos.get(0)).into(review_image1);
                    Glide.with(getApplicationContext()).load(selectedPhotos.get(1)).into(review_image2);
                    Glide.with(getApplicationContext()).load(selectedPhotos.get(2)).into(review_image3);
                    review_image1.setVisibility(View.VISIBLE);
                    review_image2.setVisibility(View.VISIBLE);
                    review_image3.setVisibility(View.VISIBLE);

                }



            }


        }
    }
    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        selectedPhotos.clear();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    private class background2 implements Runnable {
        @Override
        public void run() {
            try {
                URL connectUrl = new URL(urlString);



                HttpURLConnection conn = (HttpURLConnection)connectUrl.openConnection();
                conn.setDoInput(true);
                conn.setDoOutput(true);
                conn.setUseCaches(false);
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Connection", "Keep-Alive");
                conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
                DataOutputStream dos = new DataOutputStream(conn.getOutputStream()) ;
                long now = System.currentTimeMillis();
/*
나는 여기서 파일이름 현재시간으로 저장함 해당 서버에서 파일을 저장하는데 중복이됨으로 해결방안으로 현재시간을 통하여 파일이름을
붙여서 이이름으로 서버에 저장을함
 */

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

//                Log.e("response5", String.valueOf(rating_num));
//                params.put("u_")
//                    Log.e("id", id);?

                String fileName2 = String.valueOf(now);
                String hoho ="review";
                String fileName1 =hoho+fileName2;
                login = getApplicationContext().getSharedPreferences("hoho",Context.MODE_PRIVATE);
                Mypage_shared_id=login.getString("id","nooo");
//                MypageUpdate_pw_shared=login.getString("pw","nooo");
//                MypageUpdate_nickname_shared=login.getString("nickname","nooo");
//
//                urlEncode_title = URLEncoder.encode( fragment_two_title.getText().toString(),"UTF-8");
//                urlEncode = URLEncoder.encode(shared_location_tiltle.toString(),"UTF-8");
                //markerload에 저장하기위해서 나의 위치를 encode로 바꾼다
//                markerload();

//


                //내아이디를 보냄
                dos.writeBytes("\r\n--" + boundary + "\r\n");
                dos.writeBytes("Content-Disposition: form-data; name=\"review_id_to\"\r\n\r\n" + Mypage_shared_id);
                dos.writeBytes("\r\n--" + boundary + "\r\n");
                //상대방아이디를 보냄
                dos.writeBytes("\r\n--" + boundary + "\r\n");
                dos.writeBytes("Content-Disposition: form-data; name=\"review_id_from\"\r\n\r\n" + review_id.toString());
                dos.writeBytes("\r\n--" + boundary + "\r\n");

                //인증비인증을 보냄
                String urlEncode_no = URLEncoder.encode("노인증", "UTF-8");

                dos.writeBytes("\r\n--" + boundary + "\r\n");
                dos.writeBytes("Content-Disposition: form-data; name=\"review_certi\"\r\n\r\n" +urlEncode_no);
                dos.writeBytes("\r\n--" + boundary + "\r\n");

                //별의 갯수를 보냄
                dos.writeBytes("\r\n--" + boundary + "\r\n");
                dos.writeBytes("Content-Disposition: form-data; name=\"review_rating\"\r\n\r\n" + String.valueOf(rating_num));
                dos.writeBytes("\r\n--" + boundary + "\r\n");

                //내용을 보냄
                urlEncode_title = URLEncoder.encode(sell_review_et.getText().toString(),"UTF-8");
                dos.writeBytes("\r\n--" + boundary + "\r\n");
                dos.writeBytes("Content-Disposition: form-data; name=\"review_content\"\r\n\r\n" + urlEncode_title);
                dos.writeBytes("\r\n--" + boundary + "\r\n");





                for(int p=0; p<selectedPhotos.size(); p++) {

                    fixMediaDir();
                    Log.e("file", String.valueOf(selectedPhotos.size()));
                    FileInputStream mFileInputStream = new FileInputStream(selectedPhotos.get(p));
                    Log.e("File Up1", "mFileInputStream is " + mFileInputStream);

                    dos.writeBytes(twoHyphens + boundary + lineEnd);
                    dos.writeBytes("Content-Disposition: form-data; name=\"uploadedfile"+p+"\";filename=\"" + fileName1+p+ "\"" + lineEnd);
                    dos.writeBytes(lineEnd);

                    // 서버로 이미지를 보내는 코드
                    int bytesAvailable = mFileInputStream.available();
                    int maxBufferSize = 1024;
                    int bufferSize = Math.min(bytesAvailable, maxBufferSize);
                    byte[] buffer = new byte[bufferSize];
                    int bytesRead = mFileInputStream.read(buffer, 0, bufferSize);
//이건 이미지를 그냥 uri로 보내는것이아니라 bytearray로 만들어서 서버에 보내는것이다

                    Log.d("Test", "image byte is " + bytesRead);

                    // read image
                    while (bytesRead > 0) {
                        dos.write(buffer, 0, bufferSize);
                        bytesAvailable = mFileInputStream.available();
                        bufferSize = Math.min(bytesAvailable, maxBufferSize);
                        bytesRead = mFileInputStream.read(buffer, 0, bufferSize);
                        //해당 바이트어레이가 다운이 되도록 기다리는코드이다
                    }

                    dos.writeBytes(lineEnd);
                    dos.writeBytes("\r\n--" + boundary + "--\r\n");
                    // close streams
                    Log.e("Test", "File is written");
                    mFileInputStream.close();
                    dos.flush(); // finish upload...
                    //파일 업로드를 완료하는 코드 매우중요
                    // get response
                    rd = null;

                }
                /*
                여기 아래서부터는 서버로부터 응답을 받아오는것
                 */ rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
                while ((line = rd.readLine()) != null) {
//                    idrealplz=line;
                    Log.e("Lifeclue", line);
//                    MypageUpdate_response =  line.split("@");
//                    editor.putString("id", MypageUpdate_response[0]);
//                    editor.putString("nickname", MypageUpdate_response[1]);
//                    editor.putString("img", MypageUpdate_response[2]);
//                    editor.commit();
//                    Log.e("what",MypageUpdate_response[0]);
//                    Log.e("what",MypageUpdate_response[1]);
//                    Log.e("what",MypageUpdate_response[2]);

                }


                /*
               나는 서버로부터 아이디와 해당 이미지의 url을 받아왔다
               ex) lna1003@20180731 이렇게 받아와서 자른후 filt1에 데이터를 하나씩 넣어놨다
               idrealplz는 line에서 받은값
                 */

                dos.close();




            } catch (Throwable t)
            {
                Log.e("SampleJavaThread","Exception in thread.",t);
            }



        }
    }
    void fixMediaDir() {
        File sdcard = Environment.getExternalStorageDirectory();
        if (sdcard != null) {
            File mediaDir = new File(sdcard, "DCIM/Camera");
            if (!mediaDir.exists()) {
                mediaDir.mkdirs();
            }
        }
    }
}
