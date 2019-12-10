package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import static java.lang.Math.PI;
import static java.lang.Math.sqrt;
import static java.lang.Math.pow;

public class MyOval extends MyShape {

    private double w1,h1;

    public MyOval(double posX, double posY, double w1, double h1, Color C){
        this.setX(posX);
        this.setY(posY);
        this.setWidth(w1);
        this.setHeight(h1);
        this.setColor(C);
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

    public double getPerimeter(){
        return 2*(getHeight()+ getWidth());
    }

    public void moveTo(double cx, double cy){
        setX(cx+getX());
        setY(cy+getY());
    }

    // Area is Width*Height*PI.
    public double getArea(){
        return getHeight()*getWidth()*PI;
    }

    @Override
    public String toString(){
        return "Area: " + getArea() + "\n" + "Perimeter: " + getPerimeter() + "\n" + "Major Axis Length: " + getWidth() + "\n" + "Minor Axis Length: " + getHeight();
    }

    public void getPoint(){

        System.out.printf("X = %f Y = %f \n", getX(), getY());
    }

    public void distanceTo(double dx, double dy){

        System.out.println( "Distance To " + sqrt(pow((dx-getX()),2) + pow((dy-getY()),2)));
    }

    public void getMyBoundingBox(GraphicsContext gc){
        double w = getWidth()/2;
        double h = getHeight()/2;
        gc.strokeRect(getX()-w,getY()-h,getHeight(),getWidth());

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
        double w = getWidth()/2;
        double h = getHeight()/2;
        gc.setFill(getColor());
        gc.fillOval(getX()-w,getY()-h,getWidth(),getHeight());
    }

}
