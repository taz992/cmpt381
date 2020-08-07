package com.example.a381assignment1_2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{

    Switch aSwitch;
    SeekBar aSeekbar;
    //need modify
    //TextView textView;
    int min=0, max=100, current=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        aSwitch = (Switch)findViewById(R.id.switch1);
        aSeekbar = (SeekBar)findViewById(R.id.seekBar1);
        aSeekbar.setMax(max-min);
        aSeekbar.setProgress(current-min);
        //need modify
        //textView = (TextView)findViewById(R.id.textView13);
        //textView.setText(""+current);


        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked == true){
                    Toast.makeText(getBaseContext(),"On",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getBaseContext(),"Off",Toast.LENGTH_SHORT).show();
                }
            }
        });
        aSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //need modify
                //current=progress+min;
                //textView.setText(""+current);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }




}
