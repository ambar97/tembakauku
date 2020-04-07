package com.example.westo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.westo.Adapter.TablayoutBudidaya;
import com.google.android.material.tabs.TabLayout;

public class Burley extends AppCompatActivity {
TabLayout tabLayout;
TablayoutBudidaya tablayoutBudidaya;
ViewPager pager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_burley);
        setTitle("Tembakau Burley");
        Toolbar toolbar = findViewById(R.id.toolbar);
        tabLayout=findViewById(R.id.tablayout);
        pager=findViewById(R.id.viewpager);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setUpTablayout();
    }
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                super.onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }
    private void setUpTablayout(){
        tabLayout.addTab(tabLayout.newTab().setText("Budidaya"));
        tabLayout.addTab(tabLayout.newTab().setText("Pasca Panen"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
         tablayoutBudidaya = new TablayoutBudidaya(getSupportFragmentManager(), tabLayout.getTabCount());
        pager.setAdapter(tablayoutBudidaya);
        pager.setCurrentItem(0);
        pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tab.getPosition();
                tab.getText();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
