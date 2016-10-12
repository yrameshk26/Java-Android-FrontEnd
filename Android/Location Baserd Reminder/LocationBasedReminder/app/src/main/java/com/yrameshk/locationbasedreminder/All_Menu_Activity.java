package com.yrameshk.locationbasedreminder;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class All_Menu_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all__menu_);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(false);
        View mActionBarView = getLayoutInflater().inflate(R.layout.custom_action_bar, null);
        actionBar.setCustomView(mActionBarView);
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);

    }

    public void ExitApp(View view){
        AlertDialog.Builder alert =  new AlertDialog.Builder(this);
        alert.setIcon(R.drawable.warning);
        alert.setTitle("Exit Loc-Reminder");
        alert.setMessage("Are you sure you want to close the Loc-Reminder?");
        alert.setPositiveButton("Yes",new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int which){
                Intent intent = new Intent(All_Menu_Activity.this, Start_Screen_Activity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("Exit me", true);
                startActivity(intent);
                finish();   //Closes the application, finish is a reserved word for builder class
            }
        });
        alert.setNegativeButton("No", null);
        alert.show();
    }

    public void goToCreateNew(View view){
        Intent i = new Intent(this, CreateNew_Activity.class);
        startActivity(i);
    }

    public void goToViewAll(View view){
        Intent i = new Intent(this, ViewAll_Activity.class);
        startActivity(i);
    }

    public void goToEditDelete(View view){
        Intent i = new Intent(this, EditDelete_Activity.class);
        startActivity(i);
    }

}
