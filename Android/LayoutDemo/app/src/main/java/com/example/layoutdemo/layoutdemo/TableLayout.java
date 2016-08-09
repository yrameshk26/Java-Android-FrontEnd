package com.example.layoutdemo.layoutdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class TableLayout extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_layout);
    }

    public void goToMainMenuTable(View view){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

}
