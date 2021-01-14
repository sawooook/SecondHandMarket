package com.example.pc1.store;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.opengl.Matrix;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.OrientationEventListener;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.Semaphore;

import static org.opencv.imgcodecs.Imgcodecs.imread;

public class test extends AppCompatActivity implements CameraBridgeViewBase.CvCameraViewListener2 {

    private SensorManager sm;
    private Sensor s;
    private float sen;
    SensorEventListener mysensorListenr;
    private static final String TAG = "opencv";
    private CameraBridgeViewBase mOpenCvCameraView;
    private Mat matInput;
    private Mat matResult;
    int ret;
    private Bitmap opencv_bitjmap;
    private Bitmap rotate_img;

//    public native void ConvertRGBtoGray(long matAddrInput, long matAddrResult);

    public native long loadCascade(String cascadeFileName );
    public native int detect(long cascadeClassifier_face,

                             long cascadeClassifier_eye, long matAddrInput, long matAddrResult);
    public long cascadeClassifier_face = 0;
    public long cascadeClassifier_eye = 0;
    private final Semaphore writeLock = new Semaphore(1);

    public void getWriteLock() throws InterruptedException {
        writeLock.acquire();
    }

    public void releaseWriteLock() {

        writeLock.release();
    }

    static {
        System.loadLibrary("opencv_java3");
        System.loadLibrary("native-lib");
    }


    private void copyFile(String filename) {
        String baseDir = Environment.getExternalStorageDirectory().getPath();
        String pathDir = baseDir + File.separator + filename;

        AssetManager assetManager = this.getAssets();

        InputStream inputStream = null;
        OutputStream outputStream = null;

        try {
            Log.d( TAG, "copyFile :: 다음 경로로 파일복사 "+ pathDir);
            inputStream = assetManager.open(filename);
            outputStream = new FileOutputStream(pathDir);

            byte[] buffer = new byte[1024];
            int read;
            while ((read = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, read);
            }
            inputStream.close();
            inputStream = null;
            outputStream.flush();
            outputStream.close();
            outputStream = null;
        } catch (Exception e) {
            Log.d(TAG, "copyFile :: 파일 복사 중 예외 발생 "+e.toString() );
        }

    }

    private void read_cascade_file(){
        copyFile("haarcascade_frontalface_alt.xml");
        copyFile("haarcascade_eye_tree_eyeglasses.xml");

        Log.d(TAG, "read_cascade_file:");

        cascadeClassifier_face = loadCascade( "haarcascade_frontalface_alt.xml");
        Log.d(TAG, "read_cascade_file:");

        cascadeClassifier_eye = loadCascade( "haarcascade_eye_tree_eyeglasses.xml");
    }

    private BaseLoaderCallback mLoaderCallback = new BaseLoaderCallback(this) {
        @Override
        public void onManagerConnected(int status) {
            switch (status) {
                case LoaderCallbackInterface.SUCCESS:
                {
                    mOpenCvCameraView.enableView();
                } break;
                default:
                {
                    super.onManagerConnected(status);
                } break;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
                WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_test);
//        Resources r = Resources.getSystem();
//        Configuration config = r.getConfiguration();
//
//        onConfigurationChanged(config);





        sm = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        s = sm.getDefaultSensor(Sensor.TYPE_ORIENTATION);
        mysensorListenr = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                if (sensorEvent.sensor.getType() == Sensor.TYPE_ORIENTATION) {

                    sen=sensorEvent.values[0];
                    Log.e("sensens", Float.toString(sensorEvent.values[0]));
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {


            }
        };
        sm.registerListener( mysensorListenr, s,SensorManager.SENSOR_DELAY_UI);




//        if (orientEventListener.canDetectOrientation()) {
//            Toast.makeText(this, "Can DetectOrientation",
//                    Toast.LENGTH_LONG).show();
//            orientEventListener.enable();
//        } else {
//            Toast.makeText(this, "Can't DetectOrientation",
//                    Toast.LENGTH_LONG).show();
//            finish();
//        }
//




//        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
//            Log.e("previewrealplzplz","prrr");
//            this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
//        } else {
//            Log.e("previewrealplzplz","oro");
//            this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
//        }
//
//


        Button button = (Button)findViewById(R.id.button);


        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                try {
                    getWriteLock();



                    long now = System.currentTimeMillis();
                    String fileName1 = String.valueOf(now);

                    File path = new File(Environment.getExternalStorageDirectory() + "/Images/");
                    path.mkdirs();

                    File file = new File(path, fileName1+".png");

                    String filename = file.toString();
                    //해당 uri 이미지

                    Imgproc.cvtColor(matInput, matInput, Imgproc.COLOR_BGR2RGB, 4);
                    boolean ret = Imgcodecs.imwrite(filename, matInput);

//                         Rect rectCrop=new Rect(418,199,284,34);
//                         Mat subimage = new Mat(image ,rectCrop);





                    //파일명을 현재시간+.png로 저장함
                    if (ret) Log.d(TAG, "SUCESS");
                    else Log.d(TAG, "FAIL");
                    Log.e("retret", String.valueOf(ret));

//
//                         Mat image = imread(filename);
//                    Mat subImage = image();

                    Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                    mediaScanIntent.setData(Uri.fromFile(file));
                    sendBroadcast(mediaScanIntent);


//                    opencv_bitjmap= BitmapFactory.decodeFile(filename);
//
//
//                    rotate_img=rotateImage(opencv_bitjmap);

                    Intent img_next = new Intent(getApplicationContext(),Mypage_update_Activity.class);
                    img_next.putExtra("imageFile_uri",filename);
                    startActivity(img_next);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


                releaseWriteLock();

            }
        });



        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //퍼미션 상태 확인
            if (!hasPermissions(PERMISSIONS)) {

                //퍼미션 허가 안되어있다면 사용자에게 요청
                requestPermissions(PERMISSIONS, PERMISSIONS_REQUEST_CODE);
            } else  read_cascade_file();
        } else  read_cascade_file();




//        SurfaceHolder holder = getHolder();

        mOpenCvCameraView = (CameraBridgeViewBase)findViewById(R.id.activity_surface_view);
        mOpenCvCameraView.setVisibility(SurfaceView.VISIBLE);
        mOpenCvCameraView.setCvCameraViewListener(this);
//        mOpenCvCameraView.surfaceChanged(100,100,100,100);
//        mOpenCvCameraView.setCameraIndex(0); // front-camera(1),  back-camera(0)
        mOpenCvCameraView.setCameraIndex(1); // front-camera(1),  back-camera(0)
        mLoaderCallback.onManagerConnected(LoaderCallbackInterface.SUCCESS);
    }

    @Override
    public void onPause()
    {
        super.onPause();
        sm.unregisterListener(mysensorListenr);
        if (mOpenCvCameraView != null)
            mOpenCvCameraView.disableView();
    }

    @Override
    public void onResume()
    {
        super.onResume();

        if (!OpenCVLoader.initDebug()) {
            Log.d(TAG, "onResume :: Internal OpenCV library not found.");
            OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_3_2_0, this, mLoaderCallback);
        } else {
            Log.d(TAG, "onResum :: OpenCV library found inside package. Using it!");
            mLoaderCallback.onManagerConnected(LoaderCallbackInterface.SUCCESS);
        }
    }

    public void onDestroy() {
        super.onDestroy();

        if (mOpenCvCameraView != null)
            mOpenCvCameraView.disableView();
    }

    @Override
    public void onCameraViewStarted(int width, int height) {

    }

    @Override
    public void onCameraViewStopped() {

    }


    @Override
    public Mat onCameraFrame(CameraBridgeViewBase.CvCameraViewFrame inputFrame) {
        try {
            getWriteLock();

            matInput = inputFrame.rgba();

            if ( matResult != null ) matResult.release();
            matResult = new Mat(matInput.rows(), matInput.cols(), matInput.type());
//        Core.transpose(matInput, matInput);
//        ConvertRGBtoGray(matInput.getNativeObjAddr(), matResult.getNativeObjAddr());
            if(198<sen&&sen<305) {
                Core.flip(matInput,matInput,1);
                Core.rotate(matInput,matInput,0);

                ret= detect(cascadeClassifier_face,cascadeClassifier_eye, matInput.getNativeObjAddr(),matResult.getNativeObjAddr());

                Core.rotate(matResult,matResult,2);

                if ( ret != 0 )
                    Log.d(TAG, "face111111233333 " + ret + " found");

            }else {
                Core.flip(matInput,matInput,1);
//            Core.rotate(matInput,matInput,0);

                ret= detect(cascadeClassifier_face,cascadeClassifier_eye, matInput.getNativeObjAddr(),matResult.getNativeObjAddr());

//            Core.rotate(matResult,matResult,2);

                if ( ret != 0 )
                    Log.d(TAG, "face111111233333 " + ret + " found");

            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        releaseWriteLock();


        return matResult;

    }



    //여기서부턴 퍼미션 관련 메소드
    static final int PERMISSIONS_REQUEST_CODE = 1000;
    //    String[] PERMISSIONS  = {"android.permission.CAMERA"};
    String[] PERMISSIONS  = {"android.permission.CAMERA",
            "android.permission.WRITE_EXTERNAL_STORAGE"};

    private boolean hasPermissions(String[] permissions) {
        int result;

        //스트링 배열에 있는 퍼미션들의 허가 상태 여부 확인
        for (String perms : permissions){

            result = ContextCompat.checkSelfPermission(this, perms);

            if (result == PackageManager.PERMISSION_DENIED){
                //허가 안된 퍼미션 발견
                return false;
            }
        }

        //모든 퍼미션이 허가되었음
        return true;
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch(requestCode){

            case PERMISSIONS_REQUEST_CODE:
                if (grantResults.length > 0) {
                    boolean cameraPermissionAccepted = grantResults[0]
                            == PackageManager.PERMISSION_GRANTED;

//                    if (!cameraPermissionAccepted)
//                        showDialogForPermission("앱을 실행하려면 퍼미션을 허가하셔야합니다.");
                    boolean writePermissionAccepted = grantResults[1]
                            == PackageManager.PERMISSION_GRANTED;

                    if (!cameraPermissionAccepted || !writePermissionAccepted) {
                        showDialogForPermission("앱을 실행하려면 퍼미션을 허가하셔야합니다.");
                        return;
                    }else
                    {
                        read_cascade_file();
                    }
                }
                break;
        }
    }


    @TargetApi(Build.VERSION_CODES.M)
    private void showDialogForPermission(String msg) {

        AlertDialog.Builder builder = new AlertDialog.Builder( test.this);
        builder.setTitle("알림");
        builder.setMessage(msg);
        builder.setCancelable(false);
        builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id){
                requestPermissions(PERMISSIONS, PERMISSIONS_REQUEST_CODE);
            }
        });
        builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                finish();
            }
        });
        builder.create().show();
    }

//    @Override
//    public void onConfigurationChanged(Configuration newConfig) {
//        super.onConfigurationChanged(newConfig);
//
//        if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) // 세로 전환시
//        {
//            Log.e("hohoggg","1");
//            // 배경 화면 교체 처리
//        }
//        else if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE)// 가로 전환시
//        {
//            Log.e("hohoggg","2");
//            // 배경 화면 교체 처리
//        }
//    }


    public Bitmap rotateImage(Bitmap src) {

        // Matrix 객체 생성
        android.graphics.Matrix matrix = new android.graphics.Matrix();
        // 회전 각도 셋팅
        matrix.postRotate(0);
        // 이미지와 Matrix 를 셋팅해서 Bitmap 객체 생성
        return Bitmap.createBitmap(src, 0, 0, src.getWidth(),src.getHeight(), matrix, true);
    }

}