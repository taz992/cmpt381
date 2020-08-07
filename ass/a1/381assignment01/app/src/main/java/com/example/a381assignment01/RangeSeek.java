package com.example.a381assignment01;

import android.app.ActionBar;
import android.content.Context;
import android.graphics.Color;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.content.res.TypedArray;
import android.graphics.*;
import android.graphics.Paint.Style;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.widget.ImageView;
import java.math.BigDecimal;


public class RangeSeek extends LinearLayout {
        public static final Integer DEFAULT_MINIMUM = 0;
        public static final Integer DEFAULT_MAXIMUM = 100;
        SeekBar Left, Right;
        int leftnumber, rightnumber;
        MainActivity parent;
        RangeSeek result;



    public RangeSeek(Context context, final int leftnum, int rightnum, MainActivity newparent, RangeSeek resultbar){
        super(context);

        leftnumber = leftnum;
        rightnumber = rightnum;
        parent = newparent;
        result = this;

        setOrientation(LinearLayout.VERTICAL);
        setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT));
        setBackgroundColor(Color.argb(125,(int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255)));

    }

}
