package Controller;

import Model.Board;
import Model.Player;
import Model.Sullogi;
import Model.Turn;

import java.util.ArrayList;

public class Controller {
    private int months;
    private Turn turn = new Turn();
    private boolean notstarted;
    private Board board;
    private Player player1, player2;
    private ArrayList<Player> players = new ArrayList<Player>();
    private int dice;
    private Sullogi dealcards = new Sullogi("DealCard");
    private Sullogi mailcards = new Sullogi("MailCard");

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
        players.add(player1);
        players.add(player2);
        dealcards.initCards();
        mailcards.initCards();
        this.notstarted = true;
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

    }

    /**
     * <b>Observer</b>:Return true if a game has finished, false otherwise
     * <p><b>Postcondition:</b> return true if a game has finished, false otherwise
     * </p>
     *
     * @return true if a game has finished, false otherwise
     */
    public boolean game_has_finished() {
        return false;
    }

    /**
     * <b>transformer(mutative)</b>: gives the turn to player<index>
     * <p><b>Postcondition:</b> gives the turn to player<index></index></p>
     */
    public void set_Turn(int index) {
        if (index == 1) {
            turn.hasturn = 1;
        } else if (index == 2) {
            turn.hasturn = 2;
        } else throw new IllegalArgumentException("There are only 2 players, so index takes only 1 and 2 for values");
    }

}

