package GameEntities.DrawbleObjects.CollidableObjects;

import GameEntities.DrawbleObjects.Components.Line;
import GameEntities.DrawbleObjects.Components.Point;
import GameEntities.DrawbleObjects.Components.Rectangle;
import GameEntities.DrawbleObjects.Components.Velocity;
import GameEntities.DrawbleObjects.Sprite.Sprite;
import biuoop.DrawSurface;

/**
 * a Class representing a Collidable Block in the Game, can be a screen frame or a rectangle
 * made from a Rectangle class internally
 */
public class Block implements Collidable, Sprite {
    Rectangle rectangle;

    public Block(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    /**
     * gets the rectangle of this Block
     * @return this.rectangle
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /**
     * calculates the hit position of this Block's Rectangle with the Ball's Center Point
     * and calculates the Ball's new velocity accordingly
     * @param collisionPoint: the point where the Ball Collided with the Collidable (Ball's Center)
     * @param currentVelocity: the current velocity of the Ball
     * @return new Velocity(new_dx, new_dy)
     */
    @Override
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
        double dx = currentVelocity.getDx(); // Horizontal Velocity
        double dy = currentVelocity.getDy(); // Vertical Velocity

        // Upper Line Definition
        Line UpperLine = new Line(this.rectangle.getUpperLeft(), this.rectangle.getUpperRight());

        // Lower Line Definition
        Line LowerLine = new Line(this.rectangle.getLowerLeft(), this.rectangle.getLowerRight());

        // Left Line Definition
        Line LeftLine = new Line(this.rectangle.getUpperLeft(), this.rectangle.getLowerLeft());

        // Right Line Definition
        Line RightLine = new Line(this.rectangle.getUpperRight(), this.rectangle.getLowerRight());

        // condition the Ball to hit one of the Block's lines.
        boolean hitUpOrDown = UpperLine.contains(collisionPoint) || LowerLine.contains(collisionPoint);
        boolean hitLeftOrRight = LeftLine.contains(collisionPoint) || RightLine.contains(collisionPoint);

        if (hitUpOrDown) {
            dy = -currentVelocity.getDy();
        }

        if (hitLeftOrRight) {
            dx = -currentVelocity.getDx();
        }

        return new Velocity(dx, dy);
    }

    @Override
    public void drawOn(DrawSurface drawSurface) {
        Rectangle rec = this.rectangle;
        drawSurface.setColor(rec.getColor());
        drawSurface.fillRectangle((int)rec.getUpperLeft().getX(),
                (int)rec.getUpperLeft().getY(), (int)rec.getWidth(), (int)rec.getHeight());
    }

    @Override
    public void timePassed() {

    }

    public void fillOn(DrawSurface drawSurface) {
        Rectangle rec = this.rectangle;
        drawSurface.setColor(rec.getColor());
        drawSurface.drawRectangle((int)rec.getUpperLeft().getX(),
                (int)rec.getUpperLeft().getY(), (int)rec.getWidth(), (int)rec.getHeight());
    }
}
