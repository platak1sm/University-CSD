package Model;

import Controller.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Position abstract class describes the characteristics of a position
 * and provides modification methods.
 *
 * @author plata
 */
public abstract class Position {
    protected int num;
    protected Day day;
    protected String image, type;


    /**
     * Constructor.
     *
     * <b>Postcondition</b>Creates a new Positions with the new number of the day and the new value for the day itself.
     *
     * @param day   the day
     * @param image the image
     * @param num   number of the day in the month
     */
    public Position(int num, Day day, String image, String type) {
        this.num = num;
        this.day = day;
        this.image = image;
        this.type = type;
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

    public int getNum() {
        return num;
    }

    /**
     * <b>transformer(mutative)</b>: It sets the day of a position <br />
     * <b>postcondition</b>:the day is changed to day
     *
     * @param day the new day of the position
     */
    public void setDay(Day day) {
        this.day = day;
    }

    /**
     * Method that defines the action that has got to be performed
     * for the specific position(its overridden in every subclass of positions)(
     *
     * @param P    the player that is in this position
     * @param dnum the dice number of the player that is in this position
     */

    public abstract void performAction(Player P, Player P2, int dnum, String type, Stack<Card> rejectedcards, Stack<DealCard> dc, Stack<MailCard> mc, int cardtobesold, Jackpot jp, ArrayList<Integer> db) throws Exception;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
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
     * @param day        the day
     * @param image      the image
     * @param num        number of the day in the month
     * @param diceNumber the number of the dice
     * @param money      the money
     */
    public DicePosition(int num, Day day, String image, int diceNumber, int money, String type) {
        super(num, day, image, type);
        this.diceNumber = diceNumber;
        this.money = money;
    }

    @Override
    public void performAction(Player P, Player P2, int dnum, String type, Stack<Card> rejectedcards, Stack<DealCard> dc, Stack<MailCard> mc, int cardtobesold, Jackpot jp, ArrayList<Integer> db) throws Exception {
        P.movePosition(diceNumber);
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
    private String type;

    public CardPosition(int num, Day day, String image, String type) {
        super(num, day, image, type);

    }

    @Override
    public void performAction(Player P, Player P2, int i, String type, Stack<Card> rejectedcards, Stack<DealCard> dc, Stack<MailCard> mc, int cardtobesold, Jackpot jp, ArrayList<Integer> db) throws Exception {
        int x = 0;
        switch (type) {
            case "Mail1":
                if (!mc.isEmpty()) {
                    MailCard m = mc.get(i);
                    m.action(P, P2, m.getPrice(), jp, db, 0);
                    rejectedcards.add(mc.get(i));

                }
                break;
            case "Mail2":
                if (!mc.isEmpty()) {
                    MailCard m = new MailCard("image", "", MailCardsType.MoveToDeal_Buyer, "", 0);
                    m = mc.get(i);
                    if (m.getType() == MailCardsType.MoveToDeal_Buyer) {
                        m = mc.get(i + 1);
                        rejectedcards.add(mc.get(i + 1));
                    } else rejectedcards.add(mc.get(i));
                    m.action(P, P2, m.getPrice(), jp, db, 0);

                }
                if (!mc.isEmpty()) {
                    MailCard m = mc.get(i + 1);
                    if (mc.get(i).getType() == MailCardsType.MoveToDeal_Buyer) {
                        m = mc.get(i);
                        rejectedcards.add(mc.get(i));
                    } else rejectedcards.add(mc.get(i + 1));
                    m.action(P, P2, m.getPrice(), jp, db, 1);
                }
                break;
            case "Deal":
                if (!dc.isEmpty()) {
                    DealCard d = dc.get(0);
                    boolean choice = P.getChoice();
                    if (choice) {
                        P.sullogi.DealCardStack.add(dc.get(0));
                        int currmoney = P.getMoney();
                        int currloan = P.getLoan();
                        if (currmoney < d.getBuyprice()) {
                            int l = 0;
                            do {
                                l += 1000;
                                currmoney += 1000;
                            } while (currmoney < d.getBuyprice());

                            P.setLoan(currloan + l);
                        }
                        P.setMoney(currmoney - d.getBuyprice());
                    } else rejectedcards.add(dc.get(0));
                }
                break;
            case "Buyer":
                int currmoney = P.getMoney();
                if (!P.sullogi.DealCardStack.isEmpty()) {
                    P.setMoney(currmoney + P.sullogi.DealCardStack.get(cardtobesold).getSellprice());
                    rejectedcards.add(P.sullogi.getCardD(cardtobesold));
                    P.sullogi.removeCard(cardtobesold, "Deal");
                }
                break;
        }
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
    public PayDayPosition(String image) {
        super(31, Day.Wed, image, "PayDay");
    }

    @Override
    public void setNum(int num) {
        super.setNum(31);
    }

    @Override
    public void setDay(Day day) {
        super.setDay(Day.Wed);
    }

    @Override
    public void performAction(Player p1, Player p2, int dnum, String type, Stack<Card> rejectedcards, Stack<DealCard> dc, Stack<MailCard> mc, int cardtobesold, Jackpot jp, ArrayList<Integer> db) throws Exception {
        int x = 0;
        int currloan = p1.getLoan();
        int currmoney = p1.getMoney();
        p1.setMoney(3500 + currmoney);
        currmoney += 3500;
        if (currmoney - currloan >= p1.getBills()) {
            p1.setMoney(currmoney - p1.getBills() - currloan);
            p1.setLoan(0);
        } else if (currmoney >= p1.getBills()) {
            p1.setMoney(currmoney - p1.getBills());

            currmoney -= p1.getBills();
            int c = 0;
            if (p1.getMoney() < 0.1 * p1.getLoan()) {
                do {
                    currmoney += 1000;
                    c += 1000;
                } while (currmoney < 0.1 * p1.getLoan());
                p1.setMoney((int) (currmoney - 0.1 * p1.getLoan()));
                p1.setLoan((int) (currloan + c - 0.1 * currloan));
            } else {
                p1.setMoney((int) (currmoney - 0.1 * p1.getLoan()));
                p1.setLoan((int) (currloan - 0.1 * currloan));
            }
            if (currmoney >= 1000) {
                do {
                    if (currmoney < 2000) {
                        p1.setLoan(currloan - 1000);
                        p1.setMoney(currmoney - 1000);
                        currmoney -= 1000;
                    }
                } while (currmoney >= 1000);
            }
        } else if (currmoney < p1.getBills()) {
            do {
                x += 1000;
                p1.setLoan(currloan + x);
            } while (currmoney + x < p1.getBills());
            p1.setMoney(currmoney + x - p1.getBills());
        }
        p1.setBills(0);
        p1.movePosition(0);
        x = 0;
        currloan = p2.getLoan();
        currmoney = p2.getMoney();
        p2.setMoney(3500 + currmoney);
        currmoney += 3500;
        if (currmoney - currloan >= p2.getBills()) {
            p2.setMoney(currmoney - p2.getBills());
            p2.setLoan(0);
        } else if (currmoney >= p2.getBills()) {
            p2.setMoney(currmoney - p2.getBills());
        } else if (currmoney < p2.getBills()) {
            do {
                x += 1000;
                p2.setLoan(currloan + 1000);
            } while (currmoney + x < p2.getBills());
            p2.setMoney(currmoney + x - p2.getBills());
        }
        p2.setBills(0);
        p2.movePosition(0);
    }
}

class StartPosition extends Position {

    /**
     * Constructor.
     *
     * <b>Postcondition</b>Creates a new StartPosition with the new number of the day and the new value for the day itself.
     *
     * @param image the image
     */
    public StartPosition(String image) {
        super(0, Day.Start, image, "Start");
    }

    @Override
    public void performAction(Player P, Player P2, int dnum, String type, Stack<Card> rejectedcards, Stack<DealCard> dc, Stack<MailCard> mc, int cardtobesold, Jackpot jp, ArrayList<Integer> db) throws Exception {

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
    private String type;

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
    public OnePlayerDicePosition(int num, Day day, String image, int diceNumber, int money, String type) {
        super(num, day, image, diceNumber, money, type);
    }

    @Override
    public void performAction(Player P, Player P2, int dnum, String type, Stack<Card> rejectedcards, Stack<DealCard> dc, Stack<MailCard> mc, int cardtobesold, Jackpot jp, ArrayList<Integer> db) throws Exception {
        switch (type) {
            case "Sweepstakes":
                P.setDice(1 + (int) (Math.random() * 6));
                int currmoney = P.getMoney();
                P.setMoney(currmoney + 1000 * P.getDice());
                break;
            case "Casino":
                if (dnum % 2 == 1) {
                    if (P.getMoney() >= 500) {
                        jp.refresh_money(500, "+");
                        currmoney = P.getMoney();
                        P.setMoney(currmoney - 500);
                    } else {
                        int currloan = P.getLoan();
                        currmoney = P.getMoney();
                        P.setLoan(currloan + 1000);
                        P.setMoney(currmoney + 500);
                    }
                } else {
                    currmoney = P.getMoney();
                    P.setMoney(currmoney + 500);
                }
                break;
            case "Yard":
                if (!dc.isEmpty()) {
                    P.setDice(1 + (int) (Math.random() * 6));
                    currmoney = P.getMoney();
                    int currloan = P.getLoan();
                    if (currmoney > 100 * P.getDice()) P.setMoney(currmoney - 100 * P.getDice());
                    else {
                        P.setMoney(currmoney + 1000 - 100 * P.getDice());
                        P.setLoan(currloan + 1000);
                    }
                    DealCard dcard = dc.get(0);
                    rejectedcards.add(dcard);
                    P.sullogi.addCard(dcard);
                }
                break;
        }
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

    private String type;

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
    public BothPlayersDicePosition(int num, Day day, String image, int diceNumber, int money, String type) {
        super(num, day, image, diceNumber, money, type);

    }

    @Override
    public void performAction(Player P1, Player P2, int dnum, String type, Stack<Card> rejectedcards, Stack<DealCard> dc, Stack<MailCard> mc, int cardtobesold, Jackpot jp, ArrayList<Integer> db) throws Exception {
        switch (type) {
            case "Lottery":
                int x1, x2;
                int d1, d2;
                do {
                    d1 = 1 + (int) (Math.random() * 6);
                    P1.setDice(d1);
                    if (d1 == P1.getDicelot()) {
                        int currmoney = P1.getMoney();
                        P1.setMoney(currmoney + 1000);
                        break;
                    }

                    d2 = 1 + (int) (Math.random() * 6);
                    P2.setDice(d2);
                    if (d2 == P2.getDicelot()) {
                        int currmoney = P2.getMoney();
                        P2.setMoney(currmoney + 1000);
                        break;
                    }
                } while ((P1.getDice() != P1.getDicelot()) && (P2.getDice() != P2.getDicelot()));

                break;
            case "Radio":
                do {
                    d1 = 1 + (int) (Math.random() * 6);
                    P1.setDice(d1);
                    d2 = 1 + (int) (Math.random() * 6);
                    P2.setDice(d2);
                } while (d1 == d2);
                if (d1 > d2) {
                    int currmoney = P1.getMoney();
                    P1.setMoney(currmoney + 1000);
                } else {
                    int currmoney = P2.getMoney();
                    P2.setMoney(currmoney + 1000);
                }
                break;
        }
    }
}

/**
 * MailCards enumeration contains the names of the Mail Cards.
 *
 * @author plata
 */
enum CardPositionsNames {
    Mail1, Mail2, Deal, Buyer
}

/**
 * OnePlayerDicePositionsNames enumeration contains the names of the One Player Dice Positions.
 *
 * @author plata
 */
enum OnePlayerDicePositionsNames {
    Sweepstakes, Casino, Yard
}

/**
 * BothPlayersDicePositionsNames enumeration contains the names of the Both Players Dice Positions.
 *
 * @author plata
 */
enum BothPlayersDicePositionsNames {
    Lottery, Radio
}

