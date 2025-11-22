package GUI.Balls;

import Basics.Balls.Ball;
import Basics.Balls.Velocity;
import Basics.LinesAndPoints.Point;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import biuoop.*;

public class BouncingBallAnimation {
    final static String Title = "Part 3.2 - Bouncing Balls";
    final static int Width = 800;
    final static int Height = 600;
    static void main(String[] args) {
        Point Point = new Point(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        Velocity argVelocity = new Velocity(Integer.parseInt(args[2]), Integer.parseInt(args[3]));
        drawAnimation(Point, argVelocity.getDx(), argVelocity.getDy());
    }

    static private void drawAnimation(Point start, double dx, double dy) {
        GUI gui = new GUI(Title, Width, Height);
        Sleeper sleeper = new Sleeper();
        Ball ball = new Ball(start.getX(), start.getY(), 30, java.awt.Color.BLACK);
        ball.setVelocity(dx, dy);
        while (true) {
            ball.moveOneStep();
            DrawSurface d = gui.getDrawSurface();
            ball.drawOn(d);
            gui.show(d);
            sleeper.sleepFor(50);  // wait for 50 milliseconds.
        }
    }
}
