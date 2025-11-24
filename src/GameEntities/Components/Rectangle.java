package GameEntities.Components;

import biuoop.DrawSurface;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Rectangle Class for the Project
 */
public class Rectangle {
    GameEntities.Components.Point upperLeft;
    double width;
    double height;
    Color color;

    // Create a new rectangle with location and width/height.
    public Rectangle(GameEntities.Components.Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    public Rectangle(GameEntities.Components.Point upperLeft, double width, double height, Color color) {
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
    public List<GameEntities.Components.Point> intersectionPoints(Line line) {
        GameEntities.Components.Point upperLeft = getUpperLeft();
        GameEntities.Components.Point upperRight = getUpperRight();
        GameEntities.Components.Point lowerLeft = getLowerLeft();
        GameEntities.Components.Point lowerRight = getLowerRight();
        List<Line> rectangleLines = getRectangleEdges(upperLeft, upperRight, lowerLeft, lowerRight);
        List<GameEntities.Components.Point> intersections = new ArrayList<>();
        for (Line lineEdge: rectangleLines) {
            if (line.intersectionWith(lineEdge) != null) {
                intersections.add(line.intersectionWith(lineEdge));
            }
        }
        return intersections;
    }

    public GameEntities.Components.Point getUpperLeft() {
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

    public GameEntities.Components.Point getUpperRight() {
        return new GameEntities.Components.Point(this.width, this.upperLeft.getY());
    }

    public GameEntities.Components.Point getLowerLeft() {
        return new GameEntities.Components.Point(-this.width, this.height);
    }

    public GameEntities.Components.Point getLowerRight() {
        return new GameEntities.Components.Point(this.width, this.height);
    }

    public ArrayList<Line> getRectangleEdges(GameEntities.Components.Point upperLeft, GameEntities.Components.Point upperRight, GameEntities.Components.Point lowerLeft, Point lowerRight) {
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
