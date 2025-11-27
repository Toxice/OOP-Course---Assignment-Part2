package Game.GameEntities.DrawbleObjects.Components;

import Game.GameEntities.DrawbleObjects.CollidableObjects.Collidable;
import Game.GameEntities.DrawbleObjects.Sprite.Sprite;
import Game.GameEntities.Entity;
import Game.GameEntities.EnvironmentUtilities.CollisionInfo;
import Game.GameEntities.EnvironmentUtilities.GameEnvironment;
import Game.GameEntities.EnvironmentUtilities.Game;
import biuoop.DrawSurface;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Ball Object, made of a Point (xCenter, yCenter),
 * Radius (integer),
 * Color (java.awt.Color)
 * and a Velocity (dx, dy)
 * There's also a GameEnvironment Instance as a Reference to the Collidable Objects the ball can interact with
 */
public class Ball extends Entity implements Sprite {
    Point Center;
    int Radius;
    Color color;
    Velocity velocity;
    GameEnvironment environment;

    private static final double EPS = 0.1;

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
     * Constructor for the GameEnvironment Object
     * @param center: Center Point
     * @param radius: Radius
     * @param color: java,awt.Color
     * @param environment: GameEnvironment
     */
    public Ball(Point center, int radius,Color color, GameEnvironment environment, Velocity velocity) {
        this(center, radius, color);
        this.setVelocity(velocity);
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

    public void setVelocity(Velocity velocity) {
        this.setVelocity(velocity.getDx(), velocity.getDy());
    }

    public Velocity getVelocity() {
        return this.velocity;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Sets a new environment by reference
     * @param environment: GameEnvironment Object
     */
    public void setEnvironment(GameEnvironment environment) {
        this.environment = environment;
    }

    /**
     * Sets a new environment by value
     * @param collidables: Collidable Objects
     */
    public void setEnvironment(ArrayList<Collidable> collidables) {
        for (Collidable collidable : collidables) {
            this.environment.addCollidable(collidable);
        }
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
     * Change the Ball's Velocity according to its trajectory and the collidable object he hits
     * algorithm:
     * (1) compute the ball trajectory and look for collidable objects in its path
     * (2) If there isn't a collidable on its path,then move the ball to the end of the trajectory.
     * (3) assuming there is a collidable in its path, use the GameEnvironment to look
     * for a collidable matching the collision point found on the ball's trajectory
     * (4) move the ball so it hits the collision point, but just slightly before it.
     *  notify the hit collidable object (using its hit() method) that a collision occurred.
     *  update the ball's velocity to the new velocity returned by the hit() method.
     */
    public void moveOneStep() {
        Point nextPosition = this.getVelocity().applyToPoint(this.Center);
        Line ballTrajectory = new Line(this.Center, nextPosition);
        CollisionInfo nextCollision = getClosetCollision(ballTrajectory);
        /*
            if there are no collisions whatsoever, then move the ball directly by its path
         */
        if (nextCollision == null) {
            this.Center = new Point(nextPosition);
        }

        /*
            there is a collision to check for
         */
        else if (nextCollision.collisionPoint() != null) {
            // get the collision point
            Point collisionPoint = nextCollision.collisionPoint();
            // get the collided object
            Collidable collisionObject = nextCollision.collisionObject();

            // move the ball to the (almost) collision point
            Point almostCollisionPoint = new Point(collisionPoint.getX() - EPS, collisionPoint.getY() - EPS);
            this.Center = new Point(almostCollisionPoint);

            // set the new velocity of the ball
            Velocity collisionVelocity = collisionObject.hit(collisionPoint, this.velocity);
                this.setVelocity(collisionVelocity);


            // set the new location of the ball
            this.Center = this.velocity.applyToPoint(this.Center);
        }
    }

    /**
     * Draw the Ball to the Surface
     * @param drawSurface: DrawSurface Object
     */
    @Override
    public void drawOn(DrawSurface drawSurface) {
        drawSurface.setColor(this.getColor());
        drawSurface.fillCircle(this.getX(), this.getY(), this.getSize());
    }

    @Override
    public void timePassed() {
        moveOneStep();
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
        return this.Center.toString();
    }

    /**
     * adds the Ball to the Game Instance
     * @param game: Game
     */
    @Override
    public void addToGame(Game game) {
        game.addSprite(this);
    }
}
