package Game.MainGameClass;

import Game.GameEntities.DrawbleObjects.CollidableObjects.Collidable;
import Game.GameEntities.DrawbleObjects.Components.Ball;
import Game.GameEntities.DrawbleObjects.Components.Point;
import Game.GameEntities.DrawbleObjects.Sprite.Sprite;
import Game.GameEntities.EnvironmentUtilities.GameEnvironment;
import Game.GameEntities.EnvironmentUtilities.SpriteCollection;
import Game.GameEntities.PlayerEntities.Paddle;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.*;
import java.util.ArrayList;

/**
 * Acts a Frame Class for the Game
 * holds all entities: Drawable and Collidable
 */
public class Game {
    GUI gui = new GUI(Title, WIDTH, HEIGHT);
    Sleeper sleeper = new Sleeper();
    private SpriteCollection sprites;
    private GameEnvironment environment;

    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    private static final String Title = "Assignment 2 - Arkanoid";
    private static final int framesPerSecond = 60;

    /**
     * Adds a Collidable Object to the Game
     * @param c: Collidable
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    /**
     * Initialize a new game: create the Blocks and Ball (and Paddle)
     * and add them to the game.
     */
    public void initialize() {
        Ball gameBall = new Ball(new Point(10,10), 5, Color.BLACK);
        Paddle player = new Paddle();
        ArrayList<Collidable> Blocks = environment.getEnvironment();
        gameBall.setEnvironment(Blocks);
    }

    /**
     * Run the Game Loop
     * Runs at 60 frames per second
     */
    public void run() {
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (true) {
            long startTime = System.currentTimeMillis(); // timing

            DrawSurface drawSurface = gui.getDrawSurface();
            this.sprites.drawAllOn(drawSurface);
            gui.show(drawSurface);
            this.sprites.notifyAllTimePassed();

            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}
