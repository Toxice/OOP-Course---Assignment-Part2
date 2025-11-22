package Basics.Balls;

import Basics.LinesAndPoints.Point;
import biuoop.DrawSurface;
import java.awt.*;
import java.util.Random;

/**
 * Ball Object, made of a Point representing its center,
 * an int representing its radius,
 * and a Color representing its Color
 */
public class Ball {
    Point Center;
    int Radius;
    Color color;
    Velocity velocity;

    static final int Width = 800;
    static final int Height = 600;

    /**
     * Point Constructor
     * @param point: (x,y)
     * @param r: radius
     * @param color: java.awt.Color
     */
    public Ball(Point point, int r, Color color) {
        this.Center = point;
        this.Radius = r;
        this.color = color;
    }

    /**
     * @param _x: x coordinate
     * @param _y: y coordinate
     * @param radius: radius
     * @param color: java.awt.Color
     */
    public Ball(double _x, double _y, int radius, Color color) {
        this(new Point(_x, _y), radius, color);
    }

    /**
     * @param Radius: Radius
     * @param random: Random
     */
    public Ball(int Radius, Random random) {
        this(new Point(random.nextInt(10), random.nextInt(10)), Radius,
                pickRandomColor());
    }

    public int getX() {
        return (int)Math.round(this.Center.getX());
    }

    public int getY() {
        return (int)Math.round(this.Center.getY());
    }

    public int getSize() {
        return this.Radius;
    }

    public Color getColor() {
        return this.color;
    }

    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * Method Meant to move the Ball to some direction using its velocity
     * we need to move the Center Point from (x_Center, y_Center) to (x_Center + dx, y_Center + dy)
     * Collision Detection:
     * We need the Ball to Bounce off a Wall to the opposite direction
     * Algorithm:
     * create a Point named nextPosition that position (x_Center + dx, y_Center + dy)
     * Collision with right border:
     * if nextPosition x value + the radius is equal to more than the right border - bounce off the opposite direction
     * if nextPosition x value + the radius is less than the zero (left border) - bounce off the opposite direction
     * if nextPosition y value + radius is equal to more than the top border - bounce off the opposite direction
     * if nextPosition x value + the radius is less than the zero (left border) - bounce off the opposite direction
     * if nextPosition y value + the radius is less than the zero (bottom border) - bounce off the opposite direction
     * After all the checking, update the ball's location
     */
    public void moveOneStep() {
        Point nextPosition = this.getVelocity().applyToPoint(this.Center);
        double nextX = nextPosition.getX();
        double nextY = nextPosition.getY();

        // Handle X collisions
        if (nextX + this.Radius > Width) {
            this.velocity.setDx(-this.velocity.getDx());
            nextX = Width - this.Radius;
        }
        if (nextX - this.Radius < 0) {
            this.velocity.setDx(-this.velocity.getDx());
            nextX = this.Radius;
        }

        // Handle Y collisions
        if (nextY + this.Radius > Height) {
            this.velocity.setDy(-this.velocity.getDy());
            nextY = Height - this.Radius;
        }
        if (nextY - this.Radius < 0) {
            this.velocity.setDy(-this.velocity.getDy());
            nextY = this.Radius;
        }

        this.Center = new Point(nextX, nextY);
    }

    /**
     * Draw the Ball to the Surface
     * @param drawSurface: DrawSurface Object
     */
    public void drawOn(DrawSurface drawSurface) {
        drawSurface.setColor(this.getColor());
        drawSurface.fillCircle(this.getX(), this.getY(), this.getSize());
    }

    public static Color pickRandomColor() {
        Color[] colors = {
                Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW,
                Color.ORANGE, Color.PINK, Color.CYAN, Color.MAGENTA
        };
        Random rand = new Random();
        return colors[rand.nextInt(colors.length)];
    }

    @Override
    public String toString() {
        return "("+ this.getX() + "," + this.getY() + ")";
    }
}
