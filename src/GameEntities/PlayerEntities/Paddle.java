package GameEntities.PlayerEntities;

import GameEntities.DrawbleObjects.CollidableObjects.Collidable;
import GameEntities.DrawbleObjects.Components.Point;
import GameEntities.DrawbleObjects.Components.Rectangle;
import GameEntities.DrawbleObjects.Components.Velocity;
import GameEntities.DrawbleObjects.Sprite.Sprite;
import biuoop.DrawSurface;

public class Paddle implements Collidable, Sprite {
    Rectangle rectangle;

    @Override
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    @Override
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
        return null;
    }

    @Override
    public void drawOn(DrawSurface drawSurface) {

    }

    @Override
    public void timePassed() {

    }
}
