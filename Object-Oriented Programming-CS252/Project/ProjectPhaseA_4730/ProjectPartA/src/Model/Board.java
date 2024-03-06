package Model;

import java.util.Stack;

/**
 * Board Class
 * Describes the board
 *
 * @author plata
 */
public class Board {
    private Position[] tiles= new Position[32];
    private Sullogi deal=new Sullogi("DealCard");
    private Sullogi mail=new Sullogi("MailCard");


    /**
     * Constructor
     * Creates a new Array of positions
     * @param tiles an array
     */
    public  Board(Position[] tiles, Sullogi deal, Sullogi mail){
        this.tiles=tiles;
        this.deal=deal;
        this.mail=mail;
    }

    /**
     * method that initializes the positions
     */
    public void initPositions(){}

}
