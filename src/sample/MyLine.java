package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import static java.lang.Math.sqrt;
import static java.lang.Math.pow;
import static java.lang.Math.atan;
import static java.lang.Math.toDegrees;

public class MyLine extends MyShape{
   protected double x2, y2;

    public MyLine(double x0, double y0, double x1, double y1, Color C){
        this.setX(x0);
        this.setY(y0);
        this.setX2(x1);
        this.setY2(y1);
        this.setColor(C);
    }
    @Override
    public String toString(){
        return "Length " + sqrt(pow((getX2()-getX()),2) + pow((getY2()-getY()),2)) + "\n" // Length uses distance formula sqrt((x2-x1)^2 + (y2-y1)^2)
                + "Angle " + toDegrees(atan((getY2()-getY())/(getX2()-getX())));
    }

    // sets x2 which is x end point of line.
    public void setX2(double X2){ this.x2 = X2; }

    public double getX2(){ return this.x2; }

    public double getY2(){ return this.y2; }
    // sets y2 which is y end point of line.
    public void setY2(double Y2){ this.y2 = Y2; }

    public void getMyBoundingBox(GraphicsContext gc){}

    public boolean doOverlap(MyShape F, MyShape S){
        if(F.getX() == S.getX()) {
            return true;
        }
        if(F.getY() == S.getY()) {
            return true;
        }
        else {
            return false;
        }
    }

    public void getPoint(){
        System.out.println(getX() + getY());
    }

    public void moveTo(double cx, double cy){
        setX(cx+getX());
        setY(cy+getY());
    }

    public void distanceTo(double dx, double dy){

        System.out.println( "Distance To " + sqrt(pow((dx-getX()),2) + pow((dy-getY()),2)));
    }

    public void draw(GraphicsContext gc){
        gc.setStroke(getColor());
        gc.strokeLine(getX(),getY(), getX2(),getY2());

    }
}
