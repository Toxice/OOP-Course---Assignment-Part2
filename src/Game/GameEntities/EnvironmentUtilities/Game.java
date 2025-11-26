package Game.GameEntities.EnvironmentUtilities;

import Game.GameEntities.DrawbleObjects.CollidableObjects.Block;
import Game.GameEntities.DrawbleObjects.CollidableObjects.Collidable;
import Game.GameEntities.DrawbleObjects.Components.Point;
import Game.GameEntities.DrawbleObjects.Components.Rectangle;
import Game.GameEntities.DrawbleObjects.Sprite.Sprite;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import java.awt.*;
/**
 * Acts a Frame Class for the Game
 * holds all entities: Drawable and Collidable
 */
public class Game {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    private static final String Title = "Assignment 2 - Arkanoid";
    private static final int framesPerSecond = 60;

    GUI gui;
    Sleeper sleeper;
    private SpriteCollection sprites;
    private GameEnvironment environment;

    private static final int BLOCK_WIDTH  = 80;  // 10 blocks: 800 / 80
    private static final int BLOCK_HEIGHT = 30;
    private static final int TOP_ROW_Y    = 40;  // vertical position of the grey row

    /**
     * Adds a Collidable Object to the Game
     *
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
        gui = new GUI(Title, WIDTH, HEIGHT);
        sprites = new SpriteCollection();
        environment = new GameEnvironment();
        sleeper = new Sleeper();

        createTopGreyRow();

        //Ball ball = new Ball(new Point(50, 50), 5, Color.RED);
       // ball.setVelocity(1,1);
       // ball.addToGame(this);
       // ball.setEnvironment(environment);
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

    /**
     * generates blocks and adds their collidables and sprites to the environment and collection
     */
    private void createTopGreyRow() {
        int numBlocks = WIDTH / BLOCK_WIDTH; // 800 / 80 = 10

        for (int i = 0; i < numBlocks; i++) {
            double x = i * BLOCK_WIDTH;

            Point upperLeft   = new Point(x, TOP_ROW_Y);
            Rectangle rect    = new Rectangle(upperLeft, BLOCK_WIDTH, BLOCK_HEIGHT, Color.GRAY);
            Block block       = new Block(rect);

            // add to game
            this.addSprite(block);
            this.addCollidable(block);
        }
    }
}
