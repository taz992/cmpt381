package com.example.a381assignment01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.Layout;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity {
//implements TextWatcher, View.OnClickListener

    Switch aSwitch;
    SeekBar aSeekbar;
    EditText aEditText;
    TextView aTextView;
    int min=0, max=100, current=0;
    int volume;
    boolean notification;
    String display;
    SeekBar range1;
    SeekBar range2;
    LinearLayout aLinearLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        aSwitch = (Switch)findViewById(R.id.switch1);
        aSeekbar = (SeekBar)findViewById(R.id.seekBar1);
        aEditText = (EditText)findViewById(R.id.editText1);
        aTextView = (TextView) findViewById(R.id.textView4);

        //aLinearLayout = (LinearLayout) findViewById(R.id.);

       // LinearLayout
        //range1 = new RangeSeek(this,20,80,this);
        //range2 = new RangeSeek();
        //LinearLayout aLinearLayout = new LinearLayout(this);
       // aLinearLayout.addView(range1);
       // aLinearLayout.addView(range2);

        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            notification = isChecked;
            SummaryUpdate();

            }
        });

        aSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                volume=progress;
                SummaryUpdate();
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
                //aEditText1.setText(s);
                SummaryUpdate();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        SummaryUpdate();
    }

    public void SummaryUpdate(){

        if (notification == true){
            display = "On";
        }
        else {
            display = "Off";
        }
        aTextView.setText("Summary of settings:" + "\n" + "Notifications: " + display + "\n" +"Volume: " + volume +"\n" + "Phone number: "  +aEditText.getText() + "\n");

    }
}
