
package View;

import Controller.Controller;
import Model.Day;
import Model.MailCardsType;
import Model.Turn;
import View.PaydayCards.src.PayDayCards;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;

/**
 * GraphicUI class that extends JFrame
 *
 * @author plata
 */
public class GraphicUI extends JFrame {
    private Icon im;
    private Icon jp, dealic, mailic;
    private Icon dice;
    private Image PayDayImage;
    private Image background;
    private JButton dice1, dice2;
    private JLabel pawn1b, pawn2b;
    private Icon pawn1;
    private Icon pawn2;
    private JButton RollDice1, MyDealCards1, GetLoan1, EndTurn1;
    private JButton RollDice2, MyDealCards2, GetLoan2, EndTurn2;
    private myDesktopPane basic_panel;
    private JPanel player2_field;
    private JPanel player1_field, pawnpanel;
    private JButton jackPot, dealC, mailC;
    private JLabel jackPotLabel;
    private JPanel jackpotPanel;
    private JPanel boardPanel = new JPanel();
    private JPanel sboardPanel = new JPanel();
    private GridLayout sboard;
    private GridLayout board;
    private GridLayout p2;
    private GridLayout p1;
    private PayDayCards paydaycards;
    private JTextArea info_box;
    private Controller game;
    private URL imageURL;
    private String months, loan, str;
    private ClassLoader cldr;
    public JButton[] barr32 = new JButton[32];
    public ArrayList<JButton> barr = new ArrayList<JButton>();
    public JLabel[] larr = new JLabel[32];
    public JPanel[] parr = new JPanel[32];
    public ArrayList<Integer> b = new ArrayList<Integer>();
    public ArrayList<Integer> d = new ArrayList<Integer>();
    public ArrayList<Integer> db = new ArrayList<Integer>();
    public ArrayList<Integer> m = new ArrayList<Integer>();
    public int mailCardCount = 0, dealCardCount = 0, n, k, f;


    /**
     * <b>constructor</b>: Creates a new Window and initializes some buttons and panels <br />
     * <b>postconditions</b>: Creates a new Window and initializes some buttons and panels
     * starting a new game.
     */
    public GraphicUI() throws Exception {
        game = new Controller();
        do {
            months = JOptionPane.showInputDialog("For how many months do you want to play? ");
        } while (Integer.parseInt(months) != 1 && Integer.parseInt(months) != 2 && Integer.parseInt(months) != 3);
        game.months = Integer.parseInt(months);
        cldr = this.getClass().getClassLoader();
        imageURL = cldr.getResource("images/bg_green.png");
        background = new ImageIcon(imageURL).getImage();
        basic_panel = new myDesktopPane();
        imageURL = cldr.getResource("images/logo.png");
        PayDayImage = new ImageIcon(imageURL).getImage();
        cldr = this.getClass().getClassLoader();
        imageURL = cldr.getResource("images/jackpot.png");
        jp = new ImageIcon(new ImageIcon(imageURL).getImage().getScaledInstance(240, 140, Image.SCALE_SMOOTH));
        imageURL = cldr.getResource("images/dealCard.png");
        dealic = new ImageIcon(new ImageIcon(imageURL).getImage().getScaledInstance(155, 85, Image.SCALE_SMOOTH));
        imageURL = cldr.getResource("images/mailCard.png");
        mailic = new ImageIcon(new ImageIcon(imageURL).getImage().getScaledInstance(155, 85, Image.SCALE_SMOOTH));
        player1_field = new JPanel();
        player2_field = new JPanel();
        jackPotLabel = new JLabel();
        jackPot = new JButton();
        paydaycards = new PayDayCards();
        jackpotPanel = new JPanel();
        this.setTitle("PayDay Game");
        initComponents();
        init_board();
        init_playersFields();
        init_infobox();
        this.boardPanel.setLocation(0, 141);
        this.setSize(1200, 1020);
        Graphics g = basic_panel.getGraphics();
        basic_panel.paintComponents(g);
        basic_panel.repaint();
        this.add(basic_panel);
        repaint();
        setVisible(true);
        setResizable(false);

    }

    /**
     * Method that initializes the info box
     */
    public void init_infobox() throws Exception {
        info_box = new JTextArea();
        info_box.setText("Info Box");
        if (Integer.parseInt(months) == 1) {
            info_box.setText("Info Box\n" + "1 Month Left\n" + "Turn: Player " + String.valueOf(game.seeTurn()) + "\n-->");
        } else {
            info_box.setText("Info Box\n" + String.valueOf(game.months) + " Months Left\n" + "Turn: Player " + String.valueOf(game.seeTurn()) + "\n-->");
        }
        Border blackline = BorderFactory.createLineBorder(Color.BLACK);
        info_box.setBorder(blackline);
        this.add(info_box);
        this.info_box.setLocation(850, 370);
        this.info_box.setSize(300, 150);
    }

    /**
     * Method that refreshes the info box
     */
    public void refresh_infobox() throws Exception {
        if (game.months == 1) {
            info_box.setText("Info Box\n" + "1 Month Left\n" + "Turn: Player " + String.valueOf(game.seeTurn()) + "\n-->");
        } else {
            info_box.setText("Info Box\n" + String.valueOf(game.months) + " Months Left\n" + "Turn: Player " + String.valueOf(game.seeTurn()) + "\n-->");
        }
        Border blackline = BorderFactory.createLineBorder(Color.BLACK);
        info_box.setBorder(blackline);
        this.repaint();
    }

    /**
     * Method that initializes the board
     */
    public void init_board() {
        board = new GridLayout(5, 7);
        boardPanel.setLayout(board);
        boardPanel.setSize(770, 780);
        for (int i = 0; i < 4; i++) {
            imageURL = cldr.getResource("images/mc1.png");
            if (imageURL != null) {
                im = new ImageIcon(new ImageIcon(imageURL).getImage().getScaledInstance(110, 140, Image.SCALE_SMOOTH));
                barr.add(new JButton(im));
            } else {
                System.err.println("Couldn't find file: " + "images/mc1.png");
            }

            imageURL = cldr.getResource("images/mc2.png");
            if (imageURL != null) {
                im = new ImageIcon(new ImageIcon(imageURL).getImage().getScaledInstance(110, 140, Image.SCALE_SMOOTH));
                barr.add(new JButton(im));
            } else {
                System.err.println("Couldn't find file: " + "images/mc2.png");
            }
        }
        for (int i = 8; i < 10; i++) {
            imageURL = cldr.getResource("images/yard.png");
            if (imageURL != null) {
                im = new ImageIcon(new ImageIcon(imageURL).getImage().getScaledInstance(110, 140, Image.SCALE_SMOOTH));
                barr.add(new JButton(im));
            } else {
                System.err.println("Couldn't find file: " + "images.yard/png");
            }

            imageURL = cldr.getResource("images/radio.png");
            if (imageURL != null) {
                im = new ImageIcon(new ImageIcon(imageURL).getImage().getScaledInstance(110, 140, Image.SCALE_SMOOTH));
                barr.add(new JButton(im));
            } else {
                System.err.println("Couldn't find file: " + "images/radio.png");
            }

            imageURL = cldr.getResource("images/casino.png");
            if (imageURL != null) {
                im = new ImageIcon(new ImageIcon(imageURL).getImage().getScaledInstance(110, 140, Image.SCALE_SMOOTH));
                barr.add(new JButton(im));
            } else {
                System.err.println("Couldn't find file: " + "images/casino.png");
            }

            imageURL = cldr.getResource("images/sweep.png");
            if (imageURL != null) {
                im = new ImageIcon(new ImageIcon(imageURL).getImage().getScaledInstance(110, 140, Image.SCALE_SMOOTH));
                barr.add(new JButton(im));
            } else {
                System.err.println("Couldn't find file: " + "images/sweep.png");
            }

        }
        for (int i = 16; i < 22; i++) {
            imageURL = cldr.getResource("images/buyer.png");
            if (imageURL != null) {
                im = new ImageIcon(new ImageIcon(imageURL).getImage().getScaledInstance(110, 140, Image.SCALE_SMOOTH));
                barr.add(new JButton(im));
            } else {
                System.err.println("Couldn't find file: " + "images/buyer.png");
            }

        }
        for (int i = 22; i < 25; i++) {
            imageURL = cldr.getResource("images/lottery.png");
            if (imageURL != null) {
                im = new ImageIcon(new ImageIcon(imageURL).getImage().getScaledInstance(110, 140, Image.SCALE_SMOOTH));
                barr.add(new JButton(im));
            } else {
                System.err.println("Couldn't find file: " + "images/lottery.png");
            }

        }
        for (int i = 25; i < 30; i++) {
            imageURL = cldr.getResource("images/deal.png");
            if (imageURL != null) {
                im = new ImageIcon(new ImageIcon(imageURL).getImage().getScaledInstance(110, 140, Image.SCALE_SMOOTH));
                barr.add(new JButton(im));
            } else {
                System.err.println("Couldn't find file: " + "images/deal.png");
            }

        }

        imageURL = cldr.getResource("images/start.png");
        if (imageURL != null) {
            im = new ImageIcon(new ImageIcon(imageURL).getImage().getScaledInstance(110, 140, Image.SCALE_SMOOTH));
            barr32[0] = new JButton(im);
        } else {
            System.err.println("Couldn't find file: " + "images/start.png");
        }
        imageURL = cldr.getResource("images/pay.png");
        if (imageURL != null) {
            im = new ImageIcon(new ImageIcon(imageURL).getImage().getScaledInstance(110, 140, Image.SCALE_SMOOTH));
            barr32[31] = new JButton(im);
        } else {
            System.err.println("Couldn't find file: " + "images/pay.png");
        }

        for (int i = 1; i < 31; i++) {
            barr32[i] = barr.get(game.intarr.get(i - 1));
        }
        larr[0] = new JLabel("<html><span bgcolor=\"yellow\">Start</span></html>");
        larr[31] = new JLabel("<html><span bgcolor=\"yellow\">Wed. 31</span></html>");
        for (int i = 1; i < 31; i++) {
            larr[i] = new JLabel("<html><span bgcolor=\"yellow\"> " + Day.valueOf(i).name() + ". " + String.valueOf(i) + "</span></html>");
            larr[i].setBackground(Color.YELLOW);
        }
        for (int i = 0; i < 32; i++) {
            parr[i] = new JPanel();
            parr[i].add(larr[i]);
            parr[i].add(barr32[i]);
            boardPanel.add(parr[i]);
        }
        boardPanel.setOpaque(false);
        this.add(boardPanel);
    }

    /**
     * Method that refreshes the board
     */
    public void refresh_board() {

    }


    /**
     * Method that initializes the players' field
     */
    public void init_playersFields() throws Exception {
        imageURL = cldr.getResource("images/dice-1.jpg");

        player1_field.setLayout(null);
        player2_field.setLayout(null);
        dice = new ImageIcon(new ImageIcon(imageURL).getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH));
        dice1 = new JButton(dice);
        dice2 = new JButton(dice);
        dice1.setSize(60, 60);
        dice2.setSize(60, 60);

        RollDice1 = new JButton("Roll Dice");
        RollDice1.addActionListener(new DiceListener());
        MyDealCards1 = new JButton("My Deal Cards");
        MyDealCards1.addActionListener(new MyDealCardsListener());
        GetLoan1 = new JButton("Get Loan");
        GetLoan1.addActionListener(new GetLoanListener());
        EndTurn1 = new JButton("End Turn");
        EndTurn1.addActionListener(new EndTurnListener());

        RollDice2 = new JButton("Roll Dice");
        RollDice2.addActionListener(new DiceListener());
        MyDealCards2 = new JButton("My Deal Cards");
        MyDealCards2.addActionListener(new MyDealCardsListener());
        GetLoan2 = new JButton("Get Loan");
        GetLoan2.addActionListener(new GetLoanListener());
        EndTurn2 = new JButton("End Turn");
        EndTurn2.addActionListener(new EndTurnListener());


        Border blueline = BorderFactory.createTitledBorder("Player 1");
        blueline = BorderFactory.createLineBorder(Color.BLUE);
        Border yellowline = BorderFactory.createTitledBorder("Player 2");
        yellowline = BorderFactory.createLineBorder(Color.YELLOW);
        player1_field.setBorder(blueline);
        player2_field.setBorder(yellowline);

        JLabel label = new JLabel("Player1: ");
        label.setBounds(5, 0, 300, 20);
        player1_field.add(label);

        label = new JLabel("Money: " + game.players.get(0).getMoney() + " Euros ");
        label.setBounds(5, 25, 300, 30);
        player1_field.add(label);

        label = new JLabel("Loan: " + game.players.get(0).getLoan() + " Euros ");
        label.setBounds(5, 50, 300, 30);
        player1_field.add(label);

        label = new JLabel("Bills: " + game.players.get(0).getBills() + " Euros");
        label.setBounds(5, 75, 300, 30);
        player1_field.add(label);

        RollDice1.setBounds(5, 150, 150, 21);
        MyDealCards1.setBounds(5, 190, 150, 21);
        GetLoan1.setBounds(5, 230, 150, 21);
        EndTurn1.setBounds(5, 270, 150, 21);
        dice1.setLocation(230, 200);


        player1_field.add(RollDice1);

        player1_field.add(MyDealCards1);

        player1_field.add(GetLoan1);

        player1_field.add(EndTurn1);

        player1_field.add(dice1);

        label = new JLabel("Player2: ");
        label.setBounds(5, 0, 300, 20);
        player2_field.add(label);

        label = new JLabel("Money: " + game.players.get(1).getMoney() + " Euros ");
        label.setBounds(5, 25, 300, 30);
        player2_field.add(label);

        label = new JLabel("Loan: " + game.players.get(1).getLoan() + " Euros ");
        label.setBounds(5, 50, 300, 30);
        player2_field.add(label);

        label = new JLabel("Bills: " + game.players.get(1).getBills() + " Euros");
        label.setBounds(5, 75, 300, 30);
        player2_field.add(label);

        RollDice2.setBounds(5, 150, 150, 21);
        MyDealCards2.setBounds(5, 190, 150, 21);
        GetLoan2.setBounds(5, 230, 150, 21);
        EndTurn2.setBounds(5, 270, 150, 21);
        dice2.setLocation(230, 200);


        player2_field.add(RollDice2);
        player2_field.add(MyDealCards2);
        player2_field.add(GetLoan2);
        player2_field.add(EndTurn2);
        player2_field.add(dice2);

        this.player1_field.setSize(300, 300);
        this.player2_field.setSize(300, 300);
        this.player1_field.setLocation(850, 10);
        this.player2_field.setLocation(850, 670);
        this.add(player1_field);
        this.add(player2_field);
    }

    /**
     * Method that refreshes player1's field
     */
    public void refresh_player1field(int dicenum) throws Exception {
        player1_field.removeAll();

        dice1.setSize(60, 60);

        Border blueline = BorderFactory.createTitledBorder("Player 1");
        blueline = BorderFactory.createLineBorder(Color.BLUE);
        player1_field.setBorder(blueline);

        imageURL = cldr.getResource("images/dice-" + String.valueOf(game.players.get(0).getDice()) + ".jpg");
        dice = new ImageIcon(new ImageIcon(imageURL).getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH));
        dice1.setIcon(dice);
        player1_field.add(dice1);

        JLabel label = new JLabel("Player1: ");
        label.setBounds(5, 0, 300, 20);
        player1_field.add(label);

        label = new JLabel("Money: " + game.players.get(0).getMoney() + " Euros ");
        label.setBounds(5, 25, 300, 30);
        player1_field.add(label);
        repaint();

        label = new JLabel("Loan: " + game.players.get(0).getLoan() + " Euros ");
        label.setBounds(5, 50, 300, 30);
        player1_field.add(label);
        repaint();

        label = new JLabel("Bills: " + game.players.get(0).getBills() + " Euros");
        label.setBounds(5, 75, 300, 30);
        player1_field.add(label);

        RollDice1.setBounds(5, 150, 150, 21);
        MyDealCards1.setBounds(5, 190, 150, 21);
        GetLoan1.setBounds(5, 230, 150, 21);
        EndTurn1.setBounds(5, 270, 150, 21);
        dice1.setLocation(230, 200);


        player1_field.add(RollDice1);

        player1_field.add(MyDealCards1);

        player1_field.add(GetLoan1);

        player1_field.add(EndTurn1);

        player1_field.add(dice1);
        repaint();
    }

    /**
     * Method that refreshes player2's field
     */
    public void refresh_player2field(int dicenum) throws Exception {
        player2_field.removeAll();

        dice2.setSize(60, 60);

        Border yellowline = BorderFactory.createTitledBorder("Player 2");
        yellowline = BorderFactory.createLineBorder(Color.YELLOW);
        player2_field.setBorder(yellowline);

        imageURL = cldr.getResource("images/dice-" + String.valueOf(game.players.get(1).getDice()) + ".jpg");
        dice = new ImageIcon(new ImageIcon(imageURL).getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH));
        dice2.setIcon(dice);
        player2_field.add(dice2);

        JLabel label = new JLabel("Player2: ");
        label.setBounds(5, 0, 300, 20);
        player2_field.add(label);

        label = new JLabel("Money: " + game.players.get(1).getMoney() + " Euros ");
        label.setBounds(5, 25, 300, 30);
        player2_field.add(label);

        label = new JLabel("Loan: " + game.players.get(1).getLoan() + " Euros ");
        label.setBounds(5, 50, 300, 30);
        player2_field.add(label);

        label = new JLabel("Bills: " + game.players.get(1).getBills() + " Euros");
        label.setBounds(5, 75, 300, 30);
        player2_field.add(label);

        RollDice2.setBounds(5, 150, 150, 21);
        MyDealCards2.setBounds(5, 190, 150, 21);
        GetLoan2.setBounds(5, 230, 150, 21);
        EndTurn2.setBounds(5, 270, 150, 21);
        dice2.setLocation(230, 200);


        player2_field.add(RollDice2);
        player2_field.add(MyDealCards2);
        player2_field.add(GetLoan2);
        player2_field.add(EndTurn2);
        player2_field.add(dice2);
        repaint();
    }

    /**
     * <b>transformer(mutative)</b>:initializes some buttons and labels <br />
     * <p><b>Postcondition:</b> initializes some buttons and labels </p>
     */
    public void initComponents() {
        jackPot = new JButton(jp);
        dealC = new JButton();
        mailC = new JButton();
        jackPotLabel = new JLabel("Jackpot: " + String.valueOf(game.jp.getMoney()) + " Euros");
        jackPot.setOpaque(false);
        jackpotPanel.setOpaque(false);
        jackpotPanel.add(jackPot);
        jackpotPanel.add(jackPotLabel);

        jackPot.setSize(200, 80);
        jackpotPanel.setSize(240, 220);
        jackPotLabel.setSize(240, 35);
        jackPotLabel.setFont(new Font("Serif", Font.BOLD, 30));
        this.jackpotPanel.setLocation(520, 775);
        this.jackPot.setLocation(0, 0);
        this.jackPotLabel.setLocation(0, 100);
        this.jackPot.setLocation(520, 905);
        this.add(jackpotPanel);
        dealC.setSize(140, 80);
        mailC.setSize(140, 80);
        dealC.setIcon(dealic);
        mailC.setIcon(mailic);
        mailC.addActionListener(new CardListener());
        dealC.addActionListener(new CardListener());
        this.mailC.setLocation(850, 550);
        this.dealC.setLocation(1000, 550);
        this.add(mailC);
        this.add(dealC);
        imageURL = cldr.getResource("images/pawn_blue.png");
        pawn1 = new ImageIcon(new ImageIcon(imageURL).getImage().getScaledInstance(70, 100, Image.SCALE_SMOOTH));
        pawn1b = new JLabel(pawn1);
        pawn1b.setOpaque(false);
        imageURL = cldr.getResource("images/pawn_yellow.png");
        pawn2 = new ImageIcon(new ImageIcon(imageURL).getImage().getScaledInstance(70, 100, Image.SCALE_SMOOTH));
        pawn2b = new JLabel(pawn2);
        pawn2b.setOpaque(false);
        pawn1b.setBounds(5, 25, 70, 100);
        pawnpanel = new JPanel();
        pawnpanel.setLayout(null);
        pawnpanel.add(pawn1b);
        pawn2b.setBounds(35, 25, 70, 100);
        pawnpanel.add(pawn2b);
        pawnpanel.setOpaque(false);
        pawnpanel.setLocation(0, 140);
        this.pawnpanel.setSize(770, 1000);
        this.add(pawnpanel);


    }

    public void refresh_jackpot() {
        jackpotPanel.remove(jackPotLabel);
        jackPotLabel = new JLabel("Jackpot: " + String.valueOf(game.jp.getMoney()) + " Euros");
        jackpotPanel.add(jackPotLabel);
        jackPotLabel.setSize(240, 35);
        jackPotLabel.setFont(new Font("Serif", Font.BOLD, 30));
        this.jackPotLabel.setLocation(0, 100);
        repaint();
    }

    /**
     * a class which is used for putting a background image to a jdesktoppane
     *
     * @author plata
     */
    public class myDesktopPane extends JDesktopPane {

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
            g.drawImage(background, 0, 0, this.getWidth(), getHeight(), this);
            g.drawImage(PayDayImage, 0, 0, 770, 140, this);
        }
    }

    public void movepawn(JLabel pawn) throws Exception {
        if (game.seeTurn() == 1) {
            int posit = game.players.get(0).posi;
            Point point = parr[posit].getLocation();
            pawn.setLocation(point);
        } else if (game.seeTurn() == 2) {
            int posit = game.players.get(1).posi;
            Point point = parr[posit].getLocation();
            point.x += 15;
            pawn.setLocation(point);
        }

        repaint();
    }

    public void showMailCard(int i) throws Exception {

        Object[] options = {game.mailcards.MailCardStack.get(i).getChoice()};
        URL imageURL = cldr.getResource("resources/images/" + game.mailcards.MailCardStack.get(i).getImage()); //image
        Image image = new ImageIcon(imageURL).getImage();
        image = image.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH);
        System.out.println("TypeEn: " + game.mailcards.MailCardStack.get(i).getType().name() + "\nMessage:" + game.mailcards.MailCardStack.get(i).getMessage() +
                "\nChoice: " + game.mailcards.MailCardStack.get(i).getChoice() + "\nEuro:" + game.mailcards.MailCardStack.get(i).getPrice());
        JOptionPane p = new JOptionPane();
        int n = p.showOptionDialog(this,
                game.mailcards.MailCardStack.get(i).getMessage(),
                game.mailcards.MailCardStack.get(i).getType().name(),
                JOptionPane.OK_OPTION,
                0,
                new ImageIcon(image),
                options,
                options[0]);

    }

    public void showDealCard(int i) throws Exception {
        i = 0;
        Object[] options = {game.dealcards.DealCardStack.get(i).getChoice1(), game.dealcards.DealCardStack.get(i).getChoice2()};

        URL imageURL = cldr.getResource("resources/images/" + game.dealcards.DealCardStack.get(i).getImage()); //image
        System.out.println("Type: " + game.dealcards.DealCardStack.get(i).getType()
                + "\nMessage: " + game.dealcards.DealCardStack.get(i).getMessage() + "\nCost:" + game.dealcards.DealCardStack.get(i).getBuyprice()
                + "\nValue:" + game.dealcards.DealCardStack.get(i).getSellprice() + "\nChoice1: " +
                game.dealcards.DealCardStack.get(i).getChoice1() + "\nChoice2: " + game.dealcards.DealCardStack.get(i).getChoice2());
        Image image = new ImageIcon(imageURL).getImage();
        image = image.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH);
        JOptionPane p = new JOptionPane();

        n = p.showOptionDialog(this,
                game.dealcards.DealCardStack.get(i).getMessage() + "\nΤιμή Αγοράς: " +
                        game.dealcards.DealCardStack.get(i).getBuyprice() + " Ευρώ \nΤιμή Πώλησης: "
                        + game.dealcards.DealCardStack.get(i).getSellprice() + " Ευρώ \n", game.dealcards.DealCardStack.get(i).getType()
                , JOptionPane.OK_OPTION,
                0,
                new ImageIcon(image),
                options,
                options[0]);
    }

    public void showmyDealCard(int playernum) {
        int x = 0;
        for (int i = 0; i < game.players.get(playernum).sullogi.DealCardStack.size(); i++) {
            Object[] options = {"Next"};
            URL imageURL = cldr.getResource("resources/images/" + game.players.get(playernum).sullogi.DealCardStack.get(i).getImage()); //image
            System.out.println("Type: " + game.players.get(playernum).sullogi.DealCardStack.get(i).getType()
                    + "\nMessage: " + game.players.get(playernum).sullogi.DealCardStack.get(i).getMessage() + "\nCost:" + game.players.get(playernum).sullogi.DealCardStack.get(i).getBuyprice()
                    + "\nValue:" + game.dealcards.DealCardStack.get(i).getSellprice() + "\nChoice1: " +
                    game.players.get(playernum).sullogi.DealCardStack.get(i).getChoice1() + "\nChoice2: " + game.players.get(playernum).sullogi.DealCardStack.get(i).getChoice2());
            Image image = new ImageIcon(imageURL).getImage();
            image = image.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH);
            JOptionPane p = new JOptionPane();

            x += 25;
            p.setLocation(50 + x, 50);
            int n = p.showOptionDialog(this,
                    game.players.get(playernum).sullogi.DealCardStack.get(i).getMessage() + "\nΤιμή Αγοράς: " +
                            game.players.get(playernum).sullogi.DealCardStack.get(i).getBuyprice() + " Ευρώ \nΤιμή Πώλησης: "
                            + game.players.get(playernum).sullogi.DealCardStack.get(i).getSellprice() + " Ευρώ \n", game.players.get(playernum).sullogi.DealCardStack.get(i).getType()
                    , JOptionPane.OK_OPTION,
                    0,
                    new ImageIcon(image),
                    options,
                    options[0]);
        }

    }

    public void showCryptoThursday() {
        JOptionPane q = new JOptionPane();

        URL imageURL1 = cldr.getResource("images/crypto.jpeg");
        Image image2 = new ImageIcon(imageURL1).getImage();
        image2 = image2.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH);
        Object[] options1 = {"Πόνταρε στο κρυπτονόμισμα", "Παράβλεψε το ποντάρισμα"};

        k = q.showOptionDialog(this,
                "Ποντάρισμα σε κρυπτονομίσματα",
                "Crypto Thursday", JOptionPane.OK_OPTION,
                0,
                new ImageIcon(image2),
                options1,
                options1[0]);

    }


    public void showCryptoThursdayResult(int i) {
        JOptionPane q = new JOptionPane();
        String s1, s2;
        if (i == 0) {
            s1 = "Το κρυπτονόμισμα έπεσε..";
            s2 = "Έχασες 300 ευρώ";
        } else if (i == 1) {
            s1 = "Το κρυπτονόμισμα έμεινε στάσιμο.";
            s2 = "Πάρε πίσω τα 300 ευρώ";
        } else {
            s1 = "Το κρυπτονόμισμα εκτοξεύτηκε!";
            s2 = "Κέρδισες 300 ευρώ";
        }

        URL imageURL1 = cldr.getResource("images/crypto.jpeg");
        Image image2 = new ImageIcon(imageURL1).getImage();
        image2 = image2.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH);
        Object[] options1 = {s2};

        int m = q.showOptionDialog(this,
                s1,
                "Crypto Thursday", JOptionPane.OK_OPTION,
                0,
                new ImageIcon(image2),
                options1,
                options1[0]);
    }

    public void showFootballSunday() {
        JOptionPane fs = new JOptionPane();

        URL imageURL1 = cldr.getResource("images/Barcelona_Real.jpg");
        Image image2 = new ImageIcon(imageURL1).getImage();
        image2 = image2.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH);
        Object[] options1 = {"Νίκη Μπαρτσελόνας", "Ισοπαλία", "Νίκη Ρεάλ", "Δε θέλω να στοιχηματίσω"};

        f = fs.showOptionDialog(this,
                "Στοιχημάτισε 500 ευρώ για το ντερμπι αιωνίων Barcelona-Real! ",
                "Sunday's Football Game", JOptionPane.OK_OPTION,
                0,
                new ImageIcon(image2),
                options1,
                options1[0]);

    }


    public void showFootballSundayResult(int i) {
        JOptionPane sf = new JOptionPane();
        String s1, s2;
        if (i == 0) {
            s1 = "Σωστή πρόβλεψη!";
            s2 = "Νίκησες 500 ευρώ!";
        } else {
            s1 = "Λανθασμένη πρόβλεψη..";
            s2 = "Έχασες 500 ευρώ";
        }
        URL imageURL1 = cldr.getResource("images/Barcelona_Real.jpg");
        Image image2 = new ImageIcon(imageURL1).getImage();
        image2 = image2.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH);
        Object[] options1 = {s2};

        int sfd = sf.showOptionDialog(this,
                s1,
                "Sunday's Football Game", JOptionPane.OK_OPTION,
                0,
                new ImageIcon(image2),
                options1,
                options1[0]);
    }

    public void searchForBuyer_DealPos(ArrayList<Integer> b, ArrayList<Integer> d, ArrayList<Integer> db, int pos) {
        for (int i = pos; i <= 30; i++) {
            if (Objects.equals(game.board.tiles[i].getType(), "Buyer")) b.add(i);
            if (Objects.equals(game.board.tiles[i].getType(), "Deal")) d.add(i);
            if (Objects.equals(game.board.tiles[i].getType(), "Buyer") || Objects.equals(game.board.tiles[i].getType(), "Deal"))
                db.add(i);
        }

    }


    public void searchForMailPos(ArrayList<Integer> m, int pos) {
        for (int i = pos; i <= 30; i++) {
            if (Objects.equals(game.board.tiles[i].getType(), "Mail1")) m.add(i);
            else if (Objects.equals(game.board.tiles[i].getType(), "Mail2")) m.add(i);
        }

    }


    /**
     * a class which is used for doing some action after dice1 button has been pushed
     *
     * @author plata
     */
    private class DiceListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == RollDice1) {
                try {
                    if (game.seeTurn() == 1 || game.players.get(1).posi == 31) {
                        int dicen = game.rollTheDice();
                        game.players.get(0).setDice(dicen);
                        if (dicen == 6) {
                            int currmoney = game.players.get(0).getMoney();
                            game.players.get(0).setMoney(currmoney + game.jp.getMoney());
                            currmoney = game.jp.getMoney();
                            game.jp.refresh_money(currmoney, "-");
                            refresh_jackpot();
                        }
                        if (game.players.get(1).posi != 31) RollDice1.setEnabled(false);
                        try {
                            refresh_player1field(dicen);
                            game.players.get(0).movePosition(dicen);
                            movepawn(pawn1b);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                        if (Objects.equals(game.board.tiles[game.players.get(0).posi].getType(), "Mail1") || Objects.equals(game.board.tiles[game.players.get(0).posi].getType(), "Mail2")) {
                            if (game.mailcards.MailCardStack.size() == 1) game.rejectedToMail();
                            if (Objects.equals(game.board.tiles[game.players.get(0).posi].getType(), "Mail1"))
                                showMailCard(0);
                            else {
                                showMailCard(0);
                                showMailCard(1);
                            }
                            if (game.mailcards.MailCardStack.get(0).getType() == MailCardsType.MoveToDeal_Buyer || game.mailcards.MailCardStack.get(1).getType() == MailCardsType.MoveToDeal_Buyer) {
                                searchForBuyer_DealPos(b, d, db, game.players.get(0).posi);
                            }
                            game.board.tiles[game.players.get(0).posi].performAction(game.players.get(0), game.players.get(1), game.cardnum, game.board.tiles[game.players.get(0).posi].getType(),
                                    game.rejectedcards.RejectedCards, game.dealcards.DealCardStack, game.mailcards.MailCardStack, 0, game.jp, db);
                            movepawn(pawn1b);
                            if (Objects.equals(game.board.tiles[game.players.get(0).posi].getType(), "Mail1"))
                                game.mailcards.MailCardStack.remove(0);
                            else {
                                game.mailcards.MailCardStack.remove(0);
                                game.mailcards.MailCardStack.remove(0);
                            }
                        } else if (Objects.equals(game.board.tiles[game.players.get(0).posi].getType(), "Sweepstakes")) {
                            game.board.tiles[game.players.get(0).posi].performAction(game.players.get(0), game.players.get(1), dicen, game.board.tiles[game.players.get(0).posi].getType(),
                                    game.rejectedcards.RejectedCards, game.dealcards.DealCardStack, game.mailcards.MailCardStack, 0, game.jp, db);
                        } else if (Objects.equals(game.board.tiles[game.players.get(0).posi].getType(), "Casino")) {
                            game.board.tiles[game.players.get(0).posi].performAction(game.players.get(0), game.players.get(1), dicen, game.board.tiles[game.players.get(0).posi].getType(),
                                    game.rejectedcards.RejectedCards, game.dealcards.DealCardStack, game.mailcards.MailCardStack, 0, game.jp, db);
                        } else if (Objects.equals(game.board.tiles[game.players.get(0).posi].getType(), "Yard")) {
                            showDealCard(0);
                            game.board.tiles[game.players.get(0).posi].performAction(game.players.get(0), game.players.get(1), dicen, game.board.tiles[game.players.get(0).posi].getType(),
                                    game.rejectedcards.RejectedCards, game.dealcards.DealCardStack, game.mailcards.MailCardStack, 0, game.jp, db);
                            game.dealcards.DealCardStack.remove(0);
                        } else if (Objects.equals(game.board.tiles[game.players.get(0).posi].getType(), "Lottery")) {
                            do {
                                str = JOptionPane.showInputDialog("Player 1 which number between 1-6 do you choose?");
                                game.players.get(0).setDicelot(Integer.parseInt(str));
                            } while (game.players.get(0).getDicelot() < 1 || game.players.get(0).getDicelot() > 6);
                            do {
                                str = JOptionPane.showInputDialog("Player 2 which number between 1-6 do you choose?");
                                game.players.get(1).setDicelot(Integer.parseInt(str));
                            } while (game.players.get(1).getDicelot() < 1 || game.players.get(1).getDicelot() > 6 || game.players.get(1).getDicelot() == game.players.get(0).getDicelot());

                            game.board.tiles[game.players.get(0).posi].performAction(game.players.get(0), game.players.get(1), dicen, game.board.tiles[game.players.get(0).posi].getType(),
                                    game.rejectedcards.RejectedCards, game.dealcards.DealCardStack, game.mailcards.MailCardStack, 0, game.jp, db);
                        } else if (Objects.equals(game.board.tiles[game.players.get(0).posi].getType(), "Radio")) {
                            game.board.tiles[game.players.get(0).posi].performAction(game.players.get(0), game.players.get(1), dicen, game.board.tiles[game.players.get(0).posi].getType(),
                                    game.rejectedcards.RejectedCards, game.dealcards.DealCardStack, game.mailcards.MailCardStack, 0, game.jp, db);
                        } else if (Objects.equals(game.board.tiles[game.players.get(0).posi].getType(), "Buyer")) {
                            if (game.players.get(0).sullogi.DealCardStack.isEmpty()) {
                                JOptionPane.showMessageDialog(basic_panel, "No Deal Cards to sell.", "Warning", JOptionPane.WARNING_MESSAGE);
                            } else {
                                showmyDealCard(0);
                                do {
                                    str = JOptionPane.showInputDialog("Which one do you want to sell (put a number from 0-how many cards you got-1)");
                                } while (Integer.parseInt(str) <= -1 || Integer.parseInt(str) >= game.players.get(0).sullogi.DealCardStack.size());
                                int z = Integer.parseInt(str);
                                game.cardtobesold = z;
                                game.board.tiles[game.players.get(0).posi].performAction(game.players.get(0), game.players.get(1), dicen, game.board.tiles[game.players.get(0).posi].getType(),
                                        game.rejectedcards.RejectedCards, game.dealcards.DealCardStack, game.mailcards.MailCardStack, game.cardtobesold, game.jp, db);
                            }
                        } else if (Objects.equals(game.board.tiles[game.players.get(0).posi].getType(), "Deal")) {
                            if (game.dealcards.DealCardStack.size() == 0) {
                                game.rejectedToDeal();
                                Collections.shuffle(game.dealcards.DealCardStack);
                            }
                            showDealCard(0);
                            game.players.get(0).setChoice(n == JOptionPane.YES_OPTION);
                            game.board.tiles[game.players.get(0).posi].performAction(game.players.get(0), game.players.get(1), dicen, game.board.tiles[game.players.get(0).posi].getType(),
                                    game.rejectedcards.RejectedCards, game.dealcards.DealCardStack, game.mailcards.MailCardStack, game.cardtobesold, game.jp, db);
                            game.dealcards.DealCardStack.remove(0);
                        }

                        if (game.players.get(0).movetodb == 1) {
                            if (Objects.equals(game.board.tiles[game.players.get(0).posi].getType(), "Buyer")) {
                                if (game.players.get(0).sullogi.DealCardStack.isEmpty()) {
                                    JOptionPane.showMessageDialog(basic_panel, "No Deal Cards to sell.", "Warning", JOptionPane.WARNING_MESSAGE);
                                } else {
                                    showmyDealCard(0);
                                    do {
                                        str = JOptionPane.showInputDialog("Which one do you want to sell (put a number from 0-how many cards you got-1)");
                                    } while (Integer.parseInt(str) <= -1 || Integer.parseInt(str) >= game.players.get(0).sullogi.DealCardStack.size());
                                    int z = Integer.parseInt(str);
                                    game.cardtobesold = z;
                                    game.board.tiles[game.players.get(0).posi].performAction(game.players.get(0), game.players.get(1), dicen, game.board.tiles[game.players.get(0).posi].getType(),
                                            game.rejectedcards.RejectedCards, game.dealcards.DealCardStack, game.mailcards.MailCardStack, game.cardtobesold, game.jp, db);
                                }
                            } else if (Objects.equals(game.board.tiles[game.players.get(0).posi].getType(), "Deal")) {
                                if (game.dealcards.DealCardStack.size() == 0) {
                                    game.rejectedToDeal();
                                    Collections.shuffle(game.dealcards.DealCardStack);
                                }
                                showDealCard(0);
                                game.players.get(0).setChoice(n == JOptionPane.YES_OPTION);
                                game.board.tiles[game.players.get(0).posi].performAction(game.players.get(0), game.players.get(1), dicen, game.board.tiles[game.players.get(0).posi].getType(),
                                        game.rejectedcards.RejectedCards, game.dealcards.DealCardStack, game.mailcards.MailCardStack, game.cardtobesold, game.jp, db);
                                game.dealcards.DealCardStack.remove(0);
                            }
                            if (Day.valueOf(game.players.get(game.seeTurn() - 1).posi) == Day.Thu) {
                                showCryptoThursday();
                                if (n == JOptionPane.YES_OPTION) {
                                    int dd = game.rollTheDice();
                                    if (dd == 1 || dd == 2) {
                                        showCryptoThursdayResult(0);
                                        int currmoney = game.players.get(game.seeTurn() - 1).getMoney();
                                        int currloan = game.players.get(game.seeTurn() - 1).getLoan();
                                        if (currmoney < 300) {
                                            game.players.get(game.seeTurn() - 1).setLoan(currloan + 1000);
                                            game.players.get(game.seeTurn() - 1).setMoney(currmoney + 700);
                                        } else game.players.get(game.seeTurn() - 1).setMoney(currmoney - 300);
                                    } else if (dd == 3 || dd == 4) showCryptoThursdayResult(1);
                                    else {
                                        showCryptoThursdayResult(2);
                                        int currmoney = game.players.get(game.seeTurn() - 1).getMoney();
                                        game.players.get(game.seeTurn() - 1).setMoney(currmoney + 300);
                                    }
                                }
                            } else if (Day.valueOf(game.players.get(game.seeTurn() - 1).posi) == Day.Sun) {
                                showFootballSunday();
                                int dd = game.rollTheDice();
                                if (k != 3) {
                                    if ((k == 0 && (dd == 1 || dd == 2)) || (k == 1 && (dd == 3 || dd == 4)) || (k == 2 && (dd == 5 || dd == 6))) {
                                        showFootballSundayResult(0);
                                        int currmoney = game.players.get(game.seeTurn() - 1).getMoney();
                                        game.players.get(game.seeTurn() - 1).setMoney(currmoney + 500);
                                    } else {
                                        showFootballSundayResult(1);
                                        int currmoney = game.players.get(game.seeTurn() - 1).getMoney();
                                        int currloan = game.players.get(game.seeTurn() - 1).getLoan();
                                        if (currmoney < 500) {
                                            game.players.get(game.seeTurn() - 1).setLoan(currloan + 1000);
                                            game.players.get(game.seeTurn() - 1).setMoney(currmoney + 500);
                                        } else game.players.get(game.seeTurn() - 1).setMoney(currmoney - 500);
                                    }
                                }

                            }
                            game.players.get(0).movetodb = 0;
                        }

                        b.clear();
                        d.clear();
                        db.clear();
                        refresh_jackpot();
                        refresh_player1field(dicen);
                        refresh_player2field(game.players.get(1).getDice());
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else if (e.getSource() == RollDice2) {
                try {
                    if (game.seeTurn() == 2 || game.players.get(0).posi == 31) {
                        int dicen = game.rollTheDice();
                        game.players.get(1).setDice(dicen);
                        if (dicen == 6) {
                            int currmoney = game.players.get(1).getMoney();
                            game.players.get(1).setMoney(currmoney + game.jp.getMoney());
                            currmoney = game.jp.getMoney();
                            game.jp.refresh_money(currmoney, "-");
                        }
                        if (game.players.get(0).posi != 31) RollDice2.setEnabled(false);

                        refresh_player2field(dicen);
                        game.players.get(1).movePosition(dicen);
                        movepawn(pawn2b);

                        if (Objects.equals(game.board.tiles[game.players.get(1).posi].getType(), "Mail1") || Objects.equals(game.board.tiles[game.players.get(1).posi].getType(), "Mail2")) {

                            if (game.mailcards.MailCardStack.size() == 1) game.rejectedToMail();
                            if (Objects.equals(game.board.tiles[game.players.get(1).posi].getType(), "Mail1"))
                                showMailCard(0);
                            else {
                                showMailCard(0);
                                showMailCard(1);
                            }
                            if (game.mailcards.MailCardStack.get(0).getType() == MailCardsType.MoveToDeal_Buyer || game.mailcards.MailCardStack.get(1).getType() == MailCardsType.MoveToDeal_Buyer) {
                                searchForBuyer_DealPos(b, d, db, game.players.get(1).posi);
                            }
                            game.board.tiles[game.players.get(1).posi].performAction(game.players.get(1), game.players.get(0), game.cardnum, game.board.tiles[game.players.get(1).posi].getType(),
                                    game.rejectedcards.RejectedCards, game.dealcards.DealCardStack, game.mailcards.MailCardStack, 0, game.jp, db);
                            movepawn(pawn2b);
                            if (Objects.equals(game.board.tiles[game.players.get(1).posi].getType(), "Mail1"))
                                game.mailcards.MailCardStack.remove(0);
                            else {
                                game.mailcards.MailCardStack.remove(0);
                                game.mailcards.MailCardStack.remove(0);
                            }

                        } else if (Objects.equals(game.board.tiles[game.players.get(1).posi].getType(), "Sweepstakes")) {
                            game.board.tiles[game.players.get(1).posi].performAction(game.players.get(1), game.players.get(0), dicen, game.board.tiles[game.players.get(1).posi].getType(),
                                    game.rejectedcards.RejectedCards, game.dealcards.DealCardStack, game.mailcards.MailCardStack, 0, game.jp, db);
                        } else if (Objects.equals(game.board.tiles[game.players.get(1).posi].getType(), "Casino")) {
                            game.board.tiles[game.players.get(1).posi].performAction(game.players.get(1), game.players.get(0), dicen, game.board.tiles[game.players.get(1).posi].getType(),
                                    game.rejectedcards.RejectedCards, game.dealcards.DealCardStack, game.mailcards.MailCardStack, 0, game.jp, db);
                        } else if (Objects.equals(game.board.tiles[game.players.get(1).posi].getType(), "Yard")) {
                            showDealCard(0);
                            game.board.tiles[game.players.get(1).posi].performAction(game.players.get(1), game.players.get(0), dicen, game.board.tiles[game.players.get(1).posi].getType(),
                                    game.rejectedcards.RejectedCards, game.dealcards.DealCardStack, game.mailcards.MailCardStack, 0, game.jp, db);
                            game.dealcards.DealCardStack.remove(0);
                        } else if (Objects.equals(game.board.tiles[game.players.get(1).posi].getType(), "Lottery")) {
                            do {
                                str = JOptionPane.showInputDialog("Player 2 which number between 1-6 do you choose?");
                                game.players.get(1).setDicelot(Integer.parseInt(str));
                            } while (game.players.get(1).getDicelot() < 1 || game.players.get(1).getDicelot() > 6);

                            do {
                                str = JOptionPane.showInputDialog("Player 1 which number between 1-6 do you choose?");
                                game.players.get(0).setDicelot(Integer.parseInt(str));
                            } while (game.players.get(0).getDicelot() < 1 || game.players.get(0).getDicelot() > 6 || game.players.get(1).getDicelot() == game.players.get(0).getDicelot());

                            game.board.tiles[game.players.get(1).posi].performAction(game.players.get(1), game.players.get(0), dicen, game.board.tiles[game.players.get(1).posi].getType(),
                                    game.rejectedcards.RejectedCards, game.dealcards.DealCardStack, game.mailcards.MailCardStack, 0, game.jp, db);
                        } else if (Objects.equals(game.board.tiles[game.players.get(1).posi].getType(), "Radio")) {
                            game.board.tiles[game.players.get(1).posi].performAction(game.players.get(1), game.players.get(0), dicen, game.board.tiles[game.players.get(1).posi].getType(),
                                    game.rejectedcards.RejectedCards, game.dealcards.DealCardStack, game.mailcards.MailCardStack, 0, game.jp, db);
                        } else if (Objects.equals(game.board.tiles[game.players.get(1).posi].getType(), "Buyer")) {
                            if (game.players.get(1).sullogi.DealCardStack.isEmpty()) {
                                JOptionPane.showMessageDialog(basic_panel, "No Deal Cards to sell.", "Warning", JOptionPane.WARNING_MESSAGE);
                            } else {
                                showmyDealCard(1);
                                do {
                                    str = JOptionPane.showInputDialog("Which one do you want to sell (put a number from 0-how many cards you got-1)");
                                } while (Integer.parseInt(str) <= -1 || Integer.parseInt(str) >= game.players.get(1).sullogi.DealCardStack.size());
                                int z = Integer.parseInt(str);
                                game.cardtobesold = z;
                                game.board.tiles[game.players.get(1).posi].performAction(game.players.get(1), game.players.get(0), dicen, game.board.tiles[game.players.get(1).posi].getType(),
                                        game.rejectedcards.RejectedCards, game.dealcards.DealCardStack, game.mailcards.MailCardStack, game.cardtobesold, game.jp, db);
                            }
                        } else if (Objects.equals(game.board.tiles[game.players.get(1).posi].getType(), "Deal")) {
                            if (game.dealcards.DealCardStack.size() == 0) {
                                game.rejectedToDeal();
                                Collections.shuffle(game.dealcards.DealCardStack);
                            }
                            showDealCard(0);
                            game.players.get(1).setChoice(n == JOptionPane.YES_OPTION);
                            game.board.tiles[game.players.get(1).posi].performAction(game.players.get(1), game.players.get(0), dicen, game.board.tiles[game.players.get(1).posi].getType(),
                                    game.rejectedcards.RejectedCards, game.dealcards.DealCardStack, game.mailcards.MailCardStack, game.cardtobesold, game.jp, db);
                            game.dealcards.DealCardStack.remove(0);
                        }

                        if (game.players.get(1).movetodb == 1) {
                            if (Objects.equals(game.board.tiles[game.players.get(1).posi].getType(), "Buyer")) {
                                if (game.players.get(1).sullogi.DealCardStack.isEmpty()) {
                                    JOptionPane.showMessageDialog(basic_panel, "No Deal Cards to sell.", "Warning", JOptionPane.WARNING_MESSAGE);
                                } else {
                                    showmyDealCard(1);
                                    do {
                                        str = JOptionPane.showInputDialog("Which one do you want to sell (put a number from 0-how many cards you got-1)");
                                    } while (Integer.parseInt(str) <= -1 || Integer.parseInt(str) >= game.players.get(1).sullogi.DealCardStack.size());
                                    int z = Integer.parseInt(str);
                                    game.cardtobesold = z;
                                    game.board.tiles[game.players.get(1).posi].performAction(game.players.get(1), game.players.get(0), dicen, game.board.tiles[game.players.get(1).posi].getType(),
                                            game.rejectedcards.RejectedCards, game.dealcards.DealCardStack, game.mailcards.MailCardStack, game.cardtobesold, game.jp, db);
                                }
                            } else if (Objects.equals(game.board.tiles[game.players.get(1).posi].getType(), "Deal")) {
                                if (game.dealcards.DealCardStack.size() == 0) {
                                    game.rejectedToDeal();
                                    Collections.shuffle(game.dealcards.DealCardStack);
                                }
                                showDealCard(0);
                                game.players.get(1).setChoice(n == JOptionPane.YES_OPTION);
                                game.board.tiles[game.players.get(1).posi].performAction(game.players.get(1), game.players.get(0), dicen, game.board.tiles[game.players.get(1).posi].getType(),
                                        game.rejectedcards.RejectedCards, game.dealcards.DealCardStack, game.mailcards.MailCardStack, game.cardtobesold, game.jp, db);
                                game.dealcards.DealCardStack.remove(0);
                            }
                            if (Day.valueOf(game.players.get(game.seeTurn() - 1).posi) == Day.Thu) {
                                showCryptoThursday();
                                if (n == JOptionPane.YES_OPTION) {
                                    int dd = game.rollTheDice();
                                    if (dd == 1 || dd == 2) {
                                        showCryptoThursdayResult(0);
                                        int currmoney = game.players.get(game.seeTurn() - 1).getMoney();
                                        int currloan = game.players.get(game.seeTurn() - 1).getLoan();
                                        if (currmoney < 300) {
                                            game.players.get(game.seeTurn() - 1).setLoan(currloan + 1000);
                                            game.players.get(game.seeTurn() - 1).setMoney(currmoney + 700);
                                        } else game.players.get(game.seeTurn() - 1).setMoney(currmoney - 300);
                                    } else if (dd == 3 || dd == 4) showCryptoThursdayResult(1);
                                    else {
                                        showCryptoThursdayResult(2);
                                        int currmoney = game.players.get(game.seeTurn() - 1).getMoney();
                                        game.players.get(game.seeTurn() - 1).setMoney(currmoney + 300);
                                    }
                                }
                            } else if (Day.valueOf(game.players.get(game.seeTurn() - 1).posi) == Day.Sun) {
                                showFootballSunday();
                                int dd = game.rollTheDice();
                                if (k != 3) {
                                    if ((k == 0 && (dd == 1 || dd == 2)) || (k == 1 && (dd == 3 || dd == 4)) || (k == 2 && (dd == 5 || dd == 6))) {
                                        showFootballSundayResult(0);
                                        int currmoney = game.players.get(game.seeTurn() - 1).getMoney();
                                        game.players.get(game.seeTurn() - 1).setMoney(currmoney + 500);
                                    } else {
                                        showFootballSundayResult(1);
                                        int currmoney = game.players.get(game.seeTurn() - 1).getMoney();
                                        int currloan = game.players.get(game.seeTurn() - 1).getLoan();
                                        if (currmoney < 500) {
                                            game.players.get(game.seeTurn() - 1).setLoan(currloan + 1000);
                                            game.players.get(game.seeTurn() - 1).setMoney(currmoney + 500);
                                        } else game.players.get(game.seeTurn() - 1).setMoney(currmoney - 500);
                                    }
                                }

                            }
                            game.players.get(1).movetodb = 0;
                        }
                        b.clear();
                        d.clear();
                        db.clear();


                        refresh_jackpot();
                        refresh_player2field(dicen);
                        refresh_player1field(game.players.get(0).getDice());

                    }

                } catch (Exception exception) {
                    exception.printStackTrace();
                }

            }
            try {
                if (Day.valueOf(game.players.get(game.seeTurn() - 1).posi) == Day.Thu) {
                    showCryptoThursday();
                    if (n == JOptionPane.YES_OPTION) {
                        int dd = game.rollTheDice();
                        if (dd == 1 || dd == 2) {
                            showCryptoThursdayResult(0);
                            int currmoney = game.players.get(game.seeTurn() - 1).getMoney();
                            int currloan = game.players.get(game.seeTurn() - 1).getLoan();
                            if (currmoney < 300) {
                                game.players.get(game.seeTurn() - 1).setLoan(currloan + 1000);
                                game.players.get(game.seeTurn() - 1).setMoney(currmoney + 700);
                            } else game.players.get(game.seeTurn() - 1).setMoney(currmoney - 300);
                        } else if (dd == 3 || dd == 4) showCryptoThursdayResult(1);
                        else {
                            showCryptoThursdayResult(2);
                            int currmoney = game.players.get(game.seeTurn() - 1).getMoney();
                            game.players.get(game.seeTurn() - 1).setMoney(currmoney + 300);
                        }
                    }
                } else if (Day.valueOf(game.players.get(game.seeTurn() - 1).posi) == Day.Sun) {
                    showFootballSunday();
                    int dd = game.rollTheDice();
                    if (k != 3) {
                        if ((k == 0 && (dd == 1 || dd == 2)) || (k == 1 && (dd == 3 || dd == 4)) || (k == 2 && (dd == 5 || dd == 6))) {
                            showFootballSundayResult(0);
                            int currmoney = game.players.get(game.seeTurn() - 1).getMoney();
                            game.players.get(game.seeTurn() - 1).setMoney(currmoney + 500);
                        } else {
                            showFootballSundayResult(1);
                            int currmoney = game.players.get(game.seeTurn() - 1).getMoney();
                            int currloan = game.players.get(game.seeTurn() - 1).getLoan();
                            if (currmoney < 500) {
                                game.players.get(game.seeTurn() - 1).setLoan(currloan + 1000);
                                game.players.get(game.seeTurn() - 1).setMoney(currmoney + 500);
                            } else game.players.get(game.seeTurn() - 1).setMoney(currmoney - 500);
                        }
                    }
                    refresh_jackpot();
                    refresh_player2field(game.players.get(1).getDice());
                    refresh_player1field(game.players.get(0).getDice());
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }

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
            if (e.getSource() == mailC) {
                int x = mailCardCount;
                if (mailCardCount == 48) {
                    mailCardCount = 0;
                }
                try {
                    showMailCard(x);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else if (e.getSource() == dealC) {
                int x = dealCardCount;
                if (dealCardCount == 48) {
                    dealCardCount = 0;
                }
                try {
                    showDealCard(x);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }
        }
    }

    /**
     * a class which is used for doing some action after MyDealCards1 button has been pushed
     *
     * @author plata
     */
    private class MyDealCardsListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == MyDealCards1) showmyDealCard(0);
            else if (e.getSource() == MyDealCards2) showmyDealCard(1);
        }
    }

    /**
     * a class which is used for doing some action after GetLoan1 button has been pushed
     *
     * @author plata
     */
    private class GetLoanListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == GetLoan1) {
                do {
                    loan = JOptionPane.showInputDialog("Put the loan value: ");
                } while (Integer.parseInt(loan) % 1000 != 0 || Integer.parseInt(loan) == 0);
                int loan1 = Integer.parseInt(loan);
                try {
                    int currloan1 = game.players.get(0).getLoan();
                    int currmoney1 = game.players.get(0).getMoney();
                    game.players.get(0).setLoan(currloan1 + loan1);
                    game.players.get(0).setMoney(currmoney1 + loan1);
                    refresh_player1field(game.dice);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            } else if (e.getSource() == GetLoan2) {
                do {
                    loan = JOptionPane.showInputDialog("Put the loan value: ");
                } while (Integer.parseInt(loan) % 1000 != 0 || Integer.parseInt(loan) == 0);
                int loan1 = Integer.parseInt(loan);
                try {
                    int currloan2 = game.players.get(1).getLoan();
                    int currmoney2 = game.players.get(1).getMoney();
                    game.players.get(1).setLoan(currloan2 + loan1);
                    game.players.get(1).setMoney(currmoney2 + loan1);
                    refresh_player2field(game.dice);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

        }
    }

    /**
     * a class which is used for doing some action after EndTurn1 button has been pushed
     *
     * @author plata
     */
    private class EndTurnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == EndTurn1) {
                try {
                    game.set_Turn(2);
                    RollDice2.setEnabled(true);
                    refresh_infobox();

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else if (e.getSource() == EndTurn2) {
                try {
                    game.set_Turn(1);
                    RollDice1.setEnabled(true);
                    refresh_infobox();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            try {
                refresh_player1field(game.players.get(0).getDice());
                refresh_player2field(game.players.get(1).getDice());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            if (game.players.get(0).posi == game.players.get(1).posi && game.players.get(0).posi == 31) {
                try {
                    game.board.tiles[31].performAction(game.players.get(0), game.players.get(1), 0, "PayDay",
                            game.rejectedcards.RejectedCards, game.dealcards.DealCardStack, game.mailcards.MailCardStack,
                            0, game.jp, db);

                    game.months -= 1;
                    if (game.months == 0) {
                        refresh_infobox();
                        game.setScore1();
                        game.setScore2();
                        int w = 2;
                        int l = 1;
                        if (game.players.get(0).getScore() > game.players.get(1).getScore()) {
                            w = 1;
                            l = 2;
                        } else if (game.players.get(0).getScore() == game.players.get(1).getScore()) {
                            w = 0;
                            l = 0;
                        }
                        if (w != 0)
                            JOptionPane.showMessageDialog(basic_panel, "Player " + String.valueOf(w) + " won!! With score: " + game.players.get(w - 1).getScore() +
                                    " ( > Player " + String.valueOf(l) + " score : " + game.players.get(l - 1).getScore() + ")", "Winner", JOptionPane.INFORMATION_MESSAGE);
                        else
                            JOptionPane.showMessageDialog(basic_panel, "It's a TIE!!", "TIE", JOptionPane.INFORMATION_MESSAGE);
                        System.exit(0);
                    } else {
                        game.turn = new Turn();
                        refresh_infobox();
                        refresh_player2field(1);
                        refresh_player1field(1);
                        game.players.get(0).setPos(game.board.tiles[0]);
                        game.players.get(1).setPos(game.board.tiles[0]);
                        game.players.get(0).posi = 0;
                        game.players.get(1).posi = 0;
                        movepawn(pawn1b);
                        movepawn(pawn2b);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

        }
    }


}


