package Game.MainGameClass;

import Game.GameEntities.DrawbleObjects.CollidableObjects.Block;
import Game.GameEntities.DrawbleObjects.CollidableObjects.Collidable;
import Game.GameEntities.DrawbleObjects.Components.Ball;
import Game.GameEntities.DrawbleObjects.Components.Point;
import Game.GameEntities.DrawbleObjects.Components.Rectangle;
import Game.GameEntities.DrawbleObjects.Sprite.Sprite;
import Game.GameEntities.EnvironmentUtilities.GameEnvironment;
import Game.GameEntities.EnvironmentUtilities.SpriteCollection;
import Game.GameEntities.PlayerEntities.Paddle;
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


    public Game() {
        gui = new GUI(Title, WIDTH, HEIGHT);
        sprites = new SpriteCollection();
        environment = new GameEnvironment();
        sleeper = new Sleeper();
    }
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
        Ball ball = new Ball(new Point(50, 50), 5, Color.RED);
        Block block = new Block(new Rectangle(new Point(0,0), WIDTH / 2.0, 20, Color.BLACK));
        ball.setVelocity(1,1);
        block.addToGame(this);
        ball.addToGame(this);
        ball.setEnvironment(environment);
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
     * Creates a staircase-shaped layout of blocks (like in the screenshot)
     * and adds them to this game.
     */
    private void createBlocksLayout() {
        // configuration
        final int rows = 6;              // number of rows (grey, red, yellow, blue, pink, green)
        final int topRowCols = 12;       // number of blocks in the top row
        final double blockWidth = 50;
        final double blockHeight = 20;
        final double startY = 100;       // y position of the top row

        // center the top row horizontally on the screen
        final double startX = (WIDTH - topRowCols * blockWidth) / 2.0;

        Ball ball = new Ball(new Point(10, 10), 5, Color.BLACK);
        Paddle paddle = new Paddle();

        for (int row = 0; row < rows; row++) {
            // each row is shorter by 1 block and starts one block to the right
            int colsThisRow = topRowCols - row;
            double y = startY + row * blockHeight;
            double rowStartX = startX + row * blockWidth;

            for (int col = 0; col < colsThisRow; col++) {
                double x = rowStartX + col * blockWidth;

                Rectangle rect = new Rectangle(new Point(x, y), blockWidth, blockHeight);

                Block block = new Block(rect);  // same color for all blocks
                block.addToGame(this);          // add as sprite + collidable
            }
        }

    }
}
