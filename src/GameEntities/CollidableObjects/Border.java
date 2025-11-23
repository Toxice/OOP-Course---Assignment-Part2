package GameEntities.CollidableObjects;

/**
 * Border Class made to draw the game borders
 */
public class Border extends Block implements Collidable {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    /**
     * Border Constructor
     */
    public Border(Rectangle rectangle) {
        this.rectangle = new Rectangle(rectangle.getUpperLeft(), rectangle.width, rectangle.height);
    }

    public Border(Point upperLeft, int width, int height) {
        this.rectangle = new Rectangle(upperLeft, width, height);
    }
}
