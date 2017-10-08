package com.example.root.creatingalarmusingservices;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

import java.util.Random;

/**
 * Created by root on 10/4/17.
 */

public class HelloService extends Service {

    private final IBinder mBinder = new LocalBinder();
    private final Random mGenerator = new Random();
    boolean mAllowRebind;

    @Override
    public void onCreate() {
        Toast.makeText(this, "Service is created", Toast.LENGTH_LONG).show();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "Service is started", Toast.LENGTH_LONG).show();

        /*
        for (int i = 0; i < 100; i++) {
            Toast.makeText(getApplicationContext(), "Counting: " + i, Toast.LENGTH_LONG).show();
            try {
                Thread.sleep(1000);
            }catch (Exception ex){
                Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
            }
        }
        */
        return START_STICKY;
    }



    @Override
    public IBinder onBind(Intent intent) {
        Toast.makeText(this, "OnBind", Toast.LENGTH_LONG).show();
        return mBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Toast.makeText(this, "OnUnBind", Toast.LENGTH_LONG).show();
        return mAllowRebind;
    }

    @Override
    public void onRebind(Intent intent) {
        Toast.makeText(this, "OnReBind", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Service is destroyed", Toast.LENGTH_LONG).show();
    }

    public class LocalBinder extends Binder{
        HelloService getService(){
            return HelloService.this;
        }
    }

    public int getRandomNumber(){
        return mGenerator.nextInt(100);
    }

}
