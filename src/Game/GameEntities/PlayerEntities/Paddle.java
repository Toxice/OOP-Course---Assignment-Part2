package Game.GameEntities.PlayerEntities;

import Game.GameEntities.DrawbleObjects.CollidableObjects.Collidable;
import Game.GameEntities.DrawbleObjects.Components.*;
import Game.GameEntities.DrawbleObjects.Components.Point;
import Game.GameEntities.DrawbleObjects.Components.Rectangle;
import Game.GameEntities.DrawbleObjects.Sprite.Sprite;
import Game.GameEntities.EnvironmentUtilities.Game;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.awt.*;

/**
 * represents the Paddle in the game
 */
public class Paddle implements Collidable, Sprite {
    Rectangle rectangle;
    Velocity velocity;

    KeyboardSensor keyboard;

    private static final int PaddleVelocityFactor = 5;
    private static final double PaddleWidth = 80;
    private static final double PaddleHeight = 20;
    Point startPoint = new Point(
            (Game.WIDTH / 2.0) - (PaddleWidth / 2),
            Game.HEIGHT - PaddleHeight - 20);

    public Paddle(GUI gui) {
        this.rectangle = new Rectangle(startPoint, PaddleWidth, PaddleHeight, Color.BLUE);
        keyboard = gui.getKeyboardSensor();
        velocity = new Velocity(1, 0);
    }

    public void moveLeft() {
        if (this.keyboard.isPressed("a")) {
            velocity.setDx(-PaddleVelocityFactor);
            this.rectangle.getUpperLeft().setX(velocity.applyToPoint(this.rectangle.getUpperLeft()).getX());
        }
    }

    public void moveRight() {
        if (this.keyboard.isPressed("d")) {
            velocity.setDx(PaddleVelocityFactor);
            this.rectangle.getUpperLeft().setX(velocity.applyToPoint(this.rectangle.getUpperLeft()).getX());
        }
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    @Override
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
        return new Velocity(-currentVelocity.getDx(),-currentVelocity.getDy());
    }

    @Override
    public void drawOn(DrawSurface drawSurface) {
        // draws the outline
        drawSurface.setColor(Color.BLACK);
        drawSurface.drawRectangle(
                (int)this.rectangle.getUpperLeft().getX(),
                (int)this.rectangle.getUpperLeft().getY(),
                (int)this.rectangle.getWidth(),
                (int)this.rectangle.getHeight()
                );

        // draws the rectangle
        drawSurface.setColor(this.rectangle.getColor());
        drawSurface.fillRectangle(
                (int)this.rectangle.getUpperLeft().getX(),
                (int)this.rectangle.getUpperLeft().getY(),
                (int)this.rectangle.getWidth(),
                (int)this.rectangle.getHeight()
        );
    }

    public Point getStartPoint() {
        return startPoint;
    }

    @Override
    public void timePassed() {
       moveLeft();
       moveRight();

    }
}
