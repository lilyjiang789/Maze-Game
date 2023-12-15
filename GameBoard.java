/**
 * GameBoard
 * contains all the methods to creating and playing the actual game
 * @author lily
 * @date Jan 23rd 2022
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList; 

public class GameBoard extends JPanel implements MouseMotionListener, MouseListener, ActionListener {
	
	private GridOfGameBoard[] rectangles = new GridOfGameBoard[640];
	private GUIMemory memory = new GUIMemory();
   // GraphicsPanel gamePanel;
    private ArrayList<Integer> mazePath = new ArrayList<Integer>(); 
    public Point shapeCorner=null;
	public Point currentPoint;
	private int numOfMazes;
	private boolean mazeCompleted = false;
	private boolean gameInProgress = true; 
	private String name;
	private Rectangle character;
	private ArrayList<Rectangle> walls; 
	
	public GameBoard(){
		createMaze(); //creates the maze
		//adds the mouse listeners
		this.addMouseMotionListener(this);
		this.addMouseListener(this);
	}
	
	/**
	 * saveGameBoard
	 * creates the grid of the gameboard
	 */
	public void saveGameBoard() {
		int k=0;
		walls = new ArrayList<Rectangle>();
		for(int i=0; i< 32; i++) { //32 columns
			for (int j=0; j<20; j++) {	//20 rows
				rectangles[k]=new GridOfGameBoard(i*25,j*25); //saves the coordinates of the rectangles
				walls.add(new Rectangle(i*25, j*25, 25, 25)); //saves the rectangles themselves (for later creating a barrier)
				k++; //increases the k
			}
		}
	}
	
	/**
	 * paintComponent 
	 * draws the graphics 
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		//draws the grid of the gameboard
		for(int i=0; i<640; i++) {                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    
			g.setColor(memory.initialColor); //sets the color of the rectangle
	        g.fillRect(rectangles[i].getX(),rectangles[i].getY(),25,25); //draws the individual rectangles
		}
		//draws the path of the maze
		for(int i=0; i<mazePath.size(); i++) {
			g.setColor(Color.LIGHT_GRAY);
			int indexNum = mazePath.get(i); //gets the index number of the path
           	g.fillRect(rectangles[indexNum].getX(), rectangles[indexNum].getY(),25,25); //sets it a different color
           	walls.set(indexNum, null); //does not make it a barrier
           }
		//creates the start point of the character
		if(mazePath.size()!=0) {
			int yOfShapeCorner = (mazePath.get(0)*25+7); //finds the y coordinate according to the first index of the path
			if((shapeCorner==null)&& (!memory.drawShape.equals("triangle"))) { //if shapeCorner is not changed by the listeners
				shapeCorner = new Point(9,yOfShapeCorner); //sets the starting points
			}
		}
		//if the coordinates of the shape exist
		if(shapeCorner != null) {
			if(memory.drawShape.equals("circle")) { //if the character is a circle
				character = new Rectangle ((int)shapeCorner.getX(), (int)shapeCorner.getY(), 10, 10 ); //creates rectangle over the character
				g.setColor(memory.character.getColor()); //sets the color into the color chosen by the user
				g.fillOval ((int)shapeCorner.getX(), (int)shapeCorner.getY(), 10, 10 ); //draws the character
			} else if (memory.drawShape.equals("square")) { //if the character is a square
				character = new Rectangle ((int)shapeCorner.getX(), (int)shapeCorner.getY(), 10, 10 ); //creates rectangle over the character for collision detection
				g.setColor(memory.character.getColor()); //sets the color into the color chosen by the user
				g.fillRect((int)shapeCorner.getX(), (int)shapeCorner.getY(), 10, 10 ); //draws the character
			} else if (memory.drawShape.equals("")) {
				JOptionPane.showMessageDialog(memory.gameFrame,"Character was not chosen \n Please go to personalization to choose a character", "Error", JOptionPane.ERROR_MESSAGE); //if no shape then outputs a mistake dialog
			}
		}
	}
	/**
	 * createMaze
	 * generates the mazePath
	 */
	public void createMaze() {
		int randomStartPoint = 1+ (int) (Math.random()*(19)); //chooses the start point of the maze
		mazePath.add(randomStartPoint); //adds it to the arraylist
		for(int i=0; i<30; i+=2) { //Repeats this the amount of columns -first and last columns
			if(randomStartPoint<=(9+(i*20))) { 
				int numOfBlocks = 1+ (int) (Math.random()*(19+(20*i)-randomStartPoint)); //generates a random number for the paths that can be together
				for(int j=0; j<numOfBlocks; j++) {
					mazePath.add(randomStartPoint+20+j); //if the point is less than 9 it goes down
				}
				randomStartPoint = randomStartPoint + 20 +numOfBlocks-1; //sets a new randomStartPoint as the ending point of the previous column
			}else if (randomStartPoint>(9+20*i)) {
				int numOfBlocks = 1+ (int) (Math.random()*(randomStartPoint-(20*i))); //generates a random number for the paths that can be together
				for(int j=0; j<numOfBlocks; j++) {
					mazePath.add(randomStartPoint+20-j); //if the point is greater than 9 it goes up 
				}
				randomStartPoint = randomStartPoint + 20 -numOfBlocks+1; //sets a new randomStartPoint as the ending point of the previous column
			}
			mazePath.add(randomStartPoint+20); //adds one block between filled columns 
			randomStartPoint = randomStartPoint+20; //sets the randomStartPoint as the path that was just added
		}
		mazePath.add(randomStartPoint+20); //adds the last block for the last column
	}
	
	/**
	 * gameOver
	 * the game over dialog when the level was not passed
	 */
	public void gameOver() {
		memory.gameOver = new JPanel(); //initializes the panel
		memory.gameOver.setLayout(new GridLayout(2, 3, 50, 50)); //layouts the panel
		//adds the needed labels to the panel
		memory.gameOver.add(new Label("You have completed " + numOfMazes + " mazes")); 
		memory.gameOver.add(new Label("Please insert your name"));
		//creates and adds the Jtextfield
		memory.name = new JTextField(16);
		memory.name.addActionListener(this);
		memory.name.setActionCommand("name typed");
		memory.gameOver.add(memory.name);
		
		//creates and adds the buttons (try again and back)
		memory.tryAgain = new JButton("Try Again");
		memory.tryAgain.addActionListener(this);
		memory.tryAgain.setActionCommand("tryAgain");
		memory.returnHome = new JButton("Back");
		memory.returnHome.addActionListener(this);
		memory.returnHome.setActionCommand("goHome");
		//adds the buttons
		memory.gameOver.add(memory.tryAgain);
		memory.gameOver.add(memory.returnHome);
		
		//creates the dialog and adds the jpanel
		memory.gameOverDialog = new JDialog(memory.gamePage, "GAME OVER");
		memory.gameOverDialog.add(memory.gameOver);
		memory.gameOverDialog.pack();
		memory.gameOverDialog.setLocationRelativeTo(memory.gamePage); //creates it in the gamePage
		memory.gameOverDialog.setVisible(true); //makes the dialog visible
		
	}
	
	/**
	 * continueMaze
	 * checks if the character has bumped into the wall or not
	 * @param currentPoint the point the character is dragged to
	 */
	public void continueMaze(Point currentPoint) {
		character.setLocation((int)shapeCorner.getX(), (int)shapeCorner.getY()); //sets the location of the character
		for(int i=0; i<walls.size(); i++) { //goes through every wall
			if(walls.get(i)!=null) { //if the wall is not null (not a path)
				if (character.intersects(walls.get(i))){ 
					//if the character and the walls intersect the game will end
					gameInProgress = false; 
					gameOver(); //draws the game over panel
					mazePath.clear(); //clears the path array for new maze
					repaint(); //repaints the board
				}
			}
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		if("name typed".equals(e.getActionCommand())) { 
			//stores the typed info if anything was typed
			name = memory.name.getText();
			System.out.println(name);
			Leaderboard leaderboard = null; //initializes leaderboard
			try {
				leaderboard = new Leaderboard();
			} catch (IOException e2) {
				e2.printStackTrace();
			}
			try {
				leaderboard.saveScore(name, numOfMazes);
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			numOfMazes = 0; //resets the score after the name and score was saved
		}
		if("tryAgain".equals(e.getActionCommand())) {
			mazePath.clear(); //clears the array of maze path
			createMaze(); //creates a new path
			shapeCorner = null; //sets the shapecorner to null to be reset 
			repaint(); 
			memory.gameOverDialog.setVisible(false); //does not display the dialog
			memory.gameOverDialog.dispose(); //closes the dialog
		} else if("goHome".equals(e.getActionCommand())) {
			//closes the game frame and the dialog
			memory.gameOverDialog.setVisible(false);
			memory.gameOverDialog.dispose();
		}
	}
	
	public void mouseDragged(MouseEvent e) {
		if(gameInProgress = true) {
			//if game is in progress then the character will move with
			currentPoint = e.getPoint();
			shapeCorner = currentPoint;
			continueMaze(currentPoint); //checks if there is collision
			repaint(); //repaints the board for the character to move
		}
		
		if(e.getX()>775) { 
			mazeCompleted = true; //if the character is close to the end then the maze is completed
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(mazeCompleted == false) {
			//if mouse is released and game is not completed it resets everything and invokes the gameover method
			gameOver();
			gameInProgress = false;
			shapeCorner = null;
			mazePath.clear();
			createMaze();
			repaint();
		} else if (mazeCompleted == true) { //when the maze is complete
			//resets the character coordinates clears the maze and creates a new maze
			shapeCorner = null;
			mazePath.clear();
			createMaze();
			repaint();
			numOfMazes= numOfMazes+1; //adds one to the number of mazes completed
			mazeCompleted = false; //sets the maze completed to false because new maze is generated
		}
	
	}

	@Override
	public void mouseEntered(MouseEvent e) {		
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}	
}
