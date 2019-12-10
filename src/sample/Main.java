package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import javafx.scene.layout.FlowPane;
import static java.lang.Math.tan;
import static java.lang.Math.PI;

public class Main extends Application
{

    public void start(Stage primaryStage)
    {
        Canvas canvas = new Canvas(600,600);

        GraphicsContext gc = canvas.getGraphicsContext2D();
        FlowPane flow = new FlowPane();
        Scene scene = new Scene(flow, canvas.getWidth(), canvas.getHeight());

        // The Colors I created for the shapes using myColor
        myColor C = myColor.Blue;
        myColor G = myColor.Green;
        myColor S = myColor.Silver;
        myColor B = myColor.Black;


        double a = canvas.getWidth()/2;
        double b = canvas.getHeight()/2;

        int num = 5;
        MyLine line1 = new MyLine(0,0,canvas.getWidth(),canvas.getHeight(),B.returnColour());
        MyLine line2 = new MyLine(canvas.getWidth(),0,0,canvas.getHeight(),B.returnColour());

        MyCircle circ1 = new MyCircle(a,b,a,a,C.returnColour());
        MyPolygon poly1 = new MyPolygon( a,b,a,b,circ1.getRadius(), num,S.returnColour());
        double inradius = poly1.getSide()/(tan(PI/num));  // used to get radius of the polygon
        MyCircle circ2 = new MyCircle(a,b,inradius,inradius,C.returnColour());
        MyPolygon poly2 = new MyPolygon( a,b,inradius,inradius,circ2.getRadius(), num,S.returnColour());
        double inradius2 = poly2.getSide()/(tan(PI/num));
        MyCircle circ3 = new MyCircle(a,b,inradius2,inradius2,C.returnColour());
        MyPolygon poly3 = new MyPolygon( a,b,inradius2,inradius2,circ3.getRadius(), num,G.returnColour());
        double inradius3 = poly3.getSide()/(tan(PI/num));
        MyCircle circ4 = new MyCircle(a,b,inradius3,inradius3,C.returnColour());

        circ1.draw(gc);
        poly1.draw(gc);
        circ2.draw(gc);
        poly2.draw(gc);
        circ3.draw(gc);
        poly3.draw(gc);
        circ4.draw(gc);

        line1.draw(gc);
        line2.draw(gc);

        flow.getChildren().addAll(canvas);
        primaryStage.setTitle("Assignment 2");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String args[]){
    launch(args);
    }

}