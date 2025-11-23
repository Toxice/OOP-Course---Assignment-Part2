package GameEntities.CollidableObjects;

/**
 * a Class representing a Collidable Block in the Game
 * can be a screen frame or a rectangle
 */
public class Block implements Collidable {
    Rectangle rectangle;

    public Block(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /**
     *
     * @param collisionPoint: the point where the Ball Collided with the Collidable
     * @param currentVelocity: the current velocity of the Ball
     * @return the new Velocity of the Ball afterward
     */
    @Override
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
        double dx = currentVelocity.getDx(); // Horizontal Velocity
        double dy = currentVelocity.getDy(); // Vertical Velocity

        // Upper and Lower Line
        Line UpperLine = new Line(this.rectangle.getUpperLeft(), this.rectangle.getUpperRight());

        // Lower Line
        Line LowerLine = new Line(this.rectangle.getLowerLeft(), this.rectangle.getLowerRight());

        boolean hitUpOrDown = UpperLine.contains(collisionPoint) || LowerLine.contains(collisionPoint);


        // Left Line
        Line LeftLine = new Line(this.rectangle.getUpperLeft(), this.rectangle.getLowerLeft());

        // Right Line
        Line RightLine = new Line(this.rectangle.getUpperRight(), this.rectangle.getLowerRight());

        boolean hitLeftOrRight = LeftLine.contains(collisionPoint) || RightLine.contains(collisionPoint);

        if (hitUpOrDown) {
            dy = -currentVelocity.getDy();
        }

        if (hitLeftOrRight) {
            dx = -currentVelocity.getDx();
        }

        return new Velocity(dx, dy);
    }
}
