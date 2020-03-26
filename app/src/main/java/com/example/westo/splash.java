package com.example.westo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import java.io.IOException;

public class splash extends AppCompatActivity {
    private int waktu_loading=2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        if (isOnline()){
            splash();
        }else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    //setelah loading maka akan langsung berpindah ke home activity
                    Intent home=new Intent(splash.this, Lokal.class);
                    startActivity(home);
                    finish();

                }
            },1000);
        }

    }
    public boolean isOnline(){
        System.out.println("executeCommand");
        Runtime localRuntime = Runtime.getRuntime();
        try {
            int i = localRuntime.exec("/system/bin/ping -c 1 8.8.8.8")
                    .waitFor();
            System.out.println(" mExitValue " + i);
            boolean bool = false;
            if (i == 0) {
                bool = true;
            }
            return bool;
        } catch (InterruptedException localInterruptedException) {
            localInterruptedException.printStackTrace();
            System.out.println(" Exception:" + localInterruptedException);
            return false;
        } catch (IOException localIOException) {
            localIOException.printStackTrace();
            System.out.println(" Exception:" + localIOException);
        }
        return false;

    }
    private void splash(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                //setelah loading maka akan langsung berpindah ke home activity
                Intent home=new Intent(splash.this, MainActivity.class);
                startActivity(home);
                finish();

            }
        },waktu_loading);
    }


}
