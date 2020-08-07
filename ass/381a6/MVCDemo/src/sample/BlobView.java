package sample;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class BlobView extends Pane implements BlobModelListener {
    Canvas myCanvas;
    GraphicsContext gc;
    double width, height;
    BlobModel model;
    BlobController controller;

    public BlobView(int newW, int newH) {
        width = newW;
        height = newH;
        setStyle("-fx-background-color: mediumseagreen");
        myCanvas = new Canvas(width, height);
        gc = myCanvas.getGraphicsContext2D();
        getChildren().add(myCanvas);
    }

    public void setModel(BlobModel newModel) {
        model = newModel;
    }

    public void setController(BlobController newController) {
        controller = newController;
        myCanvas.setOnMousePressed(controller::handlePressed);
        myCanvas.setOnMouseDragged(controller::handleDrag);
        myCanvas.setOnMouseReleased(controller::handleRelease);
    }

    public void draw() {
        gc.clearRect(0,0,width,height);
        Blob b = model.getSingleBlob();
        gc.setFill(Color.ORANGERED);
        gc.fillRect(b.x, b.y, b.width, b.height);
    }

    public void layoutChildren() {
        width = this.getWidth();
        height = this.getHeight();
        myCanvas.setWidth(width);
        myCanvas.setHeight(height);
        draw();
    }

    public void modelChanged() {
        draw();
    }
}
