package sample;

import java.util.ArrayList;
import java.util.List;

public class InteractionModel {
    ArrayList<Groupable> selection;
    ArrayList<BlobModelListener> subscribers;
    RubberRectangle rubber;
    boolean controlDown;
    double viewWidth, viewHeight;

    public InteractionModel() {
        subscribers = new ArrayList<>();
        selection = new ArrayList<>();
        rubber = null;
        controlDown = false;
    }

    public void recordViewSize(double w, double h) {
        viewWidth = w;
        viewHeight = h;
    }

    public void setControl(boolean isDown) {
        controlDown = isDown;
        notifySubscribers();
    }

    public void clearSelection() {
        selection.clear();
        notifySubscribers();
    }

    public void setSelection(Groupable g) {
        selection.clear();
        selection.add(g);
        notifySubscribers();
    }

    public void setSelection(ArrayList<Groupable> group) {
        selection = group;
        notifySubscribers();
    }

    public boolean isSelected(Groupable g) {
        return selection.contains(g);
    }

    public void createRubber(double x1, double y1) {
        rubber = new RubberRectangle(x1, y1);
    }

    public void setRubberEnd(double x2, double y2) {
        rubber.updateCoords(x2, y2);
        notifySubscribers();
    }

    public boolean hasRubberband() {
        return (rubber != null);
    }

    public void deleteRubber() {
        rubber = null;
        notifySubscribers();
    }

    public void addSubtractSelection(Groupable g) {
        if (selection.contains(g)) {
            selection.remove(g);
        } else {
            selection.add(g);
        }
        notifySubscribers();
    }

    public void addSubtractSelection(List<Groupable> set) {
        set.forEach(g -> addSubtractSelection(g));
    }

    public void addSubscriber (BlobModelListener aSub) {
        subscribers.add(aSub);
    }

    private void notifySubscribers() {
        subscribers.forEach(sub -> sub.modelChanged());
    }
}
