package main.java.cell;

import main.java.utilities.Position;

public class Cell {
    private Position position;
    private CellType cellType;

    public Cell(Position position, CellType cellType) {
        this.position = position;
        this.cellType = cellType;
    }

    public CellType getCellType() {
        return cellType;
    }

    public void setCellType(CellType cellType) {
        this.cellType = cellType;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void setPosition(int x, int y) {
        position = new Position(x,y);
    }
}
