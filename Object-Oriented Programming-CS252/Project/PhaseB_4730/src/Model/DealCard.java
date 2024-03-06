package Model;

import java.util.ArrayList;

/**
 * DealCard class describes the characteristics of a deal card
 * extends Class Card
 * and provides modification methods.
 *
 * @author Manos Platakis
 */
public class DealCard extends Card {
    private int buyprice;
    private int sellprice;
    String choice1, choice2;
    String type;

    /**
     * Constructor.
     *
     * <b>Postcondition</b>Creates a new Card with the new image.
     *
     * @param image     the image of the card
     * @param buyprice  the buy price of the card
     * @param sellprice the sell price of the card
     * @param message   the message
     */
    public DealCard(String image, String message, int buyprice, int sellprice, String type) {
        super(image, message);
        this.buyprice = buyprice;
        this.sellprice = sellprice;
        this.choice1 = "Agorase";
        this.choice2 = "Agnohse th Symfwnia";
        this.type= type;
    }

    public void setBuyprice(int buyprice) {
        this.buyprice = buyprice;
    }

    public int getBuyprice() {
        return buyprice;
    }

    public void setChoice1(String choice1) {
        this.choice1 = choice1;
    }

    public String getChoice1() {
        return choice1;
    }

    public void setChoice2(String choice2) {
        this.choice2 = choice2;
    }

    public String getChoice2() {
        return choice2;
    }

    public void setSellprice(int sellprice) {
        this.sellprice = sellprice;
    }

    public int getSellprice() {
        return sellprice;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    @Override
    public void action(Player p1, Player p2, int price, Jackpot jp, ArrayList<Integer> db, int z) {


    }
}
