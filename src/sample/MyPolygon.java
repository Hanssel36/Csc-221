package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import static java.lang.Math.tan;
import static java.lang.Math.sin;
import static java.lang.Math.cos;
import static java.lang.Math.PI;
import static java.lang.Math.sqrt;
import static java.lang.Math.pow;

public class MyPolygon extends MyShape{
    public double[] x;
    public double[] y;
    double w1, h1, radi;
    int numside;
    public MyPolygon(double posX, double posY, double w1, double h1, double radiC, int n, Color C){
        this.setX(posX);
        this.setY(posY);
        this.setWidth(w1);
        this.setHeight(h1);
        this.setColor(C);
        this.radi = radiC;
        this.numside = n;
        x = new double[numside];
        y = new double[numside];
        for(int i = 0; i < n; i++){
            x[i] = getX()+ (radi*(-sin(i*(2*PI)/n)));
            y[i] = getY()+ (radi*(-cos(i*(2*PI)/n)));

        }
    }

    public void setWidth(double X){
        this.w1 = X;
    }

    public double getWidth(){
        return w1;
    }

    public void setHeight(double X){
        this.h1 = X;
    }

    public double getHeight(){
        return h1;
    }

    public double getSide(){
        // gets the length of one of the the sides.
        return sqrt(pow((x[0]-x[1]),2) + pow((y[0]-y[1]),2));
    }

    public double getArea(){
        double bottom = 4*tan(PI/numside);
        double top = getSide()*getSide()*numside;
        return top/bottom;
    }

    public void getMyBoundingBox(GraphicsContext gc){
        double w = getWidth()/2;
        double h = getHeight()/2;
        gc.strokeRect(getX()-w,getY()-h,getWidth(),getHeight());

    }

    public double getPerimeter(){
        return numside*getSide();
    }

    public double getInteriorangle(){
        return 180 -(360/numside);
    }

    @Override
    public String toString(){
        return "Area: " + getArea() + "\n" + "Perimeter: " + getPerimeter() + "\n" + "Interior angle: " + getInteriorangle() + "\n" + "side length: " + getSide();
    }
    // this is regular polygon so all sides should be equal.
    public void distanceTo(double dx, double dy){
        System.out.println( "Distance To " + sqrt(pow((dx-getX()),2) + pow((dy-getY()),2))); // Center point to a certain point.
    }

    public void getPoint(){

        System.out.printf("X = %f Y = %f \n", getX(), getY()); // returns the center of the polygon
    }

    // polygon was different with how points are drawn so have to move all the points to maintain shape.
    public void moveTo(double cx, double cy){
        for(int i = 0; i < numside; i++){
            x[i] = x[i] + cx;
            y[i] = y[i] + cy;

        }
    }

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

    public void draw(GraphicsContext gc){
        // This is so the Oval is in the center of the canvas.
//        double w = getWidth()/2;
//        double h = getHeight()/2;
        gc.setFill(getColor());
        gc.fillPolygon(x,y,numside);
    }
}
