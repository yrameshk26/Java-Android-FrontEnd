package com.yrameshk.locationbasedreminder;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;




public class EditDelete_Activity extends AppCompatActivity {

    public ReminderDataBase mydb;
    public ListView obj;
    public static int ID;
    ArrayAdapter arrayAdapter;
    ArrayList array_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_delete_);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(false);
        View mActionBarView = getLayoutInflater().inflate(R.layout.custom_action_bar, null);
        actionBar.setCustomView(mActionBarView);
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);

        mydb = new ReminderDataBase(this);
        array_list = mydb.getAllReminders();
        
        arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1, array_list);
        obj = (ListView) findViewById(R.id.EditReminderList);
        obj.setAdapter(arrayAdapter);
        obj.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,long arg3) {
                ID = arg2 + 1;
                int id_To_Search = arg2 + 1;
                Bundle dataBundle = new Bundle();
                dataBundle.putInt("id", id_To_Search);
            }
        });

    }

    public void goToEditReminder(View view){
        Intent i = new Intent(this, Edit_Activity.class);
        startActivity(i);
        mydb.getReminderName(ID);
        
    }

    public void DeleteReminder(View view) {
        AlertDialog.Builder alert =  new AlertDialog.Builder(this);
        alert.setIcon(R.drawable.warning);
        alert.setTitle("Delete Warning");
        alert.setMessage("Are You Sure, You Want To Delete This Reminder?");
        alert.setPositiveButton("Yes",new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int which){
                Log.i("!!!!@@@@@###$$$%%%%%%%",Integer.toString(mydb.numberOfRows()));
                mydb.deleteReminder(ID);
                array_list.clear();
                array_list = mydb.getAllReminders();
                arrayAdapter.clear();
                arrayAdapter.addAll(array_list);
                arrayAdapter.notifyDataSetChanged();
                obj.setAdapter(arrayAdapter);
            }
        });
        alert.setNegativeButton("No", null);
        alert.show();
    }

}
