package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;





/**
* A class that shows all the information about the Tetris game. 
* @author Ahana Ghosh
* @version Autumn 2015
*/
public class TetrisInformationPanel extends JPanel {
    /**  A generated serial version UID for object Serialization. */
    private static final long serialVersionUID = 8891249398592291793L;
    
    /** width of a cell. */
    private static final int CELL_WIDTH = 20;
    /** number of rows. */
    private static final int NUM_ROWS = 3;
    /** number of rows in the bottom panel. */
    private static final int NUM_ROWS_PANEL_BOTTOM = 6;
    /** number of Font size. */
    private static final int NUM_FONT_SIZE = 25;
    /** number of Font size. */
    private static final int SCORE_FONT_SIZE = 15;
    /** number of Font size big. */
    private static final int NUM_FONT_SIZE_BIG = 28;
    /** score gap between two levels. */
    private static final int SCORE_GAP = 200;
    /** font style. */
    private static final String FONT_TIMES = "Times";
    /** High score file name. */
    private static final String HIGH_SCORE_FILE = "ahang22-tetris.txt";
    /** User Home directory. */
    private static final String USER_HOME = "user.home";
    /** the width. */
    private final int myWidth;
    /** the height. */
    private final int myHeight;  
    /** a TetrisNextPiecePanel. */
    private TetrisNextPiecePanel myNextPiece;
    /** a String that shows status. */
    private JLabel myStatusString;
    /** a JLabel that shows game score. */
    private JLabel myScoreLabel;
    /** a JLable that shows the high score. */
    private JLabel myHighScoreLabel;
    /** a JLabel that shows number of lines get cleared. */
    private JLabel myLineCleared;
    /** a JLabel that shows current level. */
    private JLabel myCurrentLevel;
    /** a JLabel that shows next level distance. */
    private JLabel myNextLevelDistance;
    /** value contains current score. */
    private int myCurrentScoreInt;
    /** value contains high score. */
    private int myHighScoreInt;
    /** number of Cleared lines. */
    private int myClearedLines;
    /** value contains current level. */
    private int myLevel;
    /** level to score lines balance. */
    private int myLevelBalance;
    /** number of lines need to remove to reach the new level. */
    private int myLinesToGo;
    /**
     * Creating a  Constructor.
     * @param theColumnCount is a number of columns. 
     * @param theRowCount is number of rows. 
     */
    public TetrisInformationPanel(
                  final int theColumnCount,
                  final int theRowCount) {
        super();  
        this.myWidth = theColumnCount * CELL_WIDTH;
        this.myHeight = theRowCount * CELL_WIDTH; 
        this.setUpPanel();   
        this.myCurrentScoreInt = 0; 
        this.myClearedLines = 0;    
        this.myLevel = 1; 
        
        this.loadHighScore();
    }
        
    /** 
     * Loads the high score.
     */
    private void loadHighScore() {
        this.myHighScoreInt = 0;
        
        final String userHomeFolder = System.getProperty(USER_HOME); 
        final Path higheScoreFilePath = Paths.get(userHomeFolder, HIGH_SCORE_FILE);
        
        final File f = new File(higheScoreFilePath.toString());
        if (f.exists() && !f.isDirectory()) { 
            FileReader in = null;
            BufferedReader br = null;
            try {
                in = new FileReader(higheScoreFilePath.toString());
                br = new BufferedReader(in);
                String scoreAsString = br.readLine();
                if (scoreAsString != null) {
                    scoreAsString = scoreAsString.trim();
                    this.myHighScoreInt = Integer.parseInt(scoreAsString);
                }
            } catch (final FileNotFoundException e) {
                emptyFunc();
            } catch (final IOException e) {
                emptyFunc();
            } 
            
            try {
                if (in != null) {
                    in.close();
                }
                if (br != null) {
                    br.close();
                }
            } catch (final IOException e) {   
                emptyFunc();
            }            
        } else {
            try {
                f.createNewFile();
            } catch (final IOException e) {
                return;
            }
        }
    }
    /**
     * Saves the high score.
     */
    private void persistHighScore() {
        final String userHomeFolder = System.getProperty(USER_HOME); 
        final Path higheScoreFilePath = Paths.get(userHomeFolder, HIGH_SCORE_FILE);
        
        final File f = new File(higheScoreFilePath.toString());
        if (f.exists() && !f.isDirectory()) {
            PrintWriter writer = null;
            try {
                writer = new PrintWriter(higheScoreFilePath.toString());
                writer.print("");
                writer.close();
                
            } catch (final IOException e) {
                emptyFunc();
            } 
            
            try {
                writer = new PrintWriter(higheScoreFilePath.toString());
                writer.print(Integer.toString(this.myHighScoreInt));
                writer.close();
                
            } catch (final IOException e) {
                emptyFunc();
            } 
            
            if (writer != null) {
                writer.close();
            } 
        }
    }
    /**
     * Empty function.
     */
    private void emptyFunc() {
        return;
    }
    /**
     * Sets up the appearance.
     */
    private void setUpPanel() {      
        this.setPreferredSize(new Dimension(myWidth, myHeight));        
        // Set this panel as a grid panel with 1 column, 6 rows.        
        this.setLayout(new GridLayout(NUM_ROWS, 1));        
        // Create the next piece panel.
        this.myNextPiece = new TetrisNextPiecePanel();
        this.add(myNextPiece);        
        // Add another panel which will show game instruction.
        final HowToPlayPanel instructionsPanel = new HowToPlayPanel();
        this.add(instructionsPanel);
        // add the last panel which will show the scores, level, game state etc.
        this.setUpLastPanel();
    }
    /**
     * Sets up the appearance of the last panel.
     */
    private void setUpLastPanel() {
     // add one more panel to display the game details.
        final JPanel toAddLabels = new JPanel();
        toAddLabels.setLayout(new GridLayout(NUM_ROWS_PANEL_BOTTOM, 1));
        // Add a JLabel which will show number of lines get cleared.
        myLineCleared = new JLabel();
        myLineCleared.setText("CLEARED LINES : " + Integer.toString(this.myClearedLines));
        myLineCleared.setForeground(Color.GRAY);
        myLineCleared.setFont(new Font(FONT_TIMES, Font.BOLD, SCORE_FONT_SIZE));
        myLineCleared.setBackground(null);
        myLineCleared.setHorizontalAlignment(JLabel.CENTER);
        toAddLabels.add(this.myLineCleared);
        // Add JLabel which will show game score and.
        myScoreLabel = new JLabel();
        myScoreLabel.setText("CURRENT SCORE : " + Integer.toString(this.myCurrentScoreInt));
        myScoreLabel.setForeground(Color.MAGENTA);
        myScoreLabel.setFont(new Font(FONT_TIMES, Font.BOLD, SCORE_FONT_SIZE));
        myScoreLabel.setBackground(null);
        myScoreLabel.setHorizontalAlignment(JLabel.CENTER);
        toAddLabels.add(this.myScoreLabel);
        myHighScoreLabel = new JLabel();
        myHighScoreLabel.setText("HIGH SCORE : " + Integer.toString(this.myHighScoreInt));
        myHighScoreLabel.setForeground(Color.MAGENTA);
        myHighScoreLabel.setFont(new Font(FONT_TIMES, Font.BOLD, SCORE_FONT_SIZE));
        myHighScoreLabel.setBackground(null);
        myHighScoreLabel.setHorizontalAlignment(JLabel.CENTER);
        toAddLabels.add(this.myHighScoreLabel);
         // Add a JLabel which will show the current level.
        myCurrentLevel = new JLabel();
        myCurrentLevel.setText("");
        myLevelBalance = 0;
        myCurrentLevel.setForeground(Color.MAGENTA);
        myCurrentLevel.setFont(new Font(FONT_TIMES, Font.BOLD, SCORE_FONT_SIZE));
        myCurrentLevel.setBackground(null);
        myCurrentLevel.setHorizontalAlignment(JLabel.CENTER);
        toAddLabels.add(this.myCurrentLevel);
        // Add a JLabel which shows when next level would be reached.
        myNextLevelDistance = new JLabel();
        myNextLevelDistance.setText(Integer.toString(this.myLinesToGo));
        myNextLevelDistance.setForeground(Color.MAGENTA);
        myNextLevelDistance.setFont(new Font(FONT_TIMES, Font.BOLD, SCORE_FONT_SIZE));
        myNextLevelDistance.setBackground(null);
        myNextLevelDistance.setHorizontalAlignment(JLabel.CENTER);
        toAddLabels.add(this.myNextLevelDistance);
         // Add a JLabel which will show some status.
        this.myStatusString = new JLabel("PLAY");
        this.myStatusString.setForeground(Color.GREEN);
        this.myStatusString.setFont(new Font(FONT_TIMES, Font.BOLD, NUM_FONT_SIZE));
        this.myStatusString.setBackground(null);
        this.myStatusString.setHorizontalAlignment(JLabel.CENTER);
        toAddLabels.add(this.myStatusString); 
        this.add(toAddLabels);
    }
    
    /**
     * adds score to my current score.
     * @param theScoreAddition is an int
     */
    public void addToScore(final int theScoreAddition) {
        this.myCurrentScoreInt += theScoreAddition;
        
        if (this.myCurrentScoreInt > this.myHighScoreInt) {
            this.myHighScoreInt = this.myCurrentScoreInt;     
            this.persistHighScore();
        }       
    }
   /**
    * 
    * a method which indicates the current level of the game.
    * @return returns if level is changed.
    */
    public boolean levelJumped() {
        final int currentLevel = this.myLevel;       
        
        this.myLevel = this.myLevelBalance + (this.myCurrentScoreInt / SCORE_GAP) + 1;
        
        return this.myLevel > currentLevel;
    }
    
    /**
     * 
     * @param theClearedLines is number of lines get cleared.
     */
    public void clearedLines(final int theClearedLines) {
        this.myClearedLines += theClearedLines;
        
    }
    /**
     * a method that tells user how far the next level is.
     */
    public void nextLevelTogo() {
        if (this.myClearedLines % 2 == 0) {
            myLinesToGo = 2;
        } else {
            myLinesToGo = 1;
        }
    }
    
    /**
     * an overridden method used to paint shapes.
     * @param theGraphics is a Graphics. 
     */
    @Override
    public void paintComponent(final Graphics theGraphics) {
        super.paintComponent(theGraphics);
        
        this.myLineCleared.setText("Cleared Lines : " + Integer.toString(this.myClearedLines));
        this.myScoreLabel.
        setText("Current Score : " + Integer.toString(this.myCurrentScoreInt));
        this.myHighScoreLabel.
        setText("High Score: " + Integer.toString(this.myHighScoreInt));
        this.myCurrentLevel.
        setText("Current Level :  " + Integer.toString(this.myLevel));
        this.myNextLevelDistance.
        setText("Lines To Go : " + Integer.toString(this.myLinesToGo));
        this.myNextPiece.repaint();
    }       
    
    /**
     * Updates the level.
     * @param theLevel initial level.
     */
    public void updateLevel(final int theLevel) {
        this.myLevel = theLevel;
        this.myLevelBalance = theLevel - 1;
        this.myCurrentLevel.setText("Current Level : " + Integer.toString(this.myLevel));
    }
    
    /**
     * Sets the next piece string.
     * @param theString is the next piece string. 
     */
    public void setNextPieceString(final String theString) {
        this.myNextPiece.setNextPieceString(theString);
    }  
    
    /**
     * Notifies the UI that the Game is over.
     */
    public void notifyGameOver() {
        this.myStatusString.setText("Game Over");
        final LineBorder line = new LineBorder(Color.RED, 2, true);
        this.myStatusString.setBorder(line);
        this.myStatusString.setForeground(Color.RED);
        this.myStatusString.setFont(new Font(FONT_TIMES, Font.BOLD, NUM_FONT_SIZE_BIG));
        
    }
    /**
     * Notifies the UI that the Game is ended.
     */
    public void notifyGameEnded() {
        this.myStatusString.setText("Game Ended");
        final LineBorder line = new LineBorder(Color.RED, 2, true);
        this.myStatusString.setBorder(line);
        this.myStatusString.setForeground(Color.RED);
        this.myStatusString.setFont(new Font(FONT_TIMES, Font.BOLD, NUM_FONT_SIZE_BIG));
        
    }
    /**
     * Notifies the UI that the Game is On.
     */
    public void notifyGameOn() {
        this.myStatusString.setText("Game is On");
        final LineBorder line = new LineBorder(Color.GREEN, 2, true);
        this.myStatusString.setBorder(line);
        this.myStatusString.setForeground(Color.GREEN);
        this.myStatusString.setFont(new Font(FONT_TIMES, Font.BOLD, NUM_FONT_SIZE));        
    }
    
    /**
     * Resets the score to zero.
     */
    public void resetScore() {
        this.myCurrentScoreInt = 0;
        this.myScoreLabel.setText(Integer.toString(this.myCurrentScoreInt));
    }
    /**
     * Resets the cleared lines to zero.
     */
    public void resetClearedLines() {
        this.myClearedLines = 0;
        this.myLineCleared.setText(Integer.toString(this.myClearedLines));
    }
    /**
     * Resets the score to zero.
     * @param theInitialLevel initial Level to start with.
     */
    public void resetLevel(final int theInitialLevel) {
        this.myLevelBalance = theInitialLevel - 1;
        this.myLevel = theInitialLevel;
        this.myCurrentLevel.setText("CURRENT LEVEL : " + Integer.toString(this.myLevel));
    }
   
}
