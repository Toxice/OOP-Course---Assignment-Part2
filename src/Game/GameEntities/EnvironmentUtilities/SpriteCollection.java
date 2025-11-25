package Game.GameEntities.EnvironmentUtilities;

import Game.GameEntities.DrawbleObjects.Sprite.Sprite;
import biuoop.DrawSurface;

import java.util.ArrayList;

/**
 * Holds a list of all existing sprite objects in the game environment
 */
public class SpriteCollection {
    ArrayList<Sprite> sprites = new ArrayList<>();

    /**
     * Adds a new sprite to the collection
     * @param s: Sprite
     */
    public void addSprite(Sprite s) {
        sprites.add(s);
    }

    /**
     * Move all existing sprites
     */
    public void notifyAllTimePassed() {
        for (Sprite sprite : sprites) {
            sprite.timePassed();
        }
    }

    /**
     * Draws all existing sprites to the DrawSurface
     * @param drawSurface: DrawSurface Object
     */
    public void drawAllOn(DrawSurface drawSurface) {
        for (Sprite sprite : sprites) {
            sprite.drawOn(drawSurface);
        }
    }
}
