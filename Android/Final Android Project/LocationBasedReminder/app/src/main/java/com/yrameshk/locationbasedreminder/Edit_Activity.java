package com.yrameshk.locationbasedreminder;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class Edit_Activity extends AppCompatActivity {

    public NumberPicker npEdit;
    public RadioButton onceEdit;
    public RadioButton multipleEdit;
    public CheckBox MonEdit,TueEdit,WedEdit,ThuEdit,FriEdit,SatEdit,SunEdit,TimesEdit;
    public GoogleMap mMap;
    public ReminderDataBase mydb;
    public EditText ReminderName;
    public Integer Repeating_Times = 1;
    public String Repeat_Mon = "FALSE";
    public String Repeat_Tue = "FALSE";
    public String Repeat_Wed = "FALSE";
    public String Repeat_Thu = "FALSE";
    public String Repeat_Fri = "FALSE";
    public String Repeat_Sat = "FALSE";
    public String Repeat_Sun = "FALSE";
    public String Repeat_Times = "FALSE";
    public String Repeat_Count="0";
    public String Location_Lat,Location_Long;
    PlaceAutocompleteFragment autocompleteFragment;
    public Marker marker;
    public TextView RepeatText;
    int ID = EditDelete_Activity.ID;
    public static final String TAG = CreateNew_Activity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(false);
        View mActionBarView = getLayoutInflater().inflate(R.layout.custom_action_bar, null);
        actionBar.setCustomView(mActionBarView);
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);

        mydb = new ReminderDataBase(this);
        mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.mapEdit)).getMap();
        autocompleteFragment = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment_edit);

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng point) {
                // TODO Auto-generated method stub
                mMap.clear();
                marker = mMap.addMarker(new MarkerOptions().position(point));
                Location_Lat = Double.toString(point.latitude);
                Location_Long = Double.toString(point.longitude);

            }
        });

        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                mMap.clear();
                marker = mMap.addMarker(new MarkerOptions().position(place.getLatLng()));
                Log.i(TAG, "Place: " + place.getName());
                Location_Lat = Double.toString(place.getLatLng().latitude);
                Location_Long = Double.toString(place.getLatLng().longitude);
            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                Log.i(TAG, "An error occurred: " + status);
            }
        });

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        } else {
            // Show rationale and request permission.
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    1);
            mMap.setMyLocationEnabled(true);
        }

        npEdit = (NumberPicker) findViewById(R.id.numberPickerEdit);
        npEdit.setMinValue(1);
        npEdit.setMaxValue(10);
        npEdit.setWrapSelectorWheel(false);
        npEdit.setVisibility(View.INVISIBLE);

        onceEdit = (RadioButton) findViewById(R.id.onceRadioEdit);
        multipleEdit = (RadioButton) findViewById(R.id.multipleRadioEdit);
        onceEdit.setOnCheckedChangeListener(radio_listener_edit);
        multipleEdit.setOnCheckedChangeListener(radio_listener_edit);

        ReminderName = (EditText) findViewById(R.id.NameEditTextEdit);
        RepeatText = (TextView) findViewById(R.id.RepeatTextEdit);RepeatText.setVisibility(View.INVISIBLE);

        MonEdit = (CheckBox) findViewById(R.id.MonCheckEdit);MonEdit.setVisibility(View.INVISIBLE);MonEdit.setOnCheckedChangeListener(check_listener_edit);
        TueEdit = (CheckBox) findViewById(R.id.TueCheckEdit);TueEdit.setVisibility(View.INVISIBLE);TueEdit.setOnCheckedChangeListener(check_listener_edit);
        WedEdit = (CheckBox) findViewById(R.id.WedCheckEdit);WedEdit.setVisibility(View.INVISIBLE);WedEdit.setOnCheckedChangeListener(check_listener_edit);
        ThuEdit = (CheckBox) findViewById(R.id.ThuCheckEdit);ThuEdit.setVisibility(View.INVISIBLE);ThuEdit.setOnCheckedChangeListener(check_listener_edit);
        FriEdit = (CheckBox) findViewById(R.id.FriCheckEdit);FriEdit.setVisibility(View.INVISIBLE);FriEdit.setOnCheckedChangeListener(check_listener_edit);
        SatEdit = (CheckBox) findViewById(R.id.SatCheckEdit);SatEdit.setVisibility(View.INVISIBLE);SatEdit.setOnCheckedChangeListener(check_listener_edit);
        SunEdit = (CheckBox) findViewById(R.id.SunCheckEdit);SunEdit.setVisibility(View.INVISIBLE);SunEdit.setOnCheckedChangeListener(check_listener_edit);
        TimesEdit = (CheckBox) findViewById(R.id.TimesCheckEdit);TimesEdit.setVisibility(View.INVISIBLE);TimesEdit.setOnCheckedChangeListener(check_listener_edit);
        ReminderName.setText(mydb.getReminderName(ID));

        if(mydb.getRepeatingTimes(ID)==1){
            onceEdit.setChecked(true);
            multipleEdit.setChecked(false);
        }
        else if(mydb.getRepeatingTimes(ID)==0){
            onceEdit.setChecked(false);
            multipleEdit.setChecked(true);
        }
        LatLng position;
        if(mydb.getLocationLat(ID) != 0.0 || mydb.getLocationLong(ID) != 0.0)
        {
            position = new LatLng(mydb.getLocationLat(ID) , mydb.getLocationLong(ID));
            mMap.addMarker(new MarkerOptions().position(position).title(mydb.getReminderName(ID)));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(position,15));
            // Zoom in, animating the camera.
            mMap.animateCamera(CameraUpdateFactory.zoomIn());
            // Zoom out to zoom level 10, animating with a duration of 2 seconds.
            mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
        }
        
    }

    final CompoundButton.OnCheckedChangeListener radio_listener_edit =
            new CompoundButton.OnCheckedChangeListener()
            {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
                {
                    if (!isChecked){
                        return;}
                    if (buttonView.getId() == R.id.onceRadioEdit){
                        Repeating_Times = 1;
                        MonEdit.setVisibility(View.INVISIBLE);
                        TueEdit.setVisibility(View.INVISIBLE);
                        WedEdit.setVisibility(View.INVISIBLE);
                        ThuEdit.setVisibility(View.INVISIBLE);
                        FriEdit.setVisibility(View.INVISIBLE);
                        SatEdit.setVisibility(View.INVISIBLE);
                        SunEdit.setVisibility(View.INVISIBLE);
                        TimesEdit.setVisibility(View.INVISIBLE);
                        RepeatText.setVisibility(View.INVISIBLE);
                        npEdit.setVisibility(View.INVISIBLE);}
                    else if(buttonView.getId() == R.id.multipleRadioEdit){
                        Repeating_Times = 0;
                        MonEdit.setVisibility(View.VISIBLE);
                        TueEdit.setVisibility(View.VISIBLE);
                        WedEdit.setVisibility(View.VISIBLE);
                        ThuEdit.setVisibility(View.VISIBLE);
                        FriEdit.setVisibility(View.VISIBLE);
                        SatEdit.setVisibility(View.VISIBLE);
                        SunEdit.setVisibility(View.VISIBLE);
                        TimesEdit.setVisibility(View.VISIBLE);
                        RepeatText.setVisibility(View.VISIBLE);}
                }
            };

    final CompoundButton.OnCheckedChangeListener check_listener_edit =
            new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (TimesEdit.isChecked()) {
                        npEdit.setVisibility(View.VISIBLE);
                        MonEdit.setChecked(false);
                        TueEdit.setChecked(false);
                        WedEdit.setChecked(false);
                        ThuEdit.setChecked(false);
                        FriEdit.setChecked(false);
                        SatEdit.setChecked(false);
                        SunEdit.setChecked(false);
                    } else if (MonEdit.isChecked() || TueEdit.isChecked() || WedEdit.isChecked() || ThuEdit.isChecked() || FriEdit.isChecked() || SatEdit.isChecked() || SunEdit.isChecked()) {
                        TimesEdit.setChecked(false);
                        npEdit.setVisibility(View.INVISIBLE);}
                    else if (!TimesEdit.isChecked() ){
                        npEdit.setVisibility(View.INVISIBLE);
                    }
                }
            };

    public boolean Validator(){
        if(ReminderName.getText().toString()==null || ReminderName.getText().toString().equals("") || ReminderName.getText().toString().equals(null)) {
            AlertDialog alert = new AlertDialog.Builder(this).create();
            alert.setIcon(R.drawable.warning);
            alert.setTitle("Reminder Name Mandatory");
            alert.setMessage("Reminder Name Cannot Be Null.");
            alert.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alert.show();
            return false;
        }
        else if (multipleEdit.isChecked()==true) {
            if (MonEdit.isChecked() == true || TueEdit.isChecked() == true || WedEdit.isChecked() == true || ThuEdit.isChecked() == true || FriEdit.isChecked() == true || SatEdit.isChecked() == true || SunEdit.isChecked() == true || TimesEdit.isChecked() == true) {
                return true;
            } else {
                AlertDialog alert = new AlertDialog.Builder(this).create();
                alert.setIcon(R.drawable.warning);
                alert.setTitle("Repeat Times Multiple");
                alert.setMessage("Day or Times CheckBox Should Be Selected in Multiple Mode.");
                alert.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alert.show();
                return false;
            }
        }
        else if (marker == null) {
            AlertDialog alert = new AlertDialog.Builder(this).create();
            alert.setIcon(R.drawable.warning);
            alert.setTitle("Location is Mandatory");
            alert.setMessage("Location is Mandatory to Create a Reminder.");
            alert.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alert.show();
            return false;
        }
        else {
            return true;
        }
    }

    public void EditReminder(View view){
        if (Validator()==true) {
            Repeat_Count = Integer.toString(npEdit.getValue());
            mydb.updateReminder(ID, ReminderName.getText().toString(), Repeating_Times, Repeat_Mon, Repeat_Tue, Repeat_Wed, Repeat_Thu, Repeat_Fri, Repeat_Sat, Repeat_Sun, Repeat_Times, Repeat_Count, Location_Lat, Location_Long);
            Toast.makeText(Edit_Activity.this, "Reminder Successfully Modified", Toast.LENGTH_SHORT).show();
            mydb.close();
        }
    }

}
