package com.example.layoutdemo.layoutdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void GridLayout(View view){
        Intent i = new Intent(this, GridLayout.class);
        startActivity(i);
    }

    public void LinearLayout(View view){
        Intent i = new Intent(this, LinearLayout.class);
        startActivity(i);
    }

    public void TableLayout(View view){
        Intent i = new Intent(this, TableLayout.class);
        startActivity(i);
    }

    public void FrameLayout(View view){
        Intent i = new Intent(this, FrameLayout.class);
        startActivity(i);
    }
    public void RelativeLayout(View view){
        Intent i = new Intent(this, RelativeLayout.class);
        startActivity(i);
    }

}
