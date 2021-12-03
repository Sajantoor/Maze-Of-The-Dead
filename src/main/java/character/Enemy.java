package character;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import maze.Cell;
import maze.Maze;
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
        path = getPath(getPosition(), player);
    }

    private ArrayList<Position> getPathHelper(Position current, Position target, ArrayList<Position> path,
            HashSet<Position> visited) {
        Maze maze = Maze.getInstance();
        Cell cell = maze.getCell(current);
        Position position = cell.getPosition();

        // we've already looked here
        if (visited.contains(position))
            return null;

        visited.add(position);

        // if cell is a wall, we can't move through it
        if (cell.isWall())
            return null;

        // if we have reached our target, there must be a path.
        if (cell.getPosition().equals(target))
            return path;

        // if cell is not wall or path, get adjacent cells
        ArrayList<Cell> adjacentCells = maze.getAdjacentCells(current);

        // look at all adjacent cells and recurse
        for (Cell adjacentCell : adjacentCells) {
            Position adjacentPosition = adjacentCell.getPosition();

            if (getPathHelper(adjacentPosition, target, path, visited) != null) {
                path.add(adjacentPosition);
                return path;
            }
        }

        return null;
    }

    private ArrayList<Position> getPath(Position current, Position target) {
        ArrayList<Position> path = getPathHelper(current, target, new ArrayList<Position>(), new HashSet<Position>());
        // simplify the path, remove any times it loops back on itself
        HashMap<Position, Integer> map = new HashMap<>();

        // basic O(n) solution to finding duplicates, add to hashmap
        // add number of instances we've seen of this position in the hashmap as well
        // if we've seen it before, increment the value
        for (int i = 0; i < path.size(); i++) {
            if (map.containsKey(path.get(i))) {
                map.put(path.get(i), map.get(path.get(i)) + 1);
            } else {
                map.put(path.get(i), 1);
            }
        }

        // filter out unnecessary moves, remove any times it loops back on itself
        Position previous = null;
        for (int i = 0; i < path.size(); i++) {
            // get the number of instances we've seen of this position
            previous = path.get(i);
            int count = map.get(path.get(i));

            while (count > 1 && i < path.size()) {
                // update the hashmap with stuff we've see
                Position pos = path.get(i);

                if (pos == previous) {
                    count--;
                }

                map.put(pos, map.get(pos) - 1);
                // remove all paths that are after previous
                path.remove(i);
                i++;
            }
        }

        return path;
    }

    /***
     * Moves the enemy to the next given position closes to the player
     *
     */
    public void move() {
        // check if player has moved
        Player player = Player.getInstance();

        // since path is generated from player to enemy, just look at the first position
        // in the path
        Position lastPlayerPos = path.get(0);

        if (!player.getPosition().equals(lastPlayerPos)) {
            generatePath();
        }

        // pop last position from path and move to it
        setPosition(path.get(path.size() - 1));
        path.remove(path.size() - 1);
    }
}
