package Game.GameEntities.EnvironmentUtilities;

import Game.GameEntities.DrawbleObjects.CollidableObjects.Collidable;
import Game.GameEntities.DrawbleObjects.Components.Line;
import Game.GameEntities.DrawbleObjects.Components.Point;
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

//    /**
//     * find the closet point that intersects with one of the Collidables
//     * @param trajectory: the Line the Ball is moving by
//     * @return CollisionInfo Instance, can be null
//     */
//    public CollisionInfo getClosestCollision(Line trajectory) {
//        Point collision;
//        CollisionInfo closestCollision  = null;
//        double closestDistance = Double.MAX_VALUE;
//        for (Collidable collidable : Collidables) {
//            collision = trajectory.closestIntersectionToStartOfLine(collidable.getCollisionRectangle());
//            if (!Point.isPointNull(collision)) {
//                double distance = trajectory.getStart().distance(collision);
//                // Keep only the closest collision
//                if (distance < closestDistance) {
//                    closestDistance = distance;
//                    closestCollision = new CollisionInfo(collidable, collision);
//                }
//            }
//            return closestCollision ;
//        }
//    }

    /**
     * Find the closest point that intersects with one of the Collidables.
     * FIXED: Now actually keeps track of the CLOSEST collision instead of the last one.
     *
     * @param trajectory: the Line the Ball is moving by
     * @return CollisionInfo Instance, can be null
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        CollisionInfo closestCollision = null;
        double closestDistance = Double.MAX_VALUE;

        for (Collidable collidable : Collidables) {
            Point collision = trajectory.closestIntersectionToStartOfLine(
                    collidable.getCollisionRectangle()
            );

            if (!Point.isPointNull(collision)) {
                double distance = trajectory.getStart().distance(collision);

                // Keep only the closest collision
                if (distance < closestDistance) {
                    closestDistance = distance;
                    closestCollision = new CollisionInfo(collidable, collision);
                }
            }
        }

        return closestCollision;
    }

   public Collidable getPaddle() {
        return Collidables.getLast();
   }
}
