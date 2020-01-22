package com.example.westo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

class ListViewAdapter {
    Context context;
    String[] Version;
    int[] imageVersion;
    LayoutInflater inflater;

    public ListViewAdapter(Context context, String[] Version,int[] image) {
        this.context = context;
        this.Version = Version;
        this.imageVersion = image;
    }


    public int getCount() {
        return Version.length;
    }


    public Object getItem(int position) {
        return null;
    }


    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        // Declare Variables
        TextView txtversion;
        ImageView image;

        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = inflater.inflate(R.layout.listitem, parent, false);

        // Locate the TextViews in listview_item.xml
        txtversion = (TextView) itemView.findViewById(R.id.litsitem_text);
        // Locate the ImageView in listview_item.xml
        image = (ImageView) itemView.findViewById(R.id.listitem_image);
        // Capture position and set to the TextViews
        txtversion.setText(Version[position]);
        // Capture position and set to the ImageView
        image.setImageResource(imageVersion[position]);

        return itemView;
    }
}
