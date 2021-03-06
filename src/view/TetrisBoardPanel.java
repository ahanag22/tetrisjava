package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;



/**
 * A class builds a Tetris board that extends JPanel.
 * @author Ahana Ghosh
 * @version Autumn 2015
 */
public class TetrisBoardPanel extends JPanel {

    /**  A generated serial version UID for object Serialization. */
    private static final long serialVersionUID = 6894555580478346220L;
    /** cell width. */
    private static final int CELL_WIDTH = 20;
    /** number of buffer rows. */
    private static final int BUFFER_ROWS = 4;   
    /** the current String. */
    private String myCurrent2DString;
       /** number of rows. */
    private final int myRowCount;
    /** number of columns. */
    private final int myColumnCount;
    
    /**
     * a constructor.
     * @param theColumnCount The Columns Count.
     * @param theRowCount The Row Count.
     */
    public TetrisBoardPanel(final int theColumnCount, final int theRowCount) {
        super();
        
        // Height is bufferRows more than board height
        final int newRowCount = theRowCount + BUFFER_ROWS;
        final int width = theColumnCount * CELL_WIDTH;
        final int height = newRowCount * CELL_WIDTH;
        
        myRowCount = newRowCount;
        myColumnCount = theColumnCount;
        
        this.setPreferredSize(new Dimension(width, height));
        
        final Border emptyBorder = BorderFactory.createEmptyBorder();
        this.setBorder(BorderFactory.createTitledBorder(emptyBorder, "GameBoard"));
        this.setBackground(Color.CYAN);
    }
    
    /**
     * an overridden method used to paint shapes.
     * @param theGraphics is a Graphics. 
     */
    @Override
    public void paintComponent(final Graphics theGraphics) {
        super.paintComponent(theGraphics);
     // for better graphics display
        //((Graphics2D) theGraphics).setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            // RenderingHints.VALUE_ANTIALIAS_ON);
        if (myCurrent2DString == null) {
            return;
        }           
        
        final String[] rows = myCurrent2DString.split("\n");
        
        int currentRowInBoard = 0;
        
        final int xWidthToBeUsed = determineXWidth();
        final int yWidthToBeUsed = determineYWidth();
        final int sqaureArmLen = Math.min(xWidthToBeUsed, yWidthToBeUsed);
        
        // Draw The piece above the real board
        while (currentRowInBoard < BUFFER_ROWS) {
            // Draw the row
            final String rowAsString = rows[currentRowInBoard]; 
            
            // Row size is 11.
            for (int i = 1; i < rowAsString.length(); i++) {
                if (rowAsString.charAt(i) == '*') {
                    // Draw A Blue Rectangle
                    final int startx = (i - 1) * sqaureArmLen;
                    final int starty = currentRowInBoard * sqaureArmLen;
                    
                    drawRectangle(
                              startx,
                              starty,
                              sqaureArmLen,
                              sqaureArmLen,
                              Color.BLUE,
                              true,
                              theGraphics);
                }
            }
            
            currentRowInBoard++;
        }
        
        // Draw the actual board. Grid Like.
        while (currentRowInBoard < this.myRowCount) {
            // Draw the row
            final String rowAsString = rows[currentRowInBoard];
            
            // Row size is 12.
            for (int i = 1; i < rowAsString.length() - 1; i++) {
                if (rowAsString.charAt(i) == '*') {
                    // Draw A Blue Rectangle
                    final int startx = (i - 1) * sqaureArmLen;
                    final int starty = currentRowInBoard * sqaureArmLen;
                    
                    drawRectangle(
                          startx,
                          starty,
                          sqaureArmLen,
                          sqaureArmLen, 
                          Color.BLUE,
                          true,
                          theGraphics);
                } else if (rowAsString.charAt(i) == 'X') {
                    // Draw A RED Rectangle
                    final int startx = (i - 1) * sqaureArmLen;
                    final int starty = currentRowInBoard * sqaureArmLen;
                    
                    drawRectangle(
                          startx,
                          starty,
                          sqaureArmLen,
                          sqaureArmLen,
                          Color.RED, true, theGraphics);
                } else {
                    // Draw an empty Rectangle
                    final int startx = (i - 1) * sqaureArmLen;
                    final int starty = currentRowInBoard * sqaureArmLen;
                    
                    drawRectangle(
                          startx,
                          starty,
                          sqaureArmLen,
                          sqaureArmLen, 
                          Color.RED, false, theGraphics);  
                }                
            }   
            
            currentRowInBoard++;
        }                       
    }
    
    /**
     * Determines the width of the cell.
     * @return the width of the cell.
     */
    private int determineXWidth() {
        final int width = this.getWidth();
       
        return width / myColumnCount;
    }
    
    /**
     * Determine the height of the cell.
     * @return the cell height.
     */
    private int determineYWidth() {
        final int height = this.getHeight();
        
        return height / myRowCount;
    }
    
    /**
     * Draws a rectangle.
     * @param theStartX x co-ordinate.
     * @param theStartY y co-ordinate.
     * @param theWidth The Width.
     * @param theHeight The Height.
     * @param theFillColor The fill color.
     * @param theFill the File flag.
     * @param theGraphics The graphics engine.
     */
    private void drawRectangle(
               final int theStartX,
               final int theStartY,
               final int theWidth,
               final int theHeight,
               final Color theFillColor,
               final Boolean theFill,
               final Graphics theGraphics) {
        
        if (theFill) {
            theGraphics.setColor(theFillColor);
            theGraphics.fillOval(theStartX, theStartY, theWidth, theHeight);
        } 

        theGraphics.setColor(Color.WHITE);
        ((Graphics2D) theGraphics).setStroke(new BasicStroke(1));
        ((Graphics2D) theGraphics).
        draw(new Ellipse2D.Double(theStartX, theStartY, theWidth, theHeight));              
    }
    
    /**
     * Sets the current board string.
     * @param theString the current board string.
     */
    public void setCurerntString(final String theString) {
        this.myCurrent2DString = theString;
    }          
}
