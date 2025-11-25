package Game.GameEntities.DrawbleObjects.Sprite;

import biuoop.DrawSurface;

/**
 * Represent a Sprite - a Drawable Object that is drawn to the screen at runtime
 */
public interface Sprite {

    /**
     * draw the sprite to the screen
     * @param drawSurface: DrawSurface Object
     */
    void drawOn(DrawSurface drawSurface);

    /**
     * notify the sprite that time has passed
     */
    void timePassed();
}
