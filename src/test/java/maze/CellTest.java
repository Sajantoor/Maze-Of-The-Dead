package maze;

import utilities.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CellTest {
    private Cell cell;

    @BeforeEach
    void setUp() {
        cell = new Cell(new Position(10, 10), CellType.WALL);
    }

    @Test
    void Cell() {
        Position testPos = new Position(20, 20);
        Cell cell2 = new Cell(testPos);
        Position outcome = cell2.getPosition();
        assertEquals(testPos, outcome);
    }

    @Test
    void getPosition() {
        Position outcome = cell.getPosition();
        Position testPos = new Position(10, 10);
        assertEquals(testPos, outcome);
    }

    @Test
    void getCellType() {
        CellType outcome = cell.getCellType();
        CellType testCell = CellType.WALL;
        assertEquals(testCell, outcome);
    }

    @Test
    void setCellType() {
        CellType testCell = CellType.PATH;
        cell.setCellType(testCell);
        CellType outcome = cell.getCellType();
        assertEquals(testCell, outcome);
    }

    @Test
    void setEmpty() {
        cell.setEmpty();
        CellType outcome = cell.getCellType();
        assertEquals(CellType.PATH, outcome);
    }

    @Test
    void setWall() {
        cell.setWall();
        CellType outcome = cell.getCellType();
        assertEquals(CellType.WALL, outcome);
    }

    @Test
    void setTrap() {
        cell.setTrap();
        CellType outcome = cell.getCellType();
        assertEquals(CellType.TRAP, outcome);
    }

    @Test
    void setReward() {
        cell.setReward();
        CellType outcome = cell.getCellType();
        assertEquals(CellType.REWARD, outcome);
    }

    @Test
    void isWall() {
        assertTrue(cell.isWall());
    }

    @Test
    void isEmpty() {
        cell.setEmpty();
        assertTrue(cell.isPath());
    }

    @Test
    void isStart() {
        cell.setCellType(CellType.START);
        assertTrue(cell.isStart());
    }

    @Test
    void isEnd() {
        cell.setCellType(CellType.END);
        assertTrue(cell.isEnd());
    }

    @Test
    void isTrap() {
        cell.setTrap();
        assertTrue(cell.isTrap());
    }

    @Test
    void getX() {
        int outcome = cell.getX();
        int testX = 10;
        assertEquals(testX, outcome);
    }

    @Test
    void getY() {
        int outcome = cell.getX();
        int testY = 10;
        assertEquals(testY, outcome);
    }

    @Test
    void testToString() {
        assertEquals("#", cell.toString());
    }
}
