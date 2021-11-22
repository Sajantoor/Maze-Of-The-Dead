package leaderboard;

import utilities.Constants;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static utilities.Constants.playerListSize;

/**
 * Creates a leaderboard with the top 5 players of the game
 *
 * @author Maisha Supritee Chowdhury
 * @see PlayerScore
 */
public class Leaderboard {
	private ArrayList<PlayerScore> playerScores;
	private static Leaderboard leaderboardInstance = null;

	private Leaderboard() {
		this.playerScores = new ArrayList<PlayerScore>();
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
		if(playerScore == null){
			return;
		}
		if (playerScores.size() == 0) {
			playerScores.add(playerScore);
			return;
		}

		if(playerScores.size() < playerListSize){
			boolean inserted = false;
			int i = 0;
			while(!inserted && i < playerScores.size()) {
				if(playerScore.getScore() > playerScores.get(i).getScore()){
					playerScores.add(i, playerScore);
					inserted = true;
				}
				i++;
			}
			return;
		}

		int score = playerScore.getScore();

		// if the smallest element in player scores is larger than the current score
		// don't add it to the leaderboard
		if (this.getMinimumScore() > score) {
			return;
		}

		// if it's larger than the smallest element, add it
		for (int i = 0; i < getLeaderboardSize(); i++) {
			// if the score is larger, insert it in the correct position in the
			// leaderboard, shifts everything over
			if (score > playerScores.get(i).getScore()) {
				playerScores.add(i, playerScore);
				break;
			}
		}

		// remove the last element(s) if the leaderboard is full
		for (int i = playerListSize; i < getLeaderboardSize(); i++) {
			playerScores.remove(i);
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
		// leaderboard to the public, just in case playerScores.size() > size
		if (index < 0 || index >= playerListSize) {
			throw new IndexOutOfBoundsException();
		}

		return playerScores.get(index);
	}

	@Override
	public String toString() {
		String result = "";
		for (int i = 0; i < getLeaderboardSize(); i++) {
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
			File file = new File(Constants.leaderboardFile);
			file.createNewFile();
			FileWriter fileW = new FileWriter(file);

			// outputting to the file
			fileW.write(this.toString());

			// closing the file
			fileW.close();
		} catch (IOException e) {
			System.out.println("There was an error.");
			e.printStackTrace();
		}
	}

	/**
	 * Reads Leaderboard data from a text file and adds it to the leaderboard
	 *
	 * @see Constants
	 */
	private void readFromFile() {
		try {
			File file = new File(Constants.leaderboardFile);
			//if a file doesn't exist
			if(!(file.isFile()))
				file.createNewFile();

			// reading from the file
			Scanner fileReader = new Scanner(file);
			ArrayList<PlayerScore> leaderboardData = new ArrayList<PlayerScore>();

			while (fileReader.hasNextLine()) {
				String data = fileReader.nextLine();
				String[] arr = data.split("#");
				PlayerScore temp = new PlayerScore("",0);
				String name = arr[0];
				int score = Integer.parseInt(arr[1]);
				temp.setName(name);
				temp.setScore(score);
				leaderboardData.add(temp);
			}
			// closing the file
			fileReader.close();
			this.playerScores = leaderboardData;
		} catch (IOException e) {
			System.out.println("There was an error.");
			e.printStackTrace();
		}
	}

	/**
	 * Return the number of PlayerScores in the leaderboard
	 *
	 * @return the number of PlayerScores in the leaderboard
	 * @see PlayerScore
	 */
	public int getLeaderboardSize(){
		return playerScores.size();
	}

	/**
	 * Return the minimum score of a player in the leaderboard
	 *
	 * @return the minimum score of a player in the leaderboard
	 * @see PlayerScore
	 */
	public int getMinimumScore() {

		int s = playerScores.size();
		if(s != 0){
			return playerScores.get(s - 1).getScore();
		}
		return 0;
	}
}

