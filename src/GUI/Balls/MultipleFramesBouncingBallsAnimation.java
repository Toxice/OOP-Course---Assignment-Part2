package GUI.Balls;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Multiple frames bouncing balls animation
 */
public class MultipleFramesBouncingBallsAnimation {
    // Window size (match your other animations)
    static final int WIDTH = 800;
    static final int HEIGHT = 600;
    static final String TITLE = "Part 3.4 - Bouncing Balls in Multiple Frames";

    // Frames definitions
    static final int GREY_X = 50;
    static final int GREY_Y = 50;
    static final int GREY_W = 450; // GREY frame goes from (50,50) to (500,500)
    static final int GREY_H = 450;

    static final int YELLOW_X = 450;
    static final int YELLOW_Y = 450;
    static final int YELLOW_W = 150; // Yellow frame goes from (450,450) to (600,600)
    static final int YELLOW_H = 150;

    static final Color[] COLORS = new Color[]{
            Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW,
            Color.ORANGE, Color.MAGENTA, Color.PINK, Color.CYAN
    };

    static final Random RAND = new Random();

    private static class Frame {
        int left, top, right, bottom;
        Frame(int x, int y, int w, int h) {
            this.left = x;
            this.top = y;
            this.right = x + w;
            this.bottom = y + h;
        }
    }

    private static class BallState {
        double x, y;      // center
        int r;            // radius
        double dx, dy;    // velocity
        Color color;      // color
        Frame frame;      // assigned frame

        BallState(double x, double y, int r, double dx, double dy, Color color, Frame frame) {
            this.x = x;
            this.y = y;
            this.r = r;
            this.dx = dx;
            this.dy = dy;
            this.color = color;
            this.frame = frame;
        }
    }

    private static double randVelocity() {
        // produce a reasonable velocity between -4.0 and 4.0 excluding very small values
        double v;
        do {
            v = -4 + RAND.nextDouble() * 8; // -4..4
        } while (Math.abs(v) < 0.8);
        return v;
    }

    public static void main(String[] args) {
        // If no args, show usage and exit
        if (args.length == 0) {
            System.out.println("Usage: java GUI.Balls.MultipleFramesBouncingBallsAnimation <radius1> <radius2> ...");
            return;
        }

        // Parse radii
        List<Integer> radii = new ArrayList<>();
        for (String a : args) {
            try {
                radii.add(Integer.parseInt(a));
            } catch (NumberFormatException e) {
                System.out.println("Skipping invalid radius: " + a);
            }
        }
        if (radii.isEmpty()) {
            System.out.println("No valid radii provided.");
            return;
        }

        GUI gui = new GUI(TITLE, WIDTH, HEIGHT);
        Sleeper sleeper = new Sleeper();

        Frame greyFrame = new Frame(GREY_X, GREY_Y, GREY_W, GREY_H);
        Frame yellowFrame = new Frame(YELLOW_X, YELLOW_Y, YELLOW_W, YELLOW_H);

        int total = radii.size();
        int half = (total + 1) / 2; // first half -> grey, second half -> yellow

        List<BallState> balls = new ArrayList<>();

        // Create balls: first half in grey frame, second half in yellow frame
        for (int i = 0; i < total; i++) {
            int r = radii.get(i);
            Frame f = (i < half) ? greyFrame : yellowFrame;

            // Ensure the ball is fully inside its frame
            double minX = f.left + r;
            double maxX = f.right - r;
            double minY = f.top + r;
            double maxY = f.bottom - r;
            // If the frame is too small for the ball, clamp center to center of frame:
            double x, y;
            if (minX > maxX) {
                x = (f.left + f.right) / 2.0;
            } else {
                x = minX + RAND.nextDouble() * (maxX - minX);
            }
            if (minY > maxY) {
                y = (f.top + f.bottom) / 2.0;
            } else {
                y = minY + RAND.nextDouble() * (maxY - minY);
            }

            double dx = randVelocity();
            double dy = randVelocity();
            Color color = COLORS[RAND.nextInt(COLORS.length)];

            balls.add(new BallState(x, y, r, dx, dy, color, f));
        }

        // Animation loop
        while (true) {
            DrawSurface d = gui.getDrawSurface();

            // background
            d.setColor(Color.WHITE);
            d.fillRectangle(0, 0, WIDTH, HEIGHT);

            // draw grey frame
            d.setColor(Color.GRAY);
            d.fillRectangle(greyFrame.left, greyFrame.top, GREY_W, GREY_H);

            // draw yellow frame
            d.setColor(Color.YELLOW);
            d.fillRectangle(yellowFrame.left, yellowFrame.top, YELLOW_W, YELLOW_H);

            // update and draw balls (each against its own frame)
            for (BallState b : balls) {
                // next position
                double nx = b.x + b.dx;
                double ny = b.y + b.dy;

                // horizontal collisions against frame
                if (nx + b.r > b.frame.right) {
                    b.dx = -b.dx;
                    nx = b.frame.right - b.r;
                } else if (nx - b.r < b.frame.left) {
                    b.dx = -b.dx;
                    nx = b.frame.left + b.r;
                }

                // vertical collisions against frame
                if (ny + b.r > b.frame.bottom) {
                    b.dy = -b.dy;
                    ny = b.frame.bottom - b.r;
                } else if (ny - b.r < b.frame.top) {
                    b.dy = -b.dy;
                    ny = b.frame.top + b.r;
                }

                b.x = nx;
                b.y = ny;

                // draw ball
                d.setColor(b.color);
                d.fillCircle((int) Math.round(b.x), (int) Math.round(b.y), b.r);
            }

            gui.show(d);
            sleeper.sleepFor(50); // 50 ms ~ 20 FPS
        }
    }
}
