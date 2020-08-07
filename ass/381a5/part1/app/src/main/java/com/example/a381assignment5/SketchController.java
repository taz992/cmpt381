package com.example.a381assignment5;

import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.View;
import java.util.ArrayList;
import static java.lang.Math.abs;

public class SketchController implements View.OnTouchListener{
    ArrayList<SketchPath> selectedCurve = null;
    SketchView view;
    SketchModel model;

    float lastX=0;
    float lastY=0;

    private final int STATE_READY = 0;
    private final int STATE_DRAWING = 1;
    private final int STATE_READY_TO_OPERATE = 2;
    private final int STATE_MOVING = 3;
    private final int STATE_SCALING = 4;

    int state = STATE_READY;

    public void setView(SketchView view) {
        this.view = view;
    }

    public void setModel(SketchModel model) {
        this.model = model;
    }

    private void printState(){
        switch (state){
            case 0:
                System.out.println("Ready");
                break;
            case 1:
                System.out.println("Drawing");
                break;
            case 2:
                System.out.println("Ready to operate");
                break;
            case 3:
                System.out.println("Moving");
                break;
            case 4:
                System.out.println("Scaling");
                break;
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        switch (state){
            case STATE_READY:
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        if (model.containsCurve(event.getX(), event.getY(), model.getInteractionModel().bounds) != null){
                            model.getInteractionModel().clearPoints();
                        }
                        else {
                            model.getInteractionModel().clearPoints();
                            state = STATE_DRAWING;
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        selectedCurve = model.containsCurve(event.getX(), event.getY(), model.getInteractionModel().bounds);
                        if (selectedCurve != null){
                            model.curves.remove(selectedCurve);
                            model.getInteractionModel().handlerX = model.getInteractionModel().bounds.get(2);
                            model.getInteractionModel().handlerY = model.getInteractionModel().bounds.get(3);
                            view.invalidate();
                            state = STATE_READY_TO_OPERATE;
                        }
                }
                break;
            case STATE_READY_TO_OPERATE:
                switch (event.getAction()){
                    case MotionEvent.ACTION_UP:
                        if (model.getInteractionModel().bounds.size() == 4) {
                            if (inBounds(event.getX(), event.getY())){
                            }

                            else {
                                model.curves.add(selectedCurve);
                                selectedCurve = null;
                                view.invalidate();
                                state = STATE_READY;
                            }
                        }
                        break;
                    case MotionEvent.ACTION_MOVE:
                        if (inBounds(event.getX(), event.getY())){
                            lastX = event.getX();
                            lastY = event.getY();
                            state = STATE_MOVING;
                        }
                        break;

                    case MotionEvent.ACTION_DOWN:
                        if (inHandler(event.getX(), event.getY())){
                            lastX = event.getX();
                            lastY = event.getY();
                            state = STATE_SCALING;
                        }
                        break;
                }
                break;
            case STATE_SCALING:
                switch (event.getAction()){
                    case MotionEvent.ACTION_MOVE:
                        pathScale(1+(event.getX()-lastX)/300, 1+(event.getY()-lastY)/300);
                        lastX = event.getX();
                        lastY = event.getY();
                        model.reCalculateBound(selectedCurve, model.getInteractionModel().bounds);
                        view.invalidate();
                        break;
                    case MotionEvent.ACTION_UP:
                        state = STATE_READY_TO_OPERATE;
                        break;
                }
                break;
            case STATE_MOVING:
                switch (event.getAction()){
                    case MotionEvent.ACTION_MOVE:
                        pathTranslation(event.getX()-lastX, event.getY()-lastY);
                        lastX = event.getX();
                        lastY = event.getY();
                        model.reCalculateBound(selectedCurve, model.getInteractionModel().bounds);
                        view.invalidate();
                        break;
                    case MotionEvent.ACTION_UP:
                        state = STATE_READY_TO_OPERATE;
                        break;
                }
                break;

            case STATE_DRAWING:
                switch (event.getAction()){
                    case MotionEvent.ACTION_UP:
                        if(!model.addNewCurve()){
                            model.getInteractionModel().clearPoints();
                        }
                        System.out.println("curves: " + model.curves.size());
                        state = STATE_READY;
                        printState();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        model.getInteractionModel().addRawPointAndPath(event.getX(), event.getY());
                        state = STATE_DRAWING;
                        break;
                }
                break;
        }
        return true;
    }

    private boolean inBounds(float x, float y){
        float minX = model.getInteractionModel().bounds.get(0);
        float minY = model.getInteractionModel().bounds.get(1);
        float maxX = model.getInteractionModel().bounds.get(2);
        float maxY = model.getInteractionModel().bounds.get(3);

        return (minX < x && x < maxX && minY < y && y < maxY);
    }

    private void pathTranslation(float x, float y){
        Matrix matrix = new Matrix();
        matrix.setTranslate(x, y);
        for (Path p: selectedCurve){
            p.transform(matrix);
        }
    }

    private void pathScale(float x, float y){
        RectF rectF = new RectF();
        Matrix matrix = new Matrix();
        matrix.setScale(x, y,rectF.centerX(),rectF.centerY());
        for (Path p: selectedCurve){
            p.computeBounds(rectF, true);
            p.transform(matrix);
        }
    }

    private boolean inHandler(float x, float y){
        return abs(x-model.getInteractionModel().handlerX)<20 && abs(y-model.getInteractionModel().handlerY)<20;
    }
}
