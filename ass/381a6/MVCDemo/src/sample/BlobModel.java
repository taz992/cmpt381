package sample;

import java.util.ArrayList;

public class BlobModel {
    Blob singleBlob;
    ArrayList<BlobModelListener> subscribers;

    public BlobModel() {
        subscribers = new ArrayList<>();
        singleBlob = new Blob(100,100,150,100);
    }

    public Blob getSingleBlob() {
        return singleBlob;
    }

    public boolean checkHit(double x, double y) {
        return singleBlob.checkHit(x,y);
    }

    public void moveBlob(double dX, double dY) {
        singleBlob.move(dX,dY);
        notifySubscribers();
    }

    public void addSubscriber (BlobModelListener aSub) {
        subscribers.add(aSub);
    }
    private void notifySubscribers() {
        subscribers.forEach(sub -> sub.modelChanged());
    }
}
