package sample;

import javafx.scene.paint.Color;
import javafx.scene.canvas.GraphicsContext;

public abstract class MyShape implements MyShapePositionInterface {
    // x and y are used for GetX(), GetY(), SetX(), and SetY() methods. they can be use for beginning points.
    // colour is used to set the color of the shape or line
    private double x, y;
    private Color colour;

    public double getX(){
        return this.x;
    }

    public void setX(double X){ this.x = X; }

    public double getY(){
        return this.y;
    }

    public void setY(double Y){
        this.y = Y;
    }


    public void setColor(Color c){
        this.colour = c;
    }

    public Color getColor(){

        return colour;
    }

    @Override
    public String toString(){
        return "X value: " + getX() + "\n" +"Y Value: " + getY() + "\n" + "Color: " + getColor();
    }


    public abstract void draw(GraphicsContext gc);
}
