package Model;

import Controller.*;

/**
 * Position abstract class describes the characteristics of a position
 * and provides modification methods.
 *
 * @author plata
 */
public abstract class Position {
    protected int num;
    protected String day;
    protected Player p = new Player();
    protected Sullogi sulD = new Sullogi("DealCard");
    protected Sullogi sulM = new Sullogi("MailCard");
    protected String image;
    protected Controller c=new Controller();

    /**
     * Constructor.
     *
     * <b>Postcondition</b>Creates a new Positions with the new number of the day and the new value for the day itself.
     * @param day the day
     * @param image the image
     * @param num number of the day in the month
     */
    public Position(int num, String day, String image){
        this.num=num;
        this.day=day;
        this.image=image;
    }

    /**
     * <b>accessor(selector)</b>:Returns the image of the position <br />
     *
     * <p><b>Postcondition:</b> returns the image of the position </p>
     *
     * @return the image of the position
     */
    public String getImage() {
        return image;
    }

    /**
     * <b>transformer(mutative)</b>: It sets the image of a position <br />
     * <b>postcondition</b>:the image of the position is changed to image
     *
     * @param image the new image of the position
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * <b>transformer(mutative)</b>: It sets the number of the position <br />
     * <b>postcondition</b>:the number of the position is changed to num
     *
     * @param num the new number of the position
     */
    public void setNum(int num) {
        this.num = num;
    }

    /**
     * <b>transformer(mutative)</b>: It sets the day of a position <br />
     * <b>postcondition</b>:the day is changed to day
     *
     * @param day the new day of the position
     */
    public void setDay(String day) {
        this.day = day;
    }

    /**
     * Method that defines the action that has got to be performed
     * for the specific position(its overridden in every subclass of positions)(
     *
     * @param P the player that is in this position
     * @param dnum the dice number of the player that is in this position
     */
    public abstract void performAction(Player P, int dnum);
}

/**
 * DicePosition class describes the characteristics of a dice position
 * extends Position Class
 * and overrides performAction method.
 *
 * @author plata
 */
class DicePosition extends Position {//all the other positions except the card positions{
    private int money;
    private int diceNumber;

    /**
     * Constructor.
     *
     * <b>Postcondition</b>Creates a new DicePosition with the new number of the day,
     * the new value for the day itself, a new image, a new diceNumber and new value for money.
     *
     * @param day the day
     * @param image the image
     * @param num number of the day in the month
     * @param diceNumber the number of the dice
     * @param money the money
     */
    public DicePosition(int num, String day, String image, int diceNumber, int money) {
        super(num, day, image);
        this.diceNumber=diceNumber;
        this.money=money;
    }

    @Override
    public void performAction(Player P, int diceNumber) {

    }
}

/**
 * CardPosition class describes the characteristics of a card position
 * extends Position Class
 * provides some methods
 * and overrides performAction method.
 *
 * @author plata
 */
class CardPosition extends Position {  //the positions on the board that require the player to drag a card.
    /**
     * Constructor.
     *
     * <b>Postcondition</b>Creates a new CardPosition with the new number of the day and the new value for the day itself.
     *
     * @param num number of the day in the month
     * @param day the day
     */
    public CardPosition(int num, String day, String image) {
        super(num, day,image);
    }

    /**
     * A method that returns the card on the top of the wanted deck(we will find the wanted deck from the parameter C)
     * @param C a card
     * @return the card on the top of the wanted deck(we will find the wanted deck from the parameter C)
     */
    public Card getCards(Card C) {
        if (C instanceof DealCard) return this.sulD.DealCardStack.peek();
        else if(C instanceof MailCard) return this.sulM.MailCardStack.peek();
        else return C;
    }

    @Override
    public void performAction(Player P, int dnum) {

    }
}


/**
 * PayDayPosition class describes the characteristics of a Pay day position (last position num=31)
 * extends Position Class
 * and overrides performAction, setNum, setDay methods.
 *
 * @author plata
 */
class PayDayPosition extends Position {
    public PayDayPosition(String image){
        super(31,"Wednesday",image);
    }
    @Override
    public void setNum(int num) {
        super.setNum(31);
    }

    @Override
    public void setDay(String day) {
        super.setDay("Wednesday");
    }

    @Override
    public void performAction(Player P, int dnum) {

    }
}

/**
 * OnePlayerDicePosition class describes the characteristics of an one player dice position
 * extends DicePosition Class
 * and overrides performAction method.
 *
 * @author plata
 */
class OnePlayerDicePosition extends DicePosition {
    /**
     * Constructor.
     *
     * <b>Postcondition</b>Creates a new OnePlayerDicePosition with the new number of the day,
     * the new value for the day itself, a new image, a new diceNumber and new value for money.
     *
     * @param num        number of the day in the month
     * @param day        the day
     * @param image      the image
     * @param diceNumber the number of the dice
     * @param money      the money
     */
    public OnePlayerDicePosition(int num, String day, String image, int diceNumber, int money) {
        super(num, day, image, diceNumber, money);
    }

    @Override
    public void performAction(Player P, int dnum) {
    }
}

/**
 * BothPlayerDicePosition class describes the characteristics of both players dice position
 * extends DicePosition Class
 * and overrides performAction method.
 *
 * @author plata
 */
class BothPlayersDicePosition extends DicePosition {
    /**
     * Constructor.
     *
     * <b>Postcondition</b>Creates a new BothPLayersDicePosition with the new number of the day,
     * the new value for the day itself, a new image, a new diceNumber and new value for money.
     *
     * @param num        number of the day in the month
     * @param day        the day
     * @param image      the image
     * @param diceNumber the number of the dice
     * @param money      the money
     */
    public BothPlayersDicePosition(int num, String day, String image, int diceNumber, int money) {
        super(num, day, image, diceNumber, money);
    }

    @Override
    public void performAction(Player P, int dnum) {

    }
}

/**
 * MailCards enumeration contains the names of the Mail Cards.
 *
 * @author plata
 */
enum CardPositionsNames{
    MailPosition, DealPosition, BuyerPosition
}

/**
 * OnePlayerDicePositionsNames enumeration contains the names of the One Player Dice Positions.
 *
 * @author plata
 */
enum OnePlayerDicePositionsNames{
    SweepstakesPosition, FamilyCasinoNightPosition, YardSalePosition
}

/**
 * BothPlayersDicePositionsNames enumeration contains the names of the Both Players Dice Positions.
 *
 * @author plata
 */
enum BothPlayersDicePositionsNames{
    LotteryPosition, RadioContest
}
