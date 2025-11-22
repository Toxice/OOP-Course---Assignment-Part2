package Basics.LinesAndPoints;

import biuoop.DrawSurface;
import java.awt.*;
import java.util.Random;

/**
 * Line Object, made of two Point Objects
 * start - start Point
 * end - end Point
 * slope - the slope of the line
 * intercept - the b point of the line (y = mx + b)
 */
public class Line {
    private final Point start;
    private final Point end;
    private final double slope;
    private final double intercept;

    private static final double EPS = 1e-9; // same EPS as Point
    private static final int width = 800;
    private static final int height = 600;

    /**
     * Take two Point Objects and create a Line from them
     * @param start: Point
     * @param end: Point
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
        this.slope = (this.end.getY() - this.start.getY()) / (this.end.getX() - this.start.getX());
        this.intercept = calIntercept();
    }

    /**
     * Take Four Coordinates and make two Points and Make a Line from them
     * @param x1: coordinate
     * @param y1: coordinate
     * @param x2: coordinate
     * @param y2: coordinate
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
        this.slope = ((y2 - y1) / (x2 - x1));
        this.intercept = calIntercept();
    }

    /**
     * Take a Random class and returns a new Line
     * @param random: Random Class
     */
    public Line(Random random) {
        double xS = random.nextDouble(width);
        double yS = random.nextDouble(height);
        double xE = random.nextDouble(width);
        double yE = random.nextDouble(height);
        this(new Point(xS, yS), new Point(xE, yE));
    }

    /**
     * Calculate the Length of the Line
     * @return the length of the line, made of the distance between two Points
     */
    public double length() {
        return this.start.distance(this.end);
    }

    /**
     * Find The Middle Point of the Line
     * @return the middle Point, takes the average of two Points
     */
    public Point middle() {
        return new Point((start.getX() + end.getX()) / 2, (start.getY() + end.getY()) / 2);
    }

    public Point getStart() {
        return this.start;
    }

    public Point getEnd() {
        return this.end;
    }

    /**
     * Check if This Line intersects with any other lines
     * @param other: Line Object
     * @return true iff This intersects with other Line, false otherwise
     */
    public boolean isIntersecting(Line other) {
        if (this.getSlope() == other.getSlope()) {
            return false;
        } else {
            return intersectionWith(other) != null;
        }
    }

    /**
     * Calculate the Intersection Point between the two Lines
     * Calculate the Point, and Check if it's the parameter of the two lines
     * return if the point if so, return null otherwise
     * @return Point Object, may be null
     */
    public Point intersectionWith(Line other) {
        if (this.getSlope() != other.getSlope()) {
            double _x = ((other.getIntercept() - this.getIntercept()) / (this.getSlope() - other.getSlope()));
            double _y = this.getSlope() * _x + this.getIntercept();
            Point intersectionPoint = new Point(_x, _y);

            if (this.contains(intersectionPoint) && other.contains(intersectionPoint)) {
                return intersectionPoint;
            }
        } return null;
    }

    public double getSlope() {
        return slope;
    }

    public double getIntercept() {
        return this.intercept;
    }

    private double calIntercept() {
        return this.getEnd().getY() - getSlope() * this.getEnd().getX();
    }

    /**
     * Check's if a Point is contained in this Line, uses epsilon (Infi 1)
     * @param point: Point object
     * @return true iff the Point lies between the Line Perimeter and Matches the Line Equation
     */
    public boolean contains(Point point) {
        if (point == null) return false;
        // For vertical lines: x coordinate equality within EPS
        if (Math.abs(this.getStart().getX() - this.getEnd().getX()) < EPS) {
            if (Math.abs(point.getX() - this.getStart().getX()) > EPS) return false;
        } else {
            double expectedY = this.getSlope() * point.getX() + this.getIntercept();
            if (Math.abs(point.getY() - expectedY) > EPS) return false;
        }
        // Now check if within bounding box (with EPS)
        double minX = Math.min(this.getStart().getX(), this.getEnd().getX()) - EPS;
        double maxX = Math.max(this.getStart().getX(), this.getEnd().getX()) + EPS;
        double minY = Math.min(this.getStart().getY(), this.getEnd().getY()) - EPS;
        double maxY = Math.max(this.getStart().getY(), this.getEnd().getY()) + EPS;

        return point.getX() >= minX && point.getX() <= maxX
                && point.getY() >= minY && point.getY() <= maxY;
    }

    /**
     * Draws this Line to the DrawSurface
     * @param drawSurface: DrawSurface Object
     */
    public void drawOn(DrawSurface drawSurface) {
        drawSurface.setColor(Color.BLACK);
        drawSurface.drawLine((int)Math.round(this.getStart().getX()), (int)Math.round(this.getStart().getY())
                , (int)Math.round(this.getEnd().getX()), (int)Math.round(this.getEnd().getY()));
    }

    @Override
    public String toString() {
        return "(" +  this.getStart().toString() + "," + this.getEnd().toString() + ")";
    }
}
