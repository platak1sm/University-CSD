package Model;


/**
 * Card abstract class describes the characteristics of a card
 * and provides modification methods.
 *
 * @author Manos Platakis
 */
public abstract class Card {
    private String image;

    public abstract void action();

    /**
     * Constructor.
     *
     * <b>Postcondition</b>Creates a new Card with the new image.
     *
     * @param image the image of the card
     */
    public Card(String image) {
        this.image = image;
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

}

/**
 * MailCard class describes the characteristics of a mail card
 * extends Class Card
 * and provides modification methods.
 *
 * @author Manos Platakis
 */
class MailCard extends Card {
    private MailCardsType type;

    /**
     * Constructor.
     *
     * <b>Postcondition</b>Creates a new Card with the new image.
     *
     * @param image the image of the card
     * @param type  the type of mail card
     */
    public MailCard(String image, MailCardsType type) {
        super(image);
        this.type = type;
    }

    @Override
    public void action() {

    }
    //and i will put some if statements to determine what is going to do
}

/**
 * DealCard class describes the characteristics of a deal card
 * extends Class Card
 * and provides modification methods.
 *
 * @author Manos Platakis
 */
class DealCard extends Card {
    private int buyprice;
    private int sellprice;

    /**
     * Constructor.
     *
     * <b>Postcondition</b>Creates a new Card with the new image.
     *
     * @param image the image of the card
     * @param buyprice the buy price of the card
     * @param sellprice the sell price of the card
     */
    public DealCard(String image, int buyprice, int sellprice) {
        super(image);
        this.buyprice = buyprice;
        this.sellprice = sellprice;
    }

    @Override
    public void action() {

    }
}

/**
 * MailCards enumeration contains the names of the Mail Cards.
 *
 * @author Manos Platakis
 */
enum MailCardsType {
    PayTheNeighbor, TakeMoneyFromTheNeighbor, Charity, PayTheBills, MoveToDeal_Buyer, Advertisement
}


