package sample;


import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.List;

public class BlobController {
    BlobModel model;
    double prevX, prevY;
    InteractionModel iModel;

    private enum State {
        READY, DRAGGING, PREPARE_RUBBER, RUBBERBAND
    }

    private State currentState;

    public BlobController() {
        currentState = State.READY;
    }

    public void setModel(BlobModel newModel) {
        model = newModel;
    }

    public void setInteractionModel(InteractionModel newModel) {
        iModel = newModel;
    }

    public void handlePressed(MouseEvent event) {
        switch (currentState) {
            case READY:
                // context: are we on a blob? (used in later tests)
                boolean hit = model.checkHit(event.getX(), event.getY());
                // context: if hit, what did we hit, and was it on a selected group?
                boolean wasSelected = false;
                Groupable hitGroup = null;
                if (hit) {
                    hitGroup = model.find(event.getX(), event.getY());
                    wasSelected = iModel.isSelected(hitGroup);
                }
                // context: CTRL + hit on blob + either selected or not
                if (iModel.controlDown && hit) {
                    // side effects: add/subtract, don't set up drag
                    iModel.addSubtractSelection(hitGroup);
                    // transition: stay READY
                }
                // context: CTRL + not hit
                if (iModel.controlDown && !hit) {
                    // side effects:
                    // - set up rubberband
                    prevX = event.getX();
                    prevY = event.getY();
                    // transition to new state
                    currentState = State.PREPARE_RUBBER;
                }
                // context: not CTRL, hit on blob, already selected
                if (!iModel.controlDown && hit && wasSelected) {
                    // side effects:
                    // - set up for drag
                    prevX = event.getX();
                    prevY = event.getY();
                    // - leave the selection as is
                    // transition to new state
                    currentState = State.DRAGGING;
                }
                // context: not CTRL, hit on blob, not selected
                if (!iModel.controlDown && hit && !wasSelected) {
                    // side effects:
                    // - set up for drag
                    prevX = event.getX();
                    prevY = event.getY();
                    // - set selection (un-setting any old selection)
                    iModel.setSelection(hitGroup);
                    // transition to new state
                    currentState = State.DRAGGING;
                }
                // context: not CTRL, hit on background
                if (!iModel.controlDown && !hit) {
                    // side effects:
                    // - set up rubberband
                    prevX = event.getX();
                    prevY = event.getY();
                    // - clear selection
                    iModel.clearSelection();
                    // transition to new state
                    currentState = State.PREPARE_RUBBER;
                }
                break;
        }
    }

    public void handleDrag(MouseEvent event) {
        switch (currentState) {
            case DRAGGING:
                // context: none
                // side effects:
                // - calculate amount of movement
                double dX = event.getX() - prevX;
                double dY = event.getY() - prevY;
                prevX = event.getX();
                prevY = event.getY();
                // - move blob
                model.move(iModel.selection, dX, dY);
                // transition to new state: same state
                break;

            case PREPARE_RUBBER:
                // context: none
                // side effects:
                // - create rubberband
                iModel.createRubber(prevX, prevY);
                // - update rubberband
                iModel.setRubberEnd(event.getX(), event.getY());
                // transition to new state
                currentState = State.RUBBERBAND;
                break;

            case RUBBERBAND:
                // context: none
                // side effects:
                // - update rubberband selection
                iModel.setRubberEnd(event.getX(), event.getY());
                // transition to new state: stay here
                break;
        }
    }

    public void handleRelease(MouseEvent event) {
        switch (currentState) {
            case DRAGGING:
                // context: none
                // side effects: none
                // transition to new state
                currentState = State.READY;
                break;

            case PREPARE_RUBBER:
                // context: none
                // side effects:
                iModel.clearSelection();
                // transition to new state
                currentState = State.READY;
                break;

            case RUBBERBAND:
                // context: none
                // side effects:
                // - see if we selected anything with the rubberband
                List<Groupable> rubberSet = model.findInRubberband(iModel.rubber.left,
                        iModel.rubber.top, iModel.rubber.left + iModel.rubber.width,
                        iModel.rubber.top + iModel.rubber.height);
                iModel.addSubtractSelection(rubberSet);
                iModel.deleteRubber();
                // transition to new state
                currentState = State.READY;
                break;
        }
    }

    public void handleKeyPressed(KeyEvent event) {
        switch (event.getCode()) {
            case CONTROL:
                iModel.setControl(true);
                break;
            case A:
                System.out.println("A");
                model.createBlob(Math.random()*iModel.viewWidth,
                        Math.random()*iModel.viewHeight);
                break;
        }
    }

    public void handleKeyReleased(KeyEvent event) {
        switch (event.getCode()) {
            case CONTROL:
                iModel.setControl(false);
                break;
            case G:
                if (iModel.selection.size() > 0) {
                    Groupable newGroup = model.createGroup(iModel.selection);
                    iModel.setSelection(newGroup);
                }
                break;
            case U:
                if (iModel.selection.size() > 0) {
                    if (iModel.selection.size() == 1 && iModel.selection.get(0).hasChildren()) {
                        ArrayList<Groupable> items = model.unGroup(iModel.selection.get(0));
                        iModel.addSubtractSelection(items);
                        iModel.addSubtractSelection(iModel.selection.get(0));
                    }
                }
                break;
        }
    }
}