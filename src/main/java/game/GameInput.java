package game;

import java.util.ArrayList;

import character.Player;
import maze.Cell;
import maze.Maze;
import utilities.Constants;
import utilities.Functions;
import utilities.Movement;
import utilities.Position;

public class GameInput {
    private static GameInput instance = null;
    private ArrayList<Movement> moves;

    private GameInput() {
        instance = this;
        moves = new ArrayList<Movement>();
        moves.add(Movement.STATIONARY);
    }

    public static GameInput getInstance() {
        if (instance == null)
            new GameInput();

        return instance;
    }

    /**
     * Move the player in the direction specified by the movement. Checks if there
     * is a collision with a wall and acts accordingly.
     * 
     * @param movement the movement to be performed
     */
    private void movePlayer(Movement movement) {
        Player player = Player.getInstance();
        // check if there is a wall in the way that would stop movement
        Position position = player.getPosition();

        // update the player's position based on the movement
        Position nextPosition = Functions.updatePosition(position, movement);

        // check if the next position is a valid position, ie is there a wall
        // check if it fits in the constants of the maze
        if (nextPosition.getX() >= 0 && nextPosition.getX() < Constants.mazeWidth && nextPosition.getY() >= 0
                && nextPosition.getY() < Constants.mazeHeight) {
            Cell cell = Maze.getInstance().getCell(nextPosition);

            // if there is not a wall, the player can move
            if (!cell.isWall()) {
                player.move(movement);
            }
        }
    }

    public void movePlayer() {
        movePlayer(moves.get(0));
    }

    /**
     * Adds the given move to the list of moves.
     * 
     * @param move The move to be added
     */
    public void addMovement(Movement move) {
        moves.add(0, move);
    }

    /**
     * Removes the given move from the list of moves.
     * 
     * @param move The move we want to remove
     */
    public void removeMovement(Movement move) {
        moves.remove(move);
    }

    /**
     * Checks if a the list contains a move
     * 
     * @param move The move wae want to check if it is in the list
     * @return True if the move is in the list, false otherwise
     */
    public boolean checkMovement(Movement move) {
        return moves.contains(move);
    }
}
