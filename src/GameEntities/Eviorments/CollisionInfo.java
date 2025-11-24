package GameEntities.Eviorments;

import GameEntities.CollidableObjects.Collidable;
import GameEntities.CollidableObjects.Point;

public class CollisionInfo {
    Collidable collisionObject;
    Point collisionPoint;

    public CollisionInfo(Collidable collisionObject, Point collisionPoint) {
        this.collisionObject = collisionObject;
        this.collisionPoint = collisionPoint;
    }

    // the point at which the collision occurs.
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    // the collidable object involved in the collision.
    public Collidable collisionObject() {
        return this.collisionObject;
    }
}
