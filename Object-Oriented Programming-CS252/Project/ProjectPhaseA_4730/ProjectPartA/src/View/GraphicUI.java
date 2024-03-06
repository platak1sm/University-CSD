
package View;

import View.PaydayCards.src.paydaycards.PayDayCards;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.util.Stack;
import java.util.LinkedList;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * GraphicUI class that extends JFrame
 *
 * @author plata
 */
public class GraphicUI extends JFrame {

    private Image PayDayImage;
    private Image background;
    private Image dice1;
    private Image dice2;
    private Image paw1;
    private Image paw2;
    private JButton RollDice1, MyDealCards1, GetLoan1, EndTurn1;
    private JButton RollDice2, MyDealCards2, GetLoan2, EndTurn2;
    private myDesktopPane basic_panel;
    private JLayeredPane player2_field;
    private JLayeredPane player1_field;
    private GridLayout board;
    private PayDayCards cards;
    private JTextField info_box;
    private Jackpot jackpot;

    /**
     * <b>constructor</b>: Creates a new Window and initializes some buttons and panels <br />
     * <b>postconditions</b>: Creates a new Window and initializes some buttons and panels
     * starting a new game.
     */
    public GraphicUI() {

    }

    /**
     * Method that initializes the info box
     */
    public void init_infobox() {

    }

    /**
     * Method that refreshes the info box
     */
    public void refresh_infobox() {

    }

    /**
     * Method that initializes the board
     */
    public void init_board() {

    }

    /**
     * Method that refreshes the board
     */
    public void refresh_board() {

    }

    /**
     * Method that initializes the players' field
     */
    public void init_playersFields() {

    }

    /**
     * Method that refreshes player1's field
     */
    public void refresh_player1field() {

    }

    /**
     * Method that refreshes player2's field
     */
    public void refresh_player2field() {

    }

    /**
     * <b>transformer(mutative)</b>:initializes some buttons and labels <br />
     * <p><b>Postcondition:</b> initializes some buttons and labels </p>
     */
    public void initComponents() {

    }


    /**
     * a class which is used for putting a background image to a jdesktoppane
     *
     * @author plata
     */
    public class myDesktopPane extends JDesktopPane {
        private Image backImage = null;

        /**
         * <b>constructor</b>: Creates a new myDesktopPane and is used for putting a background image to a jdesktoppane <br />
         * <b>postconditions</b>: Creates a new myDesktopPane and is used for putting a background image to a jdesktoppane
         * starting a new game.
         */
        public myDesktopPane() {

        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(background, 0, 0, this);
        }
    }

    /**
     * a class which is used for doing some action after a card button has been pushed
     *
     * @author plata
     */
    private class CardListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    /**
     * a class which is used for doing some action after dice1 button has been pushed
     *
     * @author plata
     */
    private class Dice1Listener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    /**
     * a class which is used for doing some action after MyDealCards1 button has been pushed
     *
     * @author plata
     */
    private class MyDealCards1Listener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    /**
     * a class which is used for doing some action after GetLoan1 button has been pushed
     *
     * @author plata
     */
    private class GetLoan1Listener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    /**
     * a class which is used for doing some action after EndTurn1 button has been pushed
     *
     * @author plata
     */
    private class EndTurn1Listener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    /**
     * a class which is used for doing some action after dice2 button has been pushed
     *
     * @author plata
     */
    private class Dice2Listener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    /**
     * a class which is used for doing some action after MyDealCards2 button has been pushed
     *
     * @author plata
     */
    private class MyDealCards2Listener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    /**
     * a class which is used for doing some action after GetLoan2 button has been pushed
     *
     * @author plata
     */
    private class GetLoan2Listener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    /**
     * a class which is used for doing some action after EndTurn2 button has been pushed
     *
     * @author plata
     */
    private class EndTurn2Listener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }
}

