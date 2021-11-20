package leaderboard;

import utilities.Constants;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Creates a leaderboard with the top 5 players of the game
 *
 * @author Maisha Supritee Chowdhury
 * @see PlayerScore
 */
public class Leaderboard {
	private ArrayList<PlayerScore> playerScores;
	int size = Constants.playerListSize;
	private static Leaderboard leaderboardInstance = null;

	private Leaderboard() {
		this.playerScores = new ArrayList<PlayerScore>(size);
		readFromFile();
	}

	/**
	 * Returns singleton leaderboard Instance
	 *
	 * @return singleton leaderboard Instance
	 * @see PlayerScore
	 */
	public static Leaderboard getInstance() {
		if (leaderboardInstance == null) {
			leaderboardInstance = new Leaderboard();
		}

		return leaderboardInstance;
	}

	/**
	 * Adds the playerScore argument to the Leaderboard
	 *
	 * @param playerScore the name and score of the player
	 * @see Constants
	 * @see PlayerScore
	 */
	public void addPlayerScore(PlayerScore playerScore) {
		if (playerScores.size() == 0) {
			playerScores.add(playerScore);
			return;
		}

		int score = playerScore.getScore();

		// if the smallest element in player scores is larger than the current score
		// don't add it to the leaderboard
		if (playerScores.get(size - 1).getScore() > score) {
			return;
		}

		// know it's larger than the smallest element, therefore add it
		for (int i = 0; i < size; i++) {
			// if the score is larger, insert it in the correct position in the
			// leaderboard, shifts everything over
			if (score > playerScores.get(i).getScore()) {
				playerScores.add(i, playerScore);
				break;
			}
		}

		// remove the last element(s) if the leaderboard is full
		for (int i = size; i < playerScores.size(); i++) {
			playerScores.remove(size);
		}
	}

	/**
	 * Returns player name and score at the index argument
	 *
	 * @param index
	 * @return playerScore at the index argument
	 * @see PlayerScore
	 */
	public PlayerScore getPlayerScore(int index) {
		// Out of bounds also we don't want to expose anything outside of size of the
		// leaderboard to the public, just incase playerScores.size() > size
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}

		return playerScores.get(index);
	}

	@Override
	public String toString() {
		String result = "";
		for (int i = 0; i < playerScores.size(); i++) {
			result += playerScores.get(i).toString() + "\n";
		}

		return result;
	}

	/**
	 * Creates a new text file and writes the Leaderboard on it
	 *
	 * @see Constants
	 * @see PlayerScore
	 */
	public void writeToFile() {
		// creating a file
		try {
			File file = new File(Constants.leardboardFile);
			file.createNewFile();

			FileWriter fileW = new FileWriter(file);
			PrintWriter printW = new PrintWriter(fileW);

			// outputting to the file
			for (int i = 0; i < playerScores.size(); i++) {
				PlayerScore player = playerScores.get(i);
				printW.println("Player: " + player.getName() + "\nScore: " + player.getScore());
			}

			// closing the file
			printW.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Reads Leaderboard data from a text file and outputs it on to the screen
	 *
	 * @see Constants
	 */
	private void readFromFile() {
		// reading from a file
		try {
			File file = new File(Constants.leardboardFile);
			Scanner fileReader = new Scanner(file);

			while (fileReader.hasNextLine()) {
				String data = fileReader.nextLine();
				System.out.println(data);
			}
			// closing the file
			fileReader.close();
		} catch (IOException e) {
			System.out.println("There was an error.");
			e.printStackTrace();
		}
	}
	/**
	 * Return the number of PlayerScore in the leaderboard
	 * @return the number of PlayerScore in the leaddrboard
	 * @see PlayerScore
	 */
	public int getLeaderboardSize(){
		return playerScores.size();
	}

	public int getMinimumScore() {
		return playerScores.get(size-1).getScore();
	}
}

