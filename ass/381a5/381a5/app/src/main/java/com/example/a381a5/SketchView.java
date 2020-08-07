package com.example.a381a5;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;
import java.util.ArrayList;

public class SketchView extends View implements SketchListener {
    SketchModel model;
    SketchController controller;

    public SketchView(Context context) {


        super(context);
        setBackgroundColor(Color.LTGRAY);
        System.out.println("VIEW initialized");
    }

    public void setModel(SketchModel model) {
        this.model = model;
    }

    @Override
    public void modelChanged() {
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        Paint paint = new Paint();

        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);
        paint.setColor(Color.BLACK);
        for (Path path: model.getInteractionModel().rawPaths){
            canvas.drawPath(path, paint);
        }

        paint.setColor(Color.RED);
        for (ArrayList<SketchPath> curves: model.curves){
//            System.out.println(model.getInteractionModel().getSmoothedPoints());
            for (SketchPath path: curves){
                canvas.drawPath(path, paint);
            }
        }

        if (controller.selectedCurve != null){
            paint.setColor(Color.BLUE);
            for (Path path: controller.selectedCurve){
                canvas.drawPath(path, paint);
            }

            paint.setStyle(Paint.Style.STROKE);
            paint.setColor(Color.BLACK);
            paint.setStrokeWidth(5);
            canvas.drawRect(controller.model.getInteractionModel().bounds.get(0),
                    controller.model.getInteractionModel().bounds.get(1),
                    controller.model.getInteractionModel().bounds.get(2),
                    controller.model.getInteractionModel().bounds.get(3),
                    paint);

            paint.setStyle(Paint.Style.FILL);
            paint.setColor(Color.BLACK);
            canvas.drawOval(model.getInteractionModel().handlerX-15,
                    model.getInteractionModel().handlerY-15,
                    model.getInteractionModel().handlerX+15,
                    model.getInteractionModel().handlerY+15, paint);
        }
    }
}

