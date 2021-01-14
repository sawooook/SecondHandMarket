package com.example.pc1.store;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
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
import com.yongbeam.y_photopicker.util.photopicker.PhotoPagerActivity;
import com.yongbeam.y_photopicker.util.photopicker.PhotoPickerActivity;
import com.yongbeam.y_photopicker.util.photopicker.utils.YPhotoPickerIntent;
//import com.yongbeam.y_photopicker.util.photopicker.PhotoPagerActivity;
//import com.yongbeam.y_photopicker.util.photopicker.PhotoPickerActivity;
//import com.yongbeam.y_photopicker.util.photopicker.utils.YPhotoPickerIntent;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FragmentTwo extends Fragment {
    private static final int RESULT_OK = -1;
    private String MypageUpdate_realUri;
    private String urlString = "http://52.14.144.55/fragment_two.php";
    private String Mypage_shared_id;
    private String[] Fragment_two_response;
    private String line;
    private String MypageUpdate_id_shared;
    private TextView fragment_two_loacation;
    private String shared_location_tiltle;
    private String[] images;
    private double shared_location_Longtitude,shared_location_Latitude;
    String urlEncode;
    String saouk ="saou";
    viewadapter1 adapter1;
    private String urlEncode_title;
    public final static int REQUEST_CODE = 1;
    private ImageView imageView333;
    ViewPager viewPager;
    private ImageView arrow_left,arrow_rignt;
    private BufferedReader rd;

    public static Fragment newInstance() {
        return new FragmentTwo();
    }

    String lineEnd = "\r\n";
    private int COUNT;
    String twoHyphens = "--";
    String boundary = "*****";
    private static final int PICK_FROM_CAMERA = 0;
    private static final int PICK_FROM_ALBUM = 1;
    private static final int CROP_FROM_CAMERA = 2;
    private Uri mImageCaptureUri;
    String path;
    Uri photoUri;
    EditText fragment_two_title, fragment_two_money, fragment_two_content;
    ImageView fragment_two_img;
    CheckBox fragment_two_check;
    SharedPreferences.Editor editor;
    SharedPreferences login;
    int maxBufferSize;
    Button fragment_two_btn;
    boolean scheckboxs;
    LinearLayout linearLayout13;
    ImageView sell_imageView1,sell_imageView2;
    private static ArrayList<String> selectedPhotos = new ArrayList<>();
    adapter9 a;
    //아답터
    private ArrayList<String> temp2;
    //뷰페이저 string배열 변수를 담음

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_fragment_two, container, false);
        fragment_two_title = (EditText) v.findViewById(R.id.editText5);
        fragment_two_money = (EditText) v.findViewById(R.id.editText7);
        fragment_two_content = (EditText) v.findViewById(R.id.editText9);
        fragment_two_img = (ImageView) v.findViewById(R.id.imageView);
        fragment_two_check = (CheckBox) v.findViewById(R.id.checkBox);
        login = getActivity().getSharedPreferences("hoho", Context.MODE_PRIVATE);
        Mypage_shared_id = login.getString("id", "nooo");
        fragment_two_btn = (Button) v.findViewById(R.id.fragment_two_btn);
         linearLayout13= (LinearLayout)v.findViewById(R.id.linearLayout13);
        fragment_two_loacation=(TextView)v.findViewById(R.id.location_ok);





        temp2 = new ArrayList<>();
        //뷰페이저
        a = new adapter9(temp2,getActivity());
        //아답터

        //뷰페이져
        ViewPager pager = (ViewPager)v.findViewById(R.id.photos_viewpager2);
        pager.setAdapter(a);
        TabLayout tabLayout = (TabLayout)v.findViewById(R.id.tab_layout3);
        tabLayout.setupWithViewPager(pager, true);


        shared_location_tiltle=login.getString("location_markerTitle","위치인증을 해주세요");
        shared_location_Latitude=Double.parseDouble(login.getString("location_getLatitude","37.56"));
        shared_location_Longtitude=Double.parseDouble(login.getString("location_getLongtitde","126.97"));

        fragment_two_loacation.setText(shared_location_tiltle);

        fragment_two_check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if (fragment_two_check.isChecked()) {
                    scheckboxs = true;
                } else {
                    scheckboxs = false;
                }

            }
        });
        fragment_two_img.setOnClickListener(new View.OnClickListener() {
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

                new AlertDialog.Builder(getActivity())
                        .setTitle("원하시는것을 선택해주세요")
                        .setPositiveButton("카메라촬영", cameraListener)
                        .setNeutralButton("갤러리", albumListener)
                        .setNegativeButton("취소", cancelListener)
                        .show();

            }
        });

        fragment_two_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Runnable runnable1 = new FragmentTwo.background2();
                Thread thread = new Thread(runnable1);
                thread.start();

//                Log.e("hhhhh", MypageUpdate_realUri);
                Intent fragment_one_intent =new Intent(getActivity(),Main_Activity.class);
                startActivity(fragment_one_intent);
                Toast.makeText(getActivity(),"등록이 완료되었습니다",Toast.LENGTH_SHORT).show();

            }
        });
        return v;


    }

    private void doTakeCamera() {
        YPhotoPickerIntent intent = new YPhotoPickerIntent(getActivity());
        intent.setMaxSelectCount(3);
        intent.setShowCamera(true);
        intent.setShowGif(true);
        intent.setSelectCheckBox(false);
        intent.setMaxGrideItemCount(3);
        startActivityForResult(intent, REQUEST_CODE);

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

    private void doTakeAlbumAction() {

        YPhotoPickerIntent intent = new YPhotoPickerIntent(getActivity());
        intent.setMaxSelectCount(3);
        intent.setShowCamera(true);
        intent.setShowGif(true);
        intent.setSelectCheckBox(false);
        intent.setMaxGrideItemCount(3);
        startActivityForResult(intent, REQUEST_CODE);

//        Intent intent = new Intent(Intent.ACTION_PICK);
//        intent.setType(android.provider.MediaStore.Images.Media.CONTENT_TYPE);
//        startActivityForResult(intent, PICK_FROM_ALBUM);

    }


//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (resultCode != RESULT_OK) {
//            return;
//        }
//
//        switch (requestCode) {
//            case CROP_FROM_CAMERA: {
//
//
//                final Bundle extras = data.getExtras();
//
//                if (extras != null) {
//                    Bitmap photo = extras.getParcelable("data");
//                    fixMediaDir();
//                    Log.e("plzplzplzplz", String.valueOf(photo));
//                    String path = MediaStore.Images.Media.insertImage(getActivity().getContentResolver(), photo, null, null);
//                    photoUri = Uri.parse(path);
//                    fragment_two_img.setImageBitmap(photo);
//                    Log.e("plzplzplzplz", String.valueOf(photoUri));
//                    MypageUpdate_realUri = getPath(photoUri);
//                    Log.e("plzplzplzplz", String.valueOf(MypageUpdate_realUri));
//
//
//                }
//
//                // 임시 파일 삭제
//                File f = new File(mImageCaptureUri.getPath());
//                if (f.exists()) {
//                    f.delete();
//                }
//
//                break;
//            }
//
//            case PICK_FROM_ALBUM: {
//                // 이후의 처리가 카메라와 같으므로 일단  break없이 진행합니다.
//                // 실제 코드에서는 좀더 합리적인 방법을 선택하시기 바랍니다.
//
//                mImageCaptureUri = data.getData();
//            }
//
//            case PICK_FROM_CAMERA: {
//                // 이미지를 가져온 이후의 리사이즈할 이미지 크기를 결정합니다.
//                // 이후에 이미지 크롭 어플리케이션을 호출하게 됩니다.
//
//                Intent intent = new Intent("com.android.camera.action.CROP");
//                intent.setDataAndType(mImageCaptureUri, "image/*");
//
//                intent.putExtra("outputX", 90);
//                intent.putExtra("outputY", 90);
//                intent.putExtra("aspectX", 1);
//                intent.putExtra("aspectY", 1);
//                intent.putExtra("scale", true);
//                intent.putExtra("return-data", true);
//                startActivityForResult(intent, CROP_FROM_CAMERA);
//
//                break;
//            }
//        }
//    }

//    private void DoFileUpload(ArrayList<String> MypageUpdate_realUri) {
//        Runnable runnable1 = new FragmentTwo.background2();
//        Thread thread = new Thread(runnable1);
//        thread.start();
//
//    }

    //이미지 절대경로를 얻는 메소드
    //일반적으로 사진에서 uri를 얻으면 완벽한주소는 나오지않는다 하지만 getpath이 메소드를 사용하면 완벽하게 주소가나온다
    // .jpg까지 나오는 메소드이다
    public String getPath(Uri contentUri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = getActivity().managedQuery(contentUri, projection, null, null, null);
        getActivity().startManagingCursor(cursor);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }

//    private class SaveImageTask extends AsyncTask<byte[], Void, Void> {
//
//        {
//
//
//        }

    //이미지파일을 업로드 하는 메서드
//        @Override
//        protected Void doInBackground(byte[]... data) {
//            try {
//                Socket sock = new Socket("172.20.10.12", 5001);
//                OutputStream out = sock.getOutputStream();
//
//
//                String fileLength = String.valueOf(data[0].length);
//                Log.e("ImageFile Length: ", fileLength);
//
//                String header = "0000000000".substring(0, 10 - fileLength.length()) + fileLength;
//                Log.e("Header", header);
//
//                out.write(header.getBytes());
//                out.write(data[0]);
//                Log.e("datawhat", String.valueOf(data[0]));
//                sock.close();
//                Log.e("Soket", "Running!");
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            return null;
//
//
//        }
//    }

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
                String fileName2 = String.valueOf(now);
                String hoho ="hoho";
                String fileName1 =hoho+fileName2;
                login = getActivity().getSharedPreferences("hoho",Context.MODE_PRIVATE);
                MypageUpdate_id_shared=login.getString("id","nooo");
//                MypageUpdate_pw_shared=login.getString("pw","nooo");
//                MypageUpdate_nickname_shared=login.getString("nickname","nooo");
//
                urlEncode_title = URLEncoder.encode( fragment_two_title.getText().toString(),"UTF-8");
                urlEncode = URLEncoder.encode(shared_location_tiltle.toString(),"UTF-8");
                //markerload에 저장하기위해서 나의 위치를 encode로 바꾼다
//                markerload();

//
                dos.writeBytes("\r\n--" + boundary + "\r\n");
                dos.writeBytes("Content-Disposition: form-data; name=\"fragment_two_title\"\r\n\r\n" + urlEncode_title);
                dos.writeBytes("\r\n--" + boundary + "\r\n");
                //shared아이디를 보냄
                dos.writeBytes("\r\n--" + boundary + "\r\n");
                dos.writeBytes("Content-Disposition: form-data; name=\"fragment_two_money\"\r\n\r\n" + fragment_two_money.getText().toString());
                dos.writeBytes("\r\n--" + boundary + "\r\n");
                //shared pw를 보냄
                String urlEncode_content = URLEncoder.encode( fragment_two_content.getText().toString(),"UTF-8");
                dos.writeBytes("\r\n--" + boundary + "\r\n");
                dos.writeBytes("Content-Disposition: form-data; name=\"fragment_two_content\"\r\n\r\n" + urlEncode_content);
                dos.writeBytes("\r\n--" + boundary + "\r\n");
                //shared nickname보냄
                dos.writeBytes("\r\n--" + boundary + "\r\n");
                dos.writeBytes("Content-Disposition: form-data; name=\"fragment_two_id\"\r\n\r\n" + MypageUpdate_id_shared.toString());
                dos.writeBytes("\r\n--" + boundary + "\r\n");
                Log.e("test", String.valueOf(MypageUpdate_id_shared.toString()));
                //서버로 아이디를 보낸다
                dos.writeBytes("\r\n--" + boundary + "\r\n");
                dos.writeBytes("Content-Disposition: form-data; name=\"fragment_two_check\"\r\n\r\n" + scheckboxs);
                Log.e("test", String.valueOf(scheckboxs));



                dos.writeBytes("\r\n--" + boundary + "\r\n");
                //서버로 체크값을보냄
                dos.writeBytes("\r\n--" + boundary + "\r\n");
                dos.writeBytes("Content-Disposition: form-data; name=\"fragment_two_location\"\r\n\r\n" + urlEncode);
                dos.writeBytes("\r\n--" + boundary + "\r\n");
                //서버로 나의 판매위치를 보낸다


                dos.writeBytes("\r\n--" + boundary + "\r\n");
                dos.writeBytes("Content-Disposition: form-data; name=\"fragment_two_location_Longtitude\"\r\n\r\n" +String.valueOf(shared_location_Longtitude));
                dos.writeBytes("\r\n--" + boundary + "\r\n");
                //서버로 나의 판매위치위도 보낸다

                dos.writeBytes("\r\n--" + boundary + "\r\n");
                dos.writeBytes("Content-Disposition: form-data; name=\"fragment_two_location_Latitude\"\r\n\r\n" +String.valueOf(shared_location_Latitude));
                dos.writeBytes("\r\n--" + boundary + "\r\n");
                //서버로 나의 판매위치경도 보낸다



                for(int p=0; p<selectedPhotos.size(); p++) {

                    fixMediaDir();
                    Log.e("file", String.valueOf(selectedPhotos.size()));
                    FileInputStream mFileInputStream = new FileInputStream(selectedPhotos.get(p));
                    Log.e("File Up1", "mFileInputStream is " + mFileInputStream);


                    dos.writeBytes(twoHyphens + boundary + lineEnd);
                    dos.writeBytes("Content-Disposition: form-data; name=\"uploadedfile"+p+"\";filename=\"" + fileName1+p+ "\"" + lineEnd);
                    Log.e("saoukkkkkkkkkkkkkkkkkk", saouk + p);
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
                 */
                    rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
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

    private void markerload() {
        StringRequest request = new StringRequest(Request.Method.POST, "http://52.14.144.55/markersave.php", new Response.Listener<String>() {
            //해당 mysql에 데이터를 저장함
            @Override
            public void onResponse(String response) {

                if(response.equals("saouk")){
                    Log.e("saouk","success");
                }else{

                    Log.e("saouk","fail");
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
                params.put("location",urlEncode);
                //한글로나와있는 나의 위치를 저장
                params.put("location_Latitude",  String.valueOf(shared_location_Latitude));
                params.put("location_Longtitude",  String.valueOf(shared_location_Longtitude));
                params.put("marker_title",urlEncode_title);

                //db에 마커를 저장




                return params;
            }
        };

        Volley.newRequestQueue(getActivity()).add(request);





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
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        List<String> photos = null;
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
            if (data != null) {
                photos = data.getStringArrayListExtra(PhotoPickerActivity.KEY_SELECTED_PHOTOS);
                Log.e("photo", String.valueOf(photos));
                selectedPhotos.addAll(photos);
                fragment_two_img.setVisibility(View.GONE);
                linearLayout13.setVisibility(View.VISIBLE);

                Log.e("selectedPhotos", String.valueOf(selectedPhotos));

//                    String path = MediaStore.Images.Media.insertImage(getActivity().getContentResolver(), photos, null, null);

                if(selectedPhotos.size()==2){
                    temp2.add(selectedPhotos.get(0));
                    temp2.add(selectedPhotos.get(1));

                }else if(selectedPhotos.size()==3){

                    temp2.add(selectedPhotos.get(0));
                    temp2.add(selectedPhotos.get(1));
                    temp2.add(selectedPhotos.get(2));

                }
                a.notifyDataSetChanged();


            }


        }
    }
    @Override
    public void onPause() {
        Log.e("onPause", "onPause()");
        super.onPause();
    }

    @Override
    public void onStop() {
        Log.e("onStop", "onStop()");
        super.onStop();
        selectedPhotos.clear();

    }

    @Override
    public void onDestroyView() {
        Log.e("onDestroyView", "onDestroyView()");
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        Log.e("onDestroy", "onDestroy()");
        super.onDestroy();
    }


}