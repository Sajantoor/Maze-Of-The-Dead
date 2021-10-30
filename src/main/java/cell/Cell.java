package main.java.cell;

import main.java.utilities.Position;

public class Cell {
    private Position position;
    private int cellType;

    public Cell(Position position, int cellType) {
        // TODO: Implement me!
    }

    public int getCellType() {
        return cellType;
    }

    public void setCellType(int cellType) {
        this.cellType = cellType;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void setPosition(int x, int y) {
        // TODO: Implement me!
    }
}
