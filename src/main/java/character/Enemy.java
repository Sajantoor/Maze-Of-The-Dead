package character;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

import maze.Maze;
import utilities.Position;

/**
 * This class represents an enemy in the game.
 * 
 * @author Sajan Toor
 */
public class Enemy extends CharacterModel {
    ArrayList<Position> currentPath;
    ArrayList<Position> nextPath;

    /**
     * This represents the enemy for the game
     * 
     * @param pos The position the enemy will start
     */
    public Enemy(Position pos) {
        super(pos);
        currentPath = generatePath();
        nextPath = null;
    }

    private ArrayList<Position> generatePath() {
        Position player = Player.getInstance().getPosition();
        ArrayList<Position> generatedPath = null;
        if (getPosition() != null)
            generatedPath = getPath(getPosition(), player);

        if (generatedPath == null)
            return null;

        if (generatedPath.size() > 0)
            generatedPath.remove(0); // remove the current position

        return generatedPath;
    }

    /**
     * Checks if the enemy can move to the given position.
     * The enemy cannot go through walls
     * 
     * @param position the position to check
     * @return true if the enemy can move to the given position
     */
    private boolean validateMovement(Position position) {
        // if there's a wall at the position, can't go there
        return !Maze.getInstance().isWall(position);
    }

    /**
     * Get the shortest path from the current position to the target. Uses
     * breadth-first search.
     * 
     * @param current The current position
     * @param target  The target position
     * @return A list of positions representing the path, this is the shortest path
     *         from current to target
     */
    private ArrayList<Position> getPath(Position current, Position target) {
        // check if positions are equal and return empty list if they are
        if (current.equals(target))
            return new ArrayList<Position>();

        // To mark visited positions
        HashSet<Position> visited = new HashSet<Position>();
        // To store the current path in bfs
        Queue<ArrayList<Position>> queue = new LinkedList<ArrayList<Position>>();
        // Start initial path from current position
        ArrayList<Position> initialPath = new ArrayList<Position>();
        initialPath.add(current);
        queue.add(initialPath);

        while (!queue.isEmpty()) {
            ArrayList<Position> path = queue.remove();

            for (Position position : path) {
                visited.add(position);
            }

            Position last = path.get(path.size() - 1);

            // if cell is a wall or another enemy
            if (!validateMovement(last))
                continue;

            // if we have reached our target, this must be the shortest path
            if (last.equals(target))
                return path;

            ArrayList<Position> adjacentPos = Maze.getInstance().getAdjacentPositions(last);

            // traverse through all adjacent positions
            for (Position adjacent : adjacentPos) {
                if (!visited.contains(adjacent)) {
                    // add the adjacent position to the current path and the queue and iterate
                    ArrayList<Position> newPath = new ArrayList<Position>(path);
                    newPath.add(adjacent);
                    queue.add(newPath);
                }
            }
        }
        return null;
    }

    /**
     * If the player has moved, regenerate the path to the player
     */
    public void regeneratePath() {
        // check if player moved
        Position player = Player.getInstance().getPosition();

        // the end of the path contains the player's position by definition
        if (currentPath.size() - 1 >= 0) {
            if (player.equals(currentPath.get(currentPath.size() - 1))) {
                return;
            }
        }

        do {
            nextPath = generatePath();
        } while (nextPath == null);
    }

    /***
     * Moves the enemy to the next given position closes to the player
     *
     */
    public void move() {
        if (currentPath == null) {
            return;
        }

        // check if a new path has been generated and use that instead
        if (nextPath != null) {
            currentPath = nextPath;
            nextPath = null;
        }

        // pop last position from path and move to it
        if (currentPath != null && currentPath.size() > 0) {
            Position nextPos = currentPath.remove(0);
            setPosition(nextPos);
        }
    }
}
