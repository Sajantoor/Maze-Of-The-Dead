package leaderboard;
import Constants.java;
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
    final int size = 5;
    
    private Leaderboard() {
    	this.playerScores[]= new PlayerScore[size];
    }

    /**
     * Returns singleton leaderboard Instance
     * @return singleton leaderboard Instance
     * @see PlayerScore
     */

    public static Leaderboard getInstance() {
    	
    	if(singleLeaderboardInstance == null) {
            singleLeaderboardInstance = new Leaderboard();
        }
        return singleLeaderboardInstance;
    }

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
    					PlayerScore[] result = new PlayerScore[size];
    	    			System.arraycopy(playerScores, 0, result, 0, i);
    	    			result[i] = playerScore;
    	    			System.arraycopy(playerScores, i, result, i+1, playerScores.length-i-1);
    	    			playerScores = result;
    					break;
    				}
    			}
    			
    		}
    }

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

    public void writeToFile() throws IOException{
    	//creating a file
    	File file = new File(constants.LeaderBoard);
        FileWriter fileW = new FileWriter(file);
        PrintWriter printW = new PrintWriter(fileW);
        
        //outputting to the file
        for(int i = 0; i < playerScores.length; i++)
        {
        	printW.println("Player: " + playerScores[i].getName() + "\nScore: " + playerScores[i].getScore() +"\n");
        }
        
        //closing the file
        printW.close();
    }

    private void readFromFile() throws IOException{
    	 
    	File file = new File(constants.LeaderBoard); 
    	Scanner fileReader = new Scanner(file);
    	while (fileReader.hasNextLine()) {
    		String data = fileReader.nextLine();
    	    System.out.println(data);
    	}
    	      
    	fileReader.close();
    }
}
