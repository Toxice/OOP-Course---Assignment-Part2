package GUI.Balls;

import Basics.Balls.Ball;
import Basics.Balls.Velocity;
import Basics.LinesAndPoints.Point;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.*;
import java.util.Random;

/**
 * Updated MultipleBouncingBallsAnimation
 * - Ball speed depends on radius (larger ball = slower)
 * - Uses Velocity.fromAngleAndSpeed() correctly
 */
public class MultipleBouncingBallsAnimation {
    static Random random = new Random();
    static final int Width = 800;
    static final int Height = 600;
    static final String Title = "Part 3.3 - Multiple Balls (Updated)";

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Usage: java MultipleBouncingBallsAnimation <r1> <r2> <r3> ...");
            return;
        }

        Ball[] balls = new Ball[args.length];

        for (int i = 0; i < args.length; i++) {
            int radius = Integer.parseInt(args[i]);

            int x = random.nextInt(Math.max(1, Width - 2 * radius)) + radius;
            int y = random.nextInt(Math.max(1, Height - 2 * radius)) + radius;

            Ball ball = new Ball(new Point(x, y), radius, Ball.pickRandomColor());

            // speed scale (bigger radius -> slower ball)
            double speed;
            if (radius > 50) {
                speed = 1.0; // very big balls slow
            } else {
                speed = Math.max(1.0, (50.0 - radius) / 8.0);
            }

            double angle = random.nextDouble() * 360.0;
            Velocity v = Velocity.fromAngleAndSpeed(angle, speed);
            ball.setVelocity(v.getDx(), v.getDy());

            balls[i] = ball;
        }

        drawAnimation(balls);
    }

    private static void drawAnimation(Ball[] balls) {
        GUI gui = new GUI(Title, Width, Height);
        Sleeper sleeper = new Sleeper();

        while (true) {
            DrawSurface d = gui.getDrawSurface();
            d.setColor(Color.WHITE);
            d.fillRectangle(0, 0, Width, Height);

            for (Ball ball : balls) {
                ball.moveOneStep();
                ball.drawOn(d);
            }

            gui.show(d);
            sleeper.sleepFor(50);
        }
    }
}
