package sample;


import javafx.scene.input.MouseEvent;

public class BlobController {
    BlobModel model;
    double prevX, prevY;

    private enum State {
        READY, DRAGGING
    }

    private State currentState;

    public BlobController() {
        currentState = State.READY;
    }

    public void setModel(BlobModel newModel) {
        model = newModel;
    }

    public void handlePressed(MouseEvent event) {
        if (currentState == State.READY) {
            // hit detection
            boolean hit = model.checkHit(event.getX(), event.getY());
            if (hit) {
                // set start location for drag
                prevX = event.getX();
                prevY = event.getY();
                currentState = State.DRAGGING;
            }
        }
    }

    public void handleDrag(MouseEvent event) {
        if (currentState == State.DRAGGING) {
            // calculate amount of movement
            double dX = event.getX() - prevX;
            double dY = event.getY() - prevY;
            prevX = event.getX();
            prevY = event.getY();
            // move blob
            model.moveBlob(dX, dY);
        }
    }

    public void handleRelease(MouseEvent event) {
        // release selection
        currentState = State.READY;
    }
}
