package GUI.Lines;

import Basics.LinesAndPoints.Line;
import Basics.LinesAndPoints.Point;
import biuoop.DrawSurface;
import biuoop.GUI;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Class Made for Plotting Lines and Points
 * Description:
 * Creates random Lines, and prints them, their middle points and their intersections points to the screen
 */
public class AbstractArtDrawing {
    static final String Title = "Assignment 1: Part 2 - Lines and Points";
    static final Random random = new Random();
    static final int width = 800;
    static final int height = 600;
    static final int number_of_lines = 10;

    static void main() {
        drawStaticImage();
    }

    public static void drawStaticImage() {
        GUI gui = new GUI(Title, width, height);
        DrawSurface drawSurface = gui.getDrawSurface();
        ArrayList<Line> lines = new ArrayList<>();
        // Create Random Lines and draw them and their middle points to the DrawSurface
        for (int i = 0; i < number_of_lines; i++) {
            lines.add(new Line(random));
            lines.get(i).drawOn(drawSurface);
            // Draw the middle point of every line to the DrawSurface
            lines.get(i).middle().drawOn(drawSurface, false);
        }
        // Find All Intersections and draw them to the DrawSurface
        for (int i = 0; i < number_of_lines; i++) {
            for (int j = i + 1; j < number_of_lines; j++) {
                Point intersection = lines.get(i).intersectionWith(lines.get(j));
                if (intersection != null) {
                    intersection.drawOn(drawSurface, true);
                }
            }
        }
        gui.show(drawSurface);
    }
}


