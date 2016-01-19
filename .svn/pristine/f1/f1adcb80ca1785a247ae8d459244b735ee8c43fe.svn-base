package main;

import java.awt.EventQueue;
import view.TetrisGUI;
/**
 * A class does have main method.
 * @author Ahana Ghosh
 * @version Autumn 2015
 */
public final class TetrisMain {

    /**
     * Private constructor, to prevent instantiation of this class.
     */
    private TetrisMain() {
        throw new IllegalStateException();
    }

    /**
     * The main method, invokes the Tetris GUI. Command line arguments are
     * ignored.
     * 
     * @param theArgs Command line arguments.
     */
    public static void main(final String[] theArgs) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TetrisGUI().startGame();
            }
        });
    }
}
