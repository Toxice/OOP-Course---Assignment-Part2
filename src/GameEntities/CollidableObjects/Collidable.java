package GameEntities.CollidableObjects;

import GameEntities.Components.Point;
import GameEntities.Components.Rectangle;
import GameEntities.Components.Velocity;

/**
 * an Interface made to represent a collidable object
 */
public interface Collidable {

    /**
     * Get the rectangle of the Collidable Object
     * @return the Rectangle of the Object
     */
    Rectangle getCollisionRectangle();

    /**
     * Notify the object that we collided with it at collisionPoint with
     * a given velocity.
     * The return is the new velocity expected after the hit (based on
     * the force the object inflicted on us).
     * @param collisionPoint the point that collided with the Collidable
     * @param currentVelocity the current velocity of the Ball
     * @return the new Velocity of the Ball after the collision
     */
    Velocity hit(Point collisionPoint, Velocity currentVelocity);
}
