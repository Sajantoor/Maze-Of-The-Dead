package maze;

import org.junit.jupiter.api.Test;

import utilities.Constants;
import utilities.Position;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

public class MazeTest {
    private Maze maze;
    private Position start;
    private Position end;

    private Position getRandomMazePosition() {
        int x = (int) (Math.random() * (Constants.mazeWidth));
        int y = (int) (Math.random() * (Constants.mazeHeight));
        return new Position(x, y);
    }

    @BeforeEach
    public void setUp() {
        maze = Maze.getInstance();
        maze.regenerateMaze();
        start = new Position(Constants.playerStartX, Constants.playerStartY);
        end = new Position(Constants.playerEndX, Constants.playerEndY);
    }

    @Test
    public void getInstance() {
        Maze maze2 = Maze.getInstance();
        assertEquals(maze, maze2);
    }

    @Test
    public void getCell() {
        for (int i = 0; i < Constants.mazeWidth; i++) {
            for (int j = 0; j < Constants.mazeHeight; j++) {
                Cell cell = maze.getCell(i, j);
                assertNotNull(cell);
            }
        }

    }

    @Test
    void getCellIllegal() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            maze.getCell(-1, -1);
        });

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            maze.getCell(Constants.mazeWidth, Constants.mazeHeight);
        });
    }

    @Test
    public void getCellPos() {
        for (int i = 0; i < Constants.mazeWidth; i++) {
            for (int j = 0; j < Constants.mazeHeight; j++) {
                Position pos = new Position(i, j);
                Cell cell = maze.getCell(pos);
                assertNotNull(cell);
            }
        }
    }

    @Test
    public void getCellPosIllegal() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Position pos = new Position(-1, -1);
            maze.getCell(pos);
        });

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Position pos = new Position(Constants.mazeWidth, Constants.mazeHeight);
            maze.getCell(pos);
        });
    }

    @Test
    public void isEnd() {
        for (int i = 0; i < Constants.mazeWidth; i++) {
            for (int j = 0; j < Constants.mazeHeight; j++) {
                Position pos = new Position(i, j);
                if (Constants.playerEndX == i && Constants.playerEndY == j) {
                    assertEquals(true, maze.isEnd(pos));
                } else {
                    assertEquals(false, maze.isEnd(pos));
                }
            }
        }
    }

    @Test
    public void isEndIllegal() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Position pos = new Position(-1, -1);
            maze.isEnd(pos);
        });

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Position pos = new Position(Constants.mazeWidth, Constants.mazeHeight);
            maze.isEnd(pos);
        });
    }

    @Test
    public void isWall() {
        // by definition of the maze, the start point and end point is never a wall
        assertEquals(false, maze.isWall(start));
        assertEquals(false, maze.isWall(end));

        // the point above the start point is a wall
        Position aboveStart = new Position(start.getX(), start.getY() - 1);
        assertEquals(true, maze.isWall(aboveStart));

        // set some random point to be a wall and check if it is a wall
        Position pos = getRandomMazePosition();
        maze.getCell(pos).setWall();
        assertEquals(true, maze.isWall(pos));

        // set it to not be a wall
        maze.getCell(pos).setEmpty();
        assertEquals(false, maze.isWall(pos));
    }

    @Test
    public void isTrap() {
        // by definition of the maze, the start point and end point is never a wall
        assertEquals(false, maze.isTrap(start));
        assertEquals(false, maze.isTrap(end));

        // set some random point to be a trap and check if it is a trap
        Position pos = getRandomMazePosition();

        maze.getCell(pos).setTrap();
        assertEquals(true, maze.isTrap(pos));

        // set it to not be a wall
        maze.getCell(pos).setEmpty();
        assertEquals(false, maze.isTrap(pos));
    }

    @Test
    public void isSolvable() {
        assertEquals(true, maze.isSolvable());
        // change end to a wall to make it unsolvable
        maze.getCell(end).setWall();
        assertEquals(false, maze.isSolvable());
    }

    // Set entire maze to be walls
    private void clearMaze() {
        for (int i = 0; i < Constants.mazeWidth; i++) {
            for (int j = 0; j < Constants.mazeHeight; j++) {
                maze.getCell(i, j).setWall();
            }
        }
    }

    // Create path from start x blocks away from start and y blocks away from start
    private void createPath(Position start, int x, int y) {
        // create path y blocks
        for (int i = 0; i < x; i++) {
            maze.getCell(start.getX() + i, start.getY()).setEmpty();
        }

        // create path y blocks from start
        for (int i = 0; i < y; i++) {
            maze.getCell(start.getX(), start.getY() + i).setEmpty();
        }
    }

    // creates a cycle path
    private void createCycle(int radius) {
        // go right radius blocks -> pos: (startX + raidus, startY)
        createPath(start, radius, 0);
        // go down from there -> pos: (startX + radius, startY + radius)
        createPath(new Position(start.getX() + radius, start.getY()), 0, radius);

        // go down radius blocks from start -> pos: (startX, startY + radius)
        createPath(start, 0, radius);

        // go right raidus blocks from there -> pos: (startX + raidus, startY + radius)
        // idk why + 1 but it works and i cannot be bothered to fix this
        createPath(new Position(start.getX(), start.getY() + radius), radius + 1, 0);
    }

    // Create new maze with a path from start x blocks away from start and y blocks
    // away from start
    private void createPathMaze(int x, int y) {
        clearMaze();
        createPath(start, x, y);
    }

    // create a cycle maze
    private void createCycleMaze(int radius) {
        clearMaze();
        createCycle(radius);
    }

    @Test
    public void isPath() {
        // maze by definition should have a path from start to end
        assertEquals(true, maze.isRoute(start, end));

        // // block off the block at the end of the path
        maze.getCell(end).setWall();
        assertEquals(false, maze.isRoute(start, end));

        // create new non random path 8 blocks away from start
        createPathMaze(8, 0);
        Position pathEnd = new Position(Constants.playerStartX + 7,
                Constants.playerStartY);

        assertEquals(true, maze.isRoute(start, pathEnd));

        createCycleMaze(8);
        assertEquals(true, maze.isRoute(start, pathEnd));

        // block off the block aff one before the end
        Position pos = new Position(pathEnd.getX() - 1, pathEnd.getY());
        maze.getCell(pos).setWall();
        // by definition, of cycle the path should still be there
        assertEquals(true, maze.isRoute(start, pathEnd));

        // block off end
        maze.getCell(pathEnd).setWall();
        assertEquals(false, maze.isRoute(start, pathEnd));
    }

    @Test
    public void getAdjacentPositions() {
        Position pos = new Position(8, 8);

        // get adjacent positions
        ArrayList<Position> adjPos = maze.getAdjacentPositions(pos);

        // check if they are actually the adjacent positions
        Position down = new Position(pos.getX(), pos.getY() - 1);
        assertEquals(down, adjPos.get(0));

        Position up = new Position(pos.getX(), pos.getY() + 1);
        assertEquals(up, adjPos.get(1));

        Position left = new Position(pos.getX() - 1, pos.getY());
        assertEquals(left, adjPos.get(2));

        Position right = new Position(pos.getX() + 1, pos.getY());
        assertEquals(right, adjPos.get(3));
    }

}
