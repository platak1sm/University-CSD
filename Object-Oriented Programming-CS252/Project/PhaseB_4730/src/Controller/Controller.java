package Controller;

import Model.*;
import View.GraphicUI;
import View.PaydayCards.src.PayDayCards;

import javax.swing.*;
import java.awt.image.Kernel;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;

public class Controller {
    public int months, cardtobesold = -1;
    public Turn turn;
    private boolean notstarted;
    public Board board;
    private Player player1, player2;
    public ArrayList<Player> players = new ArrayList<Player>();
    public int dice = 1;
    public Sullogi dealcards, mailcards;
    public Sullogi rejectedcards;
    private double score1, score2;
    private GraphicUI view;
    private PayDayCards paydaycards;
    public Jackpot jp = new Jackpot();
    private int mn = 0; //the month now
    private JButton[] buttons = new JButton[32];
    public ArrayList<Integer> intarr = new ArrayList<>();
    public int cardnum=0;


    /**
     * <b>constructor</b>: Constructs a new Controller and sets the game as
     * eligible to start .<br />
     * <b>postcondition</b>: constructs a new Controller,with new 4 players,new
     * instances of Turn Class , Round Class and Sullogi Class and initialize
     * some int or boolean variables.So,is responsible for creating a new game and
     * initializing it.
     */
    public Controller() {
        player1 = new Player();
        player2 = new Player();
        Turn turn = new Turn();
        players.add(player1);
        players.add(player2);
        paydaycards = new PayDayCards();
        rejectedcards = new Sullogi("Card");
        this.notstarted = true;
        mailcards = new Sullogi("Mail");
        dealcards = new Sullogi("Deal");
        makePaydaycardsToStacks();
        init_board();
        score1 = 0;
        score2 = 0;
        player1.setScore(0);
        player2.setScore(0);
        mn = 0;
    }

    /**
     * <p><b>Postcondition:</b> Returns random int value between 1-6 </p>
     *
     * @return random int value between 1-6
     */
    public int rollTheDice() {
        this.dice = 1 + (int) (Math.random() * 6);
        return this.dice;
    }

    /**
     * <b>accessor(selector)</b>:Returns which player has the turn <br />
     *
     * <p><b>Postcondition:</b> Returns which player has the turn </p>
     *
     * @return which player has the turn (for example 1 if player1 has the turn )
     */
    public int seeTurn() throws Exception {
        return turn.getHasturn();
    }

    public int seeLastPlayer() {
        return turn.getLastPlayer();
    }

    /**
     * <b>transformer(mutative)</b>: sets the variable not_started to false
     * <p><b>Postcondition:</b>  sets the variable not_started to false</p>
     */
    public void set_started() {
        this.notstarted = false;
    }


    /*
     *<b>Observer</b>: Return true if the game has not started  false otherwise
     * <p><b>Postcondition:</b> return true if the game  has not started  false otherwise
     * @return true if the game has not started  false otherwise
     */
    public boolean not_started() {
        return this.notstarted;
    }

    /**
     * <b>transformer(mutative)</b>: initializes some things(dealcards, mailcards, turn) for a new game
     * <p><b>Postcondition:</b>  initializes some things(dealcards, mailcards, turn) for a new game</p>
     */
    public void init_board() {
        turn = new Turn();
        board = new Board(dealcards, mailcards);


        for (int i = 0; i < 30; i++) {
            intarr.add(i);
        }
        Collections.shuffle(intarr);

        board.initPositions(intarr);
        rejectedcards.initCards("Card");
        dealcards.initCards("Deal");
        mailcards.initCards("Mail");


    }

    /**
     * <b>Observer</b>:Return true if a game has finished, false otherwise
     * <p><b>Postcondition:</b> return true if a game has finished, false otherwise
     * </p>
     *
     * @return true if a game has finished, false otherwise
     */
    public boolean game_has_finished() {
        return months == mn;
    }

    /**
     * <b>transformer(mutative)</b>: gives the turn to player<index>
     * <p><b>Postcondition:</b> gives the turn to player<index></index></p>
     */
    public void set_Turn(int index) {
        if (index == 1) {
            turn = new Turn(1);
        } else if (index == 2) {
            turn = new Turn(2);
        } else throw new IllegalArgumentException("There are only 2 players, so index takes only 1 and 2 for values");
    }

    public void setScore1() {
        try {
            score1 = players.get(0).getMoney();
            score1-= players.get(0).getBills();
            score1-= 0.1 * players.get(0).getLoan();
            players.get(0).setScore(score1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setScore2() {
        try {
            score2 = players.get(1).getMoney();
            score2-= players.get(1).getBills();
            score2-= 0.1 * players.get(1).getLoan();
            players.get(1).setScore(score2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removePlayerCard(int i, int player) {
        if (player == 1) {
            rejectedcards.addCard(player1.getSullogi().getCardD(i));
            player1.getSullogi().removeCard(i, "Deal");
        } else if (player == 2) {
            rejectedcards.addCard(player2.getSullogi().getCardD(i));
            player2.getSullogi().removeCard(i, "Deal");
        }

    }

    public void makePaydaycardsToStacks() {
        int buyPrice, sellPrice, price;
        String message, image, choice;
        DealCard dc;
        MailCard mc;
        MailCardsType type;
        String type1;

        for (int i = 0; i < 20; i++) {
            buyPrice = Integer.parseInt(paydaycards.dealCards[i][3]);
            sellPrice = Integer.parseInt(paydaycards.dealCards[i][4]);
            message = paydaycards.dealCards[i][2];
            image = paydaycards.dealCards[i][5];
            type1 = paydaycards.dealCards[i][0];
            dc = new DealCard(image, message, buyPrice, sellPrice, type1);
            dealcards.addCard(dc);
        }
        for (int i = 0; i < 48; i++) {
            switch (paydaycards.mailCards[i][1]) {
                case "Advertisement":
                    type = MailCardsType.Advertisement;
                    break;
                case "Bill":
                    type = MailCardsType.PayTheBills;
                    break;
                case "Charity":
                    type = MailCardsType.Charity;
                    break;
                case "PayTheNeighbor":
                    type = MailCardsType.PayTheNeighbor;
                    break;
                case "MadMoney":
                    type = MailCardsType.TakeMoneyFromTheNeighbor;
                    break;
                case "MoveToDealBuyer":
                    type = MailCardsType.MoveToDeal_Buyer;
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + paydaycards.mailCards[i][1]);
            }
            message = paydaycards.mailCards[i][2];
            choice = paydaycards.mailCards[i][3];
            price = Integer.parseInt(paydaycards.mailCards[i][4]);
            image = paydaycards.mailCards[i][5];
            mc = new MailCard(image, message, type, choice, price);
            mailcards.addCard(mc);
        }


    }

    public void rejectedToMail(){
        Collections.shuffle(rejectedcards.RejectedCards);
        for (int i=0; i<rejectedcards.RejectedCards.size(); i++){
            if (rejectedcards.RejectedCards.get(i) instanceof MailCard){
                mailcards.MailCardStack.add((MailCard)rejectedcards.RejectedCards.get(i));
                rejectedcards.RejectedCards.remove(i);
            }
        }
    }

    public void rejectedToDeal(){
        Collections.shuffle(rejectedcards.RejectedCards);
        for (int i=0; i<rejectedcards.RejectedCards.size(); i++){
            if (rejectedcards.RejectedCards.get(i) instanceof DealCard) {
                dealcards.DealCardStack.add((DealCard)rejectedcards.RejectedCards.get(i));
                rejectedcards.RejectedCards.remove(i);
            }
        }
    }

}

