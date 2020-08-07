package com.example.a381a5p2;

import android.graphics.Path;
import android.util.Pair;
public class SketchPath extends Path {
    public SketchPath(Pair<Float, Float> startP, Pair<Float, Float> controlP, Pair<Float, Float> endP) {

        System.out.println("start: "+startP.first+" "+startP.second+" control "+controlP.first+" "+ controlP.second+ " end "+endP.first+ " "+endP.second);
        this.moveTo(startP.first, startP.second);
        this.quadTo(controlP.first, controlP.second, endP.first, endP.second);
    }
}
