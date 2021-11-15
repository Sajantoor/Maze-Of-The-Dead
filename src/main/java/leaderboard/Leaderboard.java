package leaderboard;
import utilities.Constants;

import static utilities.Constants.playerListSize;
import static utilities.Constants.LeaderBoard;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Creates a leaderboard with the top 5 players of the game
 * 
 * @author Maisha Supritee Chowdhury
 * @see PlayerScore
 */
public class Leaderboard {
    private PlayerScore[] playerScores;
    private static Leaderboard singleLeaderboardInstance = null;
    
    private Leaderboard() {
    	this.playerScores= new PlayerScore[playerListSize];
    }

    /**
     * Returns singleton leaderboard Instance
	 *
     * @return singleton leaderboard Instance
     * @see PlayerScore
     */
    public static Leaderboard getInstance() {
    	
    	if(singleLeaderboardInstance == null) {
            singleLeaderboardInstance = new Leaderboard();
        }
        return singleLeaderboardInstance;
    }

	/**
	 * Adds the playerScore argument to the Leaderboard
	 *
	 * @param playerScore the name and score of the player
	 * @see Constants
	 * @see PlayerScore
	 */
	public void addPlayerScore(PlayerScore playerScore) {
    	
    	if(playerScores.length == 0)
    		playerScores[0] = playerScore;
    	else {
    			for(int i = 0; i < playerScores.length; i++)
    			{
    				if (playerScore.getScore() < playerScores[i].getScore())
    				{
    					i++;
    				}
    				else
    				{
    					PlayerScore[] result = new PlayerScore[playerListSize];
    	    			System.arraycopy(playerScores, 0, result, 0, i);
    	    			result[i] = playerScore;
    	    			System.arraycopy(playerScores, i, result, i+1, playerScores.length-i-1);
    	    			playerScores = result;
    					break;
    				}
    			}
    			
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
    	return playerScores[index];
    }

    @Override
    public String toString() {
        
        StringBuilder sb = new StringBuilder();
        for(int i=0; i< playerScores.length; i++)
        {
        	sb.append(playerScores[i].toString() + "\n");
        }
        return sb.toString();
    }

	/**
	 * Creates a new text file and writes the Leaderboard on it
	 *
	 * @see Constants
	 * @see PlayerScore
	 */
    public void writeToFile(){
    	//creating a file
    	try {
			File file = new File(LeaderBoard);
			if (file.createNewFile()) {
				System.out.println("File created: " + file.getName());
			} else {
				System.out.println("File already exists.");
			}
			FileWriter fileW = new FileWriter(file);
			PrintWriter printW = new PrintWriter(fileW);

			//outputting to the file
			for (int i = 0; i < playerScores.length; i++) {
				printW.println("Player: " + playerScores[i].getName() + "\nScore: " + playerScores[i].getScore() + "\n");
			}

			//closing the file
			printW.close();
		}catch(IOException e){
			System.out.println("There was an error.");
			e.printStackTrace();
		}
    }
	
	/**
	 * Reads Leaderboard data from a text file and outputs it on to the screen
	 *
	 * @see Constants
	 */
    private void readFromFile(){

		//reading from a file
    	try{
			File file = new File(LeaderBoard);
			Scanner fileReader = new Scanner(file);
			while (fileReader.hasNextLine()) {
				String data = fileReader.nextLine();
				System.out.println(data);
			}
			//closing the file
			fileReader.close();
		}catch(IOException e){
			System.out.println("There was an error.");
			e.printStackTrace();
		}

    }
}
