package com.example.uidemo.uidemo;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.DialogInterface;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void createToastMsg(String msg){
        Toast t = Toast.makeText(this, msg, Toast.LENGTH_LONG);
        t.show();
    }

    //Being called from the XML for the Button btnToast
    public void displayToastMsg(View view){
        //Calling a createToastMsg method and passing a string
        createToastMsg("Hello, How Are You?");
    }

    //Is being called from XML
    public void exitAlertDialog(View view){
        //Alert Dialogs have a lot of objects nested inside of it
        AlertDialog.Builder b = new AlertDialog.Builder(this);
        //This method is called from the Builder class which is attached to the Alert Dialg class
        b.setTitle("Exit");
        //This method is called from the Builder class which is attached to the Alert Dialg class
        b.setMessage("Are you sure you wanna exit?");

        //Dialog interface is a component of Builder which also has a listener
        b.setPositiveButton("Yes",new DialogInterface.OnClickListener(){

            //Anonymouse class/method
            public void onClick(DialogInterface dialog, int which){
                finish();   //Closes the application, finish is a reserved word for builder class
            }
        });

        b.setNegativeButton("No", null);
        AlertDialog alertDialog = b.create(); //This allows all builder elements to be places together in a
        alertDialog.show(); //This takes all the smaller builder components and shows it as one whole package
    }

    public void statusNotification(View view){
        NotificationCompat.Builder nBuilder = new NotificationCompat.Builder(this);
        nBuilder.setContentTitle("New Notification!");
        nBuilder.setContentText("You've got one!");
        nBuilder.setSmallIcon(android.R.drawable.star_on);

        //Takes all lego blocks (nBuilder elements and packages them into a notification screen
        Notification notification = nBuilder.build();
        //Calling a service because notification run in the background until closed
        NotificationManager nManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        //Notifies the status bar that a notification is coming through
        nManager.notify(1, notification);
    }

}
