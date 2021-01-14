package com.example.pc1.store;

import android.app.Service;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

public class MainService extends Service {
    public class LocalBinder extends Binder {
      public MainService getService() {
            return MainService.this; //현재 서비스를 반환.
        }
    }

    private final IBinder mBinder = new LocalBinder();

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        //throw new UnsupportedOperationException("Not yet implemented");
        return mBinder;
    }

    @Override
    public boolean bindService(Intent service, ServiceConnection conn, int flags) {
        return super.bindService(service, conn, flags);
    }

    //콜백 인터페이스 선언
    public interface ICallback {
        public void recvData(); //액티비티에서 선언한 콜백 함수.
    }

    private ICallback mCallback;

    //액티비티에서 콜백 함수를 등록하기 위함.
    public void registerCallback(ICallback cb) {
        mCallback = cb;
    }

    //액티비티에서 서비스 함수를 호출하기 위한 함수 생성
    public void myServiceFunc(){
        //서비스에서 처리할 내용
    }



}
