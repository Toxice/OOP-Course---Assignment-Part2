package GameEntities.CollidableObjects;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Rectangle Class for the Project
 */
public class Rectangle {
    Point upperLeft;
    double width;
    double height;

    // Create a new rectangle with location and width/height.
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * Returns a list of all Points the Rectangle Intersects with
     * @param line: Line Object
     * @return List of Points (can be empty) that intersects with the line
     */
    // Return a (possibly empty) List of intersection points
    // with the specified line.
    public List<Point> intersectionPoints(Line line) {
        // not implemented yet!
        return new ArrayList<>();
    }

    public Point getUpperLeft() {
        return this.upperLeft;
    }

    public double getWidth() {
        return this.width;
    }

    public double getHeight() {
        return this.height;
    }
}
