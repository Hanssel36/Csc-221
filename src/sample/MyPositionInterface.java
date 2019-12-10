package sample;

public interface MyPositionInterface {
    abstract public void getPoint(); // returns the point of origin for the line and the center point for shapes
    abstract public void moveTo(double cx, double cy); // moves the point added to what are arguments are.
    abstract public void distanceTo(double dx, double dy); // uses distances formula to determine distance.

}
