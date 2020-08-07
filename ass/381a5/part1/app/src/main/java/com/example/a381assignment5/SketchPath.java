package com.example.a381assignment5;

import android.graphics.Path;
import android.util.Pair;

public class SketchPath extends Path {
    public SketchPath(Pair<Float, Float> startP, Pair<Float, Float> controlP, Pair<Float, Float> endP) {

        this.moveTo(startP.first, startP.second);
        this.quadTo(controlP.first, controlP.second, endP.first, endP.second);
    }
}
