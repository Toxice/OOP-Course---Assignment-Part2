package Game.GameEntities.DrawbleObjects.Components;

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
    public List<Game.GameEntities.DrawbleObjects.Components.Point> intersectionPoints(Line line) {
        Game.GameEntities.DrawbleObjects.Components.Point upperLeft = getUpperLeft();
        Game.GameEntities.DrawbleObjects.Components.Point upperRight = getUpperRight();
        Game.GameEntities.DrawbleObjects.Components.Point lowerLeft = getLowerLeft();
        Game.GameEntities.DrawbleObjects.Components.Point lowerRight = getLowerRight();
        List<Line> rectangleLines = getRectangleEdges(upperLeft, upperRight, lowerLeft, lowerRight);
        List<Game.GameEntities.DrawbleObjects.Components.Point> intersections = new ArrayList<>();
        for (Line lineEdge: rectangleLines) {
            if (line.intersectionWith(lineEdge) != null) {
                intersections.add(line.intersectionWith(lineEdge));
            }
        }
        return intersections;
    }

    public Game.GameEntities.DrawbleObjects.Components.Point getUpperLeft() {
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

    public Game.GameEntities.DrawbleObjects.Components.Point getUpperRight() {
        return new Game.GameEntities.DrawbleObjects.Components.Point(this.width, this.upperLeft.getY());
    }

    public Game.GameEntities.DrawbleObjects.Components.Point getLowerLeft() {
        return new Game.GameEntities.DrawbleObjects.Components.Point(-this.width, this.height);
    }

    public Game.GameEntities.DrawbleObjects.Components.Point getLowerRight() {
        return new Game.GameEntities.DrawbleObjects.Components.Point(this.width, this.height);
    }

    public ArrayList<Line> getRectangleEdges(Game.GameEntities.DrawbleObjects.Components.Point upperLeft, Game.GameEntities.DrawbleObjects.Components.Point upperRight, Game.GameEntities.DrawbleObjects.Components.Point lowerLeft, Point lowerRight) {
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
