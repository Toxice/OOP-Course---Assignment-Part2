package GameEntities.Components;

import GameEntities.CollidableObjects.Collidable;
import GameEntities.Eviorments.CollisionInfo;
import GameEntities.Eviorments.GameEnvironment;
import biuoop.DrawSurface;
import java.awt.*;
import java.util.Random;

/**
 * Ball Object, made of a Point (xCenter, yCenter),
 * Radius (integer),
 * Color (java.awt.Color)
 * and a Velocity (dx, dy)
 * There's also a GameEnvironment Instance as a Reference to the Collidable Objects the ball can interact with
 */
public class Ball {
    Point Center;
    int Radius;
    Color color;
    Velocity velocity;
    GameEnvironment environment;

    static final int Width = 800;
    static final int Height = 600;

    private static final double EPS = 0.01;

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

    /**
     * Constructor for the GameEnvironment Object
     * @param center: Center Point
     * @param radius: Radius
     * @param color: java,awt.Color
     * @param environment: GameEnvironment
     */
    public Ball(Point center, int radius,Color color, GameEnvironment environment) {
        this(center, radius, color);
        this.environment = environment;
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

    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Compute the next collision of the ball and return its info
     * @param trajectory: Line representing the Ball's Trajectory
     * @return the closet Collision's Info
     */
    private CollisionInfo getClosetCollision(Line trajectory) {
        return this.environment.getClosestCollision(trajectory);
    }

    /**
     * Change the Ball's Velocity according to its trajectory and collidables he hits
     *
     * 1) compute the ball trajectory (the trajectory is "how the ball will move
     *  without any obstacles" -- its a line starting at current location, and
     * ending where the velocity will take the ball if no collisions will occur).
     *
     * 2) Check (using the game environment) if moving on this trajectory will hit anything.
     *
     * 2.1) If no, then move the ball to the end of the trajectory.
     *
     * 2.2) Otherwise (there is a hit):
     * 2.2.2) move the ball to "almost" the hit point, but just slightly before it.
     * 2.2.3) notify the hit object (using its hit() method) that a collision occurred.
     * 2.2.4) update the velocity to the new velocity returned by the hit() method.
     */
    public void moveOneStep() {
        Point nextPosition = this.getVelocity().applyToPoint(this.Center);
        Line ballTrajectory = new Line(this.Center, nextPosition);
        CollisionInfo nextCollision = getClosetCollision(ballTrajectory);
        // if there is no collision
        if (nextCollision == null) {
            this.Center = new Point(nextPosition);
            return;
        }
        // if the CollisionInfo is not null
        if (nextCollision.collisionPoint() != null) {
            Point collisionPoint = nextCollision.collisionPoint();
            Collidable nextCollidedBlock = nextCollision.collisionObject();
            Velocity velocityAfterCollision = nextCollidedBlock.hit(collisionPoint, this.velocity);
            Point CollidedPoint = velocityAfterCollision.applyToPoint(this.Center);
            // check if the center is close enough to the collision point
            Point almostCollidedPoint = new Point(collisionPoint.getX() - EPS, collisionPoint.getY() - EPS);
            this.Center = new Point(almostCollidedPoint);
        }
    }

    /**
     * Changes the Ball location according to its velocity
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
    private void old_moveOneStep() {
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
