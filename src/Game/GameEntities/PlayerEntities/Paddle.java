package Game.GameEntities.PlayerEntities;

import Game.GameEntities.DrawbleObjects.CollidableObjects.Collidable;
import Game.GameEntities.DrawbleObjects.Components.Line;
import Game.GameEntities.DrawbleObjects.Components.Point;
import Game.GameEntities.DrawbleObjects.Components.Rectangle;
import Game.GameEntities.DrawbleObjects.Components.Velocity;
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
    Line Path;
    Velocity velocity;

    KeyboardSensor keyboard;

    private static final int PaddleVelocityFactor = 5;
    private static final double PaddleWidth = 80;
    private static final double PaddleHeight = 35;
    private static final Point startPoint = new Point(
            (Game.WIDTH / 2.0) - (PaddleWidth / 2),
            Game.HEIGHT - PaddleHeight);

    public Paddle(GUI gui) {
        this.rectangle = new Rectangle(startPoint, PaddleWidth, PaddleHeight, Color.BLUE);
        keyboard = gui.getKeyboardSensor();
        Path = new Line(new Point(0,Game.HEIGHT), new Point(Game.WIDTH, Game.HEIGHT));
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
        return null;
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

    @Override
    public void timePassed() {
       moveLeft();
       moveRight();
    }
}
