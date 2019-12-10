package sample;

import javafx.scene.canvas.GraphicsContext;

public interface MyShapePositionInterface extends MyPositionInterface {
        abstract public void getMyBoundingBox(GraphicsContext gc);// draws a bounding box around the shappe.
        abstract boolean doOverlap(MyShape F, MyShape S); // uses x and y to determine if there is overlap.
}
