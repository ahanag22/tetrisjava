/**
 * 
 */
package view;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * A class that says about how to play Tetris.
 * @author Ahana Ghosh
 * @version Autumn 2015
 */
public final class HowToPlayPanel extends JPanel {
    /** number of rows. */
    public static final int NUMBER_OF_ROWS = 9;
    
    /**  A generated serial version UID for object Serialization. */
    private static final long serialVersionUID = -418495455843524804L;
    
    /**
     * creating a constructor.
     */
    public HowToPlayPanel() {
        super();
        setUpPanel();
    }
    /**
     * setting up the panel.
     * it says how to play the game.
     */
    private void setUpPanel() {
        this.setLayout(new GridLayout(NUMBER_OF_ROWS, 1));
        this.setBackground(Color.PINK);
        final JLabel moveRight = new JLabel("Right Arrow = Move Right");
        moveRight.setHorizontalAlignment(JLabel.CENTER);
        final JLabel moveLeft = new JLabel("Left Arrow = Move Left");
        moveLeft.setHorizontalAlignment(JLabel.CENTER);
        final JLabel moveDown = new JLabel("Down Arrow = Move Down");
        moveDown.setHorizontalAlignment(JLabel.CENTER);
        final JLabel hardDrop = new JLabel("END Key = Hard Drop");
        hardDrop.setHorizontalAlignment(JLabel.CENTER);
        final JLabel rotate = new JLabel("Up Arrow = Rotate Current Piece");
        rotate.setHorizontalAlignment(JLabel.CENTER);
        final JLabel pause = new JLabel("Space = Pause");
        pause.setHorizontalAlignment(JLabel.CENTER);
        final JLabel unpause = new JLabel("Enter = Unpause");
        unpause.setHorizontalAlignment(JLabel.CENTER);
        final JLabel newGame = new JLabel("Ctrl + N = New Game");
        newGame.setHorizontalAlignment(JLabel.CENTER);
        final JLabel endGame = new JLabel("Ctrl + E = End Game");
        endGame.setHorizontalAlignment(JLabel.CENTER);
        this.add(moveRight);
        this.add(moveLeft);
        this.add(moveDown);
        this.add(hardDrop);
        this.add(rotate);
        this.add(pause);
        this.add(unpause);
        this.add(newGame);
        this.add(endGame);
        this.setBorder(BorderFactory.createTitledBorder("Game Instuctions."));
    }
}
