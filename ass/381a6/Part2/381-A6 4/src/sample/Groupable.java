
package sample;

import java.util.ArrayList;
import javafx.scene.canvas.GraphicsContext;

public interface Groupable {
    
    boolean hasChildren();

    ArrayList<Groupable> getChildren();

    boolean contains(double x, double y);

    boolean isContained(double x1, double y1, double x2, double y2);

    double getLeft();

    double getRight();

    double getTop();

    double getBottom();

    void move(double dx, double dy);
}
