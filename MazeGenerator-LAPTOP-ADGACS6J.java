import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList; 

public class MazeGenerator extends JPanel {
	
	private static GridOfGameBoard[] rectangles = new GridOfGameBoard[160];
	private GUIMemory memory = new GUIMemory();
    GraphicsPanel gamePanel;
    private ArrayList<Integer> mazePath = new ArrayList<Integer>(); 
	
	public MazeGenerator(){
		memory.gameFrame = new JFrame("This is a frame with canvas for drawing.");
		gamePanel = new GraphicsPanel();  //this is a custom panel to draw graphics        
 
	}
	
	public void main(String[]args) {
		createMaze();
	}
	
	public static void saveGameBoard() {
		int k=0;
		for(int i=0; i< 8; i++) {
			for (int j=0; j<20; j++) {	
				rectangles[k]=new GridOfGameBoard(i*100,j*25);
				k++;
			}
		}
		for (int l=0; l<160; l++) {
			System.out.println(rectangles[l].getX() + " and " + rectangles[l].getY());
		}
	}
	/**
     * An internal class that contains all the graphics code. It can be used as any other JPanel. 
     * However, to draw in the panel it has to be customized - a paint method is added. 
     */
    public class GraphicsPanel extends JPanel {
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			for(int i=0; i<160; i++) {                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    
				//g.drawRect(rectangles[i].getX(),rectangles[i].getY(),100,25);
				g.setColor(memory.initialColor);
	            g.fillRect(rectangles[i].getX(),rectangles[i].getY(),100,25);
			}
		}
    }
	
	public void createGameFrame() {
		//creates the main frame
		memory.gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		memory.gameFrame.setSize(800, 550);
		//frame.setLayout(new FlowLayout());
		memory.gameFrame.setVisible(true);
		memory.gameFrame.add(gamePanel);
		memory.gameFrame.setLocationRelativeTo(null);
	}
	
	public void createMaze() {
		int randomStartPoint = 1+ (int) (Math.random()*(18));
		mazePath.add(randomStartPoint);
		mazePath.add(randomStartPoint+20);
		int randomNumOfBlocks = 0;
		for (int k=0; k<7; k++) {
		if(randomStartPoint<9) {
			randomNumOfBlocks = 1+ (int) (Math.random()*(19*k-randomStartPoint));
			for(int i=1; i<=randomNumOfBlocks; i++) {
				mazePath.add(randomStartPoint+20+i);
			}	
			} else if (randomStartPoint>=9) {
				randomNumOfBlocks = 1+ (int) (Math.random()*(randomStartPoint-20*k));
				for(int i=1; i<=randomNumOfBlocks; i++) {
					mazePath.add(randomStartPoint+20-i);
				}
			}
			randomStartPoint = randomStartPoint + randomNumOfBlocks+ 20;
		}
		
		System.out.println(mazePath);
		
	}
	
}
