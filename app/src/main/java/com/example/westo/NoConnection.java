package com.example.westo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class NoConnection extends AppCompatActivity {
    Button coba;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noconnection);
        progressBar = findViewById(R.id.progres);
        coba = findViewById(R.id.coba);
        coba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        //setelah loading maka akan langsung berpindah ke home activity
                        Intent home = new Intent(NoConnection.this, splash.class);
                        startActivity(home);
                        finish();

                    }
                }, 1000);
            }
        });

    }
}
