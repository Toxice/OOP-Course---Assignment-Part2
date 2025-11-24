import GameEntities.CollidableObjects.Block;
import GameEntities.Components.Ball;
import GameEntities.Components.Point;
import GameEntities.Components.Rectangle;
import GameEntities.Components.Velocity;
import GameEntities.Eviorments.GameEnvironment;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.*;

public class Main {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    private static final String Title = "Assignment 2 - Arkanoid";
    static void main() {
        drawAnimation(new Point(70,70), new Velocity(5,-5));
        }

        public static void drawAnimation(Point start, Velocity velocity) {
            GUI gui = new GUI(Title, WIDTH, HEIGHT);
            Sleeper sleeper = new Sleeper();
            Rectangle rec1 = new Rectangle(new Point(0,0), WIDTH, 20, Color.GRAY);
            Block border1 = new Block(rec1);

            GameEnvironment environment = new GameEnvironment();
            environment.addCollidable(border1);
            Ball ball = new Ball(new Point(start), 10, Color.RED, environment);
            ball.setVelocity(velocity);

            while (true) {
                ball.moveOneStep();
                DrawSurface drawSurface = gui.getDrawSurface();
                border1.drawOn(drawSurface);
                ball.drawOn(drawSurface);
                gui.show(drawSurface);
                sleeper.sleepFor(50);
            }

        }
}
