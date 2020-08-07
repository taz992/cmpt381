package com.example.a381assignment1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    TextView textView;
    SeekBar seekBar;

    TextView textView1;
    Switch switch1;


    int min =0, max =100, current = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView)findViewById(R.id.textView13);
        seekBar = (SeekBar)findViewById(R.id.seekBar);

        seekBar.setMax(max-min);
        seekBar.setProgress(current - min);
        textView.setText(""+current);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                current = progress + min;
                textView.setText("" + current);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
  }

    @Override
    public void onClick(View v) {

        textView1 = (TextView)findViewById(R.id.textView14);
        switch1 = (Switch)findViewById(R.id.switch1);
        if (v.getId()==R.id.switch1){
            if(switch1.isChecked()){

            }
        }
    }
}
