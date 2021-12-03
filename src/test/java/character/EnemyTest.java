package character;

import maze.Maze;
import org.junit.jupiter.api.Test;
import utilities.Position;

import static org.junit.jupiter.api.Assertions.*;

class EnemyTest {
    private Maze maze;
    private Enemy enemy;
    private Player player;
    @Test
    void move() {
        Position ePos = null;
        int i = 0;
        maze = Maze.getInstance();
        maze.regenerateMaze();
        do{
            ePos = new Position(10, i++);
        }while(maze.getCell(ePos).isWall());
        player = Player.getInstance();
        enemy = new Enemy(ePos);
        enemy.move();
        assertNotEquals(enemy.getPosition(), ePos);
    }
}