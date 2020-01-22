package com.example.westo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.westo.Adapter.ListViewAdapterHama;

public class Data_Hama extends AppCompatActivity {
    ListView list;
    ListViewAdapter adapter;
    String[] Version;
    int[] image;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the view from listview_main.xml
        setContentView(R.layout.activity_data__hama);

        // Generate sample data into string arrays
        Version = new String[]{"C U P C A K E", "D O N U T", "E C L A I R", "G I N G E R B R E A D", "H O N E Y C O M B", "I C E C R E A M S A N D W I C H", "J E L L Y B E A N", "K I T K A T", "L O L L I P O P", "M A R S H M A L L O W", "N O U G A T", "P"};

        image = new int[]{R.mipmap.ic_launcher, R.mipmap.ic_launcher,
                R.mipmap.ic_launcher, R.mipmap.ic_launcher,
                R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher,
                R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher};

        // Locate the ListView in listview_main.xml
        list = (ListView) findViewById(R.id.listview);

        // Pass results to ListViewAdapter Class
        adapter = new ListViewAdapter(this,Version,image);
        // Binds the Adapter to the ListView
//        list.setAdapter(adapter);
        // Capture ListView item click
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(getApplicationContext(), "You have selected :" + Version[position], Toast.LENGTH_SHORT).show();

            }

        });
    }
}
