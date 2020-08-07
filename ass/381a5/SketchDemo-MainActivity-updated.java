package com.example.gutwin.touchdemo;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class MainActivity extends Activity implements View.OnTouchListener {

    TouchView tView;
    MotionEvent currentEvent;
    ArrayList<MyPoint> points;
    int historySize;
    int pointerCount;
    float px, py;
    Path demoPath;
    float[] approximatePoints;
    boolean pathTouched = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        points = new ArrayList<>();
        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        tView = new TouchView(this, null);
        tView.setOnTouchListener(this);

        demoPath = new Path();
        demoPath.moveTo(100, 100);
        demoPath.quadTo(100, 100, 150, 105);
        demoPath.quadTo(200, 110, 250, 135);
        demoPath.quadTo(300, 160, 300, 260);
        approximatePoints = demoPath.approximate(0.5f);

        root.addView(tView);
        setContentView(root);
    }

    public boolean onTouch(View v, MotionEvent event) {
        //System.out.println(event.getAction());
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (distanceFromPath(event.getX(), event.getY()) < 10) {
                pathTouched = true;
            }
        }

        currentEvent = event;
        if (currentEvent != null) {
            historySize = currentEvent.getHistorySize();
            pointerCount = currentEvent.getPointerCount();
            for (int h = 0; h < historySize; h++) {
                for (int p = 0; p < pointerCount; p++) {
                    px = currentEvent.getHistoricalX(p, h);
                    py = currentEvent.getHistoricalY(p, h);
                    //System.out.println(px + "," + py);
                    points.add(new MyPoint(px, py, Color.RED));
                }
            }
            px = currentEvent.getX();
            py = currentEvent.getY();
            points.add(new MyPoint(px, py, Color.GREEN));
        }

        tView.invalidate();
        return true;
    }

    public float distanceFromPath(float x, float y) {
        float dist = Float.MAX_VALUE;
        float px, py;
        for (int i = 0; i < approximatePoints.length; i += 3) {
            px = approximatePoints[i + 1];
            py = approximatePoints[i + 2];
            dist = Math.min(distance(x, y, px, py), dist);
        }
        return dist;
    }

    public float distance(float x1, float y1, float x2, float y2) {
        return (float) Math.sqrt(Math.pow((double) x2 - (double) x1, 2) + Math.pow((double) y2 - (double) y1, 2));
    }

    public class TouchView extends View {
        Paint myPaint;

        public TouchView(Context context, AttributeSet attrs) {
            super(context, attrs);
            myPaint = new Paint();
            setBackgroundColor(Color.rgb(80, 50, 110));
        }

        protected void onDraw(Canvas myCanvas) {
            myPaint.setStyle(Paint.Style.FILL);

            for (MyPoint mp : points) {
                myPaint.setColor(mp.c);
                myCanvas.drawOval(mp.x - 10, mp.y - 10, mp.x + 10, mp.y + 10, myPaint);
            }
            myPaint.setColor(Color.BLUE);
            myPaint.setStyle(Paint.Style.STROKE);
            myPaint.setStrokeWidth(10);
            myCanvas.drawPath(demoPath, myPaint);
        }
    }

    class MyPoint {
        float x;
        float y;
        int c;

        public MyPoint(float newX, float newY, int newC) {
            x = newX;
            y = newY;
            c = newC;
        }
    }
}
