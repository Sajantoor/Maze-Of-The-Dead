package maze;

import utilities.Position;

/**
 * Represents a cell at a position in a maze that can contain either a path,
 * wall, start point, end point, reward, or trap.
 *
 * @author Dylan Young
 */

public class Cell {
    private final Position position;
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
     * @see CellType
     */
    public boolean isWall() {
        return getCellType() == CellType.WALL;
    }

    /**
     * 
     * @return True if the cell is a path, False otherwise
     */
    public boolean isPath() {
        return getCellType() == CellType.PATH;
    }

    /**
     * 
     * @return True if the cell is the end point, False otherwise
     */
    public boolean isEnd() {
        return getCellType() == CellType.END;
    }

    /**
     * 
     * @return True if the cell is the start point, False otherwise
     */
    public boolean isStart() {
        return getCellType() == CellType.START;
    }

    /**
     * 
     * @return True if the cell is a trap, False otherwise
     * @see CellType
     */
    public boolean isTrap() {
        return getCellType() == CellType.TRAP;
    }

    /**
     * Changes the content of the cell
     * 
     * @param cellType the CellType we want to change
     */
    public void setCellType(CellType cellType) {
        this.cellType = cellType;
    }

    /**
     * Changes the cell type to "empty" aka CellType.PATH
     */
    public void setEmpty() {
        setCellType(CellType.PATH);
    }

    /**
     * Changes the cell type to "wall"
     */
    public void setWall() {
        setCellType(CellType.WALL);
    }

    /**
     * Changes the cell type to "trap"
     */
    public void setTrap() {
        setCellType(CellType.TRAP);
    }

    /**
     * Changes the cell type to "reward"
     */
    public void setReward() {
        setCellType(CellType.REWARD);
    }

    /**
     * Returns the position of the cell
     * 
     * @return the position of the cell
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

    @Override
    public String toString() {
        String str = "";
        switch (cellType) {
            case WALL:
                str = "#";
                break;
            case PATH:
                str = "_";
                break;
            case START:
                str = "S";
                break;
            case END:
                str = "E";
                break;
            case TRAP:
                str = "T";
                break;
            case REWARD:
                str = "R";
                break;
        }
        return str;
    }
}
