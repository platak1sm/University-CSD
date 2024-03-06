package Model;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Stack;

/**
 * Board Class
 * Describes the board
 *
 * @author plata
 */
public class Board {
    public Position[] tilesnotshuffled = new Position[32], tiles= new Position[32];
    private Sullogi deal = new Sullogi("Deal");
    private Sullogi mail = new Sullogi("Mail");
    private Sullogi rejected = new Sullogi("Card");


    /**
     * Constructor
     * Creates a new Array of positions
     *
     * @param deal sullogi
     */
    public Board(Sullogi deal, Sullogi mail) {
        this.deal = deal;
        this.mail = mail;
    }

    /**
     * method that initializes the positions
     */
    public void initPositions(ArrayList<Integer> arr) {
        tiles[0]= new StartPosition("images/start.png");
        tiles[31]= new PayDayPosition("images/pay.png");
        for (int i = 0; i < 8; i++) {
            if(i % 2==0) tilesnotshuffled[i] = new CardPosition(i, Day.valueOf(i), "images/mc1.png","Mail1");
            else tilesnotshuffled[i] = new CardPosition(i+4, Day.valueOf(i + 4), "images/mc2.png", "Mail2");
        }
        for (int i = 8; i < 16; i++) {
            if(i==8 || i==12) tilesnotshuffled[i] = new OnePlayerDicePosition(i,Day.valueOf(i),"images/yard.png",0,0,"Yard");
            else if(i==9 || i==13) tilesnotshuffled[i] = new BothPlayersDicePosition(i+2,Day.valueOf(i+2),"images/radio.png",0,0,"Radio");
            else if(i==10 || i==14) tilesnotshuffled[i] = new OnePlayerDicePosition(i+4,Day.valueOf(i+4),"images/casino.png",0,0, "Casino");
            else tilesnotshuffled[i] = new OnePlayerDicePosition(i+6, Day.valueOf(i+6),"images/sweep.png",0,0, "Sweepstakes");
        }
        for (int i = 16; i < 22; i++) {
            tilesnotshuffled[i] = new CardPosition(i,Day.valueOf(i),"images/buyer.png","Buyer");
        }
        for (int i = 22; i < 25; i++) {
            tilesnotshuffled[i] = new BothPlayersDicePosition(i,Day.valueOf(i),"images/lottery.png",0,0,"Lottery");
        }
        for (int i = 25; i < 30; i++) {
            tilesnotshuffled[i] = new CardPosition(i,Day.valueOf(i),"images/deal.png","Deal");
        }

        for (int i = 1; i < 31; i++) {
            tiles[i] = tilesnotshuffled[arr.get(i-1)];
        }

        for (int i = 0; i < 32; i++) {
            System.out.println(tiles[i].getType());
        }
    }

}

