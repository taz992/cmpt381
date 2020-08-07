package com.example.a381assignment5;
import android.graphics.PathMeasure;
import android.util.Pair;
import java.util.ArrayList;
import static java.lang.Math.abs;

public class SketchModel {
    private InteractionModel interactionModel;
    ArrayList<ArrayList<SketchPath>> curves = new ArrayList<>();
    ArrayList<SketchListener> subscribers;
    SketchController controller;

    public SketchModel(InteractionModel m){
        this.interactionModel = m;
        subscribers = interactionModel.subscribers;

    }

    public InteractionModel getInteractionModel() {
        return interactionModel;
    }

    //    When drawing is finished.
    public boolean addNewCurve (){
        ArrayList<Pair<Float, Float>> smoothedPoints = interactionModel.getSmoothedPoints();
        if (smoothedPoints.size()<4){
            return false;
        }
        ArrayList<SketchPath> newCurve = new ArrayList<>();


        for (int i=0; i<smoothedPoints.size();){
            if (i==0){
                newCurve.add(new SketchPath(smoothedPoints.get(i), smoothedPoints.get(i), smoothedPoints.get(i+1)));
                i++;
            }
            else if (i+2 == smoothedPoints.size()){
                newCurve.add(new SketchPath(smoothedPoints.get(i), smoothedPoints.get(i), smoothedPoints.get(i+1)));
                break;
            }
            else
            {
                newCurve.add(new SketchPath(smoothedPoints.get(i), smoothedPoints.get(i+1), smoothedPoints.get(i+2)));
                i+=2;
                if (i== smoothedPoints.size()-1){
                    break;
                }
            }



        }

        curves.add(newCurve);
        notifySubscribers();
        return true;
    }

    private void notifySubscribers(){
        for (SketchListener l: subscribers){
            l.modelChanged();
        }
    }



    public ArrayList<SketchPath> containsCurve(float x, float y, ArrayList<Float> bounds){
        ArrayList<SketchPath> result = null;

        bounds.clear();

        float maxX=0, maxY=0;
        float minX=5000, minY=5000;

        for (ArrayList<SketchPath> c: curves){
            for (SketchPath path: c){
                for (int i=1; i<=10; i++){
                    PathMeasure pm = new PathMeasure(path, false);
                    float[] coords = new float[2];
                    pm.getPosTan((pm.getLength()*i/10), coords, null);
                    if (abs(x-coords[0])<20 && abs(y-coords[1])<20){
                        result = c;
                    }
                }
            }
        }
        if (result != null){
            for (SketchPath path: result){
                for (int i=1; i<=10; i++){
                    PathMeasure pm = new PathMeasure(path, false);
                    float[] coords = new float[2];
                    pm.getPosTan((pm.getLength()*i/10), coords, null);
                    if(coords[0]>maxX){
                        maxX = coords[0];
                    }
                    if(coords[1]>maxY){
                        maxY = coords[1];
                    }
                    if(coords[0]<minX){
                        minX = coords[0];
                    }
                    if(coords[1]<minY){
                        minY = coords[1];
                    }
                }
            }
            bounds.add(minX-5);
            bounds.add(minY-5);
            bounds.add(maxX+5);
            bounds.add(maxY+5);
        }
        return result;
    }

    public void reCalculateBound(ArrayList<SketchPath> paths, ArrayList<Float> bounds){
        bounds.clear();

        float maxX=0, maxY=0;
        float minX=5000, minY=5000;
        if (paths != null){
            for (SketchPath path: paths){
                for (int i=1; i<=10; i++){
                    PathMeasure pm = new PathMeasure(path, false);
                    float[] coords = new float[2];
                    pm.getPosTan((pm.getLength()*i/10), coords, null);
                    if(coords[0]>maxX){
                        maxX = coords[0];
                    }
                    if(coords[1]>maxY){
                        maxY = coords[1];
                    }
                    if(coords[0]<minX){
                        minX = coords[0];
                    }
                    if(coords[1]<minY){
                        minY = coords[1];
                    }
                }
            }
            bounds.add(minX-5);
            bounds.add(minY-5);
            bounds.add(maxX+5);
            bounds.add(maxY+5);

            controller.model.getInteractionModel().handlerX = bounds.get(2);
            controller.model.getInteractionModel().handlerY = bounds.get(3);
        }
    }
}
