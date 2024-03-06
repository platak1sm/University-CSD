package Model;


import java.util.ArrayList;

/**
 * Card abstract class describes the characteristics of a card
 * and provides modification methods.
 *
 * @author Manos Platakis
 */
public abstract class Card {
    private String image;
    private String message;

    public abstract void action(Player p1, Player p2, int price, Jackpot jp, ArrayList<Integer> db, int z) throws Exception;

    /**
     * Constructor.
     *
     * <b>Postcondition</b>Creates a new Card with the new image.
     *
     * @param image the image of the card
     */
    public Card(String image, String message) {
        this.image = image;
        this.message = message;
    }

    /**
     * <b>accessor(selector)</b>:Returns the image of the card <br />
     *
     * <p><b>Postcondition:</b> returns the image of the card </p>
     *
     * @return the image of the card
     */
    public String getImage() {
        return image;
    }

    /**
     * <b>transformer(mutative)</b>: It sets the image of a card <br />
     * <b>postcondition</b>:the image of the card is changed to image
     *
     * @param image the new image of the card
     */
    public void setImage(String image) {
        this.image = image;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}


