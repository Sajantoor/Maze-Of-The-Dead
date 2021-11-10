package cell;

import utilities.Position;

/**
 * Represents a cell at a position in a maze that can contain either a path,
 * wall, start point, end point, reward, or trap.
 *
 * @author Dylan Young
 */

public class Cell {
    private Position position;
    private CellType cellType = CellType.WALL;

    /**
     * Represents a cell in a maze.
     *
     * @param position the position where the cell is positioned.
     * @param cellType defines what the cell is
     */
    public Cell(Position position, CellType cellType) {
        this.position = position;
        this.cellType = cellType;
    }

    /**
     * Represents a cell in a maze. Cell type is defaulted to WALL
     * 
     * @param position the position where the cell is positioned
     * 
     */
    public Cell(Position position) {
        this.position = position;
    }

    /**
     *
     * @return the current cell
     */
    public CellType getCellType() {
        return cellType;
    }

    /**
     * 
     * @return True if the cell is a wall, False otherwise
     */
    public boolean isWall() {
        return cellType == CellType.WALL;
    }

    /**
     * Changes the content of the cell
     * 
     * @param cellType
     */
    public void setCellType(CellType cellType) {
        this.cellType = cellType;
    }

    /**
     * Returns the position of the cell
     * 
     * @return
     */
    public Position getPosition() {
        return position;
    }

    /**
     * Returns the x position of the cell
     * 
     * @return the x position of the cell
     */
    public int getX() {
        return position.getX();
    }

    /**
     * Returns the y position of the cell
     * 
     * @return the y position of the cell
     */
    public int getY() {
        return position.getY();
    }
}
