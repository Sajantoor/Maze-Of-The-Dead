package character;

import java.util.Comparator;
import java.util.PriorityQueue;

import maze.Maze;
import utilities.Functions;
import utilities.Movement;
import utilities.Position;

public class Enemy extends CharacterModel {
    public Enemy(Position pos) {
        super(pos);
    }

    /***
     * Moves the enemy to the next given position closes to the player
     *
     */
    public void move() {
        // Open queue to keep track of all possible moves the enemy could move to
        PriorityQueue<Position> openQueue = new PriorityQueue<Position>(100,
                Comparator.comparing(move -> getDistanceFromPlayer(move)));

        Position current = getPosition();
        // generate successors from the current based off the directions the enemy can
        // move find the position which minimizes the distance to the player
        // then move the enemy to that position
        for (Movement movement : Movement.values()) {
            if (movement == Movement.STATIONARY)
                continue;

            Position successor = Functions.updatePosition(current, movement);

            if (validateEnemyMove(successor)) {
                // add it to the open queue
                openQueue.add(successor);
            }
        }

        Position winner = openQueue.poll();
        setPosition(winner);
    }

    /**
     * Checks if the enemy can move in the given direction Checks if there is wall
     * in the way, which prevents movement.
     * 
     * @param position The position the enemy wants to move to
     * @return True if it can, false if it can't
     */
    private boolean validateEnemyMove(Position position) {
        return !Maze.getInstance().isWall(position);
    }

    /**
     * Gets the distance from the player in number of steps to the given position
     * 
     * @param position The current position we want to calculate the distance from
     * @return The distance between the given position and the player
     */
    private int getDistanceFromPlayer(Position position) {
        Position playerPos = Player.getInstance().getPosition();
        return Maze.getInstance().getDistance(position, playerPos);
    }
}
