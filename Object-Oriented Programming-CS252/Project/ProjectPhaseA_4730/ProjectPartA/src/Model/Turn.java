package Model;

import java.util.Random;

/**
 * This class represents the turn of the game
 *
 * @author Manos Platakis
 */
public class Turn {
    private final int pnum = 2;
    protected int lastPlayer;
    public int hasturn = 0;

    /**
     * Constructor.
     *
     * <b>Postcondition</b>Creates a new instance of Turn with lastPlayer= a random number between(1,2)
     */
    public Turn() {
        Random r = new Random();
        this.hasturn = r.nextInt(2);
        if (hasturn == 1) lastPlayer = 2;
        else lastPlayer = 1;
    }

    /**
     * Constructor.
     *
     * <b>Postcondition</b>Creates a new instance of Turn with lastPlayer= a random number between(1,2)
     */
    public Turn(int hasturn) {
        this.hasturn = hasturn;
        if (hasturn == 1) lastPlayer = 2;
        else lastPlayer = 1;
    }

    /**
     * <b>Transformer(Mutative):</b> Sets the last player that played.
     * <b>Postcondition:</b> Last player that played has been set.
     *
     * @param lastPlayer an int
     */
    public void setLastPlayer(int lastPlayer) {
        if (lastPlayer != 1 && lastPlayer != 2) throw new IllegalArgumentException("there are only two players");
        else this.lastPlayer = lastPlayer;
    }

    /**
     * <b>Accessor(Selector):</b> returns the most recent player that has played.
     * <b>Postcondition:</b> the most recent player that has played is returned.
     *
     * @return the most recent player that has played.
     */
    public int getLastPlayer() {
        return this.lastPlayer;
    }

    /**
     * <b>Transformer(Mutative):</b> Sets the player's turn.(which player has the turn to play)
     * <b>Postcondition:</b> Player's turn has been set.
     *
     * @param hasturn an int
     */
    public void setHasturn(int hasturn) {
        if (hasturn == 1 || hasturn == 2) this.hasturn = hasturn;
        else throw new IllegalArgumentException(" has turn must be either 1 or 2");
    }

    /**
     * <b>Accessor(Selector):</b> returns the player that has turn to play.
     * <b>Postcondition:</b> the player that has turn to play is returned
     *
     * @return the most recent player that has played.
     */

    public int getHasturn() throws Exception {
        if (hasturn != 0) return hasturn;
        else throw new Exception("hasturn =0");
    }


}
