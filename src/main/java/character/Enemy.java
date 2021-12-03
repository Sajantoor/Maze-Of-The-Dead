package character;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

import maze.Maze;
import utilities.Position;

public class Enemy extends CharacterModel {
    ArrayList<Position> path;
    ArrayList<Position> nextPath;

    public Enemy(Position pos) {
        super(pos);
        generatePath();
        nextPath = null;
    }

    private void generatePath() {
        Position player = Player.getInstance().getPosition();
        path = getPath(getPosition(), player);
        path.remove(0); // remove the current position
    }

    // dfs solution
    private ArrayList<Position> getPath(Position current, Position target) {
        HashSet<Position> visited = new HashSet<Position>();
        Queue<ArrayList<Position>> queue = new LinkedList<ArrayList<Position>>();
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
            if (maze.getCell(last).isWall())
                continue;

            // if we have reached our target, this must be the shortest path
            if (last.equals(target))
                return path;

            ArrayList<Position> adjacentPos = maze.getAdjacentPositions(last);

            for (Position adjacent : adjacentPos) {
                if (!visited.contains(adjacent)) {
                    ArrayList<Position> newPath = new ArrayList<Position>(path);
                    newPath.add(adjacent);
                    queue.add(newPath);
                }
            }
        }

        return null;
    }

    public void regeneratePath() {
        // check if player moved
        Position player = Player.getInstance().getPosition();

        if (player.equals(path.get(path.size() - 1))) {
            // System.out.println("they are the same");
            return;
        }
        // System.out.println("regenerating path");

        nextPath = getPath(getPosition(), player);
        nextPath.remove(0); // remove the current position
        // path = nextPath;
        // nextPath = null;
    }

    /***
     * Moves the enemy to the next given position closes to the player
     *
     */
    public void move() {
        if (nextPath != null) {
            path = nextPath;
            nextPath = null;
        }

        // // pop last position from path and move to it
        Position nextPos = path.remove(0);
        setPosition(nextPos);
    }
}
