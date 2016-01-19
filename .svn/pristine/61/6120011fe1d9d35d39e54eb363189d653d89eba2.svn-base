package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import java.awt.geom.Ellipse2D;


import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
* A class that shows the next piece. 
* @author Ahana Ghosh
* @version Autumn 2015
*/
public class TetrisNextPiecePanel extends JPanel {
    /**  A generated serial version UID for object Serialization. */
    private static final long serialVersionUID = 5892372259413496721L;
    /** cell width. */
    private static final int CELL_WIDTH = 20;
    /** x axis movement. */
    private static final int X_AXIS_MOVEMENT = 3 * 20;
    /** y axis movement. */
    private static final int Y_AXIS_MOVEMENT = 20;
    /** number of rows. */
    private static final int MAX_ROWS_IN_NEXT_PIECE = 4;
    /** next piece width. */
    private final int myNextPieceAreaWidth = 4 * CELL_WIDTH;
    /** next piece height. */
    private final int myNextPieceAreadHeight = 4 * CELL_WIDTH;
    /** current piece String. */
    private String myCurrentPieceString;
    /**
     * creating a constructor.
     */
    public TetrisNextPiecePanel() {
        super();
             
        this.setPreferredSize(new Dimension(myNextPieceAreaWidth, myNextPieceAreadHeight));
        this.setBorder(BorderFactory.createTitledBorder("NextPiece"));
        this.setBackground(Color.LIGHT_GRAY);
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
                            //RenderingHints.VALUE_ANTIALIAS_ON);
        if (myCurrentPieceString == null) {
            return;
        }
        
        // Draw the Next Piece.        
        final String[] rows = myCurrentPieceString.split("\n");
        
        int currentRowInNextPiece = 0;
        
       
        
        while (currentRowInNextPiece < MAX_ROWS_IN_NEXT_PIECE) {
            // Draw the row
            final String rowAsString = rows
                            [currentRowInNextPiece];                                     
            
            for (int i = 0; i < rowAsString.length(); i = i + 2) {
                if (rowAsString.charAt(i) == '[') {
                    // Draw A Blue Rectangle
                    final int startx = ((i / 2) * CELL_WIDTH) + X_AXIS_MOVEMENT;
                    final int starty = (currentRowInNextPiece * CELL_WIDTH) 
                                    + Y_AXIS_MOVEMENT;                                       
                    
                    drawRectangle(startx, starty, Color.BLUE, true, theGraphics);
                }
            }
            
            currentRowInNextPiece++;
        }        
    }
    
   
    
    /**
     * method that draws a rectangle.
     * @param theStartX is int
     * @param theStartY is int
     * @param theFillColor is Color
     * @param theFill is Boolean
     * @param theGraphics is Graphics
     */
    private void drawRectangle(
               final int theStartX,
               final int theStartY,
               final Color theFillColor,
               final Boolean theFill,
               final Graphics theGraphics) {                
        if (theFill) {
            theGraphics.setColor(theFillColor);
            theGraphics.fillOval(theStartX, theStartY, CELL_WIDTH, CELL_WIDTH);
        }        
        
        theGraphics.setColor(Color.GRAY);
        ((Graphics2D) theGraphics).setStroke(new BasicStroke(1));
        ((Graphics2D) theGraphics).draw(new Ellipse2D.
                                        Double(theStartX, theStartY, CELL_WIDTH, CELL_WIDTH));
    }
    /**
     * set the next piece String.
     * @param theS is a String.
     */
    public void setNextPieceString(final String theS) {
        this.myCurrentPieceString = theS;
    }    
}
