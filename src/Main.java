import GameEntities.DrawbleObjects.CollidableObjects.Block;
import GameEntities.DrawbleObjects.Components.Ball;
import GameEntities.DrawbleObjects.Components.Point;
import GameEntities.DrawbleObjects.Components.Rectangle;
import GameEntities.DrawbleObjects.Components.Velocity;
import GameEntities.EnvironmentUtilities.GameEnvironment;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.*;

public class Main {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    private static final String Title = "Assignment 2 - Arkanoid";
    static void main() {
        drawAnimation(new Point(70,70), new Velocity(10,-2));
        }

        public static void drawAnimation(Point start, Velocity velocity) {
            GUI gui = new GUI(Title, WIDTH, HEIGHT);
            Sleeper sleeper = new Sleeper();
            Rectangle rec1 = new Rectangle(new Point(0,0), WIDTH, 20, Color.GRAY);
            Rectangle rec2 = new Rectangle(new Point(0,HEIGHT-20), WIDTH, 20, Color.GRAY);
            Rectangle rec3 = new Rectangle(new Point(10, 20), 20, WIDTH-20, Color.CYAN);
            Block border1 = new Block(rec1);
            Block border2 = new Block(rec2);
            Block border3 = new Block(rec3);
            GameEnvironment environment = new GameEnvironment();
            environment.addCollidable(border1);
            environment.addCollidable(border2);
            environment.addCollidable(border3);
            Ball ball = new Ball(new Point(start), 10, Color.RED, environment);
            ball.setVelocity(velocity);

            while (true) {
                ball.moveOneStep();
                DrawSurface drawSurface = gui.getDrawSurface();
                border1.drawOn(drawSurface);
                border2.drawOn(drawSurface);
                border3.drawOn(drawSurface);
                ball.drawOn(drawSurface);
                gui.show(drawSurface);
                sleeper.sleepFor(50);
            }

        }
}
