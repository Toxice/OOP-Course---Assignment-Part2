package GameEntities.CollidableObjects;

import biuoop.DrawSurface;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Rectangle Class for the Project
 */
public class Rectangle {
    Point upperLeft;
    double width;
    double height;
    Color color;

    // Create a new rectangle with location and width/height.
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    public Rectangle(Point upperLeft, double width, double height, Color color) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    /**
     * Checks for intersection against all 4 building lines of the rectangle (as Line Objects)
     * @param line: some Line Object
     * @return a List of Points (can be empty) that intersects with the line
     */
    public List<Point> intersectionPoints(Line line) {
        Point upperLeft = getUpperLeft();
        Point upperRight = getUpperRight();
        Point lowerLeft = getLowerLeft();
        Point lowerRight = getLowerRight();
        List<Line> rectangleLines = getRectangleEdges(upperLeft, upperRight, lowerLeft, lowerRight);
        List<Point> intersections = new ArrayList<>();
        for (Line lineEdge: rectangleLines) {
            if (line.intersectionWith(lineEdge) != null) {
                intersections.add(line.intersectionWith(lineEdge));
            }
        }
        return intersections;
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

    public Color getColor() {
        return this.color;
    }

    public Point getUpperRight() {
        return new Point(this.width, this.upperLeft.getY());
    }

    public Point getLowerLeft() {
        return new Point(-this.width, this.height);
    }

    public Point getLowerRight() {
        return new Point(this.width, this.height);
    }

    public ArrayList<Line> getRectangleEdges(Point upperLeft, Point upperRight, Point lowerLeft, Point lowerRight) {
        Line upperLine = new Line(upperLeft, upperRight);
        Line lowerLine = new Line(lowerLeft, lowerRight);
        Line leftLine = new Line(upperLeft, lowerLeft);
        Line rightLine = new Line(upperRight, lowerRight);
        ArrayList<Line> recLines = new ArrayList<>();
        recLines.add(upperLine);
        recLines.add(lowerLine);
        recLines.add(leftLine);
        recLines.add(rightLine);
        return recLines;


    }

    /**
     * Draws the rectangle to the DrawSurface (without filling)
     * @param drawSurface: DrawSurface Object
     * @param color: java.awt.Color
     */
    public void drawOn(DrawSurface drawSurface, Color color) {
        drawSurface.setColor(color);
        drawSurface.drawRectangle((int)getUpperLeft().getX(), (int)getUpperLeft().getY(), (int)getWidth(), (int)getHeight());
    }

    /**
     * Draws the rectangle to the DrawSurface (with filling)
     * @param drawSurface: DrawSurface Object
     */
    public void fillOn(DrawSurface drawSurface) {
        drawSurface.setColor(color);
        drawSurface.fillRectangle((int)getUpperLeft().getX(), (int)getUpperLeft().getY(), (int)getWidth(), (int)getHeight());
    }
}
