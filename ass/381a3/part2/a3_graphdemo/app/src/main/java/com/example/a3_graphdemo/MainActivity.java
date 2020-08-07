package com.example.a3_graphdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.example.a3_graphdemo.view.customview;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        customview customview = new customview(this);



    }
}
