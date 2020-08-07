package com.example.a381assignment1_3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Switch aSwitch;
    SeekBar aSeekbar;
    //need modify
    //TextView textView;
    EditText aEditText;
    EditText aEditText1;

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
        //textView = (TextView)findViewById(R.id.textView1);
        //textView.setText(""+current);
        aEditText = (EditText)findViewById(R.id.editText1);
        aEditText1 = (EditText)findViewById(R.id.editText3);

        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked == true){
                    aEditText.setText("Notifications: On");
                    //Toast.makeText(getBaseContext(),"On",Toast.LENGTH_SHORT).show();
                }else{
                    aEditText.setText("Notifications: Off");
                    //Toast.makeText(getBaseContext(),"Off",Toast.LENGTH_SHORT).show();
                }
            }
        });

        aSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //need modify
                current=progress+min;
                aEditText.setText("Volume: "+current);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        aEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                aEditText1.setText("phone number: " + aEditText);
            }

            @Override
            public void afterTextChanged(Editable s) {
                //aEditText.setText("Phone number:" + aEditText1);
            }
        });
        //aEditText.setText("Phone number: " + aEditText1);




    }
}
