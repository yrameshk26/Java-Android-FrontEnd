package com.example.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clickBox (View view){
        Random generator = new Random();
        int i = generator.nextInt(8) + 1;
        String boxName = "box" + Integer.toString(i);
        int resID = getResources().getIdentifier(boxName, "id", "com.example.tictactoe");
        TextView GenBox = (TextView)findViewById(resID);
        TextView Box = (TextView)findViewById(view.getId());

        if(Box.getText().toString().equals("") || Box.getText().toString() == null) {
            String boxNameTempFull = view.getResources().getResourceName(view.getId());
            String boxTempName = boxNameTempFull.substring(boxNameTempFull.indexOf("/")+1,boxNameTempFull.length());
            Box.setBackgroundResource(R.drawable.xicon);
            Box.setText("X");
                if(!boxTempName.equals(boxName)) {
                    if (GenBox.toString().equals("") || GenBox.toString() == null) {
                        GenBox.setBackgroundResource(R.drawable.oicon);
                        Box.setText("O");
                    }
                }
        }

    }

}
