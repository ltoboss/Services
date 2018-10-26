package com.luisosuna.tec.services;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.util.Log;

import java.net.URL;

public class HelloService extends Service {

    private String serviceTag = "HELLO_SERVICE";

    private Boolean isRunning = false;
    private String asyncTag = "ASYNC_TAG";


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

                DownloaderAsync downloaderTask = new DownloaderAsync();
                downloaderTask.execute();

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

    public class DownloaderAsync extends AsyncTask<URL, Integer, String>{

        @Override
        protected String doInBackground(URL... urls) {
            Log.i(asyncTag, "doInBackground");
            return null;
        }

        @Override
        protected void onPreExecute() {
            Log.i(asyncTag, "onPreExecute");
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            Log.i(asyncTag, "onPostExecute");
            super.onPostExecute(s);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            Log.i(asyncTag, "onProgressExecute");
            Log.i(asyncTag, values.toString());
            super.onProgressUpdate(values);
        }
    }
}
