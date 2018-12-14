package com.example.n01193770.smarthealthtracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class Launcher extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
    Thread myThread = new Thread(){
        @Override
        public void run() {

            try {
                sleep(2000);
                Intent intent =new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }

        }
    };
        myThread.start();
    }

}
