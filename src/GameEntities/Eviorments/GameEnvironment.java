package GameEntities.Eviorments;

import GameEntities.CollidableObjects.Collidable;
import java.util.ArrayList;

/**
 * Holds all Collidable Objects existing in the game's instance
 */
public class GameEnvironment {
    ArrayList<Collidable> Collidables;


    /**
     * add the given collidable to the environment
     * @param c: Collidable Object
     */
    public void addCollidable(Collidable c) {
        Collidables.add(c);
    }

    // Assume an object moving from line.start() to line.end().
    // If this object will not collide with any of the collidables
    // in this collection, return null. Else, return the information
    // about the closest collision that is going to occur.
//    public CollisionInfo getClosestCollision(Line trajectory) {
//
//    }
}
