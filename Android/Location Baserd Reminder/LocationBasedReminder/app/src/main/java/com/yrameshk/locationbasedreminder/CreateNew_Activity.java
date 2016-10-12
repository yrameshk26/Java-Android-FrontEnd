package com.yrameshk.locationbasedreminder;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
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

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class CreateNew_Activity extends AppCompatActivity implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {

    public NumberPicker np;
    public RadioButton once;
    public RadioButton multiple;
    public CheckBox Mon,Tue,Wed,Thu,Fri,Sat,Sun,Times;
    public GoogleMap mMap;
    public PlaceAutocompleteFragment autocompleteFragment;
    public ReminderDataBase mydb;
    public TextView RepeatText;
    public EditText ReminderName;
    public int Repeating_Times;
    public String Repeat_Mon = "FALSE";
    public String Repeat_Tue = "FALSE";
    public String Repeat_Wed = "FALSE";
    public String Repeat_Thu = "FALSE";
    public String Repeat_Fri = "FALSE";
    public String Repeat_Sat = "FALSE";
    public String Repeat_Sun = "FALSE";
    public String Repeat_Times = "FALSE";
    public String Repeat_Count="0";
    public Marker marker;
    public String Location_Lat,Location_Long;
    private GoogleApiClient mGoogleApiClient;
    public static final String TAG = CreateNew_Activity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(false);
        View mActionBarView = getLayoutInflater().inflate(R.layout.custom_action_bar, null);
        actionBar.setCustomView(mActionBarView);
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);

        mydb = new ReminderDataBase(this);
        mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
        autocompleteFragment = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment_create);

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng point) {
                // TODO Auto-generated method stub
                mMap.clear();
                marker = mMap.addMarker(new MarkerOptions().position(point));
                Log.d("DEBUG","Map clicked [" + point.latitude + " / " + point.longitude + "]");
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

        // Enable / Disable zooming controls
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

        np = (NumberPicker) findViewById(R.id.numberPicker1);
        np.setMinValue(1);
        np.setMaxValue(10);
        np.setWrapSelectorWheel(false);
        np.setVisibility(View.INVISIBLE);

        once = (RadioButton) findViewById(R.id.onceRadio);
        multiple = (RadioButton) findViewById(R.id.multipleRadio);
        once.setOnCheckedChangeListener(radio_listener);
        multiple.setOnCheckedChangeListener(radio_listener);

        ReminderName = (EditText) findViewById(R.id.NameEditText);
        RepeatText = (TextView) findViewById(R.id.RepeatText);RepeatText.setVisibility(View.INVISIBLE);

        Mon = (CheckBox) findViewById(R.id.MonCheck);Mon.setVisibility(View.INVISIBLE);Mon.setOnCheckedChangeListener(check_listener);
        Tue = (CheckBox) findViewById(R.id.TueCheck);Tue.setVisibility(View.INVISIBLE);Tue.setOnCheckedChangeListener(check_listener);
        Wed = (CheckBox) findViewById(R.id.WedCheck);Wed.setVisibility(View.INVISIBLE);Wed.setOnCheckedChangeListener(check_listener);
        Thu = (CheckBox) findViewById(R.id.ThuCheck);Thu.setVisibility(View.INVISIBLE);Thu.setOnCheckedChangeListener(check_listener);
        Fri = (CheckBox) findViewById(R.id.FriCheck);Fri.setVisibility(View.INVISIBLE);Fri.setOnCheckedChangeListener(check_listener);
        Sat = (CheckBox) findViewById(R.id.SatCheck);Sat.setVisibility(View.INVISIBLE);Sat.setOnCheckedChangeListener(check_listener);
        Sun = (CheckBox) findViewById(R.id.SunCheck);Sun.setVisibility(View.INVISIBLE);Sun.setOnCheckedChangeListener(check_listener);
        Times = (CheckBox) findViewById(R.id.TimesCheck);Times.setVisibility(View.INVISIBLE);Times.setOnCheckedChangeListener(check_listener);
        once.setChecked(true);
    }

    final CompoundButton.OnCheckedChangeListener radio_listener =
            new CompoundButton.OnCheckedChangeListener()
            {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
                {
                    if (!isChecked){
                        Repeating_Times = 1;
                        return;}
                    if (buttonView.getId() == R.id.onceRadio){
                        Repeating_Times = 1;
                        Mon.setVisibility(View.INVISIBLE);
                        Tue.setVisibility(View.INVISIBLE);
                        Wed.setVisibility(View.INVISIBLE);
                        Thu.setVisibility(View.INVISIBLE);
                        Fri.setVisibility(View.INVISIBLE);
                        Sat.setVisibility(View.INVISIBLE);
                        Sun.setVisibility(View.INVISIBLE);
                        Times.setVisibility(View.INVISIBLE);
                        RepeatText.setVisibility(View.INVISIBLE);
                        np.setVisibility(View.INVISIBLE);}
                    else if(buttonView.getId() == R.id.multipleRadio){
                        Repeating_Times = 0;
                        Mon.setVisibility(View.VISIBLE);
                        Tue.setVisibility(View.VISIBLE);
                        Wed.setVisibility(View.VISIBLE);
                        Thu.setVisibility(View.VISIBLE);
                        Fri.setVisibility(View.VISIBLE);
                        Sat.setVisibility(View.VISIBLE);
                        Sun.setVisibility(View.VISIBLE);
                        Times.setVisibility(View.VISIBLE);
                        RepeatText.setVisibility(View.VISIBLE);}
                }
            };

    final CompoundButton.OnCheckedChangeListener check_listener =
            new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (Times.isChecked()) {
                        Repeat_Times = "TRUE";
                        Repeat_Mon = "FALSE";Repeat_Tue = "FALSE";Repeat_Wed = "FALSE";Repeat_Thu = "FALSE";
                        Repeat_Fri = "FALSE";Repeat_Sat = "FALSE";Repeat_Sun = "FALSE";
                        np.setVisibility(View.VISIBLE);
                        Mon.setChecked(false);
                        Tue.setChecked(false);
                        Wed.setChecked(false);
                        Thu.setChecked(false);
                        Fri.setChecked(false);
                        Sat.setChecked(false);
                        Sun.setChecked(false);
                    } else if (Mon.isChecked() || Tue.isChecked() || Wed.isChecked() || Thu.isChecked() || Fri.isChecked() || Sat.isChecked() || Sun.isChecked()) {
                        Times.setChecked(false);
                        np.setVisibility(View.INVISIBLE);
                        np.setValue(0);}
                    else if (!Times.isChecked() ){
                        Repeat_Times = "FALSE";
                        np.setVisibility(View.INVISIBLE);
                        np.setValue(0);}
                    else if(Mon.isChecked()){Repeat_Mon = "TRUE";}else if(!Mon.isChecked()){Repeat_Mon = "FALSE";}
                    else if(Tue.isChecked()){Repeat_Tue = "TRUE";}else if(!Tue.isChecked()){Repeat_Tue = "FALSE";}
                    else if(Wed.isChecked()){Repeat_Wed = "TRUE";}else if(!Wed.isChecked()){Repeat_Wed = "FALSE";}
                    else if(Thu.isChecked()){Repeat_Thu = "TRUE";}else if(!Thu.isChecked()){Repeat_Thu = "FALSE";}
                    else if(Fri.isChecked()){Repeat_Fri = "TRUE";}else if(!Fri.isChecked()){Repeat_Fri = "FALSE";}
                    else if(Sat.isChecked()){Repeat_Sat = "TRUE";}else if(!Sat.isChecked()){Repeat_Sat = "FALSE";}
                    else if(Sun.isChecked()){Repeat_Sun = "TRUE";}else if(!Sun.isChecked()){Repeat_Sun = "FALSE";}
                }
            };

    public void createReminder(View view){
        if (Validator()==true) {
            Repeat_Count = Integer.toString(np.getValue());
            mydb.insertReminder(ReminderName.getText().toString(), Repeating_Times, Repeat_Mon, Repeat_Tue, Repeat_Wed, Repeat_Thu, Repeat_Fri, Repeat_Sat, Repeat_Sun, Repeat_Times, Repeat_Count, Location_Lat, Location_Long);
            Toast.makeText(CreateNew_Activity.this, "Reminder Successfully Created", Toast.LENGTH_SHORT).show();
            startService(new Intent(CreateNew_Activity.this, NearByNotifier.class));
            finish();
            
        }
    }

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
        else if (multiple.isChecked()==true) {
            if (Mon.isChecked() == true || Tue.isChecked() == true || Wed.isChecked() == true || Thu.isChecked() == true || Fri.isChecked() == true || Sat.isChecked() == true || Sun.isChecked() == true || Times.isChecked() == true) {
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

    private void handleNewLocation(Location location) {
        Log.d(TAG, location.toString());
    }

    @Override
    public void onConnected(Bundle bundle) {
        Log.i(TAG, "Location services connected.");
        Location location = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if (location == null) {
            // Blank for a moment...
        }
        else {
            handleNewLocation(location);
        };
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.i(TAG, "Location services suspended. Please reconnect.");
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        //setUpMapIfNeeded();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }
}
