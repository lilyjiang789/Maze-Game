/**
 * MainClass
 * draws the welcome page and the components of the welcome page in the program
 * @author lily
 * @date Jan 23rd 2022
 */
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class MainClass extends JPanel implements ActionListener {
	
	public static void main(String[]args) {
		MainClass mainClass = new MainClass(); //creates an instance of the main class (because drawWelcomeFrame is not static)
		mainClass.drawWelcomeFrame(); //draws the welcome frame
	}
	
	private GUIMemory memory = new GUIMemory();
	
	/**
	 * drawWelcomeFrame
	 * draws and creates the welcome frame when the program is opened
	 */
	public void drawWelcomeFrame() {
		//creates the main frame
		memory.gamePage = new JFrame("Maze Game");
		memory.gamePage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //exits the program when closed
		memory.gamePage.setLayout(new FlowLayout()); //sets a flow layout for the welcome frame
		memory.gamePage.setVisible(true); //makes the frame visible
		memory.gamePage.setExtendedState(JFrame.MAXIMIZED_BOTH); //maximizes the game welcome frame
		
		//creates the welcome panel
		memory.welcome = new JPanel(new GridLayout(5, 1)); //sets the layout of the JPanel
		memory.welcomeLine = new JLabel("Welcome to the Maze Game"); //initializes the welcome line
		
		memory.startButton = new JButton("Start"); //creates the buttons
		memory.startButton.setActionCommand("game board"); //sets the action command "rules page"
		memory.startButton.addActionListener(this); //listens for the button 
		
		memory.rulesButton = new JButton("Rules");
		memory.rulesButton.setActionCommand("rules page"); //sets the action command "rules page"
		memory.rulesButton.addActionListener(this); //listens for the button 
		
		memory.personalizationButton = new JButton("Personalization");
		memory.personalizationButton.setActionCommand("personalization page"); //sets the action command "personalization page"
		memory.personalizationButton.addActionListener(this); //listens for the button 
		
		memory.leaderboardButton = new JButton("Leaderboard");
		memory.leaderboardButton.setActionCommand("leaderboard page"); //sets the action command "leaderboard page"
		memory.leaderboardButton.addActionListener(this); //listens for the button 
		
		memory.gamePage.add(memory.welcome,BorderLayout.CENTER); //places the panel in the middle
		
		//adds the buttons to different pages
		memory.welcome.add(memory.welcomeLine);
		memory.welcome.add(memory.startButton);
		memory.welcome.add(memory.rulesButton);
		memory.welcome.add(memory.personalizationButton);
		memory.welcome.add(memory.leaderboardButton);
		
		//creates the back button for future references
		memory.backButton = new Object[]{"back"};
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if("rules page".equals(e.getActionCommand())) { //if the rules page button was chosen
			System.out.println("rules page opened");
			rulesDialogue(); //opens the rules dialog box
		} else if("personalization page".equals(e.getActionCommand())) { //if personalization page is opened
			System.out.println("personalization page opened"); 
			personalizationDialogue(); //draws the personalization dialog
		} else if("game board".equals(e.getActionCommand())){ //if the start button is pressed
			System.out.println("game board opened");
			createGameBoard(); //creates the game board/frame
		} else if ("leaderboard page".equals(e.getActionCommand())){
			System.out.println("leaderboard open");
			createLeaderboard();
		}
		
		if("circleChosen".equals(e.getActionCommand())) { //if the radio button for circle is chosen
			memory.character = new Circle(); //the new character would be a circle
			memory.circle.setVisible(true); //show the button as chosen
			((Circle) memory.character).drawShape(); //draws the shape
		} else if("squareChosen".equals(e.getActionCommand())) { //if the radio button for a square is chosen
			memory.character = new Square(); //the character would be a square
			memory.square.setVisible(true); //show the button as chosen
			((Square) memory.character).drawShape(); //set the shape to draw as a square
		} 

		if(("choose color".equals(e.getActionCommand()))&&(memory.character != null)) { //if color choose is pressed and there is a character
			memory.initialColor = Color.GRAY;
			Color color = JColorChooser.showDialog(this, "Select a color" , memory.initialColor); //set the color to the chosen color
			memory.character.setNewColor(color); //set the color of the character to the chosen color
		}else if (("choose color".equals(e.getActionCommand()))&&(memory.character == null)){ //if no character was chosen
			noCharacter(); //create error message
		}
	}

	
	/**
	 * ruleDialogue
	 * creates the rules dialogue box
	 */
	public void rulesDialogue() {
		String rules = "RULES \n"
				+ "1. Walk your character through the maze using your mouse without touching the walls of the maze and letting go of your mouse button\n"
				+ "2. Once you touch the wall of the maze you will lose the game and need to start again\n"
				+ "3. Once you let go of the mouse the game will end\n"
				+ "4. When the level has ended you can save your scores by typing in a name\n"
				+ "5. You are able to choose the colour of your character by pressing the personalization button\n"; //sets the rules
		memory.rules= new JOptionPane(); //creates an option pane
		
		JOptionPane.showOptionDialog(memory.gamePage, rules, "Rules", JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,
				null, memory.backButton, memory.backButton[0]); //writes the rules and creates the buttons for the option pane
	}
	/**
	 * perosnalizationDialogue
	 * creates the personalization dialog
	 */
	public void personalizationDialogue() {
		//creates the JPanel for personalization
		memory.personalization = new JPanel();
		
		//setting up layout
		memory.personalization.setLayout(new GridLayout(2,1, 50, 50)); //laying out the components
		
		memory.personalization.add(new JLabel("PERSONALIZATION ")); //adds the title 
		memory.colorString = new JLabel("Choose a character colour"); //initializes color string

		memory.chooseColour = new JButton("Choose Color");
		memory.chooseColour.addActionListener(this);
		memory.chooseColour.setActionCommand("choose color");
		memory.initialColor= Color.GRAY; //sets the default color
		memory.personalization.add(memory.colorString);
		memory.personalization.add(memory.chooseColour); //adds the JColorChooser to the panel
		memory.characterString = new JLabel("Choose a character");
		memory.personalization.add(memory.characterString);
		
		//creates the radio buttons
		memory.circle= new JRadioButton("Circle");
		memory.circle.setActionCommand("circleChosen"); //sets the action command

		memory.square = new JRadioButton("Square");
	    memory.square.setActionCommand("squareChosen");//sets the action command
	    
	    //adds the buttons to one group
	    memory.characters = new ButtonGroup();
	    memory.characters.add(memory.circle);
	    memory.characters.add(memory.square);
	    
	    //adds the action listener
	    memory.circle.addActionListener(this);
	    memory.square.addActionListener(this);
	    
	    //adds the buttons to the jpanel
	    memory.personalization.add(memory.circle);
	    memory.personalization.add(memory.square);
	    
		memory.personalizationDialog = new JDialog(memory.gamePage, "Personalization"); //creates the dialog
		memory.personalizationDialog.add(memory.personalization); //adds the jpanel
		memory.personalizationDialog.pack();
		memory.personalizationDialog.setLocationRelativeTo(memory.gamePage);
		memory.personalizationDialog.setVisible(true); //makes the dialog visible
	}
	/**
	 * noCharacter
	 * creates an error message when a character was not chosen before a color was chosen
	 */
	public void noCharacter() {
		JOptionPane.showMessageDialog(memory.gameFrame,"Character was not chosen \n Please go choose a character before colour", "Error", JOptionPane.ERROR_MESSAGE);
	}
	
	/**
	 * createGameBoard
	 * creates the game board
	 */
	public void createGameBoard() {
		GameBoard gameBoard = new GameBoard(); //creates an instance of the game board
		//creates the frame
		memory.gameFrame = new JFrame();
		memory.gameFrame.setSize(820, 540);
		memory.gameFrame.setVisible(true);
		memory.gameFrame.add(gameBoard);
		memory.gameFrame.setLocationRelativeTo(null);
		gameBoard.saveGameBoard(); //calls the method in game board to create the rectangle
	}
	
	/**
	 * createLeaderboard
	 * draws the leaderboard dialog with the leaderboard
	 */
	public void createLeaderboard() {
		Leaderboard leaderboard = null;
		try {
			leaderboard = new Leaderboard(); //creates an instance of the leaderboard class
		} catch (IOException e) {
			e.printStackTrace();
		} 
		memory.leaderboard = new JPanel(); //creates a new jpanel
		String[] columnNames = {"Rank", "Name", "Score"}; //creates the columns of the table
		memory.leaderboardDialog = new JDialog(memory.gamePage, "Leaderboard");
		if(memory.leaderboardInfo != null) { //if there is info in the data array
			memory.leaderboardTable = new JTable(leaderboard.storeIn2D(), columnNames); //Initializes the table
			memory.leaderboard.add(new JLabel("LEADERBOARD")); //adding a jlabel to the jpanel
			memory.leaderboard.add(new JScrollPane(memory.leaderboardTable)); //adds the table in a scrollpane to the jpanel
			memory.leaderboardDialog.add(memory.leaderboard); //adds the jpanel
			memory.leaderboardDialog.pack();
			memory.leaderboardDialog.setLocationRelativeTo(memory.gamePage); //adds the jpanel to the gamepage
			memory.leaderboardDialog.setVisible(true); //shows the jpanel
		} else {
			JOptionPane.showMessageDialog(memory.gamePage,"There is no data \n Please play the game", "Error", JOptionPane.ERROR_MESSAGE); //if the data array is null displays an error message
		}
	}
	
}
