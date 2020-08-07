package sample;

import com.sun.prism.Graphics;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.ArcType;
import javafx.scene.Group;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class Main extends Application {


    BorderPane border = new BorderPane();

    ListView<String> listView;

    FlowPane flowPane = new FlowPane();

    Circle circle = new Circle();




    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        //primaryStage.setTitle("Hello World");
        //primaryStage.setScene(new Scene(root, 300, 275));
        //primaryStage.show();

        //HBox hbox = addHBox();
        border.setTop(addHBox());
        border.setLeft(addVBox());
        //addStackPane(hbox);         // Add stack to HBox in top region
        border.setCenter(addGridPane());
        border.setRight(addFlowPane());
        border.setPrefSize(500,400);
        //public static final ObservableList names FXCollections.observableArrayList();
        border.setBottom(addbottom());
        primaryStage.setTitle("layout demo");
        primaryStage.setScene(new Scene(border, 1000, 750));
        primaryStage.show();
    }

//top
    public MenuBar addHBox() {
        //file meun
        Menu filemenu = new Menu("file");
        //menu items
        filemenu.getItems().add(new MenuItem("file1"));
        filemenu.getItems().add(new MenuItem("file2"));
        filemenu.getItems().add(new MenuItem("file3"));
        filemenu.getItems().add(new MenuItem("file4"));
        filemenu.getItems().add(new MenuItem("file5"));
        filemenu.getItems().add(new MenuItem("file6"));
        filemenu.getItems().add(new MenuItem("file7"));
        filemenu.getItems().add(new MenuItem("file8"));
        filemenu.getItems().add(new MenuItem("file9"));
        filemenu.getItems().add(new MenuItem("file10"));
        filemenu.getItems().add(new MenuItem("file11"));

        Menu editmenu = new Menu("edit");
        editmenu.getItems().add(new MenuItem("edit1"));
        editmenu.getItems().add(new MenuItem("edit2"));
        editmenu.getItems().add(new MenuItem("edit3"));
        editmenu.getItems().add(new MenuItem("edit4"));
        editmenu.getItems().add(new MenuItem("edit5"));
        editmenu.getItems().add(new MenuItem("edit6"));
        editmenu.getItems().add(new MenuItem("edit7"));
        editmenu.getItems().add(new MenuItem("edit8"));
        editmenu.getItems().add(new MenuItem("edit9"));
        editmenu.getItems().add(new MenuItem("edit10"));
        editmenu.getItems().add(new MenuItem("edit11"));

        Menu viewmenu = new Menu(("view"));
        viewmenu.getItems().add(new MenuItem("view1"));
        viewmenu.getItems().add(new MenuItem("view2"));
        viewmenu.getItems().add(new MenuItem("view3"));
        viewmenu.getItems().add(new MenuItem("view4"));
        viewmenu.getItems().add(new MenuItem("view5"));
        viewmenu.getItems().add(new MenuItem("view6"));
        viewmenu.getItems().add(new MenuItem("view7"));
        viewmenu.getItems().add(new MenuItem("view8"));
        viewmenu.getItems().add(new MenuItem("view9"));
        viewmenu.getItems().add(new MenuItem("view10"));
        viewmenu.getItems().add(new MenuItem("view11"));

        //main menubar
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(filemenu,editmenu,viewmenu);

        return menuBar;
    }
//right
    public Node addFlowPane() {
        listView = new ListView<>();
        listView.getItems().addAll("context1","context2","context3","context4","context5","context6","context7",
                "context8","context9","context10","context11","context12","context13","context14","context15",
                "context16","context17","context18","context19","context20","context21","context22","context23",
                "context24","context25","context26");
        listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        return listView;
    }
//centre
    public Node addGridPane() {

        Group root = new Group();
        Canvas canvas = new Canvas(300,250);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.THISTLE);

        //root.setBackground(Color.BLUE);


        return root;
    }

//left
    public Node addVBox() {
        Scene scene = new Scene(flowPane, 150, 100);
        //1
        flowPane.setPrefWidth(200);
        //circle.setCenterX(25);
        //circle.setCenterY(25);
        //circle.setRadius(25);
        //circle.setFill(Color.RED);
        //flowPane.getChildren().add(circle);

        Circle circle9 = new Circle();
        //1
        Circle circle = new Circle(25,25,25,Color.RED);
        //2
        Circle circle1 = new Circle(75,25,25,Color.BLACK);
        //3
        Circle circle2 = new Circle(125,25,25,Color.GREEN);
        //4
        Circle circle3 = new Circle(175,25,25,Color.YELLOW);

        //5
        Circle circle4 = new Circle(75,75,25,Color.FIREBRICK);
        //6
        Circle circle5 = new Circle(125,75,25,Color.AZURE);
        //7
        Circle circle6 = new Circle(175,75,25,Color.MEDIUMAQUAMARINE);
        //8
        Circle circle7 = new Circle(225,75,25,Color.SANDYBROWN);

        //9
        Circle circle8 = new Circle(75,125,25,Color.CHOCOLATE);
        //10
        Circle circle10 = new Circle(125,125,25,Color.LIGHTBLUE);
        //11
        Circle circle11 = new Circle(175,125,25,Color.DARKKHAKI);
        //12
        Circle circle12 = new Circle(225,125,25,Color.DARKOLIVEGREEN);


        //13
        Circle circle13 = new Circle(75,175,25,Color.FUCHSIA);
        //14
        Circle circle14 = new Circle(125,175,25,Color.IVORY);
        //15
        Circle circle15 = new Circle(175,175,25,Color.OLIVEDRAB);
        //16
        Circle circle16 = new Circle(225,175,25,Color.LIGHTPINK);


        //17
        Circle circle17 = new Circle(75,225,25,Color.SALMON);
        //18
        Circle circle18 = new Circle(125,225,25,Color.MEDIUMSPRINGGREEN);
        //19
        Circle circle19 = new Circle(175,225,25,Color.CORNFLOWERBLUE);
        //20
        Circle circle20 = new Circle(225,225,25,Color.LAVENDER);

        //21
        Circle circle21 = new Circle(75,275,25,Color.CORNFLOWERBLUE);
        //22
        Circle circle22 = new Circle(125,275,25,Color.AQUAMARINE);
        //23
        Circle circle23 = new Circle(175,275,25,Color.BEIGE);
        //24
        Circle circle24 = new Circle(225,275,25,Color.DARKSALMON);

        //25
        Circle circle25 = new Circle(75,325,25,Color.LIME);
        //26
        Circle circle26 = new Circle(125,325,25,Color.TURQUOISE);
        //27
        Circle circle27 = new Circle(175,325,25,Color.DEEPPINK);
        //28
        Circle circle28 = new Circle(225,325,25,Color.ORCHID);

        //29
        Circle circle29 = new Circle(75,375,25,Color.SILVER);
        //30
        Circle circle30 = new Circle(125,375,25,Color.BROWN);
        //31
        Circle circle31 = new Circle(175,375,25,Color.ROSYBROWN);
        //32
        Circle circle32 = new Circle(225,375,25,Color.KHAKI);

        //33
        Circle circle33 = new Circle(75,425,25,Color.CORNFLOWERBLUE);
        //34
        Circle circle34 = new Circle(125,425,25,Color.LAVENDER);
        //35
        Circle circle35 = new Circle(175,425,25,Color.SALMON);
        //36
        Circle circle36 = new Circle(225,425,25,Color.MEDIUMSPRINGGREEN);

        //37
        Circle circle37 = new Circle(75,475,25,Color.THISTLE);
        //38
        Circle circle38 = new Circle(125,475,25,Color.TAN);
        //39
        Circle circle39 = new Circle(175,475,25,Color.LIME);
        //40
        Circle circle40 = new Circle(225,475,25,Color.ROSYBROWN);


        flowPane.getChildren().addAll(circle,circle1,circle2,circle3,circle4,circle5,circle6,circle7,circle8,circle10,
                circle11,circle12,circle13,circle14,circle15,circle16,circle17,circle18,circle19,circle20,circle21,
                circle22,circle23,circle24,circle25,circle26,circle27,circle28,circle29,circle30,circle31,circle32,
                circle33,circle34,circle35,circle36,circle37,circle38,circle39,circle40);

        /*
        for(int i =1;i<=40;i++){
            flowPane.getChildren().add(new Circle(Double.valueOf(i)));
        }
        */

        return flowPane;
    }
//bottom

    public Node addbottom()
    {

        FlowPane root = new FlowPane();
        root.setMaxHeight(50);
        root.setPrefWidth(200);
        root.getChildren().add(new TextArea(String.valueOf("ListView Selection: none")));


        return root;
    }

    public void RowLayoutPane(){
        //public void addwidget(Widget w){

        //}
    }
    public void RowCell(){
        //addWidge(widget(w));
        //public void drawCell(GraphicsContext gc){

        //}
    }
    public void widget(){
        FlowPane fix = new FlowPane();
        fix.setMinSize(200, 300);
        fix.setMaxSize(400, 600);
        fix.setPrefSize(300, 450);
    }


    public static void main(String[] args) {
        launch(args);
    }

}
