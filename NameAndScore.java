/**
 * NameAndScore
 * an object that saves both the name and the score of a user
 * @author lily
 * @date Jan 23rd 2022
 */
public class NameAndScore {
	
	String name;
	int numOfMazes;
	
	public NameAndScore(String name, int numOfMazes) {
		this.name = name; //sets the name
		this.numOfMazes = numOfMazes; //sets the score
	}
	
	public String getName() {
		return this.name; //returns the name
	}
	public int getScore() {
		return this.numOfMazes; //returns the score
	}
}
