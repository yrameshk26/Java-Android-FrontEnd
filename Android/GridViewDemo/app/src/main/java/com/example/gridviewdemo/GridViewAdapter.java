package com.example.gridviewdemo;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;


public class GridViewAdapter extends BaseAdapter {
    private Context context;

    public GridViewAdapter(Context context){
        this.context = context;
    }
    private int[] icons = {
            android.R.drawable.btn_star_big_off,
            android.R.drawable.btn_star_big_on,
            android.R.drawable.alert_light_frame,
            android.R.drawable.alert_dark_frame,
            android.R.drawable.arrow_down_float,
            android.R.drawable.gallery_thumb,
            android.R.drawable.ic_dialog_map,
            android.R.drawable.ic_popup_disk_full,
            android.R.drawable.star_big_on,
            android.R.drawable.star_big_off,
            android.R.drawable.arrow_up_float
    };

    public int getCount(){
        return icons.length;
    }

    public Object getItem(int position){
        return null;
    }

    public long getItemId(int position){
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        //the View itself is an object
        //the ViewGroup collects the individual views as a whole
        ImageView imageView;
        if(convertView == null){
            imageView = new ImageView(context);
            //if there is no image for a list item, get the item location and create an ImageView
            imageView.setLayoutParams(new GridView.LayoutParams(100, 100));
            //now actually pass an image to it width the specified dimensions and display it
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            //if the image is too big then center it and crop
            imageView.setPadding(10, 10, 10, 10);
            //set padding so the cells all look uniform
        }
        else {
            imageView = (ImageView) convertView;
            //if there is something already there, convert it, make sure it stays there
        }

        imageView.setImageResource(icons[position]);

        return imageView;
    }
}
