package com.example.westo;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ViewFlipper;

public class MainActivity extends AppCompatActivity {
FloatingActionButton fab,fab1,fab2,fab3,fab4;
ViewFlipper viewFlipper;
CardView diagnosa,gejala,penyakit,hama;
private boolean isFABOpen = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        diagnosa = findViewById(R.id.diagnosa);
        gejala = findViewById(R.id.gejala);
        penyakit = findViewById(R.id.penyakit);
        hama = findViewById(R.id.hama);
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        viewFlipper = findViewById(R.id.vlipper);
        int image[] = {R.drawable.slide1,R.drawable.slide2,R.drawable.slide3,R.drawable.slide4};
        for (int images:image){
            flipper(images);
        }
//        fab = findViewById(R.id.fab);
//        fab1 = (FloatingActionButton) findViewById(R.id.fab1);
//        fab2 = (FloatingActionButton) findViewById(R.id.fab2);
//        fab3 = (FloatingActionButton) findViewById(R.id.fab3);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(!isFABOpen){
//                    showFABMenu();
//                }else{
//                    closeFABMenu();
//                }
//            }
//        });
        diagnosa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Diagnosa.class);
//                finish();
                startActivity(intent);
            }
        });
        gejala.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Data_Gejala.class);
//                finish();
                startActivity(intent);
            }
        });
        hama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Data_Hama.class);
//                finish();
                startActivity(intent);
            }
        });
        penyakit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Data_Penyakit.class);
//                finish();
                startActivity(intent);
            }
        });
    }
    public void flipper(int image){
        ImageView imageView = new ImageView(this);
        imageView.setBackgroundResource(image);
        viewFlipper.addView(imageView);
        viewFlipper.setFlipInterval(4000);
        viewFlipper.setAutoStart(true);

        viewFlipper.setInAnimation(this, android.R.anim.slide_in_left);
        viewFlipper.setOutAnimation(this,android.R.anim.slide_out_right);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

//    private void showFABMenu(){
//        isFABOpen=true;
//        fab1.animate().translationY(-getResources().getDimension(R.dimen.standard_55));
//        fab2.animate().translationY(-getResources().getDimension(R.dimen.standard_105));
//        fab3.animate().translationY(-getResources().getDimension(R.dimen.standard_155));
//    }
//
//    private void closeFABMenu(){
//        isFABOpen=false;
//        fab1.animate().translationY(0);
//        fab2.animate().translationY(0);
//        fab3.animate().translationY(0);
//    }
}
