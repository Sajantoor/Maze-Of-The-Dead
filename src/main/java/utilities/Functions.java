package utilities;

public class Functions {
    /**
     * Common equal function betweeen 2 objects to avoid code duplication
     * 
     * @param a Object a
     * @param b Object b
     * @return True if they are equal, false otherwise
     */
    public static boolean equals(Object a, Object b) {
        if (a == b)
            return true;

        if (a == null || a.getClass() != b.getClass())
            return false;

        return true;
    }

    /**
     * Update position based off movement enum
     * 
     * @param position
     * @param movement
     * @return New Position
     */
    public static Position updatePosition(Position position, Movement movement) {
        switch (movement) {
            case UP:
                position.setY(position.getY() - 1);
                break;
            case DOWN:
                position.setY(position.getY() + 1);
                break;
            case LEFT:
                position.setX(position.getX() - 1);
                break;
            case RIGHT:
                position.setX(position.getX() + 1);
                break;
            default:
                break;
        }

        return position;
    }
}
