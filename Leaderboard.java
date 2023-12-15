/**
 * Leaderboard
 * contains all the methods to creating and editing the leaderboard
 * @author lily
 * @date Jan 23rd 2022
 */
import java.io.*;
import java.util.*;

public class Leaderboard {
	private GUIMemory memory = new GUIMemory();
	
	Leaderboard() throws IOException{
		memory.leaderboardInfo = new ArrayList<NameAndScore>(); //initializes the data storing array
		createFile(); //creates a file
		savePreviousData();
		updateData();
	}
	
	public void createFile() {
		try {
		      memory.leaderboardtxt = new File("leaderboard.txt");
		      if (memory.leaderboardtxt.createNewFile()) {
		        System.out.println("File created: " + memory.leaderboardtxt.getName());
		        memory.addNewInfo = new FileWriter(memory.leaderboardtxt,true);
		      } else {
		        System.out.println("File already exists.");
		        memory.addNewInfo = new FileWriter(memory.leaderboardtxt,true);
		      }
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
	}
	/**
	 * savePreviousData
	 * saves the data from the existing txt file
	 * @throws IOException
	 */
	public void savePreviousData() throws IOException {
		Scanner readPrevious = new Scanner(memory.leaderboardtxt); //scanner to read from txt
		while(readPrevious.hasNext()) { //if theres more
			String line = readPrevious.nextLine(); //reads a line
			NameAndScore old = new NameAndScore(line.substring(line.indexOf(' ')+1,line.lastIndexOf(' ')), Integer.valueOf(line.substring(line.lastIndexOf(' ')+1))); //saves the data
			memory.leaderboardInfo.add(old); //adds to array list
		}
		readPrevious.close(); //closes the scanner
	}
	/**
	 * updateData
	 * updates the txt file with the new data (score)
	 * @throws IOException
	 */
	public void updateData() throws IOException {
		FileWriter updatedInfo = new FileWriter(memory.leaderboardtxt); //creates a writer
		for(int i=0; i<memory.leaderboardInfo.size(); i++) { //for the amount of data
			try {
				updatedInfo.write(Integer.toString(i+1) +  " " + memory.leaderboardInfo.get(i).getName() +  " " +Integer.toString(memory.leaderboardInfo.get(i).getScore())+"\n"); //writes the info
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			updatedInfo.flush(); //makes sure to write everything
			updatedInfo.close(); //closes the writer
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void saveScore(String name, int score) throws IOException {
		NameAndScore addInfo = new NameAndScore(name, score);
		memory.leaderboardInfo.add(ranking(score),addInfo);	 //adds the new NameAndScore
		updateData();
	}
	/**
	 * ranking
	 * ranks the score gotten
	 * @param numOfMazes the number of mazes they went through
	 * @return rank the rank of this score
	 */
	public int ranking(int numOfMazes) {
		int rank=0; //initializes rank
		for(int i=0; i<memory.leaderboardInfo.size(); i++) {
			if(memory.leaderboardInfo.size()!=0) {
				if(numOfMazes<(memory.leaderboardInfo.get(i)).getScore()) {
					rank = i+1; //when the while loop stops (the score is higher) the rank would be at its right place
				}
			} else {
				rank = 1; //rank is first place if no other data
			}
		}	
		return rank; //returns the rank
	}
	
	
	/**
	 * storeIn2D
	 * stores the data in a 2D array for the Jtable
	 * @return infoIn2D the 2d array with all the data
	 */
	public String[][] storeIn2D(){
		String [][] infoIn2D = new String[memory.leaderboardInfo.size()][3]; //creates a 2d array
		for(int i=0; i<memory.leaderboardInfo.size(); i++) { //for the size number of people
			infoIn2D[i][0]= Integer.toString(i+1); //sets the rank
			infoIn2D[i][1]= memory.leaderboardInfo.get(i).getName(); //sets the name
			infoIn2D[i][2]= Integer.toString(memory.leaderboardInfo.get(i).getScore()); //sets the score
		}
		return infoIn2D; //returns the 2d array
	}
}
