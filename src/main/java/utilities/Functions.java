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

    /**
     * Get a random position in some range
     * 
     * @param minX Min X of the range
     * @param maxX Max X of the range
     * @param minY Min Y of the range
     * @param maxY Max Y of the range
     * @return Random position within some range
     */
    public static Position getRandomPosition(int minX, int maxX, int minY, int maxY) {
        int x = getRandomNumber(minX, maxX);
        int y = getRandomNumber(minY, maxY);
        return new Position(x, y);
    }

    /**
     * Get a random position in the maze
     * 
     * @return Random position in the maze
     */
    public static Position getRandomPosition() {
        int x = getRandomNumber(0, Constants.mazeWidth - 1);
        int y = getRandomNumber(0, Constants.mazeHeight - 1);
        return new Position(x, y);
    }
}
