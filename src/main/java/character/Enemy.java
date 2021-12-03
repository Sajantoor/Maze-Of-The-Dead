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

    private void regeneratePath() {
        Thread regenThread = new Thread(new Runnable() {
            Position player = Player.getInstance().getPosition();

            @Override
            public void run() {
                while (nextPath == null) {
                    nextPath = getPath(getPosition(), player);
                }

                System.out.println("regenerating path");

                path = nextPath;
                nextPath = null;
            }
        });

        regenThread.start();
    }

    /***
     * Moves the enemy to the next given position closes to the player
     *
     */
    public void move() {
        if (path == null) {
            return;
        }

        // // pop last position from path and move to it
        Position nextPos = path.remove(0);
        setPosition(nextPos);

        // check if player has moved
        Player player = Player.getInstance();

        // since path is generated from player to enemy, just look at the last position
        // in the path
        Position lastPlayerPos = path.get(path.size() - 1);

        if (!player.getPosition().equals(lastPlayerPos)) {
            regeneratePath();
        }
    }
}
