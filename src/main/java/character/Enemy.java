package character;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

import game.Entities;
import maze.Maze;
import utilities.Position;

public class Enemy extends CharacterModel {
    ArrayList<Position> currentPath;
    ArrayList<Position> nextPath;

    public Enemy(Position pos) {
        super(pos);
        currentPath = generatePath();
        nextPath = null;
    }

    private ArrayList<Position> generatePath() {
        Position player = Player.getInstance().getPosition();
        ArrayList<Position> generatedPath = getPath(getPosition(), player);

        if (generatedPath == null)
            return null;

        generatedPath.remove(0); // remove the current position
        return generatedPath;
    }

    /**
     * Checks if the enemy can move to the given position.
     * The enemy cannot go through other enemies or walls.
     * 
     * @param position the position to check
     * @return true if the enemy can move to the given position
     */
    private boolean validateMovement(Position position) {
        Maze maze = Maze.getInstance();

        // TODO: After merged check if it's in bounds, that's in functions already

        // if there's a wall at the position, can't go there
        if (maze.isWall(position))
            return false;

        // if there's another enemy at the position, can't go there
        Entities entities = Entities.getInstance();

        if (entities.containsEnemy(position))
            return false;

        return true;
    }

    /**
     * Get the shorest path from the current position to the target. Uses
     * breadth-first search.
     * 
     * @param current The current position
     * @param target  The target position
     * @return A list of positions representing the path, this is the shortest path
     *         from current to target
     */
    private ArrayList<Position> getPath(Position current, Position target) {
        // To mark visited positions
        HashSet<Position> visited = new HashSet<Position>();
        // To store the current path in bfs
        Queue<ArrayList<Position>> queue = new LinkedList<ArrayList<Position>>();
        // Start intiial path from current position
        ArrayList<Position> initialPath = new ArrayList<Position>();
        initialPath.add(current);
        queue.add(initialPath);

        Maze maze = Maze.getInstance();

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

            ArrayList<Position> adjacentPos = maze.getAdjacentPositions(last);

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
     * If the player has moved, regeneate the path to the player
     */
    public void regeneratePath() {
        // check if player moved
        Position player = Player.getInstance().getPosition();

        // the end of the path contains the player's position by definition
        if (player.equals(currentPath.get(currentPath.size() - 1))) {
            return;
        }

        nextPath = generatePath();
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
        Position nextPos = currentPath.remove(0);
        setPosition(nextPos);
    }
}
