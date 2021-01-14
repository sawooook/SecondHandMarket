package com.example.pc1.store;

import android.app.Activity;
import android.app.Application;

import com.kakao.auth.KakaoSDK;

public class Globalapplication  extends Application {

    private static volatile Globalapplication instance = null;
    private static volatile Activity currentActivity = null;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        KakaoSDK.init(new KaKaoSDKAdapter());
    }

    public static Activity getCurrentActivity() {
        return currentActivity;
    }

    public static void setCurrentActivity(Activity currentActivity) {
        Globalapplication.currentActivity = currentActivity;
    }

    //    /**
//     * singleton 애플리케이션 객체를 얻는다.
//     * @return singleton 애플리케이션 객체
//     */
    public static Globalapplication getGlobalApplicationContext() {
        if(instance == null)
            throw new IllegalStateException("this application does not inherit com.kakao.GlobalApplication");
        return instance;
    }

    //    /**
//     * 애플리케이션 종료시 singleton 어플리케이션 객체 초기화한다.
//     */
//    @Override
    public void onTerminate() {
        super.onTerminate();
        instance = null;
    }
}


