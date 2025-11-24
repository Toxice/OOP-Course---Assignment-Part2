package GameEntities.PlayerEntities;

import GameEntities.CollidableObjects.Collidable;
import GameEntities.Components.Point;
import GameEntities.Components.Rectangle;
import GameEntities.Components.Velocity;

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
