package com.example.pc1.store;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class filter_Activity extends AppCompatActivity {
    String opncv_img_uri;
    Intent opencv_intent;
    Bitmap opencv_bitjmap;
    Bitmap rotate_img;
    ImageView opencv_img;
    Button filter_update,opencv_black_btn,opencv_black_color,opencv_black_gray;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_);

//        uri_img
        opencv_img=(ImageView)findViewById(R.id.opencv_img);
        opencv_intent = getIntent();
        Button btn = (Button)findViewById(R.id.button9);
        opncv_img_uri=opencv_intent.getStringExtra("imageFile_uri");
        //이미지파일을 받음
        Log.e("imageFile_uri",opncv_img_uri);
        opencv_black_btn=(Button)findViewById(R.id.button4);
        opencv_black_color=(Button)findViewById(R.id.button7);
        opencv_black_gray=(Button)findViewById(R.id.button8);
        filter_update=(Button)findViewById(R.id.filter_update);

        //해당이미지의 uri를 가져와서 비트맵으로 바꿔준후 이미지를 띄어준다
        opencv_bitjmap= getBitmapFromURL(opncv_img_uri);
//
//
////        opencv_img.setImageBitmap(opencv_bitjmap);
//        rotate_img=rotateImage(opencv_bitjmap);
        //비트맵으로 받아온 이미지를 90도로 회전시킨다

//      opencv_img.setImageURI(Uri.parse("/storage/emulated/0/Images/1534416279591.png"));
        Glide.with(getApplicationContext()).load(opencv_bitjmap).apply(new RequestOptions().circleCrop()).into(opencv_img);
        //그후에 이미지를 이미지뷰에 띄어준다

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Glide.with(getApplicationContext()).load(opencv_bitjmap).apply(new RequestOptions().circleCrop()).into(opencv_img);

            }
        });
        opencv_black_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bitmap sepia= sepia(opencv_bitjmap);
                //이미지를 비트맵에 넣어준다
                Glide.with(getApplicationContext()).load(sepia).apply(new RequestOptions().circleCrop()).into(opencv_img);





            }
        });
        opencv_black_gray.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //해당 로테이션 이미지를 회색으로 만들어준다
                Bitmap grayopencv= toGrayscale(opencv_bitjmap);
                //이미지를 비트맵에 넣어준다
                Glide.with(getApplicationContext()).load(grayopencv).apply(new RequestOptions().circleCrop()).into(opencv_img);


            }
        });

        opencv_black_color.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //해당 로테이션 이미지를 회색으로 만들어준다
//                Bitmap grayopencv= toGrayscale(rotate_img);
//                이미지를 비트맵에 넣어준다
                Bitmap gremm=tosepia(opencv_bitjmap);
                Glide.with(getApplicationContext()).load(gremm).apply(new RequestOptions().circleCrop()).into(opencv_img);




            }
        });

        filter_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent filter_update =new Intent(getApplicationContext(),Mypage_update_Activity.class);
//                filter_update.putExtra("imageFile_uri",opencv_img);
                startActivity(filter_update);

            }
        });




    }
    public Bitmap toGrayscale(Bitmap bmpOriginal)
    {
        int width, height;
        height = bmpOriginal.getHeight();
        width = bmpOriginal.getWidth();

        Bitmap bmpGrayscale = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bmpGrayscale);
        Paint paint = new Paint();
        ColorMatrix cm = new ColorMatrix();
        cm.setSaturation(0);
        ColorMatrixColorFilter f = new ColorMatrixColorFilter(cm);
        paint.setColorFilter(f);
        c.drawBitmap(bmpOriginal, 0, 0, paint);
        return bmpGrayscale;
    }

    public Bitmap tosepia(Bitmap bmpOriginal)
    {
        int width, height;
        height = bmpOriginal.getHeight();
        width = bmpOriginal.getWidth();

        Bitmap tosepia12 = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(tosepia12);
        Paint paint = new Paint();
//        ColorMatrix cm = new ColorMatrix();
//        cm.setSaturation(0);
//        ColorMatrixColorFilter f = new ColorMatrixColorFilter(cm);
        paint.setColorFilter(new ColorMatrixColorFilter(
                getColorMatrix()));
        c.drawBitmap(bmpOriginal, 0, 0, paint);
        return tosepia12;

    }


    public Bitmap sepia(Bitmap bmpOriginal)
    {
        int width, height;
        height = bmpOriginal.getHeight();
        width = bmpOriginal.getWidth();

        Bitmap sepia1 = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(sepia1);
        Paint paint = new Paint();
//        ColorMatrix cm = new ColorMatrix();
//        cm.setSaturation(0);
//        ColorMatrixColorFilter f = new ColorMatrixColorFilter(cm);
        paint.setColorFilter(new ColorMatrixColorFilter(
                getColorMatrix3()));
        c.drawBitmap(bmpOriginal, 0, 0, paint);
        return sepia1;

    }

    private ColorMatrix getColorMatrix3() {
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.setSaturation(0);

        ColorMatrix colorScale = new ColorMatrix();
        colorScale.setScale(1, 1, 0.8f, 1);

        // Convert to grayscale, then apply brown color
        colorMatrix.postConcat(colorScale);

        return colorMatrix;
    }


    private ColorMatrix getColorMatrix() {
        return new ColorMatrix(new float[] {
                -1,  0,  0,  0, 255,
                0, -1,  0,  0, 255,
                0,  0, -1,  0, 255,
                0,  0,  0,  1,   0
        });
    }
    // 이미지

    // 이미지 회전 함수
    public Bitmap rotateImage(Bitmap src) {

        // Matrix 객체 생성
        Matrix matrix = new Matrix();
        // 회전 각도 셋팅
        matrix.postRotate(0);
        // 이미지와 Matrix 를 셋팅해서 Bitmap 객체 생성
        return Bitmap.createBitmap(src, 0, 0, src.getWidth(),src.getHeight(), matrix, true);
    }

    public Bitmap getBitmapFromURL(String src) {
        HttpURLConnection connection = null;
        try {
            URL url = new URL(src);
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true); connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }finally{
            if(connection!=null)connection.disconnect();
        }
    }



}
