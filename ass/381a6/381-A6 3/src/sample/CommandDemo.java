package commanddemo;

import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class CommandDemo extends Application {

     BlobView myView;
     BlobModel myModel;
     ImageView iv;

     @Override
     public void start(Stage primaryStage) {
         myModel = new BlobModel();
         iv = new ImageView();
         myView = new BlobView(myModel, iv);
         myModel.addSubscriber(myView);
         myModel.fillModel();

         VBox root = new VBox();
         root.getChildren().add(myView.myCanvas);
         root.getChildren().add(iv);

         Scene scene = new Scene(root);
         
         // BL: Added onKeyPressed handler to scene, runs method on the view 
         scene.setOnKeyPressed(myView::handleKeyPressed);
         
         primaryStage.setScene(scene);
         primaryStage.show();
     }

     /**
      * @param args the command line arguments
      */
     public static void main(String[] args) {
         launch(args);
     }
}

interface BlobListener {
     public void update();
}

class BlobView implements BlobListener {

     Canvas myCanvas;
     GraphicsContext gc;
     BlobModel myModel;
     Blob singleSelection;
     ArrayList<Blob> selectionSet;
     ImageView iv;
     CommandManager commandManager;
     
     public BlobView(BlobModel newModel, ImageView newIV) {
         myModel = newModel;
         iv = newIV;
         myCanvas = new Canvas(1000, 800);
         gc = myCanvas.getGraphicsContext2D();

         myCanvas.setOnMousePressed(this::handleMousePressed);
         
         singleSelection = null;
         
         // Instantiate the Command Manager
         commandManager = new CommandManager();
     }

     public void handleMousePressed(MouseEvent event) {
         singleSelection = myModel.checkHit(event.getX(), event.getY());
         draw();
     }

    public void handleKeyPressed(KeyEvent ke) {
        String key = ke.getText();
        System.out.println("Key pressed: " + key);

        if (key.equalsIgnoreCase("w")) { // Move up
            if (singleSelection != null) {
                commandManager.performCommand(new MoveUpCommand(singleSelection, 10));
            }
            
        } else if (key.equalsIgnoreCase("a")) { // Move left
            if (singleSelection != null) {
                commandManager.performCommand(new MoveLeftCommand(singleSelection, 10));
            }
            
        } else if (key.equalsIgnoreCase("s")) { // Move down
            if (singleSelection != null) {
                commandManager.performCommand(new MoveDownCommand(singleSelection, 10));
            }
            
        } else if (key.equalsIgnoreCase("d")) { // Move right
            if (singleSelection != null) {
                commandManager.performCommand(new MoveRightCommand(singleSelection, 10));
            }

        } else if (key.equalsIgnoreCase("z")) { // Undo
            commandManager.undo();
            
        } else if (key.equalsIgnoreCase("y")) { // Redo 
            commandManager.redo();
            
        } else if (key.equalsIgnoreCase("r")) { // Start macro recording
            System.out.println("Starting macro recording...");
            commandManager.startRecording();
            
        } else if (key.equalsIgnoreCase("t")) { // Stop macro recording
            System.out.println("Stopping macro recording...");
            commandManager.stopRecording();
            
        } else if (key.equalsIgnoreCase("p")) { // Playback macro
            System.out.println("Playing back the recorded macro");
            commandManager.playbackRecording();
            
        }

        draw();
    }
     
     void checkPixel(int x, int y, ArrayList<Point> q, PixelReader pr, PixelWriter pw) {
         if (!pr.getColor(x, y).equals(Color.RED)) {
             pw.setColor(x, y, Color.RED);
             q.add(new Point(x, y));
         }
         //System.out.println("pixel " + x + "," + y + "is" + pr.getColor(x,y));
     }

     public void draw() {
         gc.clearRect(0, 0, 1000, 800);
         gc.setLineWidth(1);

         for (Blob b : myModel.getBlobs()) {
             if (singleSelection == b) {
                 gc.setFill(Color.GREENYELLOW);
                 gc.fillOval(b.cx - b.radius, b.cy - b.radius, b.radius * 2, b.radius * 2);
                 gc.setFill(Color.BLACK);
                 gc.strokeOval(b.cx - b.radius, b.cy - b.radius, b.radius * 2, b.radius * 2);
             } else {
                 gc.setFill(Color.ORANGE);
                 gc.fillOval(b.cx - b.radius, b.cy - b.radius, b.radius * 2, b.radius * 2);
                 gc.setFill(Color.BLACK);
                 gc.strokeOval(b.cx - b.radius, b.cy - b.radius, b.radius * 2, b.radius * 2);
             }
         }
     }

     @Override
     public void update() {
         draw();
     }
}

class BlobModel {

     ArrayList<Blob> blobs;
     BlobListener mySub;
     int zValue;

     public BlobModel() {
         blobs = new ArrayList<>();
         zValue = 0;
     }

     public void fillModel() {
         for (int i = 0; i < 10; i++) {
             blobs.add(new Blob(Math.random() * 1000, Math.random() * 800, Math.random() * 50 + 20, zValue++));
         }
         mySub.update();
     }

     public List<Blob> getBlobs() {
         return blobs;
     }

     public void addSubscriber(BlobListener newSub) {
         mySub = newSub;
     }

     public Blob checkHit(double x, double y) {
         Blob topBlob = null;
         for (Blob b : blobs) {
             if (b.containsPoint(x, y)) {
                 if (topBlob != null) {
                     if (b.z > topBlob.z) {
                         topBlob = b;
                     }
                 } else {
                     topBlob = b;
                 }
             }
         }
         if (topBlob != null) {
             topBlob.z = zValue++;
         }
         return topBlob;
     }
}

class Blob {

     double cx, cy;
     int z;
     double radius;
     

     public Blob(double newX, double newY, double newR, int newZ) {
         cx = newX;
         cy = newY;
         radius = newR;
         z = newZ;
     }

     public boolean containsPoint(double x, double y) {
         double dist = Math.sqrt(Math.pow(x - cx, 2) + Math.pow(y - cy, 2));
         return (dist < radius);
     }

}

class LineSegment {

     double x1, y1, x2, y2;

     public LineSegment(double newX1, double newY1, double newX2, double newY2) {
         x1 = newX1;
         y1 = newY1;
         x2 = newX2;
         y2 = newY2;
     }
}

class Point {

     int x, y;

     public Point(int newX, int newY) {
         x = newX;
         y = newY;
     }
}
