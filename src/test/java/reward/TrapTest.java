package reward;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utilities.Position;

import static org.junit.jupiter.api.Assertions.*;

class TrapTest {
    private Trap trap;

    @BeforeEach
    void setUp() {
        trap = new Trap(new Position(1, 1), TrapType.TRAPFALL);
    }

    @Test
    void getTrapType() {
        assertEquals(TrapType.TRAPFALL, trap.getTrapType());
    }

    @Test
    void setTrapType() {
        trap.setTrapType(TrapType.BOOBYTRAP);
        assertEquals(TrapType.BOOBYTRAP, trap.getTrapType());
    }

    @Test
    void getPosition() {
        Position result = trap.getPosition();
        assertEquals(new Position(1, 1), result);
    }

    @Test
    void setPosition() {
        trap.setPosition(new Position(5, 2));
        Position result = trap.getPosition();
        assertEquals(new Position(5, 2), result);
    }
}