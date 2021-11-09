package cell;

public enum CellType {
    WALL(0), PATH(1), START(2), END(3);

    private int value;

    CellType(int value) {
        this.value = value;
    }
}
