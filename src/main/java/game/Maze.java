package game;

import cell.Cell;
import utilities.Position;
import cell.CellType;

import java.util.ArrayList;

/**
 * Represents a maze which is comprised of Cells arranged in a 2D grid.
 *
 * @author Dylan Young
 */
public class Maze {
    private Cell[][] maze;

    /**
     * Represents a randomly generated maze that the game will be played on. This
     * maze will be composed of walls, paths, a start point and an end point.
     *
     * @param width  the absolute width of the maze
     * @param height the absolute height of the maze
     */
    public Maze(int width, int height, int numRooms) {
        maze = new Cell[width][height];
        newMaze(width, height, numRooms);
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
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                maze[j][i] = new Cell(new Position(j, i));
            }
        }

        generateMaze(height, width);

        addRooms(numRooms, width, height);

        setStart(1, 1);
        setEnd(width - 1, height - 1);
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
}
