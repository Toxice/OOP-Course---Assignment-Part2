package GameEntities.Eviorments;

import GameEntities.CollidableObjects.Collidable;
import GameEntities.Components.Line;
import GameEntities.Components.Point;

import java.util.ArrayList;

/**
 * Holds all Collidable Objects existing in the game's instance
 */
public class GameEnvironment {
    ArrayList<Collidable> Collidables;

    public GameEnvironment() {
        this.Collidables = new ArrayList<>();
    }


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

    /**
     * find the closet point that intersects with one of the Collidables
     * @param trajectory: the Line the Ball is moving by
     * @return CollisionInfo Instance, can be null
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        Point collision;
        CollisionInfo collisionInfo = null;
        for (Collidable collidable: Collidables) {
            collision = trajectory.closestIntersectionToStartOfLine(collidable.getCollisionRectangle());
            if (!Point.isPointNull(collision)) {
                collisionInfo = new CollisionInfo(collidable, collision);
            }
        }
        return collisionInfo;
    }
}
