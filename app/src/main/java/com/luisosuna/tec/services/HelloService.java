package com.luisosuna.tec.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class HelloService extends Service {

    private String serviceTag = "HELLO_SERVICE";
    private Boolean isRunning = false;


    public HelloService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(serviceTag, "OnCreate");
        isRunning =  true;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(serviceTag, "onStart");

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i<5; i++){
                    try {
                        Thread.sleep(1000);
                    }catch (Exception e){
                    }

                    if(isRunning){
                        Log.i(serviceTag, "Service running");
                    }
                }

                stopSelf();
            }
        }).start();

        return Service.START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(serviceTag, "OnDestroy");
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
