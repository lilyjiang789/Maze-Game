/**
 * GUIMemory
 * contains all the variables of the program
 * @author lily
 * @date Jan 23rd 2022
 */
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList; 
import java.util.Scanner; 
import java.io.*;


public class GUIMemory {
	public JFrame gamePage;
	public JFrame gameFrame;
	
	//welcome page components 
	public JPanel welcome;
	public JLabel welcomeLine;
	public JButton rulesButton;
	public JButton personalizationButton;
	public JButton leaderboardButton;
	public JButton startButton;
	
	//rules page components
	public JOptionPane rules;
	
	//personalization page components
	public JPanel personalization;
	public JDialog personalizationDialog;
	public JButton chooseColour;
	public JLabel characterString;
	public JLabel colorString;
	public static Color initialColor;
	public JTabbedPane tabbedPane;
	public JComponent colorPane;
	public JComponent characterPane;
	public JRadioButton circle;
	public JRadioButton square;
	public ButtonGroup characters;
	public static String drawShape= "";
	
	//personalization components
	public static Shape character; 
	
	//leaderboard page components
	public JPanel leaderboard;
	public JDialog leaderboardDialog;
	public JScrollPane scroll; 
	public JTable leaderboardTable;
	public static ArrayList<NameAndScore> leaderboardInfo;
	public File leaderboardtxt; 
	public FileWriter addNewInfo;
	public Scanner readInfo;
	
	public Object[] backButton;
	
	//game over components
	public JPanel gameOver;
	public JDialog gameOverDialog;
	public JTextField name;
	public JButton tryAgain; 
	public JButton returnHome; 
	
	
}
