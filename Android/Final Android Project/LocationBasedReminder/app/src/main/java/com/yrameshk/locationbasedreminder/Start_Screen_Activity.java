package com.yrameshk.locationbasedreminder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Start_Screen_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);
        getSupportActionBar().hide();
        if( getIntent().getBooleanExtra("Exit me", false)){
            finish();
        }
    }

    public void goToMenuActivity(View view){
        Intent i = new Intent(this, All_Menu_Activity.class);
        startActivity(i);
    }

}
