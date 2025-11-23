package GameEntities.PlayerEntities;

import GameEntities.CollidableObjects.Collidable;
import GameEntities.CollidableObjects.Point;
import GameEntities.CollidableObjects.Rectangle;
import GameEntities.CollidableObjects.Velocity;

public class Paddle implements Collidable {
    Rectangle rectangle;

    @Override
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    @Override
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
        return null;
    }
}
