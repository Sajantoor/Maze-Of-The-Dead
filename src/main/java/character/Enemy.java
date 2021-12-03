package character;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

import maze.Maze;
import utilities.Functions;
import utilities.Movement;
import utilities.Position;

public class Enemy extends CharacterModel {
    ArrayList<Position> path;

    public Enemy(Position pos) {
        super(pos);
        generatePath();
    }

    private void generatePath() {
        Maze maze = Maze.getInstance();
        Position player = Player.getInstance().getPosition();
        path = maze.generatePath(getPosition(), player);
    }

    /***
     * Moves the enemy to the next given position closes to the player
     *
     */
    public void move() {
        // pop last position from path and move to it
        setPosition(path.get(path.size() - 1));
        path.remove(path.size() - 1);
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
