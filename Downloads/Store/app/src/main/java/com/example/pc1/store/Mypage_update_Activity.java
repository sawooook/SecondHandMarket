package com.example.pc1.store;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

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

public class Mypage_update_Activity extends AppCompatActivity {
    private static final int PICK_FROM_CAMERA = 0;
    private static final int PICK_FROM_ALBUM = 1;
    private static final int CROP_FROM_CAMERA = 2;
    private Uri mImageCaptureUri;
    String lineEnd = "\r\n";
    String twoHyphens = "--";
    String boundary = "*****";
    Button MypageUpdate_btn;
    SharedPreferences.Editor editor;

    SharedPreferences login;
    public String urlString = "http://52.14.144.55/mypage_upate.php";
    ImageView MypageUpdate_img;
    String MypageUpdate_id_string,MypageUpdate_nickname_string,MypageUpdate_realUri,MypageUpdate_id_shared;
    private String line,idrealplz,MypageUpdate_pw_shared,MypageUpdate_nickname_shared;
    private String[] MypageUpdate_response;
    Uri photoUri;
    Intent getintent;
    ImageView mypagefragment_update;
    String num;
    static {
        System.loadLibrary("native-lib");
    }

    private Bitmap opencv_bitjmap;
    private String img_uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage_update_);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.custom_bar);
        login = getSharedPreferences("hoho",MODE_PRIVATE);
        editor=login.edit();
        MypageUpdate_img = (ImageView) findViewById(R.id.MypageUpdate_img);
        MypageUpdate_btn = (Button) findViewById(R.id.MypageUpdate_btn);
        mypagefragment_update=(ImageView)findViewById(R.id.mypagefragment_update_buttfon);

        Typeface type = Typeface.createFromAsset(getAssets(),"fonts/jeju.ttf");
        TextView saoukkkk=(TextView)findViewById(R.id.saoukkkk);

        saoukkkk.setTypeface(type);
        saoukkkk.setText("프로필수정");
        saoukkkk.setTextSize(19);

        Glide.with(getApplicationContext()).load(R.drawable.kakao_editable_profile).apply(new RequestOptions().circleCrop()).into(mypagefragment_update);
        MypageUpdate_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Mypage_update_Activity.this);
                builder.setTitle("예매하실 영화관을 선택해주세요");
                builder.setItems(new CharSequence[]
                                {"얼굴검출하기", "마스크씌우기","갤러리","취소"},
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // The 'which' argument contains the index position
                                // of the selected item
                                switch (which) {
                                    case 0:
                                        facedetect();
                                        break;
                                    case 1:
                                        mask();
                                        break;
                                    case 2:
                                        gallery();
                                        break;
                                    case 3:
                                        dialog.dismiss();
                                        break;
                                }
                            }
                        });
                builder.create().show();
            }
        });

         getintent = getIntent();
         img_uri = getintent.getStringExtra("imageFile_uri");
        Glide.with(getApplicationContext())
                .load(img_uri).apply(new RequestOptions().circleCrop())
                .into(MypageUpdate_img);

//
//        if(getintent!=null) {
//
//            String img_uri = getintent.getStringExtra("imageFile_uri");
////            opencv_bitjmap= BitmapFactory.decodeFile(img_uri);
////            Log.e("bitbit", String.valueOf(opencv_bitjmap));
//
//
////            Bitmap realomg=rotateImage(opencv_bitjmap);
//            Glide.with(getApplicationContext())
//                    .load(img_uri)
//                    .into(MypageUpdate_img);
//
//        }else{
//
//        }
//    MypageUpdate_img.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View view) {
//
//
//            DialogInterface.OnClickListener cameraListener = new DialogInterface.OnClickListener() {
//
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    doTakeCamera();
//                }
//            };
//            DialogInterface.OnClickListener albumListener = new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    doTakeAlbumAction();
//                }
//            };
//
//            DialogInterface.OnClickListener cancelListener = new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    dialog.dismiss();
//                }
//            };
//
//            new AlertDialog.Builder(Mypage_update_Activity.this)
//                    .setTitle("원하시는것을 선택해주세요")
//                    .setPositiveButton("카메라촬영", cameraListener)
//                    .setNeutralButton("갤러리", albumListener)
//                    .setNegativeButton("취소", cancelListener)
//                    .show();
//        }
//    });

    MypageUpdate_btn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            DoFileUpload(img_uri);
            Intent MypageUpdateIntent = new Intent(Mypage_update_Activity.this,Mypage_Activity.class);
            editor.putString("img",img_uri);
            editor.commit();
            startActivity(MypageUpdateIntent);
            Toast.makeText(getApplicationContext(),"제발",Toast.LENGTH_SHORT).show();

        }
    });
    }

    private void filter() {
        Intent filterintent =new Intent(getApplicationContext(),filter_Activity.class);
        filterintent.putExtra("imageFile_uri",img_uri);
        startActivity(filterintent);

    }

    private void gallery() {
        doTakeAlbumAction();
    }
    private void mask() {
        Intent iii =new Intent(getApplicationContext(),FaceActivity.class);
        startActivity(iii);

    }
    private void facedetect() {
        Intent iii =new Intent(getApplicationContext(),test.class);
        startActivity(iii);

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


                    Log.e("plzplzplzplz","ggggggggggggggggggg1");

                    final Bundle extras = data.getExtras();
                    Log.e("plzplzplzplz", String.valueOf(extras));

                    if (extras != null) {
                        Bitmap photo = extras.getParcelable("data");
                        Log.e("plzplzplzplz","ggggggggggggggggggg2");
                        fixMediaDir();
                        MypageUpdate_img.setImageBitmap(photo);
                        Log.e("plzplzplzplz", String.valueOf(photo));
                        String path = MediaStore.Images.Media.insertImage(getContentResolver(), photo, null, null);
                        photoUri = Uri.parse(path);
                        Log.e("plzplzplzplz123", String.valueOf(photoUri));
                        img_uri = getPath(photoUri);
                        Log.e("plzplzplzplz123", String.valueOf(MypageUpdate_realUri));




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

    private void DoFileUpload(String MypageUpdate_realUri) {
        Runnable runnable1 = new Mypage_update_Activity.background2();
        Thread thread = new Thread(runnable1);
        thread.start();

    }

        //이미지 절대경로를 얻는 메소드
        //일반적으로 사진에서 uri를 얻으면 완벽한주소는 나오지않는다 하지만 getpath이 메소드를 사용하면 완벽하게 주소가나온다
        // .jpg까지 나오는 메소드이다
        public String getPath(Uri contentUri) {
            String[] projection = { MediaStore.Images.Media.DATA };
            Cursor cursor = managedQuery(contentUri, projection, null, null, null);
            startManagingCursor(cursor);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        }
        private class SaveImageTask extends AsyncTask<byte[], Void, Void> {

            {


            }
            //이미지파일을 업로드 하는 메서드
            @Override
            protected Void doInBackground(byte[]... data) {
                try {
                    Socket sock = new Socket("172.20.10.12", 5001);
                    OutputStream out = sock.getOutputStream();


                    String fileLength = String.valueOf(data[0].length);
                    Log.e("ImageFile Length: ", fileLength);

                    String header = "0000000000".substring(0, 10 - fileLength.length()) + fileLength;
                    Log.e("Header", header);

                    out.write(header.getBytes());
                    out.write(data[0]);
                    Log.e("datawhat", String.valueOf(data[0]));
                    sock.close();
                    Log.e("Soket", "Running!");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;


            }
        }

        private class background2 implements Runnable {
            @Override
            public void run() {
/*
위에서받은 uri를 통하여 해당 웹서버와 통신 urlstring은 서버와 통신할 웹주소이다 이 웹주소에는 이미지 저장 코드가 들어 있음
나는 이미지를 mulitpart로 보낼건데 이걸 사용하는 이유는 데이터와 이미지를 동시에 전송할 수 있어서이다
데이터를 전송하는 이유는 채팅에 필요한 아이디값을 받아오려고 하기때문임
*/

                try {
                    FileInputStream mFileInputStream = new FileInputStream(img_uri);
                    URL connectUrl = new URL(urlString);
                    Log.d("File Up1" , "mFileInputStream is " + mFileInputStream);

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
                    String fileName1 = String.valueOf(now);
                    String mypage_filename1 = "mypage_"+fileName1;
                    MypageUpdate_id_shared=login.getString("id","nooo");
                    MypageUpdate_pw_shared=login.getString("pw","nooo");
                    MypageUpdate_nickname_shared=login.getString("nickname","nooo");

                    MypageUpdate_id_string = MypageUpdate_id_shared.toString();
                    MypageUpdate_nickname_string = MypageUpdate_nickname_shared.toString();



                    dos.writeBytes("\r\n--" + boundary + "\r\n");
                    dos.writeBytes("Content-Disposition: form-data; name=\"shared_id\"\r\n\r\n" + MypageUpdate_id_shared);
                    dos.writeBytes("\r\n--" + boundary + "\r\n");
                    //shared아이디를 보냄
                    dos.writeBytes("\r\n--" + boundary + "\r\n");
                    dos.writeBytes("Content-Disposition: form-data; name=\"shared_pw\"\r\n\r\n" + MypageUpdate_pw_shared);
                    dos.writeBytes("\r\n--" + boundary + "\r\n");
                    //shared pw를 보냄
                    dos.writeBytes("\r\n--" + boundary + "\r\n");
                    dos.writeBytes("Content-Disposition: form-data; name=\"shared_nickname\"\r\n\r\n" + MypageUpdate_nickname_shared);
                    dos.writeBytes("\r\n--" + boundary + "\r\n");
                    //shared nickname보냄
                    dos.writeBytes("\r\n--" + boundary + "\r\n");
                    dos.writeBytes("Content-Disposition: form-data; name=\"name\"\r\n\r\n" + MypageUpdate_id_string);
                    dos.writeBytes("\r\n--" + boundary + "\r\n");
                    //서버로 아이디를 보낸다

                    dos.writeBytes("\r\n--" + boundary + "\r\n");
                    dos.writeBytes("Content-Disposition: form-data; name=\"nickname\"\r\n\r\n" + MypageUpdate_nickname_string);
                    dos.writeBytes("\r\n--" + boundary + "\r\n");
// 서버로 닉네임을 보내는 코드
                    dos.writeBytes(twoHyphens + boundary + lineEnd);
                    dos.writeBytes("Content-Disposition: form-data; name=\"uploadedfile\";filename=\"" +mypage_filename1+"\"" + lineEnd);
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
                    Log.e("Test" , "File is written");
                    mFileInputStream.close();
                    dos.flush(); // finish upload...
                    //파일 업로드를 완료하는 코드 매우중요
                    // get response
                    BufferedReader rd = null;


                /*
                여기 아래서부터는 서버로부터 응답을 받아오는것
                 */
                    rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
                    while ((line = rd.readLine()) != null) {
                    idrealplz=line;
                        Log.i("Lifeclue", line);
                    MypageUpdate_response =  line.split("@");
                        editor.putString("id", MypageUpdate_response[0]);
                        editor.putString("nickname", MypageUpdate_response[1]);
                        editor.putString("img", MypageUpdate_response[2]);
                        editor.commit();
                        Log.e("what",MypageUpdate_response[0]);
                        Log.e("what",MypageUpdate_response[1]);
                        Log.e("what",MypageUpdate_response[2]);

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


//            realurlimg="http://13.58.3.24/"+filt1[1];
//            Log.e("plzplzplzplz",realurlimg);
//            Chatroom_Activity.back task;
//            task = new Chatroom_Activity.back();
//            task.execute("http://13.58.3.24/"+ filt1[1]);

                /*
               realurlimg는 해당 이미지를 불러오는 메서드 서버에 저장되었기때문에 고정 서버주소에 뒤에 나의 파일이름을 붙임
               그래서 이미지를 불러오도록함 이 주소를 통해서  이미지를 보내는 메서드 진행
                 */
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
    public Bitmap rotateImage(Bitmap src) {

        // Matrix 객체 생성
        Matrix matrix = new Matrix();
        // 회전 각도 셋팅
        matrix.postRotate(270);
        // 이미지와 Matrix 를 셋팅해서 Bitmap 객체 생성
        return Bitmap.createBitmap(src, 0, 0, src.getWidth(),src.getHeight(), matrix, true);
    }

}
