package character;

import maze.Maze;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utilities.Position;

import static org.junit.jupiter.api.Assertions.*;

class EnemyTest {
    private Maze maze;
    private Enemy enemy;
    private Player player;
    Position ePos = null;

    @BeforeEach
    void setup() {
        int i = 0;
        maze = Maze.getInstance();
        maze.regenerateMaze();

        do {
            ePos = new Position(10, i++);
        } while (maze.getCell(ePos).isWall());

        player = Player.getInstance();
        enemy = new Enemy(ePos);
    }

    @Test
    void move() {
        enemy.move();
        assertNotEquals(enemy.getPosition(), ePos);
    }

    @Test
    void reachesPlayer() {
        int i = 0;
        while (!(Player.getInstance().getPosition()).equals(enemy.getPosition()) && i < 1000) {
            enemy.move();
            i++;
        }
        assertEquals(Player.getInstance().getPosition(), enemy.getPosition());
    }
}