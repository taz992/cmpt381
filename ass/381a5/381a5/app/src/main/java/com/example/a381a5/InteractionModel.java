package com.example.a381a5;

import android.graphics.Path;
import android.util.Pair;
import java.util.ArrayList;

public class InteractionModel {
    ArrayList<Float> bounds = new ArrayList<>();
    float handlerX;
    float handlerY;
    private ArrayList<Pair<Float, Float>> rawPoints = new ArrayList<>();
    private ArrayList<Pair<Float, Float>> thinnedPoints = new ArrayList<>();
    ArrayList<Pair<Float, Float>> smoothedPoints = new ArrayList<>();

    ArrayList<Path> rawPaths = new ArrayList<>();

    //    For drawing on prograss
    ArrayList<SketchListener> subscribers = new ArrayList<>();

    int thinning = 1;

    public void addSubscribers(SketchListener listener){
        subscribers.add(listener);
    }

    public void addRawPointAndPath(float x, float y){
        rawPoints.add(new Pair<>(x, y));

        if(rawPoints.size()>=2){
            Path p = new Path();
            p.moveTo(rawPoints.get(rawPoints.size()-2).first, rawPoints.get(rawPoints.size()-2).second);
            p.lineTo(rawPoints.get(rawPoints.size()-1).first, rawPoints.get(rawPoints.size()-1).second);
            rawPaths.add(p);
        }
        System.out.println(rawPoints.size());
        notifySubscribers();
    }

    private void notifySubscribers(){
        for (SketchListener l: subscribers){
            l.modelChanged();
        }
    }

    private void thinPoints(){
        if (rawPoints.size() <= 2){
            System.out.println("ERROR in interacionModel thinPoints");
        }
        else {
            thinnedPoints.add(rawPoints.get(rawPoints.size()-1));
            thinnedPoints.add(0, rawPoints.get(0));
            int index = 1;
            for (int i=thinning; i<rawPoints.size()-1; i=i+thinning){
                thinnedPoints.add(index, rawPoints.get(i));
                index ++;

            }
        }
    }

    private void smoothPoints(){
        if (thinnedPoints.size()<=2){
            System.out.println("ERROR in interacionModel smoothPoints");
        }
        else {
            for (int i=0; i<thinnedPoints.size()-1; i++){
                Pair<Float, Float> former = thinnedPoints.get(i);
                Pair<Float, Float> rear= thinnedPoints.get(i+1);
                smoothedPoints.add(former);
                smoothedPoints.add(Pair.create((former.first+rear.first)/2,(former.second+rear.second)/2));
            }
            smoothedPoints.add(thinnedPoints.get(thinnedPoints.size()-1));
        }
    }

    public ArrayList<Pair<Float, Float>> getSmoothedPoints(){
        thinnedPoints.clear();
        smoothedPoints.clear();


        thinPoints();
        smoothPoints();
//        System.out.println(smoothedPoints);
        return smoothedPoints;
    }

    public void clearPoints(){
        rawPoints.clear();
        thinnedPoints.clear();
        smoothedPoints.clear();
        rawPaths.clear();
        notifySubscribers();
    }



}
