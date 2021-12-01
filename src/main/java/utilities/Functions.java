package utilities;

public class Functions {
    /**
     * Update position based off movement enum
     * 
     * @param position the current position
     * @param movement the direction we want to move position
     * @return the updated position after being moved by movement
     */
    public static Position updatePosition(Position position, Movement movement) {
        Position newPosition = new Position(position.getX(), position.getY());

        switch (movement) {
            case UP:
                newPosition.setY(newPosition.getY() - 1);
                break;
            case DOWN:
                newPosition.setY(newPosition.getY() + 1);
                break;
            case LEFT:
                newPosition.setX(newPosition.getX() - 1);
                break;
            case RIGHT:
                newPosition.setX(newPosition.getX() + 1);
                break;
            default:
                break;
        }

        return newPosition;
    }

    /**
     * Random number in some range
     * 
     * @param min Min of the range
     * @param max Max of the range
     * @return Random number within some range
     */
    public static long getRandomNumber(long min, long max) {
        return (long) (Math.random() * (max - min + 1)) + min;
    }

    /**
     * Random number in some range
     * 
     * @param min Min of the range
     * @param max Max of the range
     * @return Random number within some range
     */
    public static int getRandomNumber(int min, int max) {
        return (int) (Math.random() * (max - min + 1)) + min;
    }
}
