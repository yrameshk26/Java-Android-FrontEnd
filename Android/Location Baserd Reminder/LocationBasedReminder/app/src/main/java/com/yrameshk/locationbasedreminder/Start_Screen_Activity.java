package com.yrameshk.locationbasedreminder;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class Start_Screen_Activity extends AppCompatActivity {

    final private int REQUEST_CODE_SOME_FEATURES_PERMISSIONS = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);
        getSupportActionBar().hide();
        PermissionChecker();
        if( getIntent().getBooleanExtra("Exit me", false)){
            finish();
        }
    }

    private void PermissionChecker() {
        int hasLocationPermission = checkSelfPermission( Manifest.permission.ACCESS_FINE_LOCATION );
        List<String> permissions = new ArrayList<String>();
        if( hasLocationPermission != PackageManager.PERMISSION_GRANTED ) {
            permissions.add( Manifest.permission.ACCESS_FINE_LOCATION );
        }

        if( !permissions.isEmpty() ) {
            requestPermissions( permissions.toArray( new String[permissions.size()] ), REQUEST_CODE_SOME_FEATURES_PERMISSIONS );
        }
    }

    public void goToMenuActivity(View view){
        Intent i = new Intent(this, All_Menu_Activity.class);
        startActivity(i);
    }

}
