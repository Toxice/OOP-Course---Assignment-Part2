package Basics.Balls;

import Basics.LinesAndPoints.Point;

/**
 * Velocity Object, not an independent Object, only exist for Ball Class
 */
public class Velocity {
    double dx;
    double dy;

    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public double getDx() {
        return this.dx;
    }

    public double getDy() {
        return this.dy;
    }

    public void setDx(double _dx) {
        this.dx = _dx;
    }

    public void setDy(double _dy) {
        this.dy = _dy;
    }

    /**
     * Creates a new Point from This Point
     * @param point: Point Object
     * @return a new Point with change in the x coordinate dx and a change in the y coordinate dy
     */
    public Point applyToPoint(Point point) {
        return new Point(point.getX() + this.dx, point.getY() + this.dy);
    }

    /**
     * Creates a new Velocity using Angle and Speed (Vector)
     * @param angleInDegrees: angle
     * @param speed: speed
     * @return a new Velocity
     */
    public static Velocity fromAngleAndSpeed(double angleInDegrees, double speed) {
        double dx = speed * Math.cos(Math.toRadians(angleInDegrees));
        double dy = speed * Math.sin(Math.toRadians(angleInDegrees));
        return new Velocity(dx, dy);
    }

    @Override
    public String toString() {
        return "(" + this.dx + "," + this.dy + ")";
    }


}
