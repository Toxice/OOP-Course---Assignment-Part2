package Game.GameEntities.PlayerEntities;

import Game.GameEntities.DrawbleObjects.CollidableObjects.Collidable;
import Game.GameEntities.DrawbleObjects.Components.Point;
import Game.GameEntities.DrawbleObjects.Components.Rectangle;
import Game.GameEntities.DrawbleObjects.Components.Velocity;
import Game.GameEntities.DrawbleObjects.Sprite.Sprite;
import Game.GameEntities.EnvironmentUtilities.Game;
import biuoop.DrawSurface;

import java.awt.*;

public class Paddle implements Collidable, Sprite {
    Rectangle rectangle;

    private static final Point startPoint = new Point(Game.WIDTH / 2.0, -Game.HEIGHT + 20);
    private static final double PaddleWidth = 20;
    private static final double PaddleHeight = Game.HEIGHT - 20;

    public Paddle() {
        this.rectangle = new Rectangle(startPoint, PaddleWidth, PaddleHeight);
    }

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
        drawSurface.setColor(Color.YELLOW);
    }

    @Override
    public void timePassed() {

    }
}
