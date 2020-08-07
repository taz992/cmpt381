package com.example.a381a5p2;

import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;


public class SketchMainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SketchController controller = new SketchController();
        SketchView view = new SketchView(this);
        view.controller = controller;
        view.setOnTouchListener(controller);


        InteractionModel interactionModel = new InteractionModel();
        interactionModel.addSubscribers(view);


        final SketchModel model = new SketchModel(interactionModel);
        model.controller = controller;

        view.setModel(model);
        controller.setModel(model);
        controller.setView(view);

        SeekBar bar = new SeekBar(this);
//        bar.incrementProgressBy(1);
        bar.setMax(9);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;


        LinearLayout vbox = new LinearLayout(this);
        vbox.setOrientation(LinearLayout.VERTICAL);

        final TextView textView = new TextView(this);
        textView.setText(String.valueOf(1));
        textView.setTextSize(20);

        bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textView.setText(String.valueOf(progress + 1));
                model.getInteractionModel().thinning = progress + 1;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        vbox.addView(bar, new LinearLayout.LayoutParams(width, 60));
        vbox.addView(textView, new LinearLayout.LayoutParams(width, 60));
        vbox.addView(view, new LinearLayout.LayoutParams(width, height - 120));
        setContentView(vbox);

    }

    //@Override
    //protected void onCreate(Bundle savedInstanceState) {
    //    super.onCreate(savedInstanceState);
    //    setContentView(R.layout.activity_sketch_main);
    //}
}
