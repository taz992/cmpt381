package sample;

public class Blob {
    double x, y;
    double width, height;

    public Blob(double newX, double newY, double newW, double newH) {
        x = newX;
        y = newY;
        width = newW;
        height = newH;
    }

    public boolean checkHit(double clickX, double clickY) {
        return clickX >= x && clickX <= x+width && clickY >= y && clickY <= y+height;
    }

    public void move(double dX, double dY) {
        x += dX;
        y += dY;
    }
}
