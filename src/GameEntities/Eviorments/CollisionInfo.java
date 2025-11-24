package GameEntities.Eviorments;

import GameEntities.CollidableObjects.Collidable;
import GameEntities.Components.Point;

public class CollisionInfo {
    Collidable collisionObject;
    Point collisionPoint;

    public CollisionInfo(Collidable collisionObject, Point collisionPoint) {
        this.collisionObject = collisionObject;
        this.collisionPoint = collisionPoint;
    }

    /**
     * get the point where the collision occurs
     * @return new Point(xCollision, yCollision)
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    // the collidable object involved in the collision.
    public Collidable collisionObject() {
        return this.collisionObject;
    }
}
