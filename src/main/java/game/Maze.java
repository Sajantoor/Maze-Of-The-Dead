package game;

import cell.Cell;
import utilities.Constants;
import utilities.Position;
import cell.CellType;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Represents a maze which is comprised of Cells arranged in a 2D grid.
 *
 * @author Dylan Young
 */
public class Maze {
    private Cell[][] maze;
    private static Maze instance = null;
    private int height;
    private int width;
    private HashSet<Cell> visited;

    /**
     * returns an instance of the Maze
     * @return an instance of the Maze
     */
    public static Maze getInstance(){
        if(instance == null){
            instance = new Maze();
        }
        return instance;
    }

    /**
     * Returns a cell at position (x,y)
     *
     * @param x the x position of the cell in the maze
     * @param y the y position of the cell in the maze
     * @return the cell of the maze the position (x,y)
     * @see Cell
     */
    public Cell getCell(int x, int y) {
        return maze[x][y];
    }

    /***
     * Returns a cell at the position object
     * 
     * @param position the position of the cell in the maze
     * @return the cell of the maze at the position object
     * @see Cell
     * @see Position
     */
    public Cell getCell(Position position) {
        return getCell(position.getX(), position.getY());
    }

    public boolean isEnd(Position position) {
        return getCell(position).isEnd();
    }

    /**
     * Returns whether or not the cell at position is a wall
     * 
     * @param position the position of the cell in the maze
     * @return True if it is a wall, false otherwise
     */
    public boolean isWall(Position position) {
        return getCell(position).isWall();
    }

    /**
     * Returns whether or not the cell at position is a trap
     * 
     * @param position the position of the cell in the maze
     * @return True if it is a trap, false otherwise
     */
    public boolean isTrap(Position position) {
        return getCell(position).isTrap();
    }

    /**
     * Sets the cell type of cell at position (x, y) to a start point
     *
     * @param x the x position of the cell in the maze
     * @param y the y position of the cell in the maze
     * @see Cell
     * @see CellType
     */
    public void setStart(int x, int y) {
        maze[x][y].setCellType(CellType.START);
    }

    /**
     * Sets the cell type of cell at position (x, y) to an end point
     *
     * @param x the x position of the cell in the maze
     * @param y the y position of the cell in the maze
     * @see Cell
     * @see CellType
     */
    public void setEnd(int x, int y) {
        maze[x][y].setCellType(CellType.END);
    }

    /**
     * Creates a new maze with walls and start point at the top left corner and end
     * point at bottom right corner
     *
     * @param width  the absolute width of the maze
     * @param height the absolute height of the maze
     */
    public void newMaze(int width, int height, int numRooms) {
        this.height = height;
        this.width = width;
        maze = new Cell[width][height];
        visited = new HashSet<Cell>();

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                maze[j][i] = new Cell(new Position(j, i));
            }
        }

        generateMaze(height, width);

        addRooms(numRooms, width, height);

        setStart(Constants.playerStartX, Constants.playerStartY);
        setEnd(Constants.playerEndX, Constants.playerEndY);

        connectStartToPath();
        connectEndToPath();
    }

    /**
     * Creates rooms at random positions, with random heights and widths. Rooms are
     * large areas with no walls to give the player more freedom to move
     *
     * @param numRooms the number of rooms that will be generated in the maze
     */
    public void addRooms(int numRooms, int mazeWidth, int mazeHeight) {
        for (int i = 0; i < numRooms; i++) {

            int x = (int) ((Math.random() * (mazeWidth - 2)) + 1);
            int y = (int) ((Math.random() * (mazeHeight - 2)) + 1);
            int width = 0;
            int height = 0;

            do {
                width = randomDimension();
            } while (width + x >= mazeWidth);

            do {
                height = randomDimension();
            } while (height + y >= mazeHeight);

            addRoom(x, y, width, height);
        }
    }

    private void addRoom(int x, int y, int width, int height) {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                maze[x + j][y + i].setCellType(CellType.PATH);
            }
        }
    }

    private int randomDimension() {
        return (int) (Math.random() * 2 + 1);
    }

    private void generateMaze(int height, int width) {
        ArrayList<Cell> cells = new ArrayList<>();

        int x = (int) ((Math.random() * (width - 2)) + 1);
        int y = (int) ((Math.random() * (height - 2)) + 1);

        maze[x][y].setCellType(CellType.PATH);

        cells = addCellsToList(cells, height, width, x, y);

        while (cells.size() > 0) {
            int i = (int) (Math.random() * cells.size() - 1);

            x = cells.get(i).getX();
            y = cells.get(i).getY();
            int count = numberAdjacentPath(x, y);

            if (count == 1) {
                maze[x][y].setCellType(CellType.PATH);

                cells = addCellsToList(cells, height, width, x, y);
            }
            cells.remove(i);
        }

    }

    private ArrayList<Cell> addCellsToList(ArrayList<Cell> cells, int height, int width, int x, int y) {

        if (x != 1)
            cells.add(maze[x - 1][y]);
        if (x != width - 2)
            cells.add(maze[x + 1][y]);
        if (y != 1)
            cells.add(maze[x][y - 1]);
        if (y != height - 2)
            cells.add(maze[x][y + 1]);

        return cells;
    }

    private int numberAdjacentPath(int x, int y) {
        int count = 0;

        if (maze[x + 1][y].getCellType() == CellType.PATH)
            count++;
        if (maze[x - 1][y].getCellType() == CellType.PATH)
            count++;
        if (maze[x][y + 1].getCellType() == CellType.PATH)
            count++;
        if (maze[x][y - 1].getCellType() == CellType.PATH)
            count++;

        return count;
    }

    private void connectStartToPath() {
        int y = 0;
        while (maze[1][y + 1].getCellType() == CellType.WALL && maze[2][y].getCellType() == CellType.WALL) {
            maze[1][y + 1].setCellType(CellType.PATH);
            y += 1;
        }
    }

    private void connectEndToPath() {
        int x = 0;
        while (maze[width - 1 - x][height - 3].getCellType() == CellType.WALL
                && maze[width - 2 - x][height - 2].getCellType() == CellType.WALL) {
            maze[width - 2 - x][height - 2].setCellType(CellType.PATH);
            x++;
        }
    }

    /**
     * Returns all adjacent cells of the current position, looking up, down, left,
     * right
     * 
     * @param position the position we want to get adjacent cells of
     * @return ArrayList of adjacent cells
     */
    private ArrayList<Cell> getAdjacentCells(Position position) {
        ArrayList<Cell> cells = new ArrayList<>();

        if (position.getX() != 0)
            cells.add(getCell(position.getX() - 1, position.getY()));
        if (position.getX() != width - 1)
            cells.add(getCell(position.getX() + 1, position.getY()));
        if (position.getY() != 0)
            cells.add(getCell(position.getX(), position.getY() - 1));
        if (position.getY() != height - 1)
            cells.add(getCell(position.getX(), position.getY() + 1));

        return cells;
    }

    /**
     * Recursive method to find a path from the start to the end of the maze, made
     * up of player moves, uses breadth first search algorithm.
     * 
     * @param current The current position
     * @param target  The target position
     * @return True if there is a path from the start to the end of the maze
     */
    private boolean isPathHelper(Position current, Position target) {
        Cell cell = getCell(current);
        // we've already looked here
        if (visited.contains(cell))
            return false;

        visited.add(cell);

        // if we have reached our target, there must be a path.
        if (cell.getPosition().equals(target))
            return true;

        // if cell is a trap or wall, we can't move through it
        if (cell.isWall() || cell.isTrap())
            return false;

        // if cell is not wall or path, get adjacent cells
        ArrayList<Cell> adjacentCells = getAdjacentCells(current);

        // look at all adjacent cells and recurse
        for (Cell adjacentCell : adjacentCells) {
            Position adjacentPosition = adjacentCell.getPosition();

            if (isPathHelper(adjacentPosition, target))
                return true;
        }

        return false;
    }

    /**
     * Checks if there is a path from the start point to the end point, using player
     * moves ie, without going through traps or walls.
     * 
     * @param start The start position
     * @param end   The end position
     * @return True if there is a path from the start to the end, false otherwise
     */
    public boolean isPath(Position start, Position end) {
        // empty visited list
        visited.clear();
        return isPathHelper(start, end);
    }

    /**
     * Checks if the maze is solvable
     *
     * @return returns true if it is, false if not
     */
    public boolean isSolvable() {
        Position start = new Position(Constants.playerStartX, Constants.playerStartY);
        Position end = new Position(Constants.playerEndX, Constants.playerEndY);
        return isPath(start, end);
    }

    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                s += maze[j][i].toString();
            }
            s += "\n";
        }

        return s;
    }
}
