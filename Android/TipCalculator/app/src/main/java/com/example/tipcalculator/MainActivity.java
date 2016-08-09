package com.example.tipcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SeekBar seekBar = (SeekBar)findViewById(R.id.seekBar);
        final TextView seekBarValue = (TextView)findViewById(R.id.Percentage);

        final EditText amountText = (EditText) findViewById(R.id.AmountText);

        final EditText EditTip = (EditText) findViewById(R.id.TipText);
        final EditText EditTotal = (EditText) findViewById(R.id.TotalText);


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // TODO Auto-generated method stub
                seekBarValue.setText(String.valueOf(progress)+"%");
                if (amountText.length() != 0) {
                    double amount = Double.parseDouble(amountText.getText().toString());
                    //double tip = Double.parseDouble(EditTip.getText().toString());
                    int seeklength = seekBarValue.getText().toString().length();
                    int tipPercent = Integer.parseInt(seekBarValue.getText().toString().substring(0,seeklength-1));
                    double finalTip = ((amount * tipPercent) / 100);
                    EditTip.setText("$"+String.valueOf(finalTip));
                    EditTip.setEnabled(false);
                    double finalAmount = (finalTip + amount);
                    EditTotal.setText("$"+String.valueOf(finalAmount));
                    EditTotal.setEnabled(false);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }
        });
    }

}


